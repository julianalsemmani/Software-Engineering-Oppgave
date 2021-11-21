package persist.json;

import core.model.StartUp;
import core.model.Store;
import core.model.User;

import java.util.Map;
import java.util.UUID;

public class JSONStartUp implements JSONDeserializer<StartUp> {
    public String name;
    public String address;
    public int phoneNumber;

    public JSONStartUp() {

    }

    public JSONStartUp(StartUp startUp) {
        name = startUp.name;
        address = startUp.address;
        phoneNumber = startUp.phoneNumber;
    }

    @Override
    public StartUp deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new StartUp(name, address, phoneNumber);
    }
}
