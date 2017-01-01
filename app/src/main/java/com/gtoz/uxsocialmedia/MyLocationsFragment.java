package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;


/**
 * Created by GtoZ on 10/6/2016.
 */

public class MyLocationsFragment extends Fragment {
    FavoritesAdapter adapter;
    private Context context;
    private FragmentManager fm;
    private ArrayList<Story> list;
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_locations, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Get favorite stories
        DBHelper dbHelper = new DBHelper(context);
        list = dbHelper.getFavoriteStories();
        // Set favorite stories in adapter
        adapter = new FavoritesAdapter(context, list);
        adapter.notifyDataSetChanged();
        listView = (ListView) view.findViewById(R.id.locationList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Load story fragment




            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu pm = new PopupMenu(getContext(), view);
                Menu menu = pm.getMenu();
                menu.add("Delete");
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Delete favorite and refresh list




                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                        return true;
                    }
                });
                pm.show();
                return false;
            }
        });
        return view;
    }
}