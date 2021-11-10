package core.repository;

import core.model.Store;
import core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.HibernateStartUpRepository;
import web.Application;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Store_owners_can_register_other_users_as_employees {

    public StartUpRepository startUpRepository;
    public Store store;
    public User owner;
    public User testUser;

//    public Store_owners_can_register_other_users_as_employees(StartUpRepository startUpRepository) {
//        this.startUpRepository = startUpRepository;
//    }

    @BeforeEach
    public void setUp() {
        startUpRepository = new HibernateStartUpRepository(Application.setupHibernateSessionFactory());

        owner = startUpRepository.createUser("test_owner", "test_password", "first_name", "last_name", "address", "email");
        store = startUpRepository.createStore("test_store", owner, "test_address", 12345678);
        testUser = startUpRepository.createUser("test_owner", "test_password", "first_name", "last_name", "address", "email");
    }

    @Test
    public void store_owners_can_register_other_users_as_employees() {
        startUpRepository.registerEmployee(store.id, testUser.id);

        assertTrue(startUpRepository.getStoreById(store.id).employees.stream()
                .anyMatch(user -> user.id == testUser.id));
    }
}
