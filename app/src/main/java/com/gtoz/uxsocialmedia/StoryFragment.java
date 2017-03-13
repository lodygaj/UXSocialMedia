package com.gtoz.uxsocialmedia;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

public class StoryFragment extends Fragment {
    private Story story;
    private MediaController mediaController;
    private VideoView video;
    private FrameLayout videoFrame;
    private int position = 0;
    private Context context;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_story, container, false);

        context = getActivity().getApplicationContext();
        final DBHelper dbHelper = new DBHelper(context);
        fm = getFragmentManager();

        // Set header resource
        ImageView image = (ImageView) view.findViewById(R.id.resource);
        int id = context.getResources().getIdentifier("drawable/" + story.getResource(), null, context.getPackageName());
        image.setImageResource(id);
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

        String uriPath = "android.resource://com.gtoz.uxsocialmedia/raw/" + story.getResource();
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
        if(story.getResourceType().equals("image")) {
            int imageId = context.getResources().getIdentifier("drawable/" + story.getResource(), null, context.getPackageName());
            image.setImageResource(imageId);
            videoFrame.setVisibility(View.GONE);
        }
        // Set video and hide image view
        else {
            image.setVisibility(View.GONE);
        }

        // Set title text
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(story.getTitle());
        // Apply font to title text
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-DmCpIt.otf");
        title.setTypeface(font);

        // Set category text
        TextView category = (TextView) view.findViewById(R.id.category);
        category.setText(story.getCategory());
        category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GridFragment gridFragment = new GridFragment();
                gridFragment.setStories(dbHelper.getStoriesByCategory(story.getCategory()));
                setFragment(gridFragment);
            }
        });

        // Setup location button
        Button location = (Button) view.findViewById(R.id.locationButton);
        location.setText(story.getLocation());
        location.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                GridFragment gridFragment = new GridFragment();
                gridFragment.setStories(dbHelper.getStoriesByLocation(story.getLocation()));
                setFragment(gridFragment);
            }
        });

        // Setup share button
        Button share = (Button) view.findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(view.getContext(), "Clicked on Share" , Toast.LENGTH_SHORT).show();
            }
        });

        // Set # of likes
        TextView likes = (TextView) view.findViewById(R.id.likes);
        likes.setText(Integer.toString(story.getLikes()));

        // Set if story is liked or not
        final ImageView likeButton = (ImageView) view.findViewById(R.id.likeButton);
        if(dbHelper.isFavorite(story.getId())) {
            likeButton.setImageResource(R.drawable.like_selected);
        }

        // Handles the Like Button
        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(dbHelper.isFavorite(story.getId())) {
                    // Update likes
                    TextView tv = (TextView) view.findViewById(R.id.likes);
                    tv.setText(Integer.toString(story.getLikes() - 1));
                    // Set like image
                    likeButton.setImageResource(R.drawable.like_unselected);

                    // Update story in database
                    story.setLikes(story.getLikes() - 1);
                    dbHelper.editStory(story);

                    // Delete story from favorites
                    dbHelper.deleteFavorite(story.getId());
                } else {
                    // Update likes
                    TextView tv = (TextView) view.findViewById(R.id.likes);
                    tv.setText(Integer.toString(story.getLikes() + 1));
                    // Set like image
                    likeButton.setImageResource(R.drawable.like_selected);

                    // Update story in database
                    story.setLikes(story.getLikes() + 1);
                    dbHelper.editStory(story);

                    // Add story to favorites
                    dbHelper.addFavorite(story.getId());
                }
            }
        });

        // Handles the Make Reservation Button
        Button btnReservation = (Button) view.findViewById(R.id.btnReservation2);
        btnReservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebsiteFragment fragment = new WebsiteFragment();
                setFragment(fragment);
                fragment.setUrl("http://www.thrifty.com");
//                Uri uri = Uri.parse("http://www.thrifty.com");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
            }
        });


        // Set caption text
        TextView text = (TextView) view.findViewById(R.id.caption);
        text.setText(story.getCaption());

        // Apply font to caption text
        Typeface franklinGothicStd = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ITCFranklinGothicStd-Book.otf");
        text.setTypeface(franklinGothicStd);

        return view;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        fm.beginTransaction().replace(R.id.fl_content, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }
}