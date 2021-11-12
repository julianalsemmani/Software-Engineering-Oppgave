package web.dtos.store;

import core.model.User;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonValidated
public class PostStoreBody {
    @NotNull
    @NotEmpty
    public String storeName;
    @NotNull
    @NotEmpty
    public String address;
    @NotNull
    @NotEmpty
    public int phoneNumber;
    @NotNull
    @NotEmpty
    public UUID owner;
}
