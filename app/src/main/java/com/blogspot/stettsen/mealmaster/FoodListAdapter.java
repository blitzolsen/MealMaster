package com.blogspot.stettsen.mealmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;


public class FoodListAdapter extends BaseAdapter {

    private List<Food> items;
    private LayoutInflater inflater = null;

    public FoodListAdapter(Context context, List<Food> items){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(R.layout.inventory_list_view_template,null,false);

        //Adapter gives position parameter.
        //This parameter helps us to know which item view is wanted by adapter.
        ((TextView)view.findViewById(R.id.item_name)).setText(items.get(position).getName()
                + " " + items.get(position).getQuantity() + " "
                + items.get(position).getQuantityFraction().getString() + " "
                + items.get(position).getAmountType());

        return view;
    }
}
