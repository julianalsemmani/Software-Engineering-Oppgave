package persist.json;

import core.model.*;
import org.eclipse.jetty.util.ajax.JSON;

import java.util.Map;
import java.util.UUID;

public class JSONProduct implements JSONDeserializer<Product> {
    public UUID id;
    public String name;
    public String productPicture;
    public JSONAuction auction;
    public JSONSale sale;

    public JSONProduct() {}

    public JSONProduct(Product product) {
        id = product.id;
        name = product.name;
        productPicture = product.productPicture;

        if(product.saleMethod instanceof Auction) {
            auction = new JSONAuction((Auction) product.saleMethod);
        } else if(product.saleMethod instanceof Sale) {
            sale = new JSONSale((Sale) product.saleMethod);
        }
    }

    @Override
    public Product deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        SaleMethod saleMethod = null;
        if(auction != null) {
            saleMethod = auction.deserialize(idUserMap, idStoreMap);
        }
        else if(sale != null) {
            saleMethod = sale.deserialize(idUserMap, idStoreMap);
        }

        return new Product(id, name, productPicture, saleMethod);
    }
}
