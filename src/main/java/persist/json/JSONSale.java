package persist.json;

import core.model.Product;
import core.model.Sale;
import core.model.Store;
import core.model.User;

import java.util.Map;
import java.util.UUID;

public class JSONSale implements JSONDeserializer<Sale> {
    public int price;

    public JSONSale() {

    }

    public JSONSale(Sale sale) {
        price = sale.price;
    }

    @Override
    public Sale deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new Sale(price);
    }
}
