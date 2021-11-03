package web;

import web.controller.ProductController;
import web.controller.StartUpController;
import web.controller.StoreController;
import web.controller.UserController;
import persist.StartUpJSONRepository;
import core.repository.StartUpRepository;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        app.config.enableWebjars();

        app.get("/", ctx -> ctx.result("Hello World"));

        // JSON Repository
        StartUpRepository startUpRepository = new StartUpJSONRepository("example_users.json");

        // Controllers
        ProductController productController = new ProductController(startUpRepository);
        StartUpController startUpController = new StartUpController(startUpRepository);
        StoreController storeController = new StoreController(startUpRepository);
        UserController userController = new UserController(startUpRepository);

        app.post("/api/store-user", userController::handleCreateStoreUser);

    }
}
