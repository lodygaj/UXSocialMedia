package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/4/2016.
 */

public class Story {
    private int id;
    private String title;
    private String location;
    private String category;
    private String caption;
    private int likes;
    private String resType;
    private String resource;
    private String storyType;

    // Null constructor
    public Story() {}
    public Story(int id, String title, String location, String category,
                 String caption, int likes, String resType, String resource, String storyType) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.category = category;
        this.caption = caption;
        this.likes = likes;
        this.resType = resType;
        this.resource = resource;
        this.storyType = storyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceType() {
        return resType;
    }

    public void setResourceType(String resourceType) {
        this.resType = resourceType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String text) {
        this.caption = text;
    }

    public String getStoryType() {
        return storyType;
    }

    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }
}