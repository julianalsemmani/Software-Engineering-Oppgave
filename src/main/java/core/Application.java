package core;

import core.controller.ProductController;
import core.controller.StartUpController;
import core.controller.StoreController;
import core.controller.UserController;
import core.model.Product;
import core.repository.StartUpJSONRepository;
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
    }
}
