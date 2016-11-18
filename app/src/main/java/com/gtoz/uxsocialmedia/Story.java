package com.gtoz.uxsocialmedia;

/**
 * Created by GtoZ on 11/4/2016.
 */

public class Story {
    private String title;
    private String location;
    private String website;
    private String type;
    private String category;
    private String text;
    private int resource;
    private int likes;

    public Story(String title, String location, String website, String type, String category,
                 String text, int resource, int likes) {
        this.title = title;
        this.location = location;
        this.website = website;
        this.type = type;
        this.category = category;
        this.text = text;
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

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}