package web.dtos.user;

import core.model.User;

import java.util.UUID;

public class UserResponseBody {
    public UUID id;
    public String username;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    // We do not include password here as that would be very bad if it was sent to front-end :D

    public UserResponseBody() {

    }

    public UserResponseBody(User user) {
        this.id = user.id;
        this.username = user.username;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.address = user.address;
        this.email = user.email;
    }
}
