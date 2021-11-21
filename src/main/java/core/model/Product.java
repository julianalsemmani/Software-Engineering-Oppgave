package core.model;

import java.util.UUID;

public class Product implements Identified {
    public UUID id;
    public String name;
    public String productPicture;
    public SaleMethod saleMethod;

    public Product(UUID id, String name, String productPicture, SaleMethod saleMethod) {
        this.id = id;
        this.name = name;
        this.productPicture = productPicture;
        this.saleMethod = saleMethod;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
