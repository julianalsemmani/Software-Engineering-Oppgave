package core.model;

import java.util.*;

public class Store {
    public UUID id;

    public String storeName;
    public String address;
    public int phoneNumber;
    public User owner;
    public Set<User> employees;
    public Set<Product> products;
    public List<Auction> currentAuctions;

    public Store(UUID id, String storeName, User owner, Set<User> employees, String address, int phoneNumber, Set<Product> products) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
        this.employees = employees;
        this.products = products;
    }

    public Store() {

    }

//    public Product getProduct(UUID productId) {
//        return products.(productId);
//    }

    public User getEmployee(String userName) {
        for (User employee : employees) {
            if (employee.username.equals(userName)) {
                return employee;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }
}
