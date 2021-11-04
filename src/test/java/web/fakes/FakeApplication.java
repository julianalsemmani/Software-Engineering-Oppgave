package web.fakes;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.fakes.FakeStartUpRepository;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import web.controller.ProductController;
import web.controller.StartUpController;
import web.controller.StoreController;
import web.controller.UserController;
import org.unbrokendome.jackson.beanvalidation.BeanValidationModule;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * Used to start Javalin application with controllers for testing
 *
 */
public class FakeApplication {

    public final FakeStartUpRepository fakeStartUpRepository = new FakeStartUpRepository();

    public void start(int port) {
        Javalin app = Javalin.create().start(port);

        ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory));

        JavalinJackson.configure(objectMapper);

        // Controllers
        ProductController productController = new ProductController(fakeStartUpRepository);
        StartUpController startUpController = new StartUpController(fakeStartUpRepository);
        StoreController storeController = new StoreController(fakeStartUpRepository);
        UserController userController = new UserController(fakeStartUpRepository);

        app.post("/api/users", userController::onPostUser);

    }
}
