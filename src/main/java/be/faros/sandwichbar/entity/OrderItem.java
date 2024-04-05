package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderItem")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double price;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "sandwich_id", referencedColumnName = "id")
    private Sandwich sandwich;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }
}
