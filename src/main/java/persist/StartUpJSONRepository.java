package persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.*;
import core.repository.StartUpRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StartUpJSONRepository implements StartUpRepository {
    //TODO(edward): We should implement multi-threading for writing to file here
    private Map<Integer, Store> idStoreMap;
    private Map<Integer, User> idUserMap;
    private int nextUserId = 0;
    private int nextStoreId = 0;
    private File storeDataFile;

    public StartUpJSONRepository(String fileName) {
        storeDataFile = new File(fileName);
        readFromJSONFile();
    }

    public void readFromJSONFile() {
        ObjectMapper readMapper = new ObjectMapper();
        idStoreMap = new HashMap<>();
        idUserMap = new HashMap<>();

        try {
            Store[] storesArray = readMapper.readValue(storeDataFile, Store[].class);

            for (Store aStore : storesArray) {
                idStoreMap.put(aStore.id, aStore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToJSONFile() {
        try {
            ObjectMapper writeMapper = new ObjectMapper();
            writeMapper.writerWithDefaultPrettyPrinter().writeValue(storeDataFile, idStoreMap.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /************************      STORES
     * @return************************/

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(idStoreMap.values());
    }

    @Override
    public Store getStoreById(int storeId) {
        return idStoreMap.get(storeId);
    }

    /************************ STORE USERS / EMPLOYEES
     * @return************************/
    @Override
    public List<User> getAllEmployees(int storeId) {
        Store currentStore = getStoreById(storeId);

        if (currentStore != null) {
            return currentStore.employees;
        }
        return null;
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
         int id = ++nextUserId;
         User newUser = new User(id, username, password, firstName, lastName, address, email);
         idUserMap.put(id, newUser);

         writeToJSONFile();

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

        writeToJSONFile();

        return user;

        /* ? */
//        deleteUser(oldUserName);
//        createUser(updatedEmployee);

        //idUserMap.put(userId, updatedUser);



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
    public User deleteUser(int id) {
         User user = idUserMap.get(id);
         if(user != null) {
             for (Store store : getAllStores()) {
                 store.employees.removeIf(theEmployee -> theEmployee.id == id);
             }

             writeToJSONFile();
         }
         return user;
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
        Store store = idStoreMap.get(storeId);
        store.addProduct(newProduct);

        writeToJSONFile();
    }

    @Override
    public void updateProduct(int storeId, int productId, Product newProduct) {
        /* ? */
        deleteProduct(storeId, productId);
        createProduct(storeId, newProduct);

        writeToJSONFile();

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
        Store store = idStoreMap.get(storeId);
        store.products.removeIf(aProduct -> aProduct.productID == productId);

        writeToJSONFile();
    }

}
