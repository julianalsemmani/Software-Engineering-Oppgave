package persist;
import core.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Repository_persists_data {
//
//    @Test
//    public void persist() {
//        String jsonPath = "src/test/resources/persist/test.db.json";
//        try {
//            File file = new File(jsonPath);
//            file.delete();
//        } catch (Exception e) { }
//
//        JSONRepository repository1 = new JSONRepository(jsonPath);
//        User user1 = repository1.addUser("user1", "aaa", "ba", "ab", "va", "email");
//        Store store1 = repository1.addStore("store1", user1, "address", 0);
//        Product product1 = repository1.createProduct(store1.id, "product1", "pic");
//
//        JSONRepository repository2 = new JSONRepository(jsonPath);
//
//        assertEquals(user1.username, repository2.getUserById(user1.id).username);
//        assertEquals(user1.id, repository2.getStoreById(store1.id).owner.id);
////        assertEquals(product1.store.id, repository2.getProductById(product1.id).store.id);
//    }
}
