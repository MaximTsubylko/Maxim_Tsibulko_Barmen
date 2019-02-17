package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

import java.util.List;
import java.util.Map;

public class Cocktaile implements Identified<Integer> {
    private Integer id;
    private String name;
    private String description;
    private int price;
    private List<Ingredient> ingredients;

    public Cocktaile(String name, String description, int price, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients  = ingredients;
    }



    public Cocktaile() {
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cocktaile cocktaile = (Cocktaile) o;

        if (price != cocktaile.price) return false;
        if (id != null ? !id.equals(cocktaile.id) : cocktaile.id != null) return false;
        if (name != null ? !name.equals(cocktaile.name) : cocktaile.name != null) return false;
        if (description != null ? !description.equals(cocktaile.description) : cocktaile.description != null)
            return false;
        return ingredients != null ? ingredients.equals(cocktaile.ingredients) : cocktaile.ingredients == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cocktaile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
