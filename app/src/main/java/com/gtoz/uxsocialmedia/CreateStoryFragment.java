package com.gtoz.uxsocialmedia;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class CreateStoryFragment extends Fragment {
    private TextView headerTxt, titleTxt, categoryTxt, locationTxt, captionTxt, uploadTxt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_story, container, false);

        // Initialize objects from layout
        headerTxt = (TextView) view.findViewById(R.id.txtViewHeader);
        titleTxt = (TextView) view.findViewById(R.id.txtViewTitle);
        categoryTxt = (TextView) view.findViewById(R.id.txtViewCategory);
        locationTxt = (TextView) view.findViewById(R.id.txtViewLocation);
        captionTxt = (TextView) view.findViewById(R.id.txtViewCaption);
        uploadTxt = (TextView) view.findViewById(R.id.txtViewUpload);

        // Apply fonts
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-DmCpIt.otf");
        headerTxt.setTypeface(font1);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-Book.otf");
        titleTxt.setTypeface(font2);
        categoryTxt.setTypeface(font2);
        locationTxt.setTypeface(font2);
        captionTxt.setTypeface(font2);
        uploadTxt.setTypeface(font2);

        // Setup category spinner





        return view;
    }
}