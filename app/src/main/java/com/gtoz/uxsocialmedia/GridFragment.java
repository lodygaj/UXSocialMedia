package com.gtoz.uxsocialmedia;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        // Set up grid layout
        recyclerView = (RecyclerView) view.findViewById(R.id.staggered_grid);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        GridAdapter adapter = new GridAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        ListSpacing dec = new ListSpacing(16);
        recyclerView.addItemDecoration(dec);

        return view;
    }
}
