package web;

import core.repository.Repository;
import persist.JSONRepository;


public class Application {
    public static void main(String[] args) {
        Repository repository = new JSONRepository("src/main/resources/persist/test.db.json");

        WebServer webServer = new WebServer(repository);

        webServer.start(7000);
    }
}
