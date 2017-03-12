package com.gtoz.uxsocialmedia;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CreateStoryTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        //DBHelper dbHelper = new DBHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        // Locate Create Story tab
        Matcher<View> matcher = allOf(withText("Create"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
        // Verify that fragment has loaded by checking that title is displayed
        onView(withText("Create a Story")).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void titleTextTest(){

        //Placing text into the Title field and checking for accuracy
        onView(withId(R.id.edtTxtTitle)).perform(typeText("New Story"), closeSoftKeyboard());
        onView(withId(R.id.edtTxtTitle)).check(matches(withText("New Story")));

        //Choosing field on the spinner and checking for accuracy
        onView(withId(R.id.categorySpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.categorySpinner)).check(matches(withSpinnerText(containsString("Sports"))));

        //Placing text into the Locaiton field and checking for accuracy
        onView(withId(R.id.edtTxtLocation)).perform(typeText("Fort Myers"), closeSoftKeyboard());
        onView(withId(R.id.edtTxtLocation)).check(matches(withText("Fort Myers")));

        //Placing text into the Caption field and checking for accuracy
        onView(withId(R.id.edtTxtCaption)).perform(typeText("This is a new story."), closeSoftKeyboard());
        onView(withId(R.id.edtTxtCaption)).check(matches(withText("This is a new story.")));

        //Clicking the submit button and checking for correct toast pop
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("Story Submitted"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        //Clicking the cancel button and checking that user is navigated back to discoveryfragment
        onView(withId(R.id.cancelButton)).perform(click());
        onView(withText("THRIFTY STORIES")).check(matches(isCompletelyDisplayed()));
    }


}

