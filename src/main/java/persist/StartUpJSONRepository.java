package persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.*;
import core.repository.StartUpRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StartUpJSONRepository implements StartUpRepository, Runnable {
    private HashMap<Integer, Store> storesHashMap;
    private File storeDataFile;

    public StartUpJSONRepository(String fileName) {
        storeDataFile = new File(fileName);
        readFromJSONFile();
    }

    public void readFromJSONFile() {
        ObjectMapper readMapper = new ObjectMapper();
        storesHashMap = new HashMap<>();

        try {
            Store[] storesArray = readMapper.readValue(storeDataFile, Store[].class);

            for (Store aStore : storesArray) {
                storesHashMap.put(aStore.storeID, aStore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ObjectMapper writeMapper = new ObjectMapper();
            writeMapper.writerWithDefaultPrettyPrinter().writeValue(storeDataFile, storesHashMap.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /************************      STORES
     * @return************************/

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(storesHashMap.values());
    }

    @Override
    public Store getStoreById(int storeId) {
        return storesHashMap.get(storeId);
    }

    /************************ STORE USERS / EMPLOYEES
     * @return************************/
    @Override
    public List<StoreUser> getAllEmployees(int storeId) {
        Store currentStore = getStoreById(storeId);

        if (currentStore != null) {
            return currentStore.employees;
        }
        return null;
    }

    @Override
    public StoreUser getAnEmployee(int storeId, String userName) {
        return getStoreById(storeId).getEmployee(userName);
    }

    @Override
    public void createStoreUser(int storeId, StoreUser newEmployee) {
        storesHashMap.get(storeId).addEmployee(newEmployee);
    }

    @Override
    public void updateStoreUser(int storeId, String oldUserName, StoreUser updatedEmployee) {

        /* ? */
        deleteStoreUser(storeId, oldUserName);
        createStoreUser(storeId, updatedEmployee);

        run();

        /* slik ville jeg egentlig gjort det, dersom vi hadde settere,
           ellers litt usikker på gjøremåte! :) ^


        ArrayList<StoreUser> tempList = storesMap.get(storeId).employees;

        for (int i = 0; i < tempList.size(); i++) {

            if (tempList.get(i).userName.equals(oldUserName)) {
                tempList.set(i, updatedEmployee);
            }
        }
        storesMap.get(storeId).setStoreUsers(tempList);
        run();

        */
    }

    @Override
    public void deleteStoreUser(int storeId, String userName) {
        Store aStore = storesHashMap.get(storeId);
        aStore.employees.removeIf(theEmployee -> theEmployee.userName.equals(userName));

        run();
    }

    /************************    PRODUCTS
     * @return************************/

    @Override
    public List<Product> getAllProducts(int storeId) {
        Store currentStore = getStoreById(storeId);

        if (currentStore != null) {
            return currentStore.products;
        }
        return null;
    }

    @Override
    public Product getAProduct(int storeId, int productId) {

        return getStoreById(storeId).getProduct(productId);
    }

    @Override
    public void createProduct(int storeId, Product newProduct) {
        Store store = storesHashMap.get(storeId);
        store.addProduct(newProduct);

        run();
    }

    @Override
    public void updateProduct(int storeId, int productId, Product newProduct) {
        /* ? */
        deleteProduct(storeId, productId);
        createProduct(storeId, newProduct);

        run();

        /*
        ArrayList<Product> tempList = storesMap.get(storeId).products;

        for (int i = 0; i < tempList.size(); i++) {

            if (tempList.get(i).productID == productId) {
                tempList.set(i, newProduct);
            }
        }
        storesMap.get(storeId).setProducts(tempList);
        run();
        */

    }

    @Override
    public void deleteProduct(int storeId, int productId) {
        Store store = storesHashMap.get(storeId);
        store.products.removeIf(aProduct -> aProduct.productID == productId);

        run();
    }

}
