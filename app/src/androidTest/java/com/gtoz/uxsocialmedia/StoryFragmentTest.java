package com.gtoz.uxsocialmedia;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.VideoView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Test class for Story Fragment methodology
 * Created by Joey Laptop on 3/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StoryFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    // Navitate to Story fragment prior to test case
    @Before
    public void setUp() {
        // Click first story in "Thrifty list" to display title
        // And again to open story fragment
        for (int i = 0 ; i < 2 ; i++) {
            onView(withId(R.id.rv1))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }

    // Test that the Story Fragment loads appropriately
    @Test
    public void testLoadStoryFragment() {
        // Verify that fragment has loaded by checking that the video view is displayed
        onView(withId(R.id.videoView)).check(matches(isCompletelyDisplayed()));
    }

    // Test that video is playing when the story is loaded
    @Test
    public void testVideo() {
        // Verify that the video player is playing
        VideoView vidView = (VideoView) mActivityRule.getActivity().findViewById(R.id.videoView);
        assertTrue (vidView.isPlaying());
    }

    // Test the Make Reservation button leads to the right location
    @Test
    public void testMakeReservation() {
        // Click on the Make Reservation Button
        onView(withId(R.id.btnReservation2))
                .perform(click());

        // Verify necessary intent
        intended(hasAction(Intent.ACTION_VIEW));
    }

}
