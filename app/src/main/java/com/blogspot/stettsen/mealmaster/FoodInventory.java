package com.blogspot.stettsen.mealmaster;

import android.util.Log;
import java.util.Vector;

public class FoodInventory {

    private Vector<Food> inventory = null;
    private static FoodInventory instance = null;

    public static FoodInventory getInstance() {
        if(instance == null)
            instance = new FoodInventory();
        return instance;
    }

    public void setFoodInventoryByFoodInventory(FoodInventory foodInventory) {
        instance = foodInventory;
    }

    private FoodInventory() {
        inventory = new Vector<Food>();
    }

    public Vector<Food> getFoodInventoryVector() {
        return inventory;
    }

    public Food getFoodByPosition(int position) {
        return inventory.get(position);
    }

    public void setFood(Food food) {
        try {
            Log.d("START", "START: Adding to inventory vector");
            inventory.add(food); // populate the cookbook on load.
            Log.d("SUCCESS", "SUCCESS: Adding to inventory vector");
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: Adding to inventory vector");
        }
    }

    public int getFoodInventorySize(){
        return inventory.size();
    }

    public void deleteFoodInventory() {
        inventory = null;
        inventory = new Vector<Food>();
    }
}
