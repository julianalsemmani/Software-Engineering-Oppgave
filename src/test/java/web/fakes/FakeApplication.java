package web.fakes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.fakes.FakeStartUpRepository;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.json.JavalinJson;
import org.eclipse.jetty.server.Server;
import web.controller.ProductController;
import web.controller.StartUpController;
import web.controller.StoreController;
import web.controller.UserController;

public class FakeApplication {

    public final FakeStartUpRepository fakeStartUpRepository = new FakeStartUpRepository();

    public void start(int port) {
        Javalin app = Javalin.create().start(port);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.)
        JavalinJackson.configure(objectMapper);

        // Controllers
        ProductController productController = new ProductController(fakeStartUpRepository);
        StartUpController startUpController = new StartUpController(fakeStartUpRepository);
        StoreController storeController = new StoreController(fakeStartUpRepository);
        UserController userController = new UserController(fakeStartUpRepository);

        app.post("/api/store-user", userController::handleCreateStoreUser);

    }
}
