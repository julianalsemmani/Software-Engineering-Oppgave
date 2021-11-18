package web.dtos.store;

import core.model.Store;
import web.dtos.product.ProductResponseBody;

import java.util.UUID;

public class StoreResponseBody {
    public UUID id;
    public String storeName;
    public String address;
    public int phoneNumber;
    public UUID owner;
    public ProductResponseBody[] products;

    public StoreResponseBody(Store store) {
        this.id = store.id;
        this.storeName = store.storeName;
        this.address = store.address;
        this.phoneNumber = store.phoneNumber;
        this.owner = store.owner.id;
        this.products = store.getAllProducts().stream().map(ProductResponseBody::new).toArray(ProductResponseBody[]::new);
    }
}
