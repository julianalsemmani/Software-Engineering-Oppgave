package persist.json;

import core.model.Sale;
import core.model.Store;
import core.model.User;

import java.util.Map;
import java.util.UUID;

public class JSONSale implements JSONDeserializer<Sale> {
    public int price;
    public UUID buyer;

    public JSONSale() {

    }

    public JSONSale(Sale sale) {
        price = sale.price;
        if(sale.buyer != null) {
            buyer = sale.buyer.id;
        }
    }

    @Override
    public Sale deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        User buyer = null;
        if(this.buyer != null) {
            buyer = idUserMap.get(this.buyer);
        }
        return new Sale(buyer, price);
    }
}
