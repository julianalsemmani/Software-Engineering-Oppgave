package core.fakes;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.repository.StartUpRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeStartUpRepository implements StartUpRepository {
    private final Map<Integer, Store> idStoreMap = new HashMap<>();
    private final Map<Integer, User> idUserMap = new HashMap<>();
    private int nextUserId = 0;
    private int nextStoreId = 0;

    public void dumpFakeData() {
        idUserMap.clear();
        idStoreMap.clear();
    }

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(idStoreMap.values());
    }

    @Override
    public List<User> getAllEmployees(int storeId) {
        return idStoreMap.get(storeId).employees;
    }

    @Override
    public List<Product> getAllProducts(int storeId) {
        return idStoreMap.get(storeId).products;
    }

    @Override
    public Store getStoreById(int storeId) {
        return idStoreMap.get(storeId);
    }

    @Override
    public Product getAProduct(int storeId, int productId) {
        return null;
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
        int id = ++nextUserId;
        User newUser = new User(id, username, password, firstName, lastName, address, email);
        idUserMap.put(id, newUser);

        return newUser;
    }

    @Override
    public User updateUser(int userId, String username, String password, String firstName, String lastName, String address, String email) {
        User user = this.idUserMap.get(userId);
        if(user != null) {
            user.username = username;
            user.password = password;
            user.firstName = firstName;
            user.lastName = lastName;
            user.address = address;
            user.email = email;
        }

        return user;
    }

    @Override
    public User deleteUser(int userId) {
        User user = idUserMap.get(userId);
        if(user != null) {
            for (Store store : getAllStores()) {
                store.employees.removeIf(theEmployee -> theEmployee.id == userId);
            }
        }
        return user;
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
