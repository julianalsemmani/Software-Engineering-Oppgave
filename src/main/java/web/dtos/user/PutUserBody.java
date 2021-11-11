package web.dtos;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonValidated
public class PutUserBody {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
}
