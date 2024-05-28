package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

@Entity
@Table(name="\"user\"")
public class User extends BaseEntity {

    private String name;
    private String username;
    private String email;
    @Column(name="user_ref")
    private String userRef;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
