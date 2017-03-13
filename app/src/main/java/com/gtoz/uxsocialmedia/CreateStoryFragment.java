package com.gtoz.uxsocialmedia;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class CreateStoryFragment extends Fragment {
    private Button submitButton, cancelButton;
    private TextView headerTxt, titleTxt, categoryTxt, locationTxt, captionTxt;
    private EditText titleEdtTxt, locationEdtTxt, captionEdtTxt;
    private String title, category, location, caption, res, resType;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_story, container, false);

        context = getActivity().getApplicationContext();
        final DBHelper dbHelper = new DBHelper(context);

        // Initialize objects from layout
        headerTxt = (TextView) view.findViewById(R.id.txtViewHeader);
        titleTxt = (TextView) view.findViewById(R.id.txtViewTitle);
        categoryTxt = (TextView) view.findViewById(R.id.txtViewCategory);
        locationTxt = (TextView) view.findViewById(R.id.txtViewLocation);
        captionTxt = (TextView) view.findViewById(R.id.txtViewCaption);

        titleEdtTxt = (EditText) view.findViewById(R.id.edtTxtTitle);
        locationEdtTxt = (EditText) view.findViewById(R.id.edtTxtLocation);
        captionEdtTxt = (EditText) view.findViewById(R.id.edtTxtCaption);

        submitButton = (Button) view.findViewById(R.id.submitButton);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);

        // Apply fonts
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-DmCpIt.otf");
        headerTxt.setTypeface(font1);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-Book.otf");
        titleTxt.setTypeface(font2);
        categoryTxt.setTypeface(font2);
        locationTxt.setTypeface(font2);
        captionTxt.setTypeface(font2);

        // Setup category spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.categorySpinner);
        // Create array of spinner elements
        List<String> categories = dbHelper.getCategories();
        category = categories.get(0);

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
                category = parent.getItemAtPosition(position).toString();
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
                Toast.makeText(getActivity().getApplicationContext(), "Story Submitted", Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        return view;
    }
}