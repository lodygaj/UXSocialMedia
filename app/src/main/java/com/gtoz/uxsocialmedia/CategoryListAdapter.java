package com.gtoz.uxsocialmedia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by GtoZ on 10/20/2016.
 */
public class CategoryListAdapter extends BaseAdapter {
    private Context context;
    private FragmentManager fm;
    private LayoutInflater inflater;

    // Hardcoded - Add code to get dynamically from database
    private String[] categories = {"Automotive", "Extreme Sports", "Hiking", "Mountain", "Snow", "Water"};

    public CategoryListAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.view_category_item, null);
        TextView txtCategory = (TextView) view.findViewById(R.id.txtCategory);
        txtCategory.setText(categories[position].toString());
        return view;
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.fl_content, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }
}
