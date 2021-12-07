package no.hiof.web.dtos.store;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonValidated
public class PostStoreBody {
    @NotNull
    @NotEmpty
    public String storeName;
    @NotNull
    @NotEmpty
    public String address;
    @NotNull
    public int phoneNumber;
//    @NotNull
//    @NotEmpty
//    public UUID owner;
    // This is assumed to be logged-in user.
}
