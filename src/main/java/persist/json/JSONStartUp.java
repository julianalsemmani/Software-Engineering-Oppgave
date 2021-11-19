package persist.json;

import core.model.StartUp;

public class JSONStartUp {
    public String name;
    public String address;
    public int phoneNumber;

    public JSONStartUp(StartUp startUp) {
        name = startUp.name;
        address = startUp.address;
        phoneNumber = startUp.phoneNumber;
    }

    public StartUp toStartUp() {
        return new StartUp(name, address, phoneNumber);
    }
}
