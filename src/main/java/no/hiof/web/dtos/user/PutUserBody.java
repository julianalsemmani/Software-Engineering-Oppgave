package no.hiof.web.dtos.user;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

@JsonValidated
public class PutUserBody {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
}
