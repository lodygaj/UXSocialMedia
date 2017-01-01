package com.gtoz.uxsocialmedia;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import static android.R.attr.bitmap;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.media.CamcorderProfile.get;
import static com.gtoz.uxsocialmedia.R.raw.surfing;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.GridView> {
    private Context context;
    private FragmentManager fm;
    private ArrayList<String> list;

    public CategoryListAdapter(Context context, FragmentManager fm, ArrayList<String> list) {
        this.context = context;
        this.fm = fm;
        this.list = list;
    }

    @Override
    public CategoryListAdapter.GridView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryListAdapter.GridView gridView = new CategoryListAdapter.GridView(layoutView);
        return gridView;
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.GridView holder, int position) {
        // Get category string
        String category = list.get(position);
        // Set category text
        holder.textView.setText(category);
        // Convert string to lowercase and set image
        category = category.substring(0).toLowerCase();
        int id = context.getResources().getIdentifier("drawable/" + category, null, context.getPackageName());
        holder.imageView.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.fl_content, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }

    public class GridView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public GridView(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
        }

        // Handles when item is clicked
        @Override
        public void onClick(View view) {
            // Load category in grid fragment
            String cat = list.get(getLayoutPosition());
            GridFragment gridFragment = new GridFragment();
            gridFragment.setCategory(cat);
            setFragment(gridFragment);
        }
    }
}