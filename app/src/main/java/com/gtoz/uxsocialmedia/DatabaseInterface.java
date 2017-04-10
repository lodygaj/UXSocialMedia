package com.gtoz.uxsocialmedia;

import java.util.ArrayList;

/**
 * Created by Joey Laptop on 4/10/2017.
 */

public interface DatabaseInterface {
    public ArrayList<Story> getStoriesByCategory(String category);
    public ArrayList<Story> getStoriesByQr(int qr);
    public ArrayList<Story> getStoriesByLocation(String location);
    public ArrayList<Story> getStoriesByType(String type);
    public ArrayList<String> getCategories();
    public Boolean addFavorite(int id);
    public Boolean deleteFavorite(int id);
    public Boolean addStory(Story story);
}
