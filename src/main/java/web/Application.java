package web;

import core.repository.Repository;
import core.service.Service;
import persist.JSONRepository;


public class Application {
    public static void main(String[] args) {
        Repository repository = new JSONRepository("src/main/resources/persist/test.db.json");
        Service service = new Service(repository);
        WebServer webServer = new WebServer(service);

        webServer.start(7000);
    }
}
