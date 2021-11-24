package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.repository.Repository;
import core.service.Service;
import io.javalin.Javalin;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.rendering.vue.VueComponent;
import web.controller.ProductController;
import web.controller.StartUpController;
import web.controller.StoreController;
import web.controller.UserController;
import org.unbrokendome.jackson.beanvalidation.BeanValidationModule;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.UUID;

/**
 * Used to start Javalin application with controllers using given repository
 */
public class WebServer {

    private final Service service;

    public WebServer(Service service) {
        this.service = service;
    }

    public void start(int port) {
        Javalin app = Javalin.create().start(port);

        app.config.enableWebjars();

        ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory))
                .registerModule(new JavaTimeModule()); // This allows serialization of Instant class

        JavalinJackson.configure(objectMapper);

        JavalinValidation.register(UUID.class, UUID::fromString);

        // Controllers
        ProductController productController = new ProductController(service);
        StartUpController startUpController = new StartUpController(service);
        StoreController storeController = new StoreController(service);
        UserController userController = new UserController(service);

        app.get("/login/:user-id", userController::onLogin);

        // API requests for user methods
        app.get("/api/users", userController::onGetAllUsers);
        app.post("/api/users", userController::onPostUser);
        app.get("/api/users/:user-id", userController::onGetUser);
        app.put("/api/users/:user-id", userController::onPutUser);
        app.delete("/api/users/:user-id", userController::onDeleteUser);


        // API requests for store methods
        app.get("/api/stores", storeController::onGetAllStores);
        app.post("/api/stores", storeController::onPostStore);
        app.get("/api/stores/:store-id", storeController::onGetStore);
        app.put("/api/stores/:store-id", storeController::onPutStore);
        app.delete("/api/stores/:store-id", storeController::onDeleteStore);

        // API requests for startUp methods
        app.get("/api/startUp/:startUp-id", startUpController::onGetStartUp);
        app.put("/api/startUp/:startUp-id", startUpController::onPutStartUp);

        // API requests for product methods
        app.post("/api/stores/:store-id/products", productController::onPostProduct);
        app.get("/api/stores/:store-id/products/:product-id", productController::onGetProduct);
        app.put("/api/stores/:store-id/products/:product-id", productController::onPutProduct);
        app.delete("/api/stores/:store-id/products/:product-id", productController::onDeleteProduct);

        app.get("/stores/:store-id", new VueComponent("store-frontpage"));
        app.get("/register-user", new VueComponent("register-user"));
        app.get("/register-store", new VueComponent("register-store"));
        app.get("/", new VueComponent("prototype-hub"));

    }
}
