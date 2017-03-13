package com.gtoz.uxsocialmedia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.design.widget.CoordinatorLayout.Behavior.getTag;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.not;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;

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
                    .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
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

        // Verify that website fragment was loaded
        onView(withId(R.id.webView)).check(matches(isCompletelyDisplayed()));
    }

    // Test the favorite button
    @Test
    public void testFavorite() {

        //Geting the TextView that shows the number of likes
        TextView image = (TextView) mActivityRule.getActivity().findViewById(R.id.likes);

        //Getting the # of likes before button click
        String beforeClick = image.toString();

        //Clicking the button
        onView(withId(R.id.likeButton)).perform(click());

        //Getting the # of likes after the button click
        String afterClick = image.toString();

        //Asserting that there was a change in the # of likes
        assertNotSame(beforeClick, afterClick);

    }

    // Test the Share button that it was actually clicked
    @Test
    public void testShareButton() {
        //Clicking the submit button and checking for correct toast pop
        onView(withId(R.id.shareButton)).perform(click());
        onView(withText("Clicked on Share"))
                .inRoot(withDecorView(Matchers.not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    // Test the Location button that it was actually clicked and user navigated to right area
    @Test
    public void testLocationButton() {
        //Clicking the submit button and checking for correct toast pop
        onView(withId(R.id.locationButton)).perform(scrollTo(), click());

        //Checking to make sure user is on new gridfragment screen
        onView(withId(R.id.staggered_grid)).check(matches((isCompletelyDisplayed())));
    }


}
