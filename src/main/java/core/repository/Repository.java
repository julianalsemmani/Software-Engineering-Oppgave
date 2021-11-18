package core.repository;

import core.model.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Repository {

    List<Store> getAllStores();

    Set<User> getAllEmployees(UUID storeId);

    List<Product> getAllProducts(UUID storeId);

    Store getStoreById(UUID storeId);

    Store createStore(String storeName, User owner, String address, int phoneNumber);

    Store updateStore(UUID storeId, String storeName, User owner, String address, int phoneNumber);

    Store deleteStore(UUID id);

    User getUserById(UUID userId);

    User createUser(String username, String password, String firstName, String lastName, String address, String email);

    User updateUser(UUID userId, String username, String password, String firstName, String lastName, String address, String email);

    User deleteUser(UUID userId);

    Product getProductById(UUID productId);

    Product createProduct(UUID storeId, String name, String productPicture);

    Product updateProduct(UUID productId, String name, String productPicture);

    Product deleteProduct(UUID productId);

    void registerEmployee(UUID storeId, UUID newEmployeeId);

    StartUp getStartUpById(UUID id);

    StartUp createStartUp(String startUpName, int phoneNumber, String address);

    StartUp updateStartUp(UUID id, String startUpName, int phoneNumber, String address);

    StartUp deleteStartUp(UUID id);
}
