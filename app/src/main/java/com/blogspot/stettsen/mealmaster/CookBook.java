package com.blogspot.stettsen.mealmaster;

import android.util.Log;
import java.util.Vector;


public class CookBook {

    private Vector<Recipe> recipeVector = null;
    private static CookBook instance = null;

    public static CookBook getInstance() {
        if(instance == null)
            instance = new CookBook();
        return instance;
    }

    public void onLoadSetCookBook(CookBook cookBook) {
        instance = cookBook;
    }
    private CookBook() {
        recipeVector = new Vector<Recipe>();
    }

    public Vector<Recipe> getCookBookVector() {
        return recipeVector;
    }

    public void setRecipe(Recipe recipe) {
        try {
            Log.d("START", "START: Adding to recipe vector");
            recipeVector.add(recipe); // populate the cookbook on load.
            Log.d("SUCCESS", "SUCCESS: Adding to recipe vector");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Adding to recipe vector");
        }
    }

    public Recipe getRecipeByPosition(int position) {
        return recipeVector.get(position);
    }

    public void enterRecipeBackButton() {
        try {
            Log.d("START", "START: Deleting last Recipe item");
            int size = recipeVector.size() - 1;
            recipeVector.remove(size);
            Log.d("SUCCESSS", "SUCCESS: Deleting last Recipe item");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Deleting last Recipe item");
        }
    }

    public void deleteCookBook(){
        recipeVector = null;
        recipeVector = new Vector<Recipe>();
    }
}
