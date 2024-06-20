package be.faros.sandwichbar.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type",
        discriminatorType = DiscriminatorType.STRING)
public class Product extends BaseEntity {

    private String name;

    private String productRef;

    private double price;


    public Product() {
    }

    public Product(String name, String productRef, double price) {
        this.name = name;
        this.productRef = productRef;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
