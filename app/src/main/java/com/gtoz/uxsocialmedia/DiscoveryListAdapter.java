package com.gtoz.uxsocialmedia;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class DiscoveryListAdapter extends RecyclerView.Adapter<DiscoveryListAdapter.GridView> {
    private Context context;
    private FragmentManager fm;
    private ArrayList<Story> list;
    private int selectedItem;
    private TextView selectedText;
    boolean textShown = false;

    public DiscoveryListAdapter(Context context, FragmentManager fm, ArrayList<Story> list) {
        this.context = context;
        this.fm = fm;
        this.list = list;
    }

    @Override
    public DiscoveryListAdapter.GridView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.discovery_item, parent, false);
        DiscoveryListAdapter.GridView gridView = new DiscoveryListAdapter.GridView(layoutView);
        return gridView;
    }

    @Override
    public void onBindViewHolder(DiscoveryListAdapter.GridView holder, int position) {
        // Get image
        if(list.get(position).getResourceType().equals("video")) {
            // Get thumbnail from video file
            Uri videoURI = Uri.parse("android.resource://com.gtoz.uxsocialmedia/raw/" + list.get(position).getResource());
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(context, videoURI);
            Bitmap bitmap = retriever.getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            // Set thumbnail
            holder.imageView.setImageDrawable(drawable);
        }
        else {
            // Get image from drawables
            int id = context.getResources().getIdentifier("drawable/" + list.get(position).getResource(), null, context.getPackageName());
            // Set image
            holder.imageView.setImageResource(id);
        }

        holder.textView.setText(list.get(position).getTitle());
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
            textView.setVisibility(View.GONE);
        }

        // Handles when item is clicked
        @Override
        public void onClick(View view) {
            // Text is showing for one item
            if(textShown) {
                // Text is showing for same item
                if(getLayoutPosition() == selectedItem) {
                    // Set selected story and load story fragment
                    StoryFragment storyFragment = new StoryFragment();
                    storyFragment.setStory(list.get(getLayoutPosition()));
                    setFragment(storyFragment);
                    textShown = false;
                }
                // Text is showing for different item
                else {
                    // Hide other items text
                    selectedText.setVisibility(View.GONE);
                    // Show clicked items text
                    selectedItem = getLayoutPosition();
                    selectedText = textView;
                    textView.setVisibility(View.VISIBLE);
                }
            }
            // Text is not showing for any item
            else {
                // Show clicked items text
                selectedItem = getLayoutPosition();
                selectedText = textView;
                textView.setVisibility(View.VISIBLE);
                textShown = true;
            }
        }
    }
}