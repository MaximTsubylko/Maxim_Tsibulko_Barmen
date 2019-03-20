package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

import java.util.List;

public class Cocktail implements Identified<Integer> {
    private Integer id;
    private String name;
    private String description;
    private int price;
    private List<Ingredient> ingredients;
    private String averageMark;

    public Cocktail(String name, String description, int price, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }


    public Cocktail() {
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

    public String getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(String averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ", averageMark='" + averageMark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cocktail cocktail = (Cocktail) o;

        if (price != cocktail.price) return false;
        if (id != null ? !id.equals(cocktail.id) : cocktail.id != null) return false;
        if (name != null ? !name.equals(cocktail.name) : cocktail.name != null) return false;
        if (description != null ? !description.equals(cocktail.description) : cocktail.description != null)
            return false;
        if (ingredients != null ? !ingredients.equals(cocktail.ingredients) : cocktail.ingredients != null)
            return false;
        return averageMark != null ? averageMark.equals(cocktail.averageMark) : cocktail.averageMark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (averageMark != null ? averageMark.hashCode() : 0);
        return result;
    }
}
