package be.faros.sandwichbar.entity;

import java.util.List;

public class Order extends BaseEntity {

    private User user;

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
