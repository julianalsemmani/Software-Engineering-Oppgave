package core.model;

import java.util.List;

public class Store {
    public int id;

    public String storeName;
    public String address;
    public int phoneNumber;
    public User owner;
    public List<User> employees;
    public List<Product> products;

    public Store(int id, String storeName, User owner, List<User> employees, String address, int phoneNumber, List<Product> products) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
        this.employees = employees;
        this.products = products;
    }

    public Product getProduct(int productId) {
        for (Product aProduct : products) {
            if (aProduct.id == productId) {
                return aProduct;
            }
        }
        return null;
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
        products.add(product);
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }
}
