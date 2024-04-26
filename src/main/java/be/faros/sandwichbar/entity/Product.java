package be.faros.sandwichbar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class Product extends BaseEntity {

    @Column(name = "product_id")
    private String productId;
    private String name;
    private double price;

    public Product() {}

    public Product(String name, double price) {
        this.productId = generateProductID();
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String generateProductID() {
        return UUID.randomUUID().toString();
    }
}
