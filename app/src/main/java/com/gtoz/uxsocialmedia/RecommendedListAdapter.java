package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class RecommendedListAdapter extends RecyclerView.Adapter<RecommendedListAdapter.GridView> {
    private Context context;
    private FragmentManager fm;

    int[] imgList = {R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};

    String[] nameList = {"Lakes Regional Park", "Everglades Excursions", "Airboat Tours",
            "Water Sports", "Bonita Beach"};

    public RecommendedListAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @Override
    public RecommendedListAdapter.GridView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        RecommendedListAdapter.GridView gridView = new RecommendedListAdapter.GridView(layoutView);
        return gridView;
    }

    @Override
    public void onBindViewHolder(RecommendedListAdapter.GridView holder, int position) {
        holder.imageView.setImageResource(imgList[position]);
        holder.textView.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return imgList.length;
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }

    class GridView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        boolean textShown = false;

        public GridView(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
            textView.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            if(textShown) {
                DetailFragment detailFragment = new DetailFragment();
                setFragment(detailFragment);
                textShown = false;
            }
            else {
                textView.setVisibility(View.VISIBLE);
                textShown = true;
            }

            Toast.makeText(view.getContext(), "Clicked on Recommended story" , Toast.LENGTH_SHORT).show();
        }
    }
}
