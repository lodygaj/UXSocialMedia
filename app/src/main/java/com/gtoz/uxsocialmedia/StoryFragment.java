package com.gtoz.uxsocialmedia;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class StoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        // Set image header
        ImageView imageTest = (ImageView) view.findViewById(R.id.imgHeader);
        imageTest.setImageResource(R.drawable.flyboard);

        //Handles the title text as button to go to relevant website
        TextView titleText = (TextView) view.findViewById(R.id.titlerText);
        titleText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://google.com";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //Handles category text as button to go to the relevant feed
        TextView catText = (TextView) view.findViewById(R.id.catText);
        catText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mainIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        return view;
    }
}