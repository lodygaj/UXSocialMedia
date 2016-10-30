package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by GtoZ on 10/30/2016.
 */

public class WelcomeFragment extends Fragment {
    private Button beginSurvey, skipSurvey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        // Initialize objects
        beginSurvey = (Button) view.findViewById(R.id.btnBeginSurvey);
        skipSurvey = (Button) view. findViewById(R.id.btnSkipSurvey);

        // Button click listeners
        beginSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load question fragment
                QuestionFragment fragment = new QuestionFragment();
                setFragment(fragment);
            }
        });

        skipSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load main activity with generic content
                Intent mainIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        return view;
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.surveyContent, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }
}
