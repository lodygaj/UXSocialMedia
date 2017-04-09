package com.gtoz.uxsocialmedia;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

/**
 * Created by GtoZ on 4/9/2017.
 */

public class DatabaseMockTest {
    private DBHelper dbHelper;
    private Story testStory;

    @Before
    public void setup() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DBHelper.class);

        // Create test story
        testStory = new Story(1,"Dwayne The Rock Johnson Q&A","Los Angeles, CA","Entertainment",
                "Q&A with Dwayne The Rock Johnson",1744,"video","dwaynetherockjohnsonqanda","thrifty");
    }

    @Test
    public void testGetStoriesByType() {
        // Create array of stories with type "thrifty"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByType()
        Mockito.when(dbHelper.getStoriesByType("thrifty")).thenReturn(stories);
        // Verify that stories are of correct type
        Assert.assertEquals(dbHelper.getStoriesByType("thrifty"), stories);
    }

    @Test
    public void testGetStoriesByCategory() {
        // Create array of stories with category "Entertainment"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByCategory()
        Mockito.when(dbHelper.getStoriesByCategory("Entertainment")).thenReturn(stories);
        // Verify that stories are of correct type
        Assert.assertEquals(dbHelper.getStoriesByType("Entertainment"), stories);
    }


}
