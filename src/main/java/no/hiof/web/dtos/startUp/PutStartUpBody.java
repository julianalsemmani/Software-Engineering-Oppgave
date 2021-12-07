package no.hiof.web.dtos.startUp;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import java.util.UUID;

@JsonValidated
public class PutStartUpBody {
    public String startUpName;
    public String address;
    public int phoneNumber;
    public UUID owner;
}