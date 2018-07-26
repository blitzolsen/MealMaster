package com.blogspot.stettsen.mealmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendIntentEnterRecipe(View view) {
        Intent enterRecipe = new Intent(this, EnterRecipe.class);
        startActivity(enterRecipe);
    }

    public void sendIntentViewCookBook(View view) {
        Intent enterRecipe = new Intent(this, RecipeBrowserEX.class);
        startActivity(enterRecipe);
    }

    public void sendIntentWhatCanIMakeFromWhatIHave(View view) {
        Intent intent = new Intent(this, WhatCanIMakeFromWhatIHave.class);
        startActivity(intent);
    }

    public void sendIntentFoodInventory(View view) {
        Intent enterRecipe = new Intent(this, viewFoodInventory.class);
        startActivity(enterRecipe);
    }

    public void sendIntentViewShoppingList(View view) {
        Intent enterRecipe = new Intent(this, ViewShoppingList.class);
        startActivity(enterRecipe);
    }
}