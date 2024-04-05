package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sandwich")
public class Sandwich extends BaseEntity {

    private String name;
    private double price;
    @ManyToMany
    @JoinTable(name = "sandwich_ingredient",
            joinColumns = {@JoinColumn(name = "sandwich_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private List<Ingredient> ingredients;


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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
