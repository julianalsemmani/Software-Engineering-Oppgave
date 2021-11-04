package core.fakes;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.repository.StartUpRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeStartUpRepository implements StartUpRepository {
    List<User> storeUserList = new ArrayList<>();
    List<Store> storeList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    public void dumpFakeData() {
        storeUserList.clear();
    }

    @Override
    public List<Store> getAllStores() {
        return storeList;
    }

    @Override
    public List<User> getAllEmployees(int storeId) {
        return storeUserList;
    }

    @Override
    public List<Product> getAllProducts(int storeId) {
        return productList;
    }

    @Override
    public Store getStoreById(int storeId) {
        return null;
    }

    @Override
    public Product getAProduct(int storeId, int productId) {
        return null;
    }

    @Override
    public void createUser(User newUser) {

    }

    @Override
    public void updateUser(int userId, User updatedUser) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void createProduct(int storeId, Product newProduct) {

    }

    @Override
    public void updateProduct(int storeId, int productId, Product newProduct) {

    }

    @Override
    public void deleteProduct(int storeId, int productId) {

    }
}
