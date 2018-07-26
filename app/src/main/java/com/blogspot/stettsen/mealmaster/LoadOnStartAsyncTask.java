package com.blogspot.stettsen.mealmaster;

import android.os.AsyncTask;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadOnStartAsyncTask extends AsyncTask<Void, Integer, Void> {

    public static final String FILE = "CookBookDB.json";
    public static final String FILE2 = "ShoppingListDB.json";
    public static final String FILE3 = "FoodInventoryDB.json";

    String fileName;
    Context context;
    ProgressBar progressBar;
    TextView progressStatus;
    CookBook cookBook;
    ShoppingList shoppingList;
    FoodInventory foodInventory;

    public LoadOnStartAsyncTask(String file, ProgressBar progressBar, Context applicationContext, TextView progressStatus) {
        fileName = file;
        this.progressBar = progressBar;
        context = applicationContext;
        this.progressStatus = progressStatus;
        cookBook = CookBook.getInstance();
        shoppingList = ShoppingList.getInstance();
        foodInventory = FoodInventory.getInstance();
    }

    @Override
    protected void onPreExecute() {
        progressBar.setProgress(0);
        progressStatus.setText("Loading Cook Book Database...");
    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected Void doInBackground(Void... voids) {

        try {
            InputStream inputStream = context.openFileInput(FILE);
            publishProgress(10);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Gson gson = new Gson();
                cookBook.onLoadSetCookBook(gson.fromJson(stringBuilder.toString(), CookBook.class));
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        publishProgress(40);

        // Start shoppinglist import
        try {
            InputStream inputStream = context.openFileInput(FILE2);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();

                Gson gson = new Gson();
                shoppingList.setShoppingListByShoppingList(gson.fromJson(stringBuilder.toString(), ShoppingList.class));
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        publishProgress(70);
        try {
            InputStream inputStream = context.openFileInput(FILE3);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Gson gson = new Gson();
                foodInventory.setFoodInventoryByFoodInventory(gson.fromJson(stringBuilder.toString(), FoodInventory.class));
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        publishProgress(100);
        return null;
    }

    protected void onProgressUpdate(Integer... percent) {
        progressStatus.setText(percent[0] + "%");
        progressBar.setProgress(percent[0]);
    }

    protected void onPostExecute(Void result) {
        progressStatus.setText("Cook Book Database Loaded");
    }
}