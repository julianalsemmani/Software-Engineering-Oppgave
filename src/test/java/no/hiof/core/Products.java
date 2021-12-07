package no.hiof.core;

import no.hiof.core.fakes.FakeRepository;
import no.hiof.core.model.Product;
import no.hiof.core.model.Store;
import no.hiof.core.model.User;
import no.hiof.core.service.Service;
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
        //FK032

        Product product = service.createProduct(store1.id, "test_product", "url");

        assertNotNull(service.getProductById(store1.id, product.id));
        assertTrue(store1.getAllProducts().contains(product));
    }

    @Test
    public void employees_can_update_existing_products() {
        //FK033

        Product product = service.createProduct(store1.id, "test_product", "url");

        service.updateProduct(store1.id, product.id, "new name", "new picture");

        assertEquals("new name", product.name);
        assertEquals("new picture", product.productPicture);
    }

    @Test
    public void employees_can_remove_products() {
        //FK034

        Product product = service.createProduct(store1.id, "product_for_auction", "url");

        assertNotNull(store1.getProduct(product.id));

        service.deleteProduct(store1.id, product.id);

        assertNull(store1.getProduct(product.id));
    }

    @Test
    public void user_can_see_all_products() {
        //FK030

        Product product = service.createProduct(store1.id, "product_for_auction", "url");
        Product product2 = service.createProduct(store1.id, "product_for_auction", "url");
        Product product3 = service.createProduct(store1.id, "product_for_auction", "url");

        assertNotNull(store1.getAllProducts());
        assertTrue(store1.getAllProducts().contains(product));
        assertTrue(store1.getAllProducts().contains(product2));
        assertTrue(store1.getAllProducts().contains(product3));
    }
}
