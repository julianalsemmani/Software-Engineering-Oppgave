package web.dtos.store;

import core.model.Store;
import core.model.User;

public class StoreResponseBody {
    public int id;
    public String storeName;
    public String address;
    public int phoneNumber;
    public User owner;


    public StoreResponseBody(Store store) {
        this.id = store.id;
        this.storeName = store.storeName;
        this.address = store.address;
        this.phoneNumber = store.phoneNumber;
        this.owner = store.owner;
    }
}
