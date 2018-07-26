package com.blogspot.stettsen.mealmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EnterIngredient extends AppCompatActivity {

    public static final String PREFS = "com.blogspot.team16byui.mealmaster";
    Recipe recipe;
    Food food;
    Fraction fraction;
    Integer amountOf;
    Boolean goneBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ingredient);

       android.content.SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
       String recipeNameString = preferences.getString("recipeName", "X");
       android.widget.TextView recipeDisplay = (android.widget.TextView) findViewById(R.id.recipeNameIngredientAc);
       recipeDisplay.setText(recipeNameString);
       recipe = Recipe.getInstance();
       food = new Food();
       goneBack = false;
    }

    @Override
    public void onResume(){
        if (goneBack) {
            recipe.enterIngredientBackButton();
            goneBack = false;
        }
        super.onResume();
    }
    @Override
    public void onPause(){
        goneBack = true;
        super.onPause();
    }

    public void addAnotherIngredient(View view) {
        getInformation(view);
        Toast.makeText(this,"Ingredient: " + food.getName() + " added.", Toast.LENGTH_SHORT).show();
        recipe.setIngredient(food);
        Intent intent = new Intent(this, EnterIngredient.class);
        startActivity(intent);
    }

    public void getInformation(View view) {
        // Set name
        EditText ingredientNameEditText = (EditText) findViewById(R.id.ingredientName);
        String recipeName = ingredientNameEditText.getText().toString();
        food.setName(recipeName);

        // Set amount of
        EditText amountOfEditText = (EditText) findViewById(R.id.amountOf);
        String temp = amountOfEditText.getText().toString();
        if (temp.equals("")) {
            amountOf = 0;
        } else {
            amountOf = Integer.parseInt(amountOfEditText.getText().toString());
        }

        // Set fractions
        Spinner fractionSpinner = (Spinner) findViewById(R.id.fractionSpinner);
        switch ((fractionSpinner.getSelectedItem().toString())) {
            case "1/2":
                fraction = new Fraction(1,2);
                break;
            case "1/3":
                fraction = new Fraction(1,3);
                break;
            case "2/3":
                fraction = new Fraction(2,3);
                break;
            case "1/4":
                fraction = new Fraction(1,4);
                break;
            case "3/4":
                fraction = new Fraction(3,4);
                break;
            case "1/8":
                fraction = new Fraction(1,8);
                break;
            case "1/16":
                fraction = new Fraction(1,16);
                break;
            default:
                fraction = new Fraction();
                break;
        }
        food.setQuantity(amountOf);
        food.setQuantityFraction(fraction);

        // Set units
        Spinner spinner = (Spinner) findViewById(R.id.meassurementUnits);
        String units = spinner.getSelectedItem().toString();
        food.setAmountType(units);

        // Set food group type
        Spinner typeSpinner = (Spinner) findViewById(R.id.foodGroup);
        String foodGroupType = typeSpinner.getSelectedItem().toString();
        food.setFoodGroup(foodGroupType);
    }

    public void sendIntentAddRecipeInstructions(View view) {
        getInformation(view);
        Toast.makeText(this,"Ingredient: " + food.getName() + " added.", Toast.LENGTH_SHORT).show();
        recipe.setIngredient(food);
        Intent intent = new Intent(this, addInstructions.class);
        startActivity(intent);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}