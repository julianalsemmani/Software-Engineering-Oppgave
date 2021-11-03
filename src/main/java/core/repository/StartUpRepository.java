package core.repository;

import core.model.*;

import java.util.List;

public interface StartUpRepository {

    List<Store> getAllStores();

    List<StoreUser> getAllEmployees(int storeId);

    List<Product> getAllProducts(int storeId);

    Store getStoreById(int storeId);

    StoreUser getAnEmployee(int storeId, String userName);

    Product getAProduct(int storeId, int productId);

    /*****************************************************/

    void createStoreUser(int storeId, StoreUser newEmployee);

    void updateStoreUser(int storeId, String oldUsername, StoreUser updatedEmployee);

    void deleteStoreUser(int storeId, String userName);

    void createProduct(int storeId, Product newProduct);

    void updateProduct(int storeId, int productId, Product newProduct);

    void deleteProduct(int storeId, int productId);
}
