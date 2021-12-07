package no.hiof.web.dtos.startUp;

import no.hiof.core.model.StartUp;

public class StartUpResponseBody {
    public String startUpName;
    public String address;
    public int phoneNumber;

    public StartUpResponseBody(StartUp startup){
        this.startUpName = startup.name;
        this.address = startup.address;
        this.phoneNumber = startup.phoneNumber;
    }

}