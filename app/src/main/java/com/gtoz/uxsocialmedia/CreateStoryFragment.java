package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import static android.app.Activity.RESULT_OK;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class CreateStoryFragment extends Fragment {
    private Button submitButton, cancelButton, uploadButton;
    private TextView headerTxt, titleTxt, categoryTxt, locationTxt, captionTxt, uploadTxt, previewTxt;
    private EditText titleEdtTxt, locationEdtTxt, captionEdtTxt;
    private String title, category, location, caption, res, resType;
    private Context context;
    private ImageView previewImage;
    String imgDecodableString;

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
        uploadTxt = (TextView) view.findViewById(R.id.txtViewUpload);
        previewTxt = (TextView) view.findViewById(R.id.txtViewPreview);

        titleEdtTxt = (EditText) view.findViewById(R.id.edtTxtTitle);
        locationEdtTxt = (EditText) view.findViewById(R.id.edtTxtLocation);
        captionEdtTxt = (EditText) view.findViewById(R.id.edtTxtCaption);

        submitButton = (Button) view.findViewById(R.id.submitButton);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        uploadButton = (Button) view.findViewById(R.id.uploadButton);

        previewImage = (ImageView) view.findViewById(R.id.previewImage);

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
                title = titleEdtTxt.getText().toString();
                location = locationEdtTxt.getText().toString();
                caption = captionEdtTxt.getText().toString();

                // Verify values



                // Add story to database
                Story story = new Story(0, title, location, category, caption, 0, resType, res, "people");
                dbHelper.addStory(story);
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
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, 1);


                resType = "video";
                res = "surfing";
            }
        });

        return view;
    }

    // Handles result of image selection
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String
                Bitmap img = BitmapFactory.decodeFile(imgDecodableString);
                previewImage.setImageBitmap(img);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}