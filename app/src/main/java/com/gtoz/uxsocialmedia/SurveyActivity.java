package com.gtoz.uxsocialmedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        // Set welcome fragment
        WelcomeFragment fragment = new WelcomeFragment();
        setFragment(fragment);
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.surveyContent, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }
}
