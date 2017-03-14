package com.gtoz.uxsocialmedia;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Created by GtoZ on 3/10/2017.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private DBHelper dbHelper;
    private Story testStory;

    //Story Parameters
    private int storyID = 31;
    private String storyTitle = "Sample Title";
    private String storyLocation = "Fort Myers";
    private String storyCategory = "Biking";
    private String storyCaption = "Caption";
    private int storyLikes = 32;
    private String storyResType = "ResType";
    private String storyResource = "Resource";
    private String storyStoryType = "Fun";

    // Consider using @BeforeClass to connect to the database. Android documentation says that it's
    // Best to use this annotation because it will only run once prior to the class, and is useful
    // For expensive tasks like connecting to a database. @Before runs before every test method
    // You could use @AfterClass for the database, and use @After to redo the story after every test
    // Not going to mess with your code because it's already working and you worked hard on it

    @Before
    public void setUp() {
        dbHelper = new DBHelper(InstrumentationRegistry.getTargetContext());
        testStory = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);
    }

    @After
    public void tearDown() {
        dbHelper = null;
        testStory = null;
    }

    @Test
    public void addStoryTest() {
        int initialCount = dbHelper.getNumberOfStories();
        dbHelper.addStory(testStory);
        int newCount = dbHelper.getNumberOfStories();
        Assert.assertEquals(++initialCount, newCount);
    }

    // Test for Story set methods
    @Test
    public void setStoryTest() {
        // Make a new story
        Story newStory = new Story();

        // Set temp values to new story
        newStory.setCaption(testStory.getCaption());
        newStory.setCategory(testStory.getCategory());
        newStory.setLikes(testStory.getLikes());
        newStory.setLocation(testStory.getLocation());
        newStory.setResource(testStory.getResource());
        newStory.setResourceType(testStory.getResourceType());
        newStory.setTitle(testStory.getTitle());
        newStory.setStoryType(testStory.getStoryType());
        newStory.setId(testStory.getId());

        Assert.assertEquals (newStory.getCaption(), testStory.getCaption());
        Assert.assertEquals (newStory.getCategory(), testStory.getCategory());
        Assert.assertEquals (newStory.getLikes(), testStory.getLikes());
        Assert.assertEquals (newStory.getLocation(), testStory.getLocation());
        Assert.assertEquals (newStory.getResource(), testStory.getResource());
        Assert.assertEquals (newStory.getResourceType(), testStory.getResourceType());
        Assert.assertEquals (newStory.getTitle(), testStory.getTitle());
        Assert.assertEquals (newStory.getStoryType(), testStory.getStoryType());
        Assert.assertEquals (newStory.getId(), testStory.getId());
    }

    @Test
    public void getStoriesByTypeTest() {
        // Get array of stories with type "Thrifty"
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getStoriesByType("thrifty");
        // Verify that the first story in array is from the "Thrifty category
        Assert.assertEquals("thrifty", stories.get(0).getStoryType());
    }

    @Test
    public void getStoriesByCategoryTest() {
        // Get array of stories with category "Animal"
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getStoriesByCategory("Animal");
        // Verify that the first story in array is from the "Animal" category
        Assert.assertEquals("Animal", stories.get(0).getCategory());
    }

    @Test
    public void getStoriesByLocationTest() {
        // Get array of stories with location "Aspen, CO"
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getStoriesByLocation("Aspen, CO");
        // Verify that the first story in array is from the "Aspen, CO" location
        Assert.assertEquals("Aspen, CO", stories.get(0).getLocation());
    }

    @Test
    public void getStoriesByQrTest() {
        // Get array of stories with qr code "123456"
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getStoriesByQr(123456);
        // Verify that the first story in array is from the "123456" qr
        Assert.assertEquals(2, stories.get(0).getId());
    }

    @Test
    public void getFavoriteStoriesTest() {
        // Get array of favorite stories
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getFavoriteStories();
        // Verify that the first story in array is story id 1
        Assert.assertEquals(1, stories.get(0).getId());
    }

    @Test
    public void getCategoriesTest() {
        // Get array of categories
        ArrayList<String> categories = new ArrayList<>();
        categories = dbHelper.getCategories();
        // Verify that the first category in array is "Adventure"
        Assert.assertEquals("Adventure", categories.get(0));
    }

    @Test
    public void addFavoriteTest() {
        // Add story id 1 to favorites
        dbHelper.addFavorite(1);
        // Get array of favorite stories
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getFavoriteStories();
        // Verify that the first story is now 1
        Assert.assertEquals(1, stories.get(0).getId());
    }

    @Test
    public void deleteFavoriteTest() {
        // Delete story id 1 from favorites
        dbHelper.addFavorite(3);
        dbHelper.deleteFavorite(1);
        // Get array of favorite stories
        ArrayList<Story> stories = new ArrayList<>();
        stories = dbHelper.getFavoriteStories();
        // Verify that the first story is now 3
        Assert.assertEquals(3, stories.get(0).getId());
    }

    @Test
    public void isFavoriteTrueTest() {
        dbHelper.addFavorite(1);
        Assert.assertTrue(dbHelper.isFavorite(1));
    }

    @Test
    public void isFavoriteFalseTest() {
        Assert.assertFalse(dbHelper.isFavorite(15));
    }

    @Test
    public void isValidQrTrueTest() {
        Assert.assertTrue(dbHelper.isValidQr(123456));
    }

    @Test
    public void isValidQrFalseTest() {
        Assert.assertFalse(dbHelper.isValidQr(1234567890));
    }
}