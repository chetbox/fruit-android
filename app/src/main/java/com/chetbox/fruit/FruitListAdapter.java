package com.chetbox.fruit;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FruitListAdapter implements ListAdapter {

    private final List<Fruit> mItems;
    private final LayoutInflater mLayoutInflator;
    private final Context mContext;

    public FruitListAdapter(Context context, List<Fruit> items) {
        mContext = context;
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
        if (convertView == null) {
            convertView = mLayoutInflator.inflate(R.layout.fruit_list_item, null);
        }

        Fruit item = mItems.get(position);

        ((TextView) convertView.findViewById(android.R.id.text1)).setText(item.getName());
        ((TextView) convertView.findViewById(android.R.id.text2)).setText(item.getDescription());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        Picasso.with(mContext)
                .load(item.getImageUri())
                .placeholder(android.R.drawable.ic_menu_help)
                .into(imageView);

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
