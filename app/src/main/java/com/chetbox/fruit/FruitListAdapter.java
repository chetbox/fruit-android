package com.chetbox.fruit;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TwoLineListItem;

import java.util.List;

public class FruitListAdapter implements ListAdapter {

    private final List<Fruit> mItems;
    private final LayoutInflater mLayoutInflator;

    public FruitListAdapter(Context context, List<Fruit> items) {
        mItems = items;
        mLayoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TwoLineListItem listItem = (TwoLineListItem) convertView;
        if (convertView == null) {
            convertView = mLayoutInflator.inflate(android.R.layout.simple_list_item_2, null);
        }

        Fruit item = mItems.get(position);
        ((TwoLineListItem) convertView).getText1().setText(item.getName());
        ((TwoLineListItem) convertView).getText2().setText(item.getDescription());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
