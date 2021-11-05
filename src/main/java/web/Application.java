package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.plugin.json.JavalinJackson;
import org.unbrokendome.jackson.beanvalidation.BeanValidationModule;
import web.controller.ProductController;
import web.controller.StartUpController;
import web.controller.StoreController;
import web.controller.UserController;
import persist.StartUpJSONRepository;
import core.repository.StartUpRepository;
import io.javalin.Javalin;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;


public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory));

        JavalinJackson.configure(objectMapper);
        app.config.enableWebjars();

        app.get("/", ctx -> ctx.result("Hello World"));

        // JSON Repository
        StartUpRepository startUpRepository = new StartUpJSONRepository("example_users.json");

        // Controllers
        ProductController productController = new ProductController(startUpRepository);
        StartUpController startUpController = new StartUpController(startUpRepository);
        StoreController storeController = new StoreController(startUpRepository);
        UserController userController = new UserController(startUpRepository);

        app.post("/api/users", userController::onPostUser);
        app.put("/api/users/:user-id", userController::onPutUser);
        app.delete("/api/users/:user-id", userController::onDeleteUser);

    }
}
