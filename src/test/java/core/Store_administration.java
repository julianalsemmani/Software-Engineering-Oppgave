package core;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.fakes.FakeRepository;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Store_administration {

    public Service service;
    public FakeRepository repository;

    public User user1, user2;
    public Store store1;

    @BeforeEach
    public void setUp() {
        repository = new FakeRepository();
        service = new Service(repository);

        user1 = service.createUser("user1", "password", "number", "one", "user1 address", "user1@fake.com");
        user2 = service.createUser("user2", "password", "number", "two", "user2 address", "user2@fake.com");

        store1 = service.createStore("store one", user1, "store1Address", 12);
    }

    @Test
    public void users_can_register_a_store_and_become_the_owner() {
        Store newStore = service.createStore("new_store", repository.getUserById(user1.id), "store_address", 12345678);

        assertNotNull(newStore);
        assertEquals(user1, newStore.owner);
    }

    @Test
    public void employees_can_update_store_information() {
        service.updateStore(store1.id, "new name", user1, "new address", 21);

        assertEquals("new name", store1.storeName);
        assertSame(user1, store1.owner);
        assertEquals("new address", store1.address);
        assertEquals(21, store1.phoneNumber);
    }

    @Test
    public void owner_can_change_owner_of_store() {
        service.updateStore(store1.id, store1.storeName, user2, store1.address, store1.phoneNumber);

        assertSame(user2, store1.owner);
    }

    @Test
    public void owner_can_register_other_users_as_employees() {
        service.registerEmployee(store1.id, user2.id);

        assertTrue(store1.employees.contains(user2));
    }
}
