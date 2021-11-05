package core;

import core.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Retrieve_A_Store_Product {

    Product product = new Product(1111, "product", 111, "picture", 0);
    User storeUser = new User(0, "store1owner", "abc", "first", "last", "address", "email");
    Store store = new Store(0, "store1", storeUser, List.of(storeUser), "store_address", 12345678, List.of(product));
    StartUp startUp = new StartUp("a", "a", 12345678, List.of(store));


    @Test
    public void Retrieving_A_Product() {

        assertEquals(product, store.getProduct(1111));
    }
}