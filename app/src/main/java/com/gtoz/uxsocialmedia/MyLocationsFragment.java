package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by GtoZ on 10/6/2016.
 */

public class MyLocationsFragment extends Fragment {

    CustomArrayAdapter adapter;
    ListView lv;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_locations, container, false);

        ListView list;
        final String[] title = {
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGCU",
                "FGC",

        };

        final String[] location = {
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
                "Fort Myers",
        };

        Integer[] imgid = {
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,
                R.drawable.cat1,

        };

            CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), title, location, imgid);
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
