package com.gtoz.uxsocialmedia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.gtoz.uxsocialmedia.R.id.btnLogin;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class CreateStoryFragment extends Fragment {
    private Button submitButton, cancelButton, uploadButton;
    private TextView headerTxt, titleTxt, categoryTxt, locationTxt, captionTxt, uploadTxt, previewTxt;
    private String selectedCategory;

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
        previewTxt = (TextView) view.findViewById(R.id.txtViewPreview);

        submitButton = (Button) view.findViewById(R.id.submitButton);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        uploadButton = (Button) view.findViewById(R.id.uploadButton);

        // Apply fonts
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-DmCpIt.otf");
        headerTxt.setTypeface(font1);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-Book.otf");
        titleTxt.setTypeface(font2);
        categoryTxt.setTypeface(font2);
        locationTxt.setTypeface(font2);
        captionTxt.setTypeface(font2);
        uploadTxt.setTypeface(font2);
        previewTxt.setTypeface(font2);

        // Setup category spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.categorySpinner);
        // Create array of spinner elements
        List<String> categories = new ArrayList<String>();
        categories.add("Animal");
        categories.add("Food");
        categories.add("Music");
        categories.add("Nature");
        categories.add("Sport");
        categories.add("Water");

        selectedCategory = "Animal";

        // Create spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categories);
        // Set drop down layout style for spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach adapter to spinner
        spinner.setAdapter(adapter);
        // Listener to determine what happens when item is selected from drop down
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Not Used
            }
        });

        // Set up button click listeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent uploadIntent = new Intent();
                //uploadIntent.setResourceType("image/*");
                //uploadIntent.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(Intent.createChooser(uploadIntent, "Select image from gallery", 1));
            }
        });

        return view;
    }
}