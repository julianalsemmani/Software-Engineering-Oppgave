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

    public boolean isAdmin = false;

    public int balance = 0;
    public List<Product> favorites = new ArrayList<>();
    public List<Product> productsBidOn = new ArrayList<>();

    public User(int id, String username, String password, String firstName, String lastName, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
