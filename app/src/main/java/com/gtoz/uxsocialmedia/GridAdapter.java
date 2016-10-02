package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joey Laptop on 10/2/2016.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridView> {
    private Context context;

    int[] imgList = {R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
            R.drawable.nine, R.drawable.ten};

    String[] nameList = {"One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten"};

    public GridAdapter(Context context) {
        this.context = context;
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

    class GridView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public GridView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
        }
    }
}
