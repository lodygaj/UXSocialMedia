package com.gtoz.uxsocialmedia;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.R.attr.fragment;
import static android.R.attr.targetActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
        //mActivityRule.getActivity().setFragment(new CreateStoryFragment());
    }

    @Test
    public void testLoadCreateStoryFragment() {




    }

    @Test
    public void newStoryTest() {
        // Fill in sample story
        //onView(withId(R.id.edtTxtTitle)).perform(typeText("New Story"), closeSoftKeyboard());
        //onView(withId(R.id.edtTxtLocation)).perform(typeText("Fort Myers"), closeSoftKeyboard());
        //onView(withId(R.id.edtTxtCaption)).perform(typeText("This is a new story."), closeSoftKeyboard());
        //onView(withId(R.id.submitButton)).perform(click());

        //Assert.assertEquals();
    }



}
