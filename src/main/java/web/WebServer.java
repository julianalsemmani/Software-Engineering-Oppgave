package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.repository.Repository;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.rendering.vue.VueComponent;
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

        // API requests for user methods
        app.post("/api/users", userController::onPostUser);
        app.get("/api/users/:user-id", userController::onGetUser);
        app.put("/api/users/:user-id", userController::onPutUser);
        app.delete("/api/users/:user-id", userController::onDeleteUser);

        // API requests for store methods
        app.post("/api/stores", storeController::onPostStore);
        app.get("/api/stores/:store-id", storeController::onGetStore);
        app.put("/api/stores/:store-id", storeController::onPutStore);
        app.delete("/api/stores/:store-id", storeController::onDeleteStore);

        // API requests for startUp methods
        app.post("/api/startUp", startUpController::onPostStartUp);
        app.get("/api/startUp/:startUp-id", startUpController::onGetStartUp);
        app.put("/api/startUp/:startUp-id", startUpController::onPutStartUp);
        app.delete("/api/startUp/:startUp-id", startUpController::onDeleteStartUp);

        // API requests for product methods
        app.post("/api/products", productController::onPostProduct);
        app.get("/api/products/:product-id", productController::onGetProduct);
        app.put("/api/products/:product-id", productController::onPutProduct);
        app.delete("/api/products/:product-id", productController::onDeleteProduct);

        app.get("store", new VueComponent("store-frontpage"));

    }
}
