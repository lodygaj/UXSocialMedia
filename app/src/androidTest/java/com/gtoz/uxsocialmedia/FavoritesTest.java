package com.gtoz.uxsocialmedia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by GtoZ on 3/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class FavoritesTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFavoriteFragmentLoad() {

    }
}
