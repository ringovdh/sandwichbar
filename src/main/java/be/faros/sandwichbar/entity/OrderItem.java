package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderitem")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;

    private String productRef;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order sorder) {
        this.order = sorder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }
}
