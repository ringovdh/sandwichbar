package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch extends BaseEntity {

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
