package persist.json;

import core.model.Store;

import java.util.Set;
import java.util.UUID;

public class JSONStore {
    public UUID id;

    public String storeName;
    public String address;
    public int phoneNumber;
    public UUID owner;
    public UUID[] employees;
    public JSONProduct[] products;
//    public UUID[] currentAuctions;

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


}
