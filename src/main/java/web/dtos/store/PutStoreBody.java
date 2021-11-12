package web.dtos.store;

import core.model.User;
import org.unbrokendome.jackson.beanvalidation.JsonValidated;

@JsonValidated
public class PutStoreBody {
    public String storeName;
    public String address;
    public int phoneNumber;
    public User owner;
}
