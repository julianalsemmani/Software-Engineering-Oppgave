package core.repository.fakes;

import core.model.Product;
import core.model.StartUp;
import core.model.Store;
import core.model.User;
import core.repository.Repository;

import java.util.*;

public class FakeRepository implements Repository {
    private final Map<UUID, Store> idStoreMap = new HashMap<>();
    private final Map<UUID, User> idUserMap = new HashMap<>();
    private final Map<UUID, Product> idProductMap = new HashMap<>();

    public void dumpFakeData() {
        idUserMap.clear();
        idStoreMap.clear();
        idProductMap.clear();
    }

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(idStoreMap.values());
    }

    @Override
    public Set<User> getAllEmployees(UUID storeId) {
        return idStoreMap.get(storeId).employees;
    }

    @Override
    public List<Product> getAllProducts(UUID storeId) {
        return idStoreMap.get(storeId).getAllProducts();
    }

    @Override
    public Store getStoreById(UUID storeId) {
        return idStoreMap.get(storeId);
    }

    @Override
    public Product getProductById(UUID productId) {
        return idProductMap.get(productId);
    }

    @Override
    public Store createStore(String storeName, User owner, String address, int phoneNumber) {
        Store newStore = new Store(UUID.randomUUID(), storeName, owner, new HashSet<>(), address, phoneNumber, new HashSet<>());
        idStoreMap.put(newStore.id, newStore);
        return newStore;
    }

    @Override
    public Store updateStore(UUID storeId, String storeName, User owner, String address, int phoneNumber) {
        return null;
    }

    @Override
    public Store deleteStore(UUID id) {
        return null;
    }

    @Override
    public User getUserById(UUID userId) {
        return idUserMap.get(userId);
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
        User newUser = new User(UUID.randomUUID(), username, password, firstName, lastName, address, email, 0, false);
        idUserMap.put(newUser.id, newUser);

        return newUser;
    }

    @Override
    public User updateUser(UUID userId, String username, String password, String firstName, String lastName, String address, String email) {
        User user = this.idUserMap.get(userId);
        if(user != null) {
            if(username != null) user.username = username;
            if(password != null) user.password = password;
            if(firstName != null) user.firstName = firstName;
            if(lastName != null) user.lastName = lastName;
            if(address != null) user.address = address;
            if(email != null) user.email = email;
        }

        return user;
    }

    @Override
    public User deleteUser(UUID userId) {
        User user = idUserMap.remove(userId);
        if(user != null) {
            for (Store store : getAllStores()) {
                store.employees.removeIf(theEmployee -> theEmployee.id == userId);
            }
        }
        return user;
    }

    @Override
    public Product createProduct(UUID storeId, String name, String productPicture) {
        Store store = idStoreMap.get(storeId);
        if(store != null) {
            Product newProduct = new Product(UUID.randomUUID(), store, name, productPicture);
            store.addProduct(newProduct);

            return newProduct;
        }

        return null;
    }

    @Override
    public Product updateProduct(UUID productId, String name, String productPicture) {
        Product product = idProductMap.get(productId);
        if(product != null) {
            if(name != null) product.name = name;
            if(productPicture != null) product.productPicture = productPicture;

            return product;
        }

        return null;
    }

    @Override
    public Product deleteProduct(UUID productId) {
        Product product = idProductMap.get(productId);
        product.store.products.remove(product);
        idProductMap.remove(productId);
        return product;
    }

    @Override
    public void registerEmployee(UUID storeId, UUID newEmployeeId) {
        Store store = idStoreMap.get(storeId);
        User newEmployee = idUserMap.get(newEmployeeId);
        if(store != null && newEmployee != null) {
            store.employees.add(newEmployee);
        }
    }

    @Override
    public StartUp getStartUpById(UUID id) {
        return null;
    }

    @Override
    public StartUp createStartUp(String startUpName, int phoneNumber, String address) {
        return null;
    }

    @Override
    public StartUp updateStartUp(UUID id, String startUpName, int phoneNumber, String address) {
        return null;
    }

    @Override
    public StartUp deleteStartUp(UUID id) {
        return null;
    }
}
