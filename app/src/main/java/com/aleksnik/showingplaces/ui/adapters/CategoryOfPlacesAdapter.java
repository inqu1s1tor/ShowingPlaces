package com.aleksnik.showingplaces.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aleksnik.showingplaces.R;


public class CategoryOfPlacesAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private String[] categoryPlaces;

    public CategoryOfPlacesAdapter(Context context, String[] categoryPlaces) {
        this.categoryPlaces = categoryPlaces;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoryPlaces.length;
    }

    @Override
    public Object getItem(int position) {
        return categoryPlaces[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            holder = new Holder();
            convertView = mLayoutInflater.inflate(R.layout.item_categoty_grid_view, parent, false);
            holder.nameCategory = (TextView) convertView.findViewById(R.id.name_category);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.nameCategory.setText(categoryPlaces[position]);

        return convertView;
    }

    private static final class Holder {
        TextView nameCategory;
    }
}
