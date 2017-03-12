package com.gtoz.uxsocialmedia;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.VideoView;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasToString;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class FavoritesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        // Locate favorites tab
        Matcher<View> matcher = allOf(withText("Favorites"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
        // Verify that fragment has loaded by checking that listview is displayed
        onView(withId(R.id.locationList)).check(matches(isCompletelyDisplayed()));
    }


    @Test
    public void selectItemTest() {

        onData(anything()).inAdapterView(withId(R.id.locationList)).atPosition(1).perform(click());
        onView(withId(R.id.shareButton)).check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void longClickToDeleteItemTest() {

        onData(anything()).inAdapterView(withId(R.id.locationList)).atPosition(1).perform(longClick());
        onView(withText("Delete")).check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void deleteItemTest(){

        onData(anything()).inAdapterView(withId(R.id.locationList)).atPosition(1).perform(longClick());
        onView(withText("Delete")).inRoot(isPlatformPopup()).perform(click());
        onView(withText("Kevin Hart")).check(doesNotExist());

    }


}
