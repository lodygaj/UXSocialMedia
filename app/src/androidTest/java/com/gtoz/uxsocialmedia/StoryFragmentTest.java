package com.gtoz.uxsocialmedia;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import android.widget.VideoView;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;

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

        // Verify that the thrifty web site is displayed
        onWebView().check(webMatches(getCurrentUrl(), containsString("https://m.thrifty.com")));

    }

    // Toggle the favorite button test and insure proper response
    @Test
    public void testFavoriteButton() {

        //Geting the TextView that shows the number of likes
        TextView image = (TextView) mActivityRule.getActivity().findViewById(R.id.likes);

        for (int i = 0; i < 2; i++){
            //Getting the # of likes before button click
            String beforeClick = image.getText().toString();

            //Clicking the button
            onView(withId(R.id.likeButton)).perform(click());

            //Getting the # of likes after the button click
            String afterClick = image.getText().toString();

            //Asserting that there was a change in the # of likes
            assertNotSame(beforeClick, afterClick);
        }

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
        onView(withId(R.id.locationButton)).perform(click());

        //Checking to make sure user is on new gridfragment screen
        onView(withId(R.id.staggered_grid)).check(matches((isCompletelyDisplayed())));
    }


}
