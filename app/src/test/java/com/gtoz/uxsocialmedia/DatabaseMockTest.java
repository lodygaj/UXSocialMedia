package com.gtoz.uxsocialmedia;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

/**
 * Created by GtoZ on 4/9/2017.
 */

public class DatabaseMockTest {
    private DatabaseInterface dbHelper;
    private Story testStory;

    @Before
    public void setup() {
        // Create test story
        testStory = new Story(1,"Dwayne The Rock Johnson Q&A","Los Angeles, CA","Entertainment",
                "Q&A with Dwayne The Rock Johnson",1744,"video","dwaynetherockjohnsonqanda","thrifty");
    }

    @Test
    public void getStoriesByCategoryTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Create array of stories with category "Entertainment"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByCategory()
        Mockito.when(dbHelper.getStoriesByCategory("Entertainment")).thenReturn(stories);
        // Verify that stories are of correct category
        Assert.assertEquals(dbHelper.getStoriesByCategory("Entertainment").get(0).getCategory(), stories.get(0).getCategory());
    }

    @Test
    public void getStoriesByQrTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Create array of stories with QR code "123456"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByQR()
        Mockito.when(dbHelper.getStoriesByQr(123456)).thenReturn(stories);
        // Verify that it returns list of stories
        Assert.assertEquals(dbHelper.getStoriesByQr(123456), stories);
    }

    @Test
    public void addStoryPassTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Mock behavior of addStory()
        Mockito.when(dbHelper.addStory(testStory)).thenReturn(true);
        // Verify that it story was submitted
        Assert.assertTrue(dbHelper.addStory(testStory));
    }

    @Test
    public void addStoryFailTest(){
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Mock behavior of addStory()
        Mockito.when(dbHelper.addStory(testStory)).thenReturn(false);
        // Verify that it story was submitted
        Assert.assertFalse(dbHelper.addStory(testStory));
    }

    @Test
    public void addFavoriteTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Mock behavior of addFavorite()
        Mockito.when(dbHelper.addFavorite(1)).thenReturn(true);
        // Verify that it story was added to favorites
        Assert.assertTrue(dbHelper.addFavorite(1));
    }

    @Test
    public void getStoriesByTypeTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Create array of stories with type "thrifty"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByType()
        Mockito.when(dbHelper.getStoriesByType("thrifty")).thenReturn(stories);
        // Verify that stories are of correct type
        Assert.assertEquals(dbHelper.getStoriesByType("thrifty").get(0).getStoryType(), stories.get(0).getStoryType());
    }

    @Test
    public void deleteFavoriteTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Mock behavior of deleteFavorite()
        Mockito.when(dbHelper.deleteFavorite(1)).thenReturn(true);
        // Verify that it story was added to favorites
        Assert.assertTrue(dbHelper.deleteFavorite(1));
    }


    @Test
    public void getCategoriesTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Create array of categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Adventure");
        // Mock behavior of getCategories()
        Mockito.when(dbHelper.getCategories()).thenReturn(categories);
        // Verify that the first category in array is "Adventure"
        Assert.assertEquals(dbHelper.getCategories().get(0), categories.get(0));
    }

    @Test
    public void getStoriesByLocationTest() {
        // Mock DBHelper class
        dbHelper = Mockito.mock(DatabaseInterface.class);
        // Create array of stories with location "Los Angeles, CA"
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        // Mock behavior of getStoriesByLocation()
        Mockito.when(dbHelper.getStoriesByLocation("Los Angeles, CA")).thenReturn(stories);
        // Verify that stories are of correct location
        Assert.assertEquals(dbHelper.getStoriesByLocation("Los Angeles, CA").get(0).getLocation(), stories.get(0).getLocation());
    }


}
