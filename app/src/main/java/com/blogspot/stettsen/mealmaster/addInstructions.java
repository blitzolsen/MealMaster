package com.blogspot.stettsen.mealmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class addInstructions extends AppCompatActivity {

    Recipe recipe;
    CookBook cookBook;
    Boolean goneBack;
    public static final String FILE = "CookBookDB.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructions);
        cookBook = CookBook.getInstance();
        recipe = Recipe.getInstance();
        goneBack = false;
    }

    @Override
    public void onResume(){
        if (goneBack) {
            cookBook.enterRecipeBackButton();
            goneBack = false;
        }
        super.onResume();
    }

    @Override
    public void onPause(){
        goneBack = true;
        super.onPause();
    }

    public void sendIntentRecipeBrowser(View view) {
        // Set Instructions
        EditText instructEditText = (EditText) findViewById(R.id.recipeInstructions);
        String instructions = instructEditText.getText().toString();
        recipe.setInstructions(instructions);
        Toast.makeText(this, recipe.getInstructions() + "", Toast.LENGTH_LONG).show();
        cookBook.setRecipe(recipe); // add recipe to cookbook

        Gson gson = new Gson();
        String cookBookJson = gson.toJson(cookBook);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(cookBookJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Log.d("INFO", cookBookJson + "");

        Intent intent = new Intent(this, RecipeBrowserEX.class);
        startActivity(intent);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
