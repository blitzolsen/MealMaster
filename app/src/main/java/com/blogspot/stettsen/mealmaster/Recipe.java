package com.blogspot.stettsen.mealmaster;

import android.util.Log;

import java.util.Vector;


public class Recipe {

    private String name;
    private Vector<Food> ingredients;
    private String instructions;
    private String tag1;
    private String tag2;
    private String type1;
    private String type2;
    private String html;
    private String url;
    private static Recipe instance = null;

    public static Recipe getCleanInstance() {
        instance = null;
        instance = new Recipe();
        return instance;
    }

    public static Recipe getCleanInstanceURL(String url) {
        instance = null;
        instance = new Recipe(url);
        return instance;
    }

    public static Recipe getInstance() {
        return instance;
    }
    public static void setInstance(Recipe recipe) {
        instance = recipe;
    }
    private Recipe() {
        ingredients = new Vector<Food>();
    }
    private Recipe(String url) {
        setUrl(url);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIngredient(Food item) {
        try {
            Log.d("START", "START: Adding to food vector");
            ingredients.add(item);
            Log.d("SUCCESS", "SUCCESS: Adding to food vector");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Adding to food vector");
        }
    }

    public Food getIngredient(int position){
        return ingredients.get(position);
    }

    public void enterIngredientBackButton() {
        try {
            Log.d("START", "START: Deleting last food item");
            int size = ingredients.size() - 1;
            ingredients.remove(size);
            Log.d("SUCCESSS", "SUCCESS: Deleting last food item");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Deleting last food item");
        }
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getInstructions() {
        return instructions;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    public String getTag1() {
        return tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    public String getTag2() {
        return tag2;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
    public String getType1() {
        return type1;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
    public String getType2() {
        return type2;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    public String getHtml() {
        return html;
    }

    public int getIngredientsSize(){
        return ingredients.size();
    }
}
