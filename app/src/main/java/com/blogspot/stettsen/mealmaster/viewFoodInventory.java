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


public class viewFoodInventory extends AppCompatActivity {

    private ListView listView;
    private FoodListAdapter adapter;
    private FoodInventory foodInventory;
    public static final String FILE3 = "FoodInventoryDB.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_inventory);
    }

    @Override
    public void onResume(){
        foodInventory = foodInventory.getInstance();

        listView = (ListView)findViewById(R.id.inventoryListView);

        adapter = new FoodListAdapter(this, (List<Food>) foodInventory.getFoodInventoryVector());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        super.onResume();
    }

    public void deleteFoodInventory(View view) {
        foodInventory.deleteFoodInventory();
        String shoppingListJson = "";

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE3, Context.MODE_PRIVATE));
            outputStreamWriter.write(shoppingListJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        listView.setAdapter(new ShoppingListAdapter(this, foodInventory.getFoodInventoryVector()));
        Toast.makeText(this,"Food Inventory cleared", Toast.LENGTH_SHORT).show();
    }

    public void sendIntentAddFood(View view) {
        Intent enterRecipe = new Intent(this, EnterFoodToInventory.class);
        startActivity(enterRecipe);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
