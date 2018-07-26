package com.blogspot.stettsen.mealmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.Vector;

public class WhatCanIMakeFromWhatIHave extends AppCompatActivity {
    private ListView listView;
    private RecipeListViewAdapter adapter;
    private CookBook cookBook;
    FoodInventory foodInventory;
    private Vector<Recipe> recipesICanMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_can_imake_from_what_ihave);
        cookBook = CookBook.getInstance();
        foodInventory = FoodInventory.getInstance();
        recipesICanMake = new Vector<Recipe>();
        whatCanIMake();
        listView = (ListView)findViewById(R.id.recipeListView2);

        adapter = new RecipeListViewAdapter(WhatCanIMakeFromWhatIHave.this, (List<Recipe>) recipesICanMake);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe.setInstance(recipesICanMake.get(position));

                Intent intent = new Intent(getApplicationContext(), viewRecipe.class);
                startActivity(intent);
            }
        });
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void whatCanIMake(){
        for (int count = 0; count < cookBook.getCookBookVector().size(); count++){
            int matchedCount = 0;
            for (int i = 0; i < cookBook.getRecipeByPosition(count).getIngredientsSize(); i++) {
                for (int k = 0; k < foodInventory.getFoodInventorySize(); k++) {
                    if (cookBook.getRecipeByPosition(count).getIngredient(i).getName().equals(foodInventory.getFoodByPosition(k).getName())
                            && (cookBook.getRecipeByPosition(count).getIngredient(i).getQuantity() + cookBook.getRecipeByPosition(count).getIngredient(i).getQuantityFraction().getDecimal())
                            < (foodInventory.getFoodByPosition(k).getQuantity() + foodInventory.getFoodByPosition(k).getQuantityFraction().getDecimal())){
                        matchedCount++;
                    }
                }

                if (matchedCount == cookBook.getRecipeByPosition(count).getIngredientsSize()){
                    recipesICanMake.add(cookBook.getRecipeByPosition(count));
                }
            }
        }
    }
}