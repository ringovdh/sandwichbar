package be.faros.sandwichbar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DRINK")
public class Drink extends Product {

    private int stock;

    public Drink() { }

    public Drink(String name, double price, int stock) {
        super(name, price);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
