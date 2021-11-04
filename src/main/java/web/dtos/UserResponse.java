package web.dtos;

import core.model.User;

public class UserResponse {
    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    // We do not include password here as that would be very bad if it was sent to front-end :D

    public UserResponse() {

    }

    public UserResponse(User user) {
        this.id = user.id;
        this.username = user.username;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.address = user.address;
        this.email = user.email;
    }
}
