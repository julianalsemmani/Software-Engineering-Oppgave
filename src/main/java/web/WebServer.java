package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.repository.Repository;
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
 * Used to start Javalin application with controllers using given repository
 */
public class WebServer {

    private final Repository repository;

    public WebServer(Repository repository) {
        this.repository = repository;
    }

    public void start(int port) {
        Javalin app = Javalin.create().start(port);

        ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory));

        JavalinJackson.configure(objectMapper);

        // Controllers
        ProductController productController = new ProductController(repository);
        StartUpController startUpController = new StartUpController(repository);
        StoreController storeController = new StoreController(repository);
        UserController userController = new UserController(repository);

        app.post("/api/users", userController::onPostUser);
        app.get("/api/users/:user-id", userController::onGetUser);
        app.put("/api/users/:user-id", userController::onPutUser);
        app.delete("/api/users/:user-id", userController::onDeleteUser);

    }
}
