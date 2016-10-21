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
 * Created by Joey Laptop on 10/2/2016.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridView> {
    private Context context;
    private FragmentManager fm;

    int[] imgList = {R.drawable.flyboard, R.drawable.two, R.drawable.three, R.drawable.four,
            R.drawable.six, R.drawable.eight,
            R.drawable.ten};

    String[] nameList = {"SkyHigh JetPacks and Flyboard", "Fort Myers Beach, FL", "Lakes Regional Park", "Everglades Excursions", "Airboat Tours",
            "Water Sports", "Bonita Beach"};

    public GridAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @Override
    public GridView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        GridView gridView = new GridView(layoutView);
        return gridView;
    }

    @Override
    public void onBindViewHolder(GridView holder, int position) {
        holder.imageView.setImageResource(imgList[position]);
        holder.textView.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.length;
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
        }
    }
}
