package core.repository;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.repository.fakes.FakeStartUpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Stores_can_setup_auction_for_a_product {

    StartUpRepository startUpRepository;
    Store store;
    User storeOwner;
    Product productToAuction;

    @BeforeEach
    public void setUp() {
        startUpRepository = new FakeStartUpRepository();
        storeOwner = startUpRepository.createUser("store_owner", "", "", "", "", "");
        store = startUpRepository.createStore("test_store", storeOwner, "", 0);
        productToAuction = startUpRepository.createProduct(store.id, "test_product", "");
    }

    @Test
    public void store_user_can_setup_auction_for_a_product() {

    }
}
