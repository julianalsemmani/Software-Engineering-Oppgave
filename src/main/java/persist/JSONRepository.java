package persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.model.*;
import core.repository.Repository;
import persist.json.JSONStartUp;
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
    private StartUp startUp = new StartUp("", "", 0);

    private final ObjectMapper objectMapper;
    private final File storeDataFile;

    public JSONRepository(String fileName) {
        storeDataFile = new File(fileName);

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        readFromJSONFile();
    }

    public void readFromJSONFile() {
        try {
            JSONStructure structure = objectMapper.readValue(storeDataFile, JSONStructure.class);

            for(JSONUser json : structure.users) {
                idUserMap.put(json.id, json.deserialize(idUserMap, idStoreMap));
            }
            for(JSONStore json : structure.stores) {
                idStoreMap.put(json.id, json.deserialize(idUserMap, idStoreMap));
            }
            startUp = structure.startUp.deserialize(idUserMap, idStoreMap);
        } catch(FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToJSONFile() {
        try {
            ObjectWriter writeMapper = objectMapper.writerWithDefaultPrettyPrinter();

            JSONStructure structure = new JSONStructure();

            for(User user : idUserMap.values()) {
                structure.users.add(new JSONUser(user));
            }
            for(Store store : idStoreMap.values()) {
                structure.stores.add(new JSONStore(store));
            }
            structure.startUp = new JSONStartUp(startUp);

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
        return startUp;
    }

    @Override
    public void updateStartUp() {
        writeToJSONFile();
    }

}
