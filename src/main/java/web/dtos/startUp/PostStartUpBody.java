package web.dtos.startUp;

import core.model.User;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonValidated
public class PostStartUpBody {
    @NotNull
    @NotEmpty
    public String startUpName;
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
