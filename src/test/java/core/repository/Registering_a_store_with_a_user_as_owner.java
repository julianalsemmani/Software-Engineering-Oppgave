package core.repository;

import core.repository.fakes.FakeStartUpRepository;
import core.model.Store;
import core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class Registering_a_store_with_a_user_as_owner {

    public StartUpRepository startUpRepository;
    public User storeOwner;

    @BeforeEach
    public void setUp() {
        startUpRepository = new FakeStartUpRepository();
        storeOwner = startUpRepository.createUser("store_owner", "password", "store", "owner", "address", "email@email.com");
    }

    @Test
    public void registering_a_store() {
        Store newStore = startUpRepository.createStore("test_store", storeOwner, "store_address", 12345678);

        assertNotNull(newStore);
        assertSame(storeOwner, newStore.owner);
    }
}
