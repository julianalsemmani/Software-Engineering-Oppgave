package core.model;

import java.util.ArrayList;

public class StartUp {
    public String name;
    public String address;
    public int phoneNumber;
    public ArrayList<Store> stores;

    public StartUp(String name, String address, int phoneNumber, ArrayList<Store> stores) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.stores = stores;
    }
}
