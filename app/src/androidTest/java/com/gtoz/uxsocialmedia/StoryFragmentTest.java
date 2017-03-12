package com.gtoz.uxsocialmedia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.contrib.RecyclerViewActions;

/**
 * Created by GtoZ on 3/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StoryFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // Click first story in "Thrifty list" to display title
        onView(withId(R.id.rv1))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Click again to open story fragment
        onView(withId(R.id.rv1))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testLoadStoryFragment() {
        // Verify that fragment has loaded by checking that the video view is displayed
        onView(withId(R.id.videoView)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testMakeReservationButton() {
        // Click reservation button
        onView(withId(R.id.btnReservation)).perform(click());
        // Verify that website fragment is loaded
        onView(withId(R.id.webView)).check(matches(isCompletelyDisplayed()));
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
