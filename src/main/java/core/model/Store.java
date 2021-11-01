package core.model;

import java.util.ArrayList;

public class Store {
    public String storeName;
    public String address;
    public int phoneNumber;
    public int storeID;
    public StoreUser Owner;
    public ArrayList<StoreUser> employees;
    public ArrayList<Product> products;

    public Store(String storeName, String address, int phoneNumber, int storeID, StoreUser owner, ArrayList<StoreUser> employees, ArrayList<Product> products) {
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.storeID = storeID;
        Owner = owner;
        this.employees = employees;
        this.products = products;
    }
}
