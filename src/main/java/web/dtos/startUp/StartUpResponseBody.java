package web.dtos.startUp;

import core.model.StartUp;
import core.model.User;

import java.util.UUID;

public class StartUpResponseBody {
    public String startUpName;
    public String address;
    public int phoneNumber;
    public int owner;

    public StartUpResponseBody(StartUp startup){
        this.startUpName = startup.name;
        this.address = startup.address;
        this.phoneNumber = startup.phoneNumber;
    }

}