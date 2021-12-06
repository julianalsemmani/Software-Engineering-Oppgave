package core.repository;

import core.model.*;
import core.repository.fakes.FakeRepository;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class When_working_with_stores {

    public Service service;
    public FakeRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new FakeRepository();
        service = new Service(repository);
    }

    @Test
    public void users_can_register_a_store_and_become_the_owner() {
        Store newStore = service.createStore("new_store", repository.getUserById(repository.user1.id), "store_address", 12345678);

        assertNotNull(newStore);
        assertEquals(repository.user1.id, repository.getStoreById(newStore.id).owner.id);
    }

    @Test
    public void employees_can_register_a_product_for_their_store() {
        Product product = service.createProduct(repository.store1.id, "test_product", "url");

        assertNotNull(service.getProductById(repository.store1.id, product.id));
        assertTrue(service.repository.getStoreById(repository.store1.id).getAllProducts().contains(product));
    }

    @Test
    public void employees_can_set_up_an_auction_for_a_product() {
        Product product = service.createProduct(repository.store1.id, "product_for_auction", "url");
        Auction auction = service.registerAuction(repository.store1.id, product.id, 100, 10, 2000,
                Instant.now(), Instant.now().plusSeconds(3600*24*7));

        assertSame(auction, product.saleMethod);
        assertTrue(service.repository.getStoreById(repository.store1.id).getAllProducts().contains(product));
    }

    @Test
    public void employees_can_set_up_a_sale_for_a_product() {
        Product product = service.createProduct(repository.store1.id, "product_for_sale", "url");
        Sale sale = service.registerSale(repository.store1.id, product.id, 100);

        assertSame(sale, product.saleMethod);
        assertTrue(service.repository.getStoreById(repository.store1.id).getAllProducts().contains(product));
    }


}
