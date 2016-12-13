package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/17/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] title;
    private final String[] location;
    private final int[] imgid;

    public CustomArrayAdapter(Activity context, String[] title, String[] location, int[] imgid) {
        super(context, R.layout.simplerow, title);

        this.context=context;
        this.title=title;
        this.imgid=imgid;
        this.location = location;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.simplerow, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(title[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(location[position]);
        return rowView;
    };
}