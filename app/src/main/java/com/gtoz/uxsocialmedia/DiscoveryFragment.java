package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.Toast;

import org.jinstagram.entity.users.feed.MediaFeedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class DiscoveryFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    private RecyclerView thriftyList, recommendedList, categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Initialize layout objects
        thriftyList = (RecyclerView) view.findViewById(R.id.rv1);
        recommendedList = (RecyclerView) view.findViewById(R.id.rv2);
        categoryList = (RecyclerView) view.findViewById(R.id.rv3);

        // Set initial horizontal list layout and spacing values
        ListSpacing dec = new ListSpacing(0, 10);

        // Set up Thrifty horizontal list
        thriftyList.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        thriftyList.setLayoutManager(layoutManager1);
        thriftyList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter thriftyAdapter = new DiscoveryListAdapter(context, fm);
        thriftyList.setAdapter(thriftyAdapter);
        thriftyList.addItemDecoration(dec);

        // Set up Recommended horizontal list

        // Testing InstagramRetrievalService
        Intent mServiceIntent = new Intent(getActivity(), InstagramRetrievalService.class);
        getActivity().startService(mServiceIntent);

        // Receive broadcast from InstagramIntentService
        IntentFilter filter = new IntentFilter("REFRESH_ACTION");
        ResponseReceiver receiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);

        recommendedList.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendedList.setLayoutManager(layoutManager2);
        recommendedList.setItemAnimator(new DefaultItemAnimator());
        DiscoveryListAdapter recommendedAdapter = new DiscoveryListAdapter(context, fm);
        recommendedList.setAdapter(recommendedAdapter);
        recommendedList.addItemDecoration(dec);

        // Set up Category horizontal list
        categoryList.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(context);
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryList.setLayoutManager(layoutManager3);
        categoryList.setItemAnimator(new DefaultItemAnimator());
        CategoryListAdapter categoryAdapter = new CategoryListAdapter(context, fm);
        categoryList.setAdapter(categoryAdapter);
        categoryList.addItemDecoration(dec);

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
            toast.show();
        }
    }
}