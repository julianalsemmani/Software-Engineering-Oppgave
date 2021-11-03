package core.model;

import core.dtos.StoreUserDTO;

public class StoreUser extends User {

    public StoreUser(String firstName, String lastName, String address, String email, String userName, String password) {
        super(firstName, lastName, address, email, userName, password);
    }
}
