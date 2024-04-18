package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderitem")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double price;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "sandwich_id", referencedColumnName = "id")
    private Sandwich sandwich;
    @OneToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order sorder) {
        this.order = sorder;
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

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }
}
