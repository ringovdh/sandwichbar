package be.faros.sandwichbar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="delivery")
public class Delivery extends BaseEntity {

    private String status;
    private Date deliveryDate;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
