package com.gtoz.uxsocialmedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by GtoZ on 10/6/2016.
 */

public class MyLocationsFragment extends Fragment {

    CustomArrayAdapter adapter;
    ListView lv;
    int[] imgid;
    String[] title, location;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_locations, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        final ListView list;

        try {
            JSONArray jsonArray2 = new JSONArray(prefs.getString("key", "[]"));

            imgid = new int[jsonArray2.length()];
            title = new String[jsonArray2.length()];
            location = new String[jsonArray2.length()];

            for (int i = 0; i < jsonArray2.length(); i++) {
                int x = i * 3;
                title[i] = jsonArray2.getString(x);
                x++;
                location[i] = jsonArray2.getString(x);
                x++;
                imgid[i] = jsonArray2.getInt(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new CustomArrayAdapter(getActivity(), title, location, imgid);
        adapter.notifyDataSetChanged();
        list = (ListView) view.findViewById(R.id.locationList);
        list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                }
            });

            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                    PopupMenu pm = new PopupMenu(getContext(), view);
                    Menu menu = pm.getMenu();
                    menu.add("Delete");
                    pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.clear();
                            editor.commit();
                            adapter.notifyDataSetChanged();
                            list.setAdapter(adapter);


//                            JSONArray jsonArray2 = null;
//                            try {
//                                jsonArray2 = new JSONArray(prefs.getString("key", "[]"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            int len = jsonArray2.length();
//
//                            if (jsonArray2 != null) {
//                                for (int i=0;i<len;i++)
//                                {
//                                    //Excluding the item at position
//                                    if (i != position)
//                                    {
//                                        jsonArray2.put(title[i]);
//                                        jsonArray2.put(location[i]);
//                                        jsonArray2.put(imgid[i]);
//                                    }
//                                }
//                                adapter.notifyDataSetChanged();
//                            }
//
//                            editor.putString("key", jsonArray2.toString());
//                            editor.commit();

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
