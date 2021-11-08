package web;

import core.repository.StartUpRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persist.HibernateStartUpRepository;


public class Application {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:src/main/resources/persist/sqlite/test.db";

//        try {
//            this.connection = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }

        SessionFactory sessionFactory = setupHibernate();

        StartUpRepository startUpRepository = new HibernateStartUpRepository(sessionFactory);//new JSONStartUpRepository("example_users.json");
        startUpRepository.createUser("edd", "lol", "edward", "langstrand", "rådyrfaret 24", "edd@edd.com");

        WebServer webServer = new WebServer(startUpRepository);

        webServer.start(7000);
    }

    private static SessionFactory setupHibernate() {
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
