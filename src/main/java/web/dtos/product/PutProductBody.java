package web.dtos.product;

import core.model.Store;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import java.util.UUID;

@JsonValidated
public class PutProductBody {
    public UUID id;
    public Store store;
    public String name;
    public String productPicture;
}
