package persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.model.*;
import core.repository.Repository;
import persist.json.JSONStore;
import persist.json.JSONStructure;
import persist.json.JSONUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class JSONRepository implements Repository {
    //TODO(edward): We should implement multi-threading for writing to file here
    private final Map<UUID, Store> idStoreMap = new HashMap<>();
    private final Map<UUID, User> idUserMap = new HashMap<>();

    private final File storeDataFile;

    public JSONRepository(String fileName) {
        storeDataFile = new File(fileName);
        readFromJSONFile();
    }

    public void readFromJSONFile() {
        ObjectReader readMapper = new ObjectMapper().reader();
        try {
            JSONStructure structure = readMapper.readValue(storeDataFile, JSONStructure.class);

            for(JSONUser json : structure.users) {
                idUserMap.put(json.id, json.toUser());
            }
            for(JSONStore json : structure.stores) {
                idStoreMap.put(json.id, json.toStore(idUserMap));
            }
        } catch(FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToJSONFile() {
        try {
            ObjectWriter writeMapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

            JSONStructure structure = new JSONStructure();

            for(User user : idUserMap.values()) {
                structure.users.add(new JSONUser(user));
            }
            for(Store store : idStoreMap.values()) {
                structure.stores.add(new JSONStore(store));
            }

            writeMapper.writeValue(storeDataFile, structure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Store> getAllStores() {
        return new ArrayList<>(idStoreMap.values());
    }

    @Override
    public Store getStoreById(UUID storeId) {
        return idStoreMap.get(storeId);
    }

    @Override
    public void addStore(Store newStore) {
        idStoreMap.put(newStore.id, newStore);
        writeToJSONFile();
    }

    @Override
    public void updateStore(Store newStore) {
        writeToJSONFile();
    }

    @Override
    public void deleteStore(Store store) {
        idStoreMap.remove(store.id);
        writeToJSONFile();
    }

    @Override
    public User getUserById(UUID userId) {
        return idUserMap.get(userId);
    }

    @Override
    public void addUser(User user) {
        idUserMap.put(user.id, user);
        writeToJSONFile();
    }

    @Override
    public void updateUser(User user) {
        writeToJSONFile();
    }

    @Override
    public void deleteUser(User user) {
        //TODO(edward): We need to think more about cleaning up references to users here...
        idUserMap.remove(user.id);

        for (Store store : getAllStores()) {
            store.employees.removeIf(theEmployee -> theEmployee.id == user.id);
        }

        writeToJSONFile();
    }

    @Override
    public StartUp getStartUp() {
        return null;
    }

    @Override
    public StartUp updateStartUp(UUID id, String startUpName, int phoneNumber, String address) {
        return null;
    }

}
