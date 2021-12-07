package core.repository;

import core.model.*;
import core.repository.fakes.FakeRepository;
import core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class Auction_and_sale {

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
    public void employees_can_set_up_an_auction_for_a_product() {
        Product product = service.createProduct(store1.id, "product_for_auction", "url");
        Auction auction = service.registerAuction(store1.id, product.id, 100, 10, 2000,
                Instant.now(), Instant.now().plusSeconds(3600*24*7));

        assertSame(auction, product.saleMethod);
        assertTrue(store1.getAllProducts().contains(product));
    }

    @Test
    public void employees_can_set_up_a_sale_for_a_product() {
        Product product = service.createProduct(store1.id, "product_for_sale", "url");
        Sale sale = service.registerSale(store1.id, product.id, 100);

        assertSame(sale, product.saleMethod);
        assertTrue(store1.getAllProducts().contains(product));
    }

    @Test
    public void user_can_bid_on_product() {
        Product product = service.createProduct(store1.id, "product_for_auction", "url");
        Auction auction = service.registerAuction(store1.id, product.id, 100, 10, 1000, Instant.now(), Instant.now());

        assertSame(auction, product.saleMethod);
        assertTrue(service.doBid(user1.id, store1.id, product.id, 200));
    }

    @Test
    public void user_can_bid_over_current_bid() {
        Product product = service.createProduct(store1.id, "product_for_auction", "url");
        Auction auction = service.registerAuction(store1.id, product.id, 100, 10, 1000, Instant.now(), Instant.now());

        assertSame(auction, product.saleMethod);
        assertTrue(service.doBid(user1.id, store1.id, product.id, 200));
        assertTrue(service.doBid(user2.id, store1.id, product.id, 210));
    }

    @Test
    public void user_can_not_bid_under_current_bid() {
        Product product = service.createProduct(store1.id, "product_for_auction", "url");
        Auction auction = service.registerAuction(store1.id, product.id, 100, 10, 1000, Instant.now(), Instant.now());

        assertSame(auction, product.saleMethod);
        assertTrue(service.doBid(user1.id, store1.id, product.id, 200));
        assertFalse(service.doBid(user2.id, store1.id, product.id, 100));
    }
}
