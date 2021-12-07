package no.hiof.web.dtos.product;

import no.hiof.core.model.Store;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

@JsonValidated
public class PutProductBody {
    public Store store;
    public String name;
    public String productPicture;
}
