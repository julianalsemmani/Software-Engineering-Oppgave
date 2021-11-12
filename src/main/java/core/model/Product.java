package core.model;

import java.util.UUID;

public class Product {
    public UUID id;
    public Store store;
    public String name;
    public String productPicture;

    public Product() {

    }

    public Product(UUID id, Store store, String name, String productPicture) {
        this.id = id;
        this.store = store;
        this.name = name;
        this.productPicture = productPicture;
    }

}
