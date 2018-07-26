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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewShoppingList extends AppCompatActivity {

    private ListView listView;
    private ShoppingListAdapter adapter;
    private ShoppingList shoppingList;
    public static final String FILE2 = "ShoppingListDB.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shopping_list);
    }

    @Override
    public void onResume(){
        shoppingList = shoppingList.getInstance();

        listView = (ListView)findViewById(R.id.shoppingListListView);

        adapter = new ShoppingListAdapter(this, (List<Food>) shoppingList.getShoppingListVector());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shoppingList.getFoodByPosition(position).toggleInCart();
                adapter.notifyDataSetChanged();
            }
        });
        super.onResume();
    }

    public void addAnotherRecipeToBrowser(View view) {
        Intent intent = new Intent(this, RecipeBrowserEX.class);
        startActivity(intent);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void deleteShoppingList(View view) {
        shoppingList.deleteShoppingList();
        String shoppingListJson = "";

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(FILE2, Context.MODE_PRIVATE));
            outputStreamWriter.write(shoppingListJson);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        listView.setAdapter(new ShoppingListAdapter(this, shoppingList.getShoppingListVector()));
        Toast.makeText(this,"Shopping list cleared", Toast.LENGTH_SHORT).show();
    }

    public void viewShoppingListByCategory(View view) {
        if (shoppingList.getShoppingListSize() > 0) {
            Collections.sort(shoppingList.getShoppingListVector(), new Comparator<Food>() {
                @Override
                public int compare(final Food obj1, final Food obj2) {
                    return obj1.getFoodGroup().compareTo(obj2.getFoodGroup());
                }
            });
        }

        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Shopping list is sorted", Toast.LENGTH_SHORT).show();
    }
}
