package com.gtoz.uxsocialmedia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static com.gtoz.uxsocialmedia.R.id.webView;
import static junit.framework.Assert.assertTrue;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.webkit.WebView;
import android.widget.VideoView;

/**
 * Test class for Story Fragment methodology
 * Created by GtoZ on 3/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StoryFragmentTest {
    private static boolean setUp = false;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

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

    // Test that reservation button loads webview of Thrifty website
    @Test
    public void testMakeReservationButton() {
        // Click reservation button

        onView(allOf(withId(R.id.btnReservation), isDisplayed())).perform(click());
        // Verify that website fragment is loaded
        WebView web = (WebView) mActivityRule.getActivity().findViewById(R.id.webView);
        Assert.assertTrue(web.getUrl().equals("http://www.thrifty.com"));
        //onView(withId(webView)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testLocationButton() {
        // Click location button
        onView(withId(R.id.locationButton)).perform(click());
        // Verify that grid fragment was loaded
        onView(withId(R.id.staggered_grid)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testCategoryButton() {
        // Click category button
        onView(withId(R.id.category)).perform(click());
        // Verify that grid fragment was loaded
        onView(withId(R.id.staggered_grid)).check(matches(isCompletelyDisplayed()));
    }

//    @Test
//    public void testLikeButton() {
//        // Click like button
//        onView(withId(R.id.likeButton)).perform(click());
//
//    }
}
