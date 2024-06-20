package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="\"order\"")
public class Order extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getOrderTotal() {
        return 0D;
    }
}
