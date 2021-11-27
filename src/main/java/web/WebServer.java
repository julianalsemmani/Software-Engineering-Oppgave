package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.model.User;
import core.service.Service;
import io.javalin.Javalin;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.rendering.vue.JavalinVue;
import io.javalin.plugin.rendering.vue.VueComponent;
import web.controller.*;
import org.unbrokendome.jackson.beanvalidation.BeanValidationModule;
import web.dtos.UserResponseBody;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.nio.file.Path;
import java.util.Map;
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
        app.config.addStaticFiles("/web/public", Location.CLASSPATH);

        ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure().buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory))
                .registerModule(new JavaTimeModule()); // This allows serialization of Instant class

        JavalinJackson.configure(objectMapper);

        JavalinVue.rootDirectory("/web/vue", Location.CLASSPATH);
        JavalinVue.stateFunction = ctx -> {
            User me = ControllerUtils.getLoggedInUser(ctx, service.repository);
            if(me != null) {
                return Map.of("me", new UserResponseBody(me));
            }
            return Map.of();
        };

        JavalinValidation.register(UUID.class, UUID::fromString);

        // Controllers
        ProductController productController = new ProductController(service);
        StartUpController startUpController = new StartUpController(service);
        StoreController storeController = new StoreController(service);
        UserController userController = new UserController(service);

        app.get("/login/:user-id", userController::onLogin);

        // API requests for user methods
        app.get("/api/me", userController::onGetMe);
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
        app.post("/api/stores/:store-id/products/:product-id/bid", productController::onPostBid);

        app.get("/stores/:store-id", new VueComponent("store-home"));
        app.get("/register-user", new VueComponent("register-user"));
        app.get("/register-store", new VueComponent("register-store"));
        app.get("/", new VueComponent("prototype-hub"));
        app.get("/stores/:store-id/products", new VueComponent("store-products"));
    }
}
