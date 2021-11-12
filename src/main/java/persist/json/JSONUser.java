package persist.json;

import core.model.Product;
import core.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JSONUser {
    public UUID id;

    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String username;
    public String password;

    public boolean isAdmin;

    public int balance;

    public UUID[] favorites;
    public UUID[] productsBidOn;

    public JSONUser(User user) {
        id = user.id;
        firstName = user.firstName;
        lastName = user.lastName;
        address = user.address;
        email = user.email;
        username = user.username;
        password = user.password;
        isAdmin = user.isAdmin;
        balance = user.balance;
        favorites = user.favorites.stream().map(product -> product.id).toArray(UUID[]::new);
        productsBidOn = user.productsBidOn.stream().map(product -> product.id).toArray(UUID[]::new);
    }
}
