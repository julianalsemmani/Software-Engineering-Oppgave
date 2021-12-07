package core.model;

public class Sale implements SaleMethod {
    public User buyer;
    public int price;

    public Sale(int price) {
        this.price = price;
    }

    public Sale(User buyer, int price) {
        this.buyer = buyer;
        this.price = price;
    }
}
