package com.gtoz.uxsocialmedia;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by GtoZ on 2/17/2017.
 */

public class StoryTest {

    //Story Parameters
    private int storyID = 3;
    private String storyTitle = "Sample Title";
    private String storyLocation = "Fort Myers";
    private String storyCategory = "Biking";
    private String storyCaption = "Caption";
    private int storyLikes = 32;
    private String storyResType = "ResType";
    private String storyResource = "Resource";
    private String storyStoryType = "Fun";


    @Test
    public void getIdTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals(3, test.getId());

    }

    @Test
    public void setIdTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setId(4);

        assertEquals(4, test.getId());

    }

    @Test
    public void getTitleTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Sample Title", test.getTitle());

    }

    @Test
    public void setTitleTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setTitle("New Title");

        assertEquals("New Title", test.getTitle());

    }

    @Test
    public void getLocationTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Fort Myers", test.getLocation());

    }

    @Test
    public void setLocationTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setLocation("Naples");

        assertEquals("Naples", test.getLocation());

    }

    @Test
    public void getLikesTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals(32, test.getLikes());

    }

    @Test
    public void setLikesTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setLikes(54);

        assertEquals(54, test.getLikes());

    }

    @Test
    public void getResourceTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Resource", test.getResource());

    }

    @Test
    public void setResourceTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setResource("New Resource");

        assertEquals("New Resource", test.getResource());

    }

    @Test
    public void getResourceTypeTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("ResType", test.getResourceType());

    }

    @Test
    public void setResourceTypeTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setResourceType("New ResType");

        assertEquals("New ResType", test.getResourceType());

    }

    @Test
    public void getCategoryTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Biking", test.getCategory());

    }

    @Test
    public void setCategoryTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setCategory("Fishing");

        assertEquals("Fishing", test.getCategory());

    }

    @Test
    public void getCaptionTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Caption", test.getCaption());

    }

    @Test
    public void setCaptionTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setCaption("New Caption");

        assertEquals("New Caption", test.getCaption());

    }

    @Test
    public void getStoryTypeTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        assertEquals("Fun", test.getStoryType());

    }

    @Test
    public void setStoryTypeTest() throws Exception {
        Story test = new Story(storyID, storyTitle, storyLocation, storyCategory, storyCaption, storyLikes,
                storyResType, storyResource, storyStoryType);

        test.setStoryType("Boring");

        assertEquals("Boring", test.getStoryType());

    }
}