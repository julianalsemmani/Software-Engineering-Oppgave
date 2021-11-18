package persist.json;

import core.model.Product;
import core.model.Sale;
import core.model.Store;
import core.model.User;

import java.util.Map;
import java.util.UUID;

public class JSONSale {
    public int price;
    public UUID product;
    public UUID store;

    public JSONSale() {

    }

    public JSONSale(Sale sale) {
        price = sale.price;
        product = sale.product.id;
        store = sale.store.id;
    }


//    public Sale toUser(Map<UUID, Store> storeIdMap) {
////        Store store = storeIdMap.get(store.id);
////        return new Sale(price, store.products.get);
//    }
}
