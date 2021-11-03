package core.model;

public class Product {
    public int productID;
    public String productName;
    public float productPrice;
    public String productPicture;
    public int storeID;

    public Product(int productID, String productName, float productPrice, String productPicture, int storeID) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productPicture = productPicture;
        this.storeID = storeID;
    }

}
