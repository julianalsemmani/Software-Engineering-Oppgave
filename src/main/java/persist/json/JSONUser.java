package persist.json;

import core.model.User;

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
        favorites = JSONUtils.mapToIds(user.favorites);
        productsBidOn = JSONUtils.mapToIds(user.productsBidOn);
    }
    
    public User toUser() {
        return new User(id, username, password, firstName,
                lastName, address, email, balance, isAdmin);
    }
}
