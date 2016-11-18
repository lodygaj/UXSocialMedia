package com.gtoz.uxsocialmedia;

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

import static com.gtoz.uxsocialmedia.R.id.likeButton;

public class StoryFragment extends Fragment {
    int numOfLikes = 0;
    Story story;

//    public StoryFragment(Story story) {
//        this.story = story;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_story, container, false);

        // Set header image
        ImageView image = (ImageView) view.findViewById(R.id.resource);
        image.setImageResource(story.getResource());

        // Set title text
        final TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(story.getTitle());
        //Handles the title click listener to go to relevant website
//        if(story.getWebsite() != null) {
//            title.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    String url = story.getWebsite();
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });
//        }

        // Set category text
        TextView category = (TextView) view.findViewById(R.id.category);
        category.setText(story.getCategory());
        //Handles category text as button to go to the relevant feed
        category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(view.getContext(), "Clicked on Category" , Toast.LENGTH_SHORT).show();
            }
        });

        // Set # of likes
        TextView likes = (TextView) view.findViewById(R.id.likes);
        likes.setText(Integer.toString(story.getLikes()));
        //Handles the Like Button
        ImageView likeButton = (ImageView) view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numOfLikes+=1;
                TextView tv = (TextView) view.findViewById(R.id.likes);
                tv.setText(numOfLikes + " Likes");
            }
        });

        // Set location text
        Button location = (Button) view.findViewById(R.id.locationButton);
        location.setText(story.getLocation());
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

        // Set content text
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(story.getText());

        return view;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}