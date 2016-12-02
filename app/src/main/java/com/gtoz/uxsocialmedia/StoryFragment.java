package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StoryFragment extends Fragment {
    private Story story;
    List<String> list = new ArrayList<String>();
    private MediaController mediaController;
    private VideoView video;
    private FrameLayout videoFrame;
    private int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_story, container, false);

        final int[] numOfLikes = {(int) story.getLikes()};

        // Set header resource
        ImageView image = (ImageView) view.findViewById(R.id.resource);
        image.setImageResource(story.getResource());
        video = (VideoView) view.findViewById(R.id.videoView);
        videoFrame = (FrameLayout) view.findViewById(R.id.videoViewFrame);

        // Set up video player
        if (mediaController == null) {
            mediaController = new MediaController(getContext());
            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(video);
            // Set MediaController for VideoView
            video.setMediaController(mediaController);
        }

        String uriPath = "android.resource://com.gtoz.uxsocialmedia/"+R.raw.surfing;
        Uri uri = Uri.parse(uriPath);
        video.setVideoURI(uri);
        video.requestFocus();

        // When the video file ready for playback.
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                video.seekTo(position);
                if (position == 0) {
                    video.start();
                }
                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(video);
                    }
                });
            }
        });

        // Set image and hide video view
        if(story.getType().equals("image")) {
            image.setImageResource(story.getResource());
            videoFrame.setVisibility(View.GONE);
        }
        // Set video and hide image view
        else {
            //video.start();
            image.setVisibility(View.GONE);
        }

        // Set title text
        final TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(story.getTitle());
        //Handles the title click listener to go to relevant website
        if(story.getWebsite() != null) {
            title.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String url = story.getWebsite();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

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
                numOfLikes[0] += 1;
                TextView tv = (TextView) view.findViewById(R.id.likes);
                tv.setText(Integer.toString(numOfLikes[0]));
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

        Button fave = (Button) view.findViewById(R.id.reservationButton);
        fave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

                JSONArray jsonArray2 = null;
                try {
                    jsonArray2 = new JSONArray(prefs.getString("key", "[]"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonArray2.put(story.getTitle());
                jsonArray2.put(story.getLocation());
                jsonArray2.put(story.getResource());

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("key", jsonArray2.toString());

                System.out.println(jsonArray2.toString());
                editor.commit();

                Toast.makeText(view.getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
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