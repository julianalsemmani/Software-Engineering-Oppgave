package no.hiof.web.dtos.user;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonValidated
public class PostUserBody {
    @NotNull
    @NotEmpty
    public String username;
    @NotNull
    @NotEmpty
    public String password;
    @NotNull
    @NotEmpty
    public String firstName;
    @NotNull
    @NotEmpty
    public String lastName;
    @NotNull
    @NotEmpty
    public String address;
    @NotNull
    @NotEmpty
    public String email;

}
