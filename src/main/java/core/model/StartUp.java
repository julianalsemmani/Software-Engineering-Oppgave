package core.model;

import java.util.List;

public class StartUp {
    public String name;
    public String address;
    public int phoneNumber;
    public List<Store> stores;

    public StartUp(String name, String address, int phoneNumber, List<Store> stores) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.stores = stores;
    }
}
