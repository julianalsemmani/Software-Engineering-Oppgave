package core.fakes;

import core.model.Product;
import core.model.Store;
import core.model.StoreUser;
import core.repository.StartUpRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeStartUpRepository implements StartUpRepository {
    List<StoreUser> storeUserList = new ArrayList<>();
    List<Store> storeList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

//    @Override
//    public void createStoreUser(StoreUserDTO dto) {
//        storeUserList.add(new StoreUser(dto.firstName, dto.lastName, dto.address, dto.email, dto.userName, dto.password));
//    }

    public void dumpFakeData() {
        storeUserList.clear();
    }

    @Override
    public List<Store> getAllStores() {
        return storeList;
    }

    @Override
    public List<StoreUser> getAllEmployees(int storeId) {
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
    public StoreUser getAnEmployee(int storeId, String userName) {
        return null;
    }

    @Override
    public Product getAProduct(int storeId, int productId) {
        return null;
    }

    @Override
    public void createStoreUser(int storeId, StoreUser newEmployee) {

    }

    @Override
    public void updateStoreUser(int storeId, String oldUsername, StoreUser updatedEmployee) {

    }

    @Override
    public void deleteStoreUser(int storeId, String userName) {

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
