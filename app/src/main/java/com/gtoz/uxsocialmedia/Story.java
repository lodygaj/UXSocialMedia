package com.gtoz.uxsocialmedia;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by GtoZ on 11/4/2016.
 */

public class Story {
    private String title;
    private String location;
    private String website;
    private String type;
    private int image;



    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public String getLocation() {
        return location;
    }

    public int getImage() { return image; }

    public void setImage(int image) {
        this.image = image;
    }


}