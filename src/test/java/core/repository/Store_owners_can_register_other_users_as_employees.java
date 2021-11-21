//package core.repository;
//
//import core.model.Store;
//import core.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import persist.JSONRepository;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class Store_owners_can_register_other_users_as_employees {
//
//    public Repository repository;
//    public Store store;
//    public User owner;
//    public User testUser;
//
//    @BeforeEach
//    public void setUp() {
//        repository = new JSONRepository("resources/persist/test.db.json");
//
//        owner = repository.addUser("test_owner", "test_password", "first_name", "last_name", "address", "email");
//        store = repository.addStore("test_store", owner, "test_address", 12345678);
//        testUser = repository.addUser("test_owner", "test_password", "first_name", "last_name", "address", "email");
//    }
//
//    @Test
//    public void store_owners_can_register_other_users_as_employees() {
//        repository.registerEmployee(store.id, testUser.id);
//
//        assertTrue(repository.getStoreById(store.id).employees.stream()
//                .anyMatch(user -> user.id == testUser.id));
//    }
//}
