package persist.json;

import core.model.Product;
import core.model.Store;
import org.eclipse.jetty.util.ajax.JSON;

import java.util.UUID;

public class JSONProduct {
    public UUID id;
    public String name;
    public String productPicture;

    public JSONProduct() {}

    public JSONProduct(Product product) {
        id = product.id;
        name = product.name;
        productPicture = product.productPicture;
    }
}
