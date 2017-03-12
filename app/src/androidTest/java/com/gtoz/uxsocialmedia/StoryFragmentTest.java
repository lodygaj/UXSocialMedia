package com.gtoz.uxsocialmedia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.widget.VideoView;

/**
 * Test class for Story Fragment methodology 
 * Created by Joey Laptop on 3/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StoryFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    // Test that the Story Fragment loads appropriately
    @Test
    public void testLoadStoryFragment() {
        // Click first story in "Thrifty list" to display title
        // And again to open story fragment
        for (int i = 0 ; i < 2 ; i++) {
            onView(withId(R.id.rv1))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
        // Click again to open story fragment
        //onView(withId(R.id.rv1))
                //.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Verify that fragment has loaded by checking that the video view is displayed
        onView(withId(R.id.videoView)).check(matches(isCompletelyDisplayed()));
    }

    // Test that video is playing when the story is loaded
    @Test
    public void testVideo() {
        // Click first story in "Thrifty list" to display title
        // And again to open story fragment
        for (int i = 0 ; i < 2 ; i++) {
            onView(withId(R.id.rv1))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }

        // Verify that the video player is playing
        VideoView vidView = (VideoView) mActivityRule.getActivity().findViewById(R.id.videoView);
        assertTrue (vidView.isPlaying());
    }

}
