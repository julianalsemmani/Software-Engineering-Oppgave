package core.repository.fakes;

import core.model.*;
import core.repository.Repository;

import java.util.*;

/**
 * A repository that stores data in memory only. Comes with some initial data.
 */
public class FakeRepository implements Repository {
    private final Map<UUID, Store> idStoreMap = new HashMap<>();
    private final Map<UUID, User> idUserMap = new HashMap<>();
    private final StartUp startUp = new StartUp("", "", 0);

    public FakeRepository() {
    }

    public void dumpFakeData() {
        idUserMap.clear();
        idStoreMap.clear();
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
    }

    @Override
    public void updateStore(Store newStore) {

    }

    @Override
    public void deleteStore(Store store) {
        idStoreMap.remove(store.id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(idUserMap.values());
    }

    @Override
    public User getUserById(UUID userId) {
        return idUserMap.get(userId);
    }

    @Override
    public void addUser(User user) {
        idUserMap.put(user.id, user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {
        idUserMap.remove(user.id);
    }

    @Override
    public StartUp getStartUp() {
        return startUp;
    }

    @Override
    public void updateStartUp() {

    }
}
