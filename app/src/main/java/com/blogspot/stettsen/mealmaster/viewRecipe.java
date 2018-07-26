package com.blogspot.stettsen.mealmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class viewRecipe extends AppCompatActivity {

    Recipe recipe;
    ShoppingList shoppingList;
    Boolean goneBack;
    String recipeAsText;
    public static final String FILE2 = "ShoppingListDB.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        TextView textView = (TextView) findViewById(R.id.displayRecipe);
        textView.setMovementMethod(new ScrollingMovementMethod());
        TextView recipeTitle = (TextView) findViewById(R.id.recipeNameTitle);
        recipe = Recipe.getInstance();
        shoppingList = ShoppingList.getInstance();
        goneBack = false;

        Log.d("Track Duplication::", "OnCreate, Recipe Ingredient 0 quantity "
                + recipe.getIngredient(0).getQuantity());

        recipeTitle.setText(recipe.getName());

        recipeAsText = /*"Name: " + recipe.getName() + "\n*/"Type 1: " + recipe.getType1()
                + "\nType2: " + recipe.getType2() + "\n\nTag1: " + recipe.getTag1() + "\nTag2: "
                + recipe.getTag2()
                + "\n\nIngredients:\n";
        for (int count = 0; count < recipe.getIngredientsSize(); count++) {
            recipeAsText += recipe.getIngredient(count).getName() + " "
                    + recipe.getIngredient(count).getQuantity() + " "
                    + recipe.getIngredient(count).getQuantityFraction().getString()
                    + " " + recipe.getIngredient(count).getAmountType() + "\n";
        }

        recipeAsText += "\nInstructions:\n" + recipe.getInstructions() + "\n";

        textView.setText(recipeAsText);
    }

    @Override
    public void onResume(){
        Log.d("Track Duplication::", "OnResume, Recipe Ingredient 0 quantity "
                + recipe.getIngredient(0).getQuantity());
        if (goneBack) {
            shoppingList.setShoppingListToBackup();
            goneBack = false;
        }
        super.onResume();
    }

    @Override
    public void onPause(){
        goneBack = true;
        super.onPause();
    }

    public void addToShoppinglist(View view) {
        Log.d("Track Duplication::", "addToShoppinglist, Recipe Ingredient 0 quantity "
                + recipe.getIngredient(0).getQuantity());
        shoppingList.backupShoppingList();

        Log.d("START", "Add to shoppinglist Function");
        Log.d("INFO", "Shopping list size: " + shoppingList.getShoppingListSize());
        Log.d("INFO", "1");
        Log.d("INFO", "Recipe Ingredient size: " + recipe.getIngredientsSize());

        Log.d("INFO", "2");
        for (int count = 0; count < recipe.getIngredientsSize(); count++) {
            Log.d("INFO", "5");
            shoppingList.setSingleFoodToShoppingList(deepCopyFood(recipe.getIngredient(count)));
            Log.d("Track Duplication::", "addToShoppinglist during loop, Recipe Ingredient"
                    + count + "quantity " + recipe.getIngredient(count).getQuantity());
        }

        Log.d("Track Duplication::", "addToShoppinglist after addloop, Recipe Ingredient 0 quantity "
                + recipe.getIngredient(0).getQuantity());

        for (int round = 0; round < 2; round++) {
            for (int count = 0; count < shoppingList.getShoppingListSize(); count++) {
                for (int i = count + 1; i < shoppingList.getShoppingListSize(); i++) {
                    if (count != i && shoppingList.getFoodByPosition(count).getName().equals(shoppingList.getFoodByPosition(i).getName())) {
                        shoppingList.getFoodByPosition(count).setQuantity(shoppingList.getFoodByPosition(i).getQuantity() + shoppingList.getFoodByPosition(count).getQuantity());
                        shoppingList.getFoodByPosition(count).getQuantityFraction().add(shoppingList.getFoodByPosition(i).getQuantityFraction());
                        shoppingList.removeFoodByPosition(i);
                    }
                }
            }
        }

        Log.d("Track Duplication::", "addToShoppinglist afterMergeLoop, Recipe Ingredient 0 quantity "
                + recipe.getIngredient(0).getQuantity());

        for (int count = 0; count < shoppingList.getShoppingListSize(); count++){
            shoppingList.getFoodByPosition(count).setQuantity(shoppingList.getFoodByPosition(count).getQuantity()
                    + (shoppingList.getFoodByPosition(count).getQuantityFraction().tranferWholeNumber()));
        }

        Log.d("INFO", "Temp list size after loop: " + shoppingList.getShoppingListSize());
        Log.d("INFO", "Shopping list size after loop: "
                + shoppingList.getShoppingListSize());
        Log.d("INFO", "Shopping list size after set to tempFood: "
                + shoppingList.getShoppingListSize());
        Log.d("SUCCESS", "Add to shoppinglist Function");

        Gson gson = new Gson();
        String shoppingListJson = gson.toJson(shoppingList);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE2, Context.MODE_PRIVATE));
            outputStreamWriter.write(shoppingListJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Toast.makeText(this, recipe.getName() + " recipe added to Shopping List", Toast.LENGTH_SHORT).show();
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToShoppingList(View view) {
        Intent intent = new Intent(this, ViewShoppingList.class);
        startActivity(intent);
    }

    public void goBackToRecipeBrowser(View view) {
        Intent intent = new Intent(this, RecipeBrowserEX.class);
        startActivity(intent);
    }

    public void goToWhatCanIMake(View view) {
        Intent intent = new Intent(this, WhatCanIMakeFromWhatIHave.class);
        startActivity(intent);
    }

    public Food deepCopyFood(Food input) {
        Food food = new Food();
        food.setName(input.getName());
        food.setQuantity(input.getQuantity());
        food.setAmountType(input.getAmountType());
        food.setBarcode(input.getBarcode());
        food.setCalories(input.getCalories());
        food.setFoodGroup(input.getFoodGroup());
        food.setInCart(input.isInCart());
        food.setQuantityFraction(input.getQuantityFraction());
        return food;
    }
}