package web;

import core.repository.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persist.HibernateRepository;


public class Application {
    public static void main(String[] args) {
        SessionFactory sessionFactory = setupHibernateSessionFactory();

        Repository repository = new HibernateRepository(sessionFactory);//new JSONRepository("example_users.json");

        WebServer webServer = new WebServer(repository);

        webServer.start(7000);
    }

    public static SessionFactory setupHibernateSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("persist/hibernate/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            System.err.println(e.getMessage());
            return null;
        }
    }
}
