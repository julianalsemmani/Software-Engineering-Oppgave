package persist;
import core.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Store_Products {

    //Data for testing purposes
    User storeUser = new User("first", "last", "address", "email", "store_owner", "abc");
    Product product1 = new Product(1111, "product1", 111, "picture1", 0);
    Product product2 = new Product(2222, "product2", 222, "picture2", 0);
    Store store = new Store("store_name", "store_address", 23456789, 0, storeUser, new ArrayList<>(List.of(storeUser)), new ArrayList<>(List.of(product1, product2)));
    StartUp startUp = new StartUp("a", "a", 12345678, new ArrayList<>(List.of(store)));

    @Test
    public void Retrieving_All_Products()
    {
        assertNotNull(store);
    }

    @Test
    public void Retrieving_A_Product(){
        /*Trenger vi denne en gang til?*/
        fail("not yet implemented");
    }


    @Test
    public void Creating_A_Product()
    {
        /*VELDIG USIKKER PÅ DENNE*/
        assertTrue(startUp.stores.get(0).products.contains(product1));
        assertTrue(startUp.stores.get(0).products.contains(product2));
    }

    @Test
    public void Updating_A_Product()
    {
        fail("not yet implemented");
    }

    @Test
    public void Deleting_A_Product()
    {
        /*VELDIG USIKKER PÅ DENNE*/
        assertEquals(product1.productID, startUp.stores.get(0).getProduct(1111).productID);
        assertEquals(product2.productID, startUp.stores.get(0).getProduct(2222).productID);

    }

}
