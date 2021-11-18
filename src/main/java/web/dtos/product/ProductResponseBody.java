package web.dtos.product;

import core.model.Product;
import core.model.Store;

import java.util.UUID;

public class ProductResponseBody {
    public UUID id;
    public Store store;
    public String name;
    public String productPicture;

    public ProductResponseBody() {
    }

    public ProductResponseBody(Product product) {
        this.id = product.id;
//        this.store = product.store;
        this.name = product.name;
        this.productPicture = product.productPicture;
    }
}
