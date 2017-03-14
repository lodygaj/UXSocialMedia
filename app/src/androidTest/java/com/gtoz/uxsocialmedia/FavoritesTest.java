package com.gtoz.uxsocialmedia;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class FavoritesTest {
    private DBHelper dbHelper;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        dbHelper = new DBHelper(InstrumentationRegistry.getTargetContext());
        // Locate favorites tab
        Matcher<View> matcher = allOf(withText("Favorites"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
    }

    // Tap the favorites tab again to insure proper behavior
    @Test
    public void loadFavoritesAgainTest () {
        // Locate favorites tab
        Matcher<View> matcher = allOf(withText("Favorites"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher)
                .perform(click())
                .check(matches(isCompletelyDisplayed()));

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
