package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="company")
public class Company extends BaseEntity {

    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "company")
    private List<User> users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
