package core.repository;

import core.model.*;

import java.util.List;
import java.util.Set;

public interface Repository {

    List<Store> getAllStores();

    Set<User> getAllEmployees(int storeId);

    List<Product> getAllProducts(int storeId);

    Product getAProduct(int storeId, int productId);

    Store getStoreById(int storeId);

    Store createStore(String storeName, User owner, String address, int phoneNumber);

    User getUserById(int userId);

    User createUser(String username, String password, String firstName, String lastName, String address, String email);

    User updateUser(int userId, String username, String password, String firstName, String lastName, String address, String email);

    User deleteUser(int userId);

    Product createProduct(int storeId, String name, String productPicture);

    Product updateProduct(int storeId, int productId, String name, String productPicture);

    Product deleteProduct(int storeId, int productId);

    void registerEmployee(int storeId, int newEmployeeId);
}
