package persist.json;

import core.model.Product;
import core.model.Store;
import core.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class JSONStore {
    public UUID id;

    public String storeName;
    public String address;
    public int phoneNumber;
    public UUID owner;
    public UUID[] employees;
    public JSONProduct[] products;

    public JSONStore() {

    }

    public JSONStore(Store store) {
        id = store.id;
        storeName = store.storeName;
        address = store.address;
        phoneNumber = store.phoneNumber;
        owner = store.owner.id;
        employees = store.employees.stream().map(user -> user.id).toArray(UUID[]::new);
        products = store.products.stream().map(JSONProduct::new).toArray(JSONProduct[]::new);
//        currentAuctions = store.currentAuctions.stream().map(auction -> auction.id).toArray(UUID[]::new);
    }

    public Store toStore(Map<UUID, User> idUserMap) {
        Store store = new Store(id, storeName, idUserMap.get(owner),
                Arrays.stream(employees).map(idUserMap::get).collect(Collectors.toCollection(HashSet::new)),
                address, phoneNumber,
                new HashSet<>());

        store.products = store.getAllProducts().stream()
                .map(jsonProduct -> new Product(jsonProduct.id, store, jsonProduct.name, jsonProduct.productPicture))
                .collect(Collectors.toCollection(HashSet::new));

        return store;
    }

}
