package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/4/2016.
 */

public class Story {
    private String title;
    private String location;
    private String category;
    private String caption;
    private int likes;
    private String type;
    private String resource;

    public Story(String title, String location, String category,
                 String caption, int likes, String type, String resource) {
        this.title = title;
        this.location = location;
        this.type = type;
        this.category = category;
        this.caption = caption;
        this.resource = resource;
        this.likes = likes;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}