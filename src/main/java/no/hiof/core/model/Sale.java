package no.hiof.core.model;

public class Sale implements SaleMethod {
    public User buyer;
    public int price;

    public Sale(User buyer, int price) {
        this.buyer = buyer;
        this.price = price;
    }

    public boolean doSale(User buyer) {
        if (!hasBeenSold()) {
            this.buyer = buyer;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasBeenSold() {
        return buyer != null;
    }

    @Override
    public User getBuyer() {
        return buyer;
    }
}
