package core.repository;

import core.model.Product;
import core.model.Store;
import core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.JSONRepository;

public class Stores_can_setup_auction_for_a_product {

    Repository repository;
    Store store;
    User storeOwner;
    Product productToAuction;

    @BeforeEach
    public void setUp() {
        repository = new JSONRepository("resources/persist/test.db.json");

        storeOwner = repository.addUser("store_owner", "", "", "", "", "");
        store = repository.addStore("test_store", storeOwner, "", 0);
        productToAuction = repository.createProduct(store.id, "test_product", "");
    }

    @Test
    public void store_user_can_setup_auction_for_a_product() {

    }
}
