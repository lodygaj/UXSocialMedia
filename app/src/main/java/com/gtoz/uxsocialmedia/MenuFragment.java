package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by GtoZ on 11/5/2016.
 */

public class MenuFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    private RecyclerView thriftyList, recommendedList, categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Initialize layout objects
        thriftyList = (RecyclerView) view.findViewById(R.id.thriftyList);
        recommendedList = (RecyclerView) view.findViewById(R.id.recommendedList);
        categoryList = (RecyclerView) view.findViewById(R.id.categoryList);

        // Set initial horizontal list layout and spacing values
        ListSpacing dec = new ListSpacing(0, 1);

        // Set up Thrifty horizontal list
        LinearLayoutManager thriftyManager = new LinearLayoutManager(getActivity().getApplicationContext());
        thriftyManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        thriftyList.setLayoutManager(thriftyManager);
        ThriftyListAdapter thriftyAdapter = new ThriftyListAdapter(context, fm);
        thriftyList.setAdapter(thriftyAdapter);
        thriftyList.addItemDecoration(dec);

        // Set up Recommended horizontal list
        LinearLayoutManager recommendedManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recommendedManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendedList.setLayoutManager(recommendedManager);
        RecommendedListAdapter recommendedAdapter = new RecommendedListAdapter(context, fm);
        recommendedList.setAdapter(recommendedAdapter);
        recommendedList.addItemDecoration(dec);

        // Set up Category horizontal list
        LinearLayoutManager categoryManager = new LinearLayoutManager(getActivity().getApplicationContext());
        categoryManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryList.setLayoutManager(categoryManager);
        CategoryListAdapter categoryAdapter = new CategoryListAdapter(context, fm);
        categoryList.setAdapter(categoryAdapter);
        categoryList.addItemDecoration(dec);

        return view;
    }

}
