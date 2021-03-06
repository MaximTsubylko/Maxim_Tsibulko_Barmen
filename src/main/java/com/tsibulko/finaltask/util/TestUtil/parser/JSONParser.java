package com.tsibulko.finaltask.util.TestUtil.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.tsibulko.finaltask.bean.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONParser {
    public static final Type COCKTAIL = new TypeToken<List<Cocktail>>() {
    }.getType();
    public static final Type CUSTOMER = new TypeToken<List<Customer>>() {
    }.getType();
    public static final Type INGREDIENT = new TypeToken<List<Ingredient>>() {
    }.getType();
    public static final Type COCKTAIL_FEEDBACK = new TypeToken<List<CocktaileFeedback>>() {
    }.getType();
    public static final Type BARMEN_FEEDBACK = new TypeToken<List<BarmenFeedback>>() {
    }.getType();


    public static List<Cocktail> CocktaileParse(String path) throws FileNotFoundException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Cocktail> data = gs.fromJson(reader, COCKTAIL);
        return data;
    }

    public static List<Customer> CustomerParse(String path) throws FileNotFoundException {

        Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Customer> data = gs.fromJson(reader, CUSTOMER);
        return data;
    }

    public static List<Ingredient> IngredientParse(String path) throws FileNotFoundException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<Ingredient> data = gs.fromJson(reader, INGREDIENT);
        return data;
    }

    public static List<CocktaileFeedback> CocktileFeedbackParse(String path) throws FileNotFoundException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<CocktaileFeedback> data = gs.fromJson(reader, COCKTAIL_FEEDBACK);
        return data;
    }

    public static List<BarmenFeedback> BarmenFeedbackParse(String path) throws FileNotFoundException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(path));
        List<BarmenFeedback> data = gs.fromJson(reader, BARMEN_FEEDBACK);
        return data;
    }


}

