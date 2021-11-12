package core.repository;

import core.model.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Repository {

    List<Store> getAllStores();

    Set<User> getAllEmployees(UUID storeId);

    List<Product> getAllProducts(UUID storeId);

    Product getProductById(UUID productId);

    Store getStoreById(UUID storeId);

    Store createStore(String storeName, User owner, String address, int phoneNumber);

    User getUserById(UUID userId);

    Store updateStore(String storeName, User userById, String address, int phoneNumber);

    Store deleteStore(int id);

    User createUser(String username, String password, String firstName, String lastName, String address, String email);

    User updateUser(UUID userId, String username, String password, String firstName, String lastName, String address, String email);

    User deleteUser(UUID userId);

    Product createProduct(UUID storeId, String name, String productPicture);

    Product updateProduct(UUID productId, String name, String productPicture);

    Product deleteProduct(UUID productId);

    void registerEmployee(UUID storeId, UUID newEmployeeId);
}
