package com.gtoz.uxsocialmedia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class QrReaderFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    // Navigate to QR code tab
    @Before
    public void setUp() {
        // Locate Qr tab
        Matcher<View> matcher = allOf(withText("Qr"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
    }

    // Test that QR fragment loads properly even when selected twice
    @Test
    public void testQrFragmentLoad() {
        // Locate Qr tab
        Matcher<View> matcher = allOf(withText("Qr"),
                isDescendantOfA(withId(R.id.tabs)));
        // Click tab
        onView(matcher).perform(click());
        // Verify that fragment has loaded
        onView(isAssignableFrom(ZXingScannerView.class)).check(matches(isCompletelyDisplayed()));
    }
}
