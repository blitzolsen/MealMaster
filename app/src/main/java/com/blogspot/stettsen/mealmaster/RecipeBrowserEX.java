package com.blogspot.stettsen.mealmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


public class RecipeBrowserEX extends AppCompatActivity {

    private ListView listView;
    private RecipeListViewAdapter adapter;
    private CookBook cookBook;
    public static final String FILE = "CookBookDB.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_browser_ex);
        cookBook = CookBook.getInstance();

        listView = (ListView)findViewById(R.id.listview);

        adapter = new RecipeListViewAdapter(RecipeBrowserEX.this, (List<Recipe>) cookBook.getCookBookVector());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe.setInstance(cookBook.getRecipeByPosition(position));

                Intent intent = new Intent(getApplicationContext(), viewRecipe.class);
                startActivity(intent);
            }
        });
    }
    public void addAnotherRecipeToBrowser(View view) {
        Intent intent = new Intent(this, EnterRecipe.class);
        startActivity(intent);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearCookBook(View view){
        cookBook.deleteCookBook();
        String cookBookJson = "";

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(cookBookJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        adapter = new RecipeListViewAdapter(RecipeBrowserEX.this, (List<Recipe>) cookBook.getCookBookVector());
        listView.setAdapter(adapter);
        Toast.makeText(this,"Cook Book Cleared", Toast.LENGTH_SHORT).show();
    }
}
