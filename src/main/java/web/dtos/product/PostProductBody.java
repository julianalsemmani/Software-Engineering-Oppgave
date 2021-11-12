package web.dtos.product;

import core.model.Store;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PostProductBody {
    @NotNull
    @NotEmpty
    public UUID id;
    @NotNull
    @NotEmpty
    public Store store;
    @NotNull
    @NotEmpty
    public String name;
    @NotNull
    @NotEmpty
    public String productPicture;
}
