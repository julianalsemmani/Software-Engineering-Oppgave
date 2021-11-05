package core.repository;

import core.model.Store;
import core.model.User;
import core.repository.fakes.FakeStartUpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Store_owners_can_register_other_users_as_employees {

    public StartUpRepository startUpRepository;
    public Store store;
    public User owner;
    public User testUser;

    @BeforeEach
    public void setUp() {
        startUpRepository = new FakeStartUpRepository();
        owner = startUpRepository.createUser("test_owner", "test_password", "first_namme", "last_name", "address", "email");
        store = startUpRepository.createStore("test_store", owner, "test_address", 12345678);
        testUser = startUpRepository.createUser("test_owner", "test_password", "first_namme", "last_name", "address", "email");
    }

    @Test
    public void store_owners_can_register_other_users_as_employees() {
        startUpRepository.registerEmployee(store.id, testUser.id);

        assertTrue(()->store.employees.contains(testUser));
    }
}
