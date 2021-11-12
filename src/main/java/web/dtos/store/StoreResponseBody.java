package web.dtos.store;

import core.model.Store;
import core.model.User;

import java.util.UUID;

public class StoreResponseBody {
    public UUID id;
    public String storeName;
    public String address;
    public int phoneNumber;
    public UUID owner;


    public StoreResponseBody(Store store) {
        this.id = store.id;
        this.storeName = store.storeName;
        this.address = store.address;
        this.phoneNumber = store.phoneNumber;
        this.owner = store.owner.id;
    }
}
