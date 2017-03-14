package com.gtoz.uxsocialmedia;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by brendansaage on 3/12/17.
 */

public class GridFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

        //loading Discovery Fragment
        Matcher<View> matcher = allOf(withText("Discovery"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
    }

    //Testing to click from Discovery tab, on Category, and load the Grid Apater view
    @Test
    public void clickingCategoryButton() {

        onView(withId(R.id.rv3))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.staggered_grid)).check(matches((isCompletelyDisplayed())));

        //Testing to click on item in the grid adapter and move into the story screen
        onView(withId(R.id.staggered_grid))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.shareButton)).check(matches(isCompletelyDisplayed()));

    }
}

