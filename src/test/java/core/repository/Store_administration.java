package core.repository;

import core.model.*;
import core.repository.fakes.FakeRepository;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    public void employees_can_register_a_product_for_their_store() {
        Product product = service.createProduct(store1.id, "test_product", "url");

        assertNotNull(service.getProductById(store1.id, product.id));
        assertTrue(store1.getAllProducts().contains(product));
    }

    @Test
    public void employees_can_update_existing_products() {
        Product product = service.createProduct(store1.id, "test_product", "url");

        service.updateProduct(store1.id, product.id, "new name", "new picture");

        assertEquals("new name", product.name);
        assertEquals("new picture", product.productPicture);
    }

    @Test
    public void employees_can_remove_products() {
        Product product = service.createProduct(store1.id, "product_for_auction", "url");

        assertNotNull(store1.getProduct(product.id));

        service.deleteProduct(store1.id, product.id);

        assertNull(store1.getProduct(product.id));
    }

    @Test
    public void employees_can_update_store_information() {
        service.updateStore(store1.id, "new name", user1, "new address", 21);

        assertEquals("new name", store1.storeName);
        assertSame(user1, store1.owner);
        assertEquals("new address", store1.address);
        assertEquals(21, store1.phoneNumber);
    }


}
