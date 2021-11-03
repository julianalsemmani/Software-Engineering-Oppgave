package core.model;

import java.util.ArrayList;

public class Store {
    public String storeName;
    public String address;
    public int phoneNumber;
    public int storeID;
    public StoreUser owner;
    public ArrayList<StoreUser> employees;
    public ArrayList<Product> products;

    public Store(String storeName, String address, int phoneNumber, int storeID, StoreUser owner, ArrayList<StoreUser> employees, ArrayList<Product> products) {
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.storeID = storeID;
        this.owner = owner;
        this.employees = employees;
        this.products = products;
    }

    public Product getProduct(int productId) {
        for (Product aProduct : products) {
            if (aProduct.productID == productId) {
                return aProduct;
            }
        }
        return null;
    }

    public StoreUser getEmployee(String userName) {
        for (StoreUser employee : employees) {
            if (employee.userName.equals(userName)) {
                return employee;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addEmployee(StoreUser employee) {
        employees.add(employee);
    }
}
