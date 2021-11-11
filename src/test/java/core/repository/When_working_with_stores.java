package core.repository;

import core.model.Store;
import core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.HibernateRepository;
import web.Application;


import static org.junit.jupiter.api.Assertions.*;

public class When_working_with_stores {

    public Repository repository;
    public User testUser;

    @BeforeEach
    public void setUp() {
        repository = new HibernateRepository(Application.setupHibernateSessionFactory());

        testUser = repository.createUser("store_owner", "password", "store", "owner", "address", "email@email.com");
    }

    @Test
    public void users_can_register_a_store_and_become_the_owner() {
        Store newStore = repository.createStore("test_store", testUser, "store_address", 12345678);

        assertNotNull(newStore);
        assertEquals(testUser.id, repository.getStoreById(newStore.id).owner.id);
    }
}
