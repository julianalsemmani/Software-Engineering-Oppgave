import java.util.ArrayList;

public class EndUser extends User {
    public int balance;
    public ArrayList<Product> favorites;
    public ArrayList<Product> productsBidOn;

    public EndUser(String firstName, String lastName, String adress, String email, String userName, String password, int balance) {
        super(firstName, lastName, adress, email, userName, password);
        this.balance = balance;
    }
}
