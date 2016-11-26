package com.gtoz.uxsocialmedia;

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
import android.widget.ListView;
import android.widget.Toast;

import org.jinstagram.entity.users.feed.MediaFeedData;

import java.util.ArrayList;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class DiscoveryFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    private ListView categoryList;
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
        //categoryList = (ListView) view.findViewById(R.id.rv3);
        
        // Testing InstagramRetrievalService
        Intent mServiceIntent = new Intent(getActivity(), InstagramRetrievalService.class);
        getActivity().startService(mServiceIntent);

        // Receive broadcast from InstagramIntentService
        IntentFilter filter = new IntentFilter("REFRESH_ACTION");
        ResponseReceiver receiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);


        // Create arrays of sample stories
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        createLists();


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
            toast.show();
        }
    }

    // Used to hardcode sample stories for testing
    public void createLists() {
        Story story1 = new Story("Story 1", "Fort Myers, FL", null, "video", "Skiing", "This is sample Text", R.drawable.one, 237);
        Story story2 = new Story("Story 2", "San Diego, CA", null, "image", "Surfing", "This is sample Text", R.drawable.two, 1216);
        Story story3 = new Story("Story 3", "Los Angeles, CA", null, "image", "Surfing", "This is sample Text", R.drawable.three, 7243);
        Story story4 = new Story("Story 4", "Denver, CO", null, "image", "Mountain", "This is sample Text", R.drawable.four, 5231);
        Story story5 = new Story("Story 5", "Nashville, TN", null, "image", "Cycling", "This is sample Text", R.drawable.five, 863);
        Story story6 = new Story("Story 6", "Miami, FL", null, "image", "Surfing", "This is sample Text", R.drawable.six, 220);
        Story story7 = new Story("Story 7", "Park City, UT", null, "image", "Moutain", "This is sample Text", R.drawable.seven, 3655);
        Story story8 = new Story("Story 8", "Aspen, CO", null, "image", "Skiing", "This is sample Text", R.drawable.eight, 2643);
        Story story9 = new Story("Story 9", "Grand Rapids, MI", null, "image", "Cycling", "This is sample Text", R.drawable.nine, 1364);
        Story story10 = new Story("Story 10", "Asheville, NC", null, "image", "Moutain", "This is sample Text", R.drawable.ten, 1744);

        list1.add(story1);
        list1.add(story2);
        list1.add(story3);
        list1.add(story4);
        list1.add(story5);
        list1.add(story6);
        list1.add(story7);
        list1.add(story8);
        list1.add(story9);
        list1.add(story10);

        list2.add(story10);
        list2.add(story9);
        list2.add(story8);
        list2.add(story7);
        list2.add(story6);
        list2.add(story5);
        list2.add(story4);
        list2.add(story3);
        list2.add(story2);
        list2.add(story1);
    }
}