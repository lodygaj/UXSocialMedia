package com.gtoz.uxsocialmedia;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    int numOfLikes = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);


        // Set image header
        ImageView imageTest = (ImageView) view.findViewById(R.id.imgHeader);
        imageTest.setImageResource(R.drawable.flyboard);

        //Handles the title text as button to go to relevant website
        final TextView titleText = (TextView) view.findViewById(R.id.titlerText);
        titleText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "https://google.com";

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

        //Handling the Like Button
        ImageView button = (ImageView) view.findViewById(R.id.likeButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numOfLikes+=1;
                TextView tv = (TextView) view.findViewById(R.id.numLikesText);
                tv.setText(numOfLikes + " Likes");
            }
        });

        Button location = (Button) view.findViewById(R.id.locationButton);
        location.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Button b = (Button)v;
                String text = b.getText().toString();

                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+text);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Button share = (Button) view.findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(view.getContext(), "Clicked on Share" , Toast.LENGTH_SHORT).show();

                
            }
        });

        return view;
    }
}