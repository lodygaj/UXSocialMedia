package com.gtoz.uxsocialmedia;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class DiscoveryFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    private RecyclerView thriftyList, recommendedList;

    private ArrayList<Story> list1;
    private ArrayList<Story> list2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Initialize layout objects
        thriftyList = (RecyclerView) view.findViewById(R.id.rv1);
        recommendedList = (RecyclerView) view.findViewById(R.id.rv2);
        TextView title1 = (TextView) view.findViewById(R.id.title1);
        TextView title2 = (TextView) view.findViewById(R.id.title2);
        TextView title3 = (TextView) view.findViewById(R.id.title3);

        // Apply fonts to category headings
        Typeface franklinGothicStd = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-DmCpIt.otf");
        title1.setTypeface(franklinGothicStd);
        title2.setTypeface(franklinGothicStd);
        title3.setTypeface(franklinGothicStd);

        // Testing InstagramRetrievalService
        Intent mServiceIntent = new Intent(getActivity(), InstagramRetrievalService.class);
        getActivity().startService(mServiceIntent);

        // Receive broadcast from InstagramIntentService
        IntentFilter filter = new IntentFilter("REFRESH_ACTION");
        ResponseReceiver receiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);

        // Get stories from database
        DBHelper dbHelper = new DBHelper(context);
        list1 = dbHelper.getStoriesByType("thrifty");
        list2 = dbHelper.getStoriesByType("people");


        // Set initial horizontal list layout and spacing values
        ListSpacing dec = new ListSpacing(0, 10);

        // Set up Thrifty horizontal list
        thriftyList.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        thriftyList.setLayoutManager(layoutManager1);
        thriftyList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter thriftyAdapter = new DiscoveryListAdapter(context, fm, list1);
        thriftyList.setAdapter(thriftyAdapter);
        thriftyList.addItemDecoration(dec);

        // Set up Recommended horizontal list
        recommendedList.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendedList.setLayoutManager(layoutManager2);
        recommendedList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter recommendedAdapter = new DiscoveryListAdapter(context, fm, list2);
        recommendedList.setAdapter(recommendedAdapter);
        recommendedList.addItemDecoration(dec);

        return view;
    }
    public class ResponseReceiver extends BroadcastReceiver {

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {
            // Deliver message in toast
            CharSequence text = "Broadcasted and received from InstagramRetrievalService.";
            int duration = Toast.LENGTH_SHORT;

            // Handle content from Intent
            //ArrayList<MediaFeedData> mediaFeed = intent.getParcelableArrayListExtra("MEDIA");

            Toast toast = Toast.makeText(context, text, duration);
            //toast.show();
        }
    }
}