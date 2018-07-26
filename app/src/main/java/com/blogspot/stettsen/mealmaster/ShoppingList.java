package com.blogspot.stettsen.mealmaster;

import android.util.Log;
import java.util.Vector;


public class ShoppingList {

    private Vector<Food> shoppingList = null;
    private Vector<Food> shoppingListBackup = null;
    private static ShoppingList instance = null;

    public static ShoppingList getInstance() {
        if(instance == null)
            instance = new ShoppingList();
        return instance;
    }

    public void setShoppingListByShoppingList(ShoppingList shoppingList) {
        instance = shoppingList;
    }

    private ShoppingList() {
        shoppingList = new Vector<Food>();
    }

    public Vector<Food> getShoppingListVector() {
        return shoppingList;
    }

    public void setSingleFoodToShoppingList(Food food) {
        try {
            Log.d("START", "START: Adding to recipe vector");
            shoppingList.add(food);
            Log.d("SUCCESS", "SUCCESS: Adding to recipe vector");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Adding to recipe vector");
        }
    }

    public Food getFoodByPosition(int position) {
        return shoppingList.get(position);
    }

    public Food removeFoodByPosition(int position) {
        return shoppingList.remove(position);
    }

    public void backupShoppingList() {
        try{
            Log.d("START", "START: setting shopping list backup");
            shoppingListBackup = null;
            this.shoppingListBackup = shoppingList;
            Log.d("SUCESS", "SUCCESS: setting shopping list backup");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: setting shopping list backup");
        }

    }

    public void setShoppingListToBackup() {
        try{
            Log.d("START", "START: setting shopping TO list backup");
            shoppingList = null;
            this.shoppingList = shoppingListBackup;
            Log.d("SUCESS", "SUCCESS: setting shopping TO list backup");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: setting shopping list TO backup");
        }
    }

    public void setFood(Food food) {
        try {
            Log.d("START", "START: Adding to shoppinglist vector");
            shoppingList.add(food); // populate the cookbook on load.
            Log.d("SUCCESS", "SUCCESS: Adding to shoppinglist vector");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Adding to shoppinglist vector");
        }
    }

    public int getShoppingListSize(){
        return shoppingList.size();
    }

    public void deleteShoppingList() {
        shoppingList = null;
        shoppingList = new Vector<Food>();
    }
}
