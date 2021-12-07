package core;

import core.fakes.FakeRepository;
import core.model.Product;
import core.model.Store;
import core.model.User;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Products {
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
}
