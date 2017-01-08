package com.gtoz.uxsocialmedia;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GridFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context;
    private FragmentManager fm;
    private ArrayList<Story> stories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Set up grid layout
        recyclerView = (RecyclerView) view.findViewById(R.id.staggered_grid);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        GridAdapter adapter = new GridAdapter(context, fm, stories);
        recyclerView.setAdapter(adapter);
        ListSpacing dec = new ListSpacing(3, 2);
        recyclerView.addItemDecoration(dec);

        return view;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }
}