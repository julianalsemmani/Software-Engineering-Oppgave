package core.repository;

import core.model.*;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.JSONRepository;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class When_working_with_stores {

    public Service service;
    public Repository repository;
    public UUID user1Id = UUID.fromString("0b665927-79c1-4d31-a6b6-23c2d85c86b8");
    public UUID store1Id = UUID.fromString("c840b1c0-82bb-4f55-85d3-580ba0f11290");

    @BeforeEach
    public void setUp() {
        repository = new JSONRepository("src/test/resources/persist/stores_test.db.json");
        service = new Service(repository);

//        user1Id = service.createUser("store_owner", "password", "store", "owner", "address", "email@email.com").getId();
//        store1Id = service.createStore("test_store", repository.getUserById(user1Id), "store_address", 12345678).getId();
    }

    @Test
    public void users_can_register_a_store_and_become_the_owner() {
        Store newStore = service.createStore("new_store", repository.getUserById(user1Id), "store_address", 12345678);

        assertNotNull(newStore);
        assertEquals(user1Id, repository.getStoreById(newStore.id).owner.id);
    }

    @Test
    public void employees_can_register_a_product_for_their_store() {
        Product product = service.createProduct(store1Id, "test_product", "url");

        assertNotNull(service.getProductById(store1Id, product.id));
        assertTrue(service.repository.getStoreById(store1Id).getAllProducts().contains(product));
    }

    @Test
    public void employees_can_set_up_an_auction_for_a_product() {
        Product product = service.createProduct(store1Id, "product_for_auction", "url");
        Auction auction = service.registerAuction(store1Id, product.id, 100, 10, 2000,
                Instant.now(), Instant.now().plusSeconds(3600*24*7));

        assertSame(auction, product.saleMethod);
        assertTrue(service.repository.getStoreById(store1Id).getAllProducts().contains(product));
    }

    @Test
    public void employees_can_set_up_a_sale_for_a_product() {
        Product product = service.createProduct(store1Id, "product_for_sale", "url");
        Sale sale = service.registerSale(store1Id, product.id, 100);

        assertSame(sale, product.saleMethod);
        assertTrue(service.repository.getStoreById(store1Id).getAllProducts().contains(product));
    }
}
