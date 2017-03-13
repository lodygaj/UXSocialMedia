package com.gtoz.uxsocialmedia;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import junit.framework.Assert;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class DiscoveryTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testDiscoveryFragmentLoad() {
        // Locate discovery tab
        Matcher<View> matcher = allOf(withText("Discovery"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
        // Verify that fragment has loaded by checking that title is displayed
        onView(withText("THRIFTY STORIES")).check(matches(isCompletelyDisplayed()));
    }


    //Tesitng the navigation drawer opens correctly
    @Test
    public void navigationDrawerTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText("Home")).check(matches(isCompletelyDisplayed()));
    }

    //Testing pressing back on navigation drawer
    @Test
    public void backPressOnNavigationTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        pressBack();

        onView(withText("THRIFTY STORIES")).check(matches(isCompletelyDisplayed()));
    }

    //Testing pressing Home on navigation drawer
    @Test
    public void homePressOnNavigationTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText("Home")).perform(click());

        onView(withText("THRIFTY STORIES")).check(matches(isCompletelyDisplayed()));
    }

    //Testing pressing My Locations on navigation drawer
    @Test
    public void myLoactionPressOnNavigationTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText("My Saved Locations")).perform(click());

        onView(withId(R.id.locationList)).check(matches(isCompletelyDisplayed()));
    }

    //Testing pressing Link Acccounts on navigation drawer
    @Test
    public void linkAccountsPressOnNavigationTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText("Link Accounts")).perform(click());

        onView(withText("Link Accounts"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    //Testing pressing Update Interests on navigation drawer
    @Test
    public void updateInterestsPressOnNavigationTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withText("Update Interests")).perform(click());

        onView(withText("Clicked Update Interests"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

}
