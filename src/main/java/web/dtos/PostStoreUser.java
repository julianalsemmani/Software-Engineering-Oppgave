package web.dtos;

import core.model.StoreUser;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonValidated
public class PostStoreUser {
    @NotNull @NotEmpty
    public String userName;
    @NotNull @NotEmpty
    public String password;
    public String firstName;
    public String lastName;
    public String address;
    public String email;

    public PostStoreUser() {

    }

    public PostStoreUser(StoreUser storeUser) {
        this.userName = storeUser.userName;
        this.password = storeUser.password;
        this.firstName = storeUser.firstName;
        this.lastName = storeUser.lastName;
        this.address = storeUser.address;
        this.email = storeUser.email;
    }
}
