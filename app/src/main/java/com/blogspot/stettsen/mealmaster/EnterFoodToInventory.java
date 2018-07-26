package com.blogspot.stettsen.mealmaster;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;


public class EnterFoodToInventory extends AppCompatActivity {

    public static final String FILE3 = "FoodInventoryDB.json";
    Food food;
    Fraction fraction;
    Integer amountOf;
    FoodInventory foodInventory;
    Boolean goneBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_food_to_inventory);
        foodInventory = FoodInventory.getInstance();
        goneBack = false;
        food = new Food();
    }

    @Override
    public void onResume(){
        if (goneBack) {
            food = new Food();
            goneBack = false;
        }
        super.onResume();
    }

    @Override
    public void onPause(){
        goneBack = true;
        super.onPause();
    }

    public void addAnotherFood(View view) {
        getInformation(view);
        Toast.makeText(this,"Food: " + food.getName() + " added.", Toast.LENGTH_SHORT).show();
        foodInventory.setFood(food);
        saveToInventory();
        Intent intent = new Intent(this, EnterFoodToInventory.class);
        startActivity(intent);
    }

    public void getInformation(View view) {
        // Set name
        EditText ingredientNameEditText = (EditText) findViewById(R.id.foodName);
        String foodName = ingredientNameEditText.getText().toString();
        food.setName(foodName);

        // Set amount of
        EditText amountOfEditText = (EditText) findViewById(R.id.amountOfFOODACTIVITY);
        String temp = amountOfEditText.getText().toString();

        if (temp.equals("")) {
            amountOf = 0;
        }
        else
            amountOf = Integer.parseInt(amountOfEditText.getText().toString());

        // Set fractions
        Spinner fractionSpinner = (Spinner) findViewById(R.id.fractionSpinnerFOODACTIVITY);
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
        Spinner spinner = (Spinner) findViewById(R.id.meassurementUnitsFOODACTIVITY);
        String units = spinner.getSelectedItem().toString();
        food.setAmountType(units);
    }

    public void addToInventory(View view) {
        getInformation(view);
        Toast.makeText(this,"Food: " + food.getName() + " added.", Toast.LENGTH_SHORT).show();
        foodInventory.setFood(food);
        saveToInventory();
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToInventory(View view){
        Intent intent = new Intent(this, viewFoodInventory.class);
        startActivity(intent);
    }

    public void saveToInventory(){
        Gson gson = new Gson();
        String foodInventoryJson = gson.toJson(foodInventory);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE3, Context.MODE_PRIVATE));
            outputStreamWriter.write(foodInventoryJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Log.d("INFO", foodInventoryJson + "");
    }
}