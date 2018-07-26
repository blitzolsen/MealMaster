package com.blogspot.stettsen.mealmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterRecipe extends AppCompatActivity {

    public static final String PREFS = "com.blogspot.team16byui.mealmaster";
    public static final String TAG = "tag";
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_recipe);
    }

    public void enterIngredient(View v) {
        recipe = Recipe.getCleanInstance();

        EditText recipeNameEditText = (EditText) findViewById(R.id.recipeName);
        recipe.setName(recipeNameEditText.getText().toString());

        Spinner type1 = (Spinner) findViewById(R.id.recipeType1);
        recipe.setType1(type1.getSelectedItem().toString());

        Spinner type2 = (Spinner) findViewById(R.id.recipeType2);
        recipe.setType2(type2.getSelectedItem().toString());

        Spinner tag1 = (Spinner) findViewById(R.id.tag1);
        recipe.setTag1(tag1.getSelectedItem().toString());

        Spinner tag2 = (Spinner) findViewById(R.id.tag2);
        recipe.setTag2(tag2.getSelectedItem().toString());

        android.content.SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString("recipeName", recipeNameEditText.getText().toString());
        editor.apply();

        Toast.makeText(this,recipe.getName() + " ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EnterIngredient.class);
        startActivity(intent);
    }

    public void makeRecipeFromURL(View v){
        EditText urlEditText = (EditText) findViewById(R.id.urlEditText);
        String url = urlEditText.getText().toString();
        recipe = Recipe.getCleanInstanceURL(url);

       DownLoadHTMLFromWeb task = new DownLoadHTMLFromWeb(recipe, getApplicationContext());
        task.execute();
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
