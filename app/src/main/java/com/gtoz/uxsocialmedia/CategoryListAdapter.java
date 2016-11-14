package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by GtoZ on 10/20/2016.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.GridView> {
    private Context context;
    private FragmentManager fm;

    int[] imgList = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4,
            R.drawable.cat5, R.drawable.cat6, R.drawable.cat7};

    public CategoryListAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @Override
    public CategoryListAdapter.GridView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        GridView gridView = new GridView(layoutView);
        return gridView;
    }

    @Override
    public void onBindViewHolder(GridView holder, int position) {
        holder.imageView.setImageResource(imgList[position]);
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

        public GridView(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked on category" , Toast.LENGTH_SHORT).show();
        }
    }
}
