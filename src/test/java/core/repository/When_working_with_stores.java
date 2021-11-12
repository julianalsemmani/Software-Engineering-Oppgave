package core.repository;

import core.model.Product;
import core.model.Store;
import core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.HibernateRepository;
import web.Application;


import static org.junit.jupiter.api.Assertions.*;

public class When_working_with_stores {

    public Repository repository;
    public User user1;
    public Store store1;

    @BeforeEach
    public void setUp() {
        repository = new HibernateRepository(Application.setupHibernateSessionFactory());

        user1 = repository.createUser("store_owner", "password", "store", "owner", "address", "email@email.com");
        store1 = repository.createStore("test_store", user1, "store_address", 12345678);
    }

    @Test
    public void users_can_register_a_store_and_become_the_owner() {
        Store newStore = repository.createStore("new_store", user1, "store_address", 12345678);

        assertNotNull(newStore);
        assertEquals(user1.id, repository.getStoreById(newStore.id).owner.id);
    }

    @Test
    public void employees_can_register_a_product_for_their_store() {
        Product product = repository.createProduct(store1.id, "test_product", "url");

        assertNotNull(repository.getProductById(product.id));
    }
}
