package com.gtoz.uxsocialmedia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_locations, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        ListView list;

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
            list = (ListView) view.findViewById(R.id.locationList);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    String Slecteditem = title[+position];
                    Toast.makeText(view.getContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                }
            });
        return view;
        }
    }
