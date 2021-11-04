package core.repository;

import core.model.*;

import java.util.List;

public interface StartUpRepository {

    List<Store> getAllStores();

    List<User> getAllEmployees(int storeId);

    List<Product> getAllProducts(int storeId);

    Store getStoreById(int storeId);

    Product getAProduct(int storeId, int productId);

    /*****************************************************/

    User createUser(String username, String password, String firstName, String lastName, String address, String email);

    User updateUser(int userId, String username, String password, String firstName, String lastName, String address, String email);

    User deleteUser(int userId);

    void createProduct(int storeId, Product newProduct);

    void updateProduct(int storeId, int productId, Product newProduct);

    void deleteProduct(int storeId, int productId);
}
