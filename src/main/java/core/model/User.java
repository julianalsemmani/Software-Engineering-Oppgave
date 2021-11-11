package core.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public int id;

    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String username;
    public String password;

    public boolean isAdmin;

    public int balance;

    public List<Product> favorites = new ArrayList<>();
    public List<Product> productsBidOn = new ArrayList<>();

    public User(int id, String username, String password, String firstName, String lastName, String address, String email, int balance, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.balance = balance;
    }

    public User() {

    }
}
