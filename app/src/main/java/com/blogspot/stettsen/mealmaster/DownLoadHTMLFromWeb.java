package com.blogspot.stettsen.mealmaster;

import android.os.AsyncTask;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DownLoadHTMLFromWeb extends AsyncTask<Void, Integer, Void> {

    Recipe recipe;
    Context context;
    TextView test;
    public static final String TAG = "DownLoadHTMLFromWeb";

    public DownLoadHTMLFromWeb(Recipe recipe, Context context) {
        this.recipe = recipe;
        this.context = context;
        this.test = null;
    }

    @Override
    protected void onPreExecute() {
    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected Void doInBackground(Void... voids) {
        String contents ="";

        try {
            URLConnection conn = new URL(recipe.getUrl()).openConnection();

            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(in, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                Log.d(TAG,"START Reading file by line");
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"ERROR Closing Input String");
                } Log.d(TAG,"SUCCESSFUL Closed Input String");
            }

            contents = sb.toString();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        String[] htmlArray = contents.split( " ");

        recipe.setHtml(contents);

        for(int i=0;i<htmlArray.length-1;i++) {
            if (htmlArray[i].equals("\"name\"")) {
                recipe.setName(htmlArray[i + 2]);
                Log.d(TAG,"SUCCESS name found in " + recipe.getUrl());
            } else
                Log.d(TAG,"ERROR No name found in " + recipe.getUrl());
        }
        return null;
    }

    protected void onProgressUpdate(Integer... percent) {
    }

    protected void onPostExecute(Void result) {
        Toast.makeText(context, recipe.getName(), Toast.LENGTH_SHORT).show();
    }
}