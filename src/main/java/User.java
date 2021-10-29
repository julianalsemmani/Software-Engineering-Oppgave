public abstract class User {
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String userName;
    public String password;

    public User(String firstName, String lastName, String adress, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = adress;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
