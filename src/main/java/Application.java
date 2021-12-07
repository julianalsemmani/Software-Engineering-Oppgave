import core.repository.Repository;
import core.service.Service;
import persist.JSONRepository;
import web.WebServer;


public class Application {
    public static void main(String[] args) {
        Repository repository = new JSONRepository("src/main/resources/persist/prototype.db.json");
        Service service = new Service(repository);
        WebServer webServer = new WebServer(service);

        webServer.start(7000);
    }
}
