package web.dtos.product;

import core.model.Product;
import core.model.Store;

import java.util.UUID;

public class ProductResponseBody {
    public UUID id;
    public String name;
    public String productPicture;

    public ProductResponseBody() {
    }

    public ProductResponseBody(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.productPicture = product.productPicture;
    }
}
