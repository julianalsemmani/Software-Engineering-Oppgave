import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
