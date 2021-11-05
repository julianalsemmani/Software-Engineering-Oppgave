package core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    public int id;

    public String storeName;
    public String address;
    public int phoneNumber;
    public User owner;
    public List<User> employees;
    public Map<Integer, Product> idProductMap;
    public List<Auction> currentAuctions;

    public Store(int id, String storeName, User owner, List<User> employees, String address, int phoneNumber, Map<Integer, Product> products) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
        this.employees = employees;
        this.idProductMap = products;
    }

    public Product getProduct(int productId) {
        return idProductMap.get(productId);
    }

    public User getEmployee(String userName) {
        for (User employee : employees) {
            if (employee.username.equals(userName)) {
                return employee;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        idProductMap.put(product.id, product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(idProductMap.values());
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }
}
