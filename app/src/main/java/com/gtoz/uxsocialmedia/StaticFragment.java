package com.gtoz.uxsocialmedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GtoZ on 11/22/2016.
 */

public class StaticFragment extends Fragment {
    private static final String TAG = "StaticFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.static_fragment, container, false);
        return view;
    }
}
