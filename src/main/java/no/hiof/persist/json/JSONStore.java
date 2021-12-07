package no.hiof.persist.json;

import no.hiof.core.model.Store;
import no.hiof.core.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class JSONStore implements JSONDeserializer<Store> {
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
        products = store.getAllProducts().stream().map(JSONProduct::new).toArray(JSONProduct[]::new);
    }

    @Override
    public Store deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        Store store = new Store(id, storeName, idUserMap.get(owner),
                Arrays.stream(employees).map(idUserMap::get).collect(Collectors.toCollection(HashSet::new)),
                address, phoneNumber,
                new HashSet<>());

        for(JSONProduct jsonProduct : products) {
            store.products.put(jsonProduct.id, jsonProduct.deserialize(idUserMap, idStoreMap));
        }

        return store;
    }
}
