package com.gtoz.uxsocialmedia;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

public class DetailFragment extends Fragment {
    boolean categoryShown = true;
    LinearLayout catLayout;
    ScrollView contentLayout;
    Context context;
    FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        contentLayout = (ScrollView) view.findViewById(R.id.scrollView);
        catLayout = (LinearLayout) view.findViewById(R.id.categoryView);
        context = getActivity().getApplicationContext();
        fm = getFragmentManager();

        // Set image header
        ImageView imageTest = (ImageView) view.findViewById(R.id.imgHeader);
        imageTest.setImageResource(R.drawable.flyboard);

        // Set content text
        TextView textTest = (TextView) view.findViewById(R.id.txtContent);
        textTest.setText(R.string.this_is_sample_text);

        // Handle image button
        ImageButton imageBtn = (ImageButton) view.findViewById(R.id.imageTest2);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoryShown) {
                    catLayout.setVisibility(View.GONE);
                    categoryShown = false;
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                            RecyclerView.LayoutParams.MATCH_PARENT, 0.0f);
                    lp.weight = 98.0f;
                    contentLayout.setLayoutParams(lp);
                    lp.weight = 0.0f;
                    catLayout.setLayoutParams(lp);
                }
                else {
                    catLayout.setVisibility(View.VISIBLE);
                    categoryShown = true;
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                            RecyclerView.LayoutParams.MATCH_PARENT, 0.0f);
                    lp.weight = 78.0f;
                    contentLayout.setLayoutParams(lp);
                    lp.weight = 20.0f;
                    catLayout.setLayoutParams(lp);
                }
            }
        });

        // Set up video view
//        VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
//        String videoToPlay = "http://bffmedia.com/bigbunny.mp4";
//        Uri video = Uri.parse(videoToPlay);
//        videoView.setVideoURI(video);
//        videoView.start();
//        <VideoView
//        android:layout_width="fill_parent"
//        android:layout_height="fill_parent"
//        android:id="@+id/videoView"
//        android:layout_gravity="center"/>

        // Set up category horizontal list view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.category_grid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoryListAdapter adapter = new CategoryListAdapter(context, fm);
        recyclerView.setAdapter(adapter);
        ListSpacing dec = new ListSpacing(16);
        recyclerView.addItemDecoration(dec);

        return view;
    }
}