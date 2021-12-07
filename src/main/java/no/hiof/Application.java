package no.hiof;

import no.hiof.core.repository.Repository;
import no.hiof.core.service.Service;
import no.hiof.persist.JSONRepository;
import no.hiof.web.WebServer;


public class Application {
    public static void main(String[] args) {
        Repository repository = new JSONRepository("src/main/resources/persist/prototype.db.json");
        Service service = new Service(repository);
        WebServer webServer = new WebServer(service);

        webServer.start(7000);
    }
}
