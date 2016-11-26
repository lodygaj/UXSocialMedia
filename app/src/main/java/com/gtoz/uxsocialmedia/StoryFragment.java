package com.gtoz.uxsocialmedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.VideoView;

import static com.gtoz.uxsocialmedia.R.id.videoView;

public class StoryFragment extends Fragment {
    private Story story;
    private MediaController mediaController;
    private VideoView video;
    private FrameLayout videoFrame;
    private int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_story, container, false);

        // Set header resource
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        video = (VideoView) view.findViewById(videoView);
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
        final Button likes = (Button) view.findViewById(R.id.likeButton);
        likes.setText(Integer.toString(story.getLikes()));
        //Handles the Like Button
        likes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                story.setLikes(story.getLikes() + 1);
                likes.setText(Integer.toString(story.getLikes()));
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