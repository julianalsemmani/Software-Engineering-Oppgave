package core.model;

public class Product {
    public int id;
    public String name;
    public float minimumBid;
    public String productPicture;
    public int storeID;

    public Product(int id, String name, float minimumBid, String productPicture, int storeID) {
        this.id = id;
        this.name = name;
        this.minimumBid = minimumBid;
        this.productPicture = productPicture;
        this.storeID = storeID;
    }

}
