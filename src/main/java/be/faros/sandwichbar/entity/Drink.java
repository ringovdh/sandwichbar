package be.faros.sandwichbar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DRINK")
public class Drink extends Product {

    private int stock;

    public Drink() { }

    public Drink(String name, String productRef, double price, int stock) {
        super(name, productRef, price);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
