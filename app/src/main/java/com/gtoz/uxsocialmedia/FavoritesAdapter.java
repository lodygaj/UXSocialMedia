package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/17/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.gtoz.uxsocialmedia.R.id.imageView;

public class FavoritesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Story> list;
    private LayoutInflater inflater;

    public FavoritesAdapter(Context context, ArrayList<Story> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,View view,ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.simplerow, null,true);

        // Get objects from layout
        TextView title = (TextView) rowView.findViewById(R.id.item);
        ImageView image = (ImageView) rowView.findViewById(R.id.icon);
        TextView location = (TextView) rowView.findViewById(R.id.textView1);

        // Set object values
        title.setText(list.get(position).getTitle());
        location.setText(list.get(position).getLocation());
        // Get image
        if(list.get(position).getResourceType().equals("video")) {
            // Get thumbnail from video file
            Uri videoURI = Uri.parse("android.resource://com.gtoz.uxsocialmedia/raw/" + list.get(position).getResource());
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(context, videoURI);
            Bitmap bitmap = retriever.getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            // Set thumbnail
            image.setImageDrawable(drawable);
        }
        else {
            // Get image from drawables
            int id = context.getResources().getIdentifier("drawable/" + list.get(position).getResource(), null, context.getPackageName());
            // Set image
            image.setImageResource(id);
        }

        return rowView;
    };
}