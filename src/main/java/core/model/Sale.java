package core.model;

public class Sale implements SaleMethod {
    public int price;
    public Product product;
    public Store store;

    public Sale(int price, Product product, Store store) {
        this.price = price;
        this.product = product;
        this.store = store;
    }
}
