package no.hiof.web.dtos.store;

import no.hiof.core.model.Store;
import no.hiof.web.dtos.user.UserResponseBody;
import no.hiof.web.dtos.product.ProductResponseBody;

import java.util.UUID;

public class StoreResponseBody {
    public UUID id;
    public String storeName;
    public String address;
    public int phoneNumber;
    public UserResponseBody owner;
    public ProductResponseBody[] products;

    public StoreResponseBody(Store store) {
        this.id = store.id;
        this.storeName = store.storeName;
        this.address = store.address;
        this.phoneNumber = store.phoneNumber;
        this.owner = new UserResponseBody(store.owner);
        this.products = store.getAllProducts().stream().map(ProductResponseBody::new).toArray(ProductResponseBody[]::new);
    }
}
