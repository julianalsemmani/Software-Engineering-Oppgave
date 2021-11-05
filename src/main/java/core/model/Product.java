package core.model;

public class Product {
    public int id;
    public Store store;
    public String name;
    public String productPicture;

    public Product(int id, Store store, String name, String productPicture) {
        this.id = id;
        this.store = store;
        this.name = name;
        this.productPicture = productPicture;
    }

}
