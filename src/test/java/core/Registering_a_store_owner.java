package core;

import core.model.StartUp;
import core.model.Store;
import core.model.StoreUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class Registering_a_store_owner {
    @Test
    public void adding_a_store_user() {
        //TODO(edward): This is just an example test, it has no meaning yet

        StoreUser storeUser = new StoreUser("first", "last", "adress", "email", "store1owner", "abc");
        Store store = new Store("store1", "store_adress", 12345678, 0, storeUser, new ArrayList<>(), new ArrayList<>());
        StartUp startUp = new StartUp("a", "a", 12345678, new ArrayList<>(List.of(store)));

        assertSame(storeUser, startUp.stores.get(0).Owner);
    }
}
