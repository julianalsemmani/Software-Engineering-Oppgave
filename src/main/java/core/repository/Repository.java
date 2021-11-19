package core.repository;

import core.model.*;

import java.util.List;
import java.util.UUID;

public interface Repository {

    List<Store> getAllStores();

    Store getStoreById(UUID storeId);

    void addStore(Store newStore);

    void updateStore(Store newStore);

    void deleteStore(Store store);

    User getUserById(UUID userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    StartUp getStartUp();

    StartUp updateStartUp(UUID id, String startUpName, int phoneNumber, String address);
}
