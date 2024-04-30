package be.faros.sandwichbar.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("SANDWICH")
public class Sandwich extends Product {

    @ManyToMany
    @JoinTable(name = "sandwich_ingredient",
            joinColumns = {@JoinColumn(name = "sandwich_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private List<Ingredient> ingredients;

    public Sandwich() {
        super();
    }

    public Sandwich(String name, double price, List<Ingredient> ingredients) {
        super(name, price);
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isAvailable() {
        return this.ingredients.stream().filter(i -> {
            return i.getStock() == 0;
        }).toList().isEmpty();
    }
}
