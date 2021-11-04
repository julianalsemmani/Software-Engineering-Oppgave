package core;

import core.model.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Retrieve_A_Store_Product {

    Product product = new Product(1111, "product", 111, "picture", 0);
    User storeUser = new User(0, "store1owner", "abc", "first", "last", "address", "email");
    Store store = new Store("store1", "store_address", 12345678, 0, storeUser, new ArrayList<>(List.of(storeUser)), new ArrayList<>(List.of(product)));
    StartUp startUp = new StartUp("a", "a", 12345678, new ArrayList<>(List.of(store)));


    @Test
    public void Retrieving_A_Product() {

        assertEquals(product, store.getProduct(1111));
    }
}