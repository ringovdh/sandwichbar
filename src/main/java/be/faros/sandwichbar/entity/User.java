package be.faros.sandwichbar.entity;

public class User extends BaseEntity {

    private String name;
    private String username;
    private String email;
    private String userRef;
    private Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
