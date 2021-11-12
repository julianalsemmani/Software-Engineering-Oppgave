package web.dtos.store;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import java.util.UUID;

@JsonValidated
public class PutStoreBody {
    public String storeName;
    public String address;
    public int phoneNumber;
    public UUID owner;
}
