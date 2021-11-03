package core.repository;

import core.model.*;

import java.util.ArrayList;

public interface StartUpRepository {

    ArrayList<Store> getAllStores();

    ArrayList<StoreUser> getAllEmployees(int storeId);

    ArrayList<Product> getAllProducts(int storeId);

    Store getAStore(int storeId);

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
