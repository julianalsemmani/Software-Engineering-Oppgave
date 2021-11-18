package web;

import core.repository.Repository;
import persist.JSONRepository;

import java.util.UUID;


public class Application {
    public static void main(String[] args) {
        Repository repository = new JSONRepository("src/main/resources/persist/test.db.json");
        repository.createProduct(UUID.fromString("3f44840e-b835-47e1-ae05-687f0de90212"), "WOOHOO", "pspd");
        WebServer webServer = new WebServer(repository);

        webServer.start(7000);
    }
}
