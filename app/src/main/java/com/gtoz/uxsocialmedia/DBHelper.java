package com.gtoz.uxsocialmedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.description;
import static android.R.attr.id;
import static android.media.tv.TvContract.Channels.COLUMN_TYPE;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "UXandSocial.db";

    // Table USERS with columns
    private static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Table STORIES with columns
    private static final String TABLE_STORIES = "stories";
    private static final String COLUMN_STORY_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_CAPTION = "caption";
    private static final String COLUMN_LIKES = "likes";
    private static final String COLUMN_RES_TYPE = "res_type";
    private static final String COLUMN_RESOURCE = "resource";
    private static final String COLUMN_STORY_TYPE = "story_type";

    // Table QR with columns
    private static final String TABLE_QR = "qr";
    private static final String COLUMN_QR_ID = "_id";
    private static final String COLUMN_QR = "qr";

    // Table FAVORITES with columns
    private static final String TABLE_FAVORITES = "favorites";
    public static final String COLUMN_FAVORITE_ID = "_id";
    public static final String COLUMN_FAVORITE_STORY_ID = "story_id";

    // Table CATEGORIES with columns
    private static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORY_ID = "_id";
    public static final String COLUMN_CATEGORY_CATEGORY = "category";

    // Table VEHICLE_STORIES with columns
    private static final String TABLE_VEHICLE_STORIES = "vehicle_stories";
    public static final String COLUMN_VEHICLE_STORY_ID = "_id";
    public static final String COLUMN_VEHICLE_STORY_QR = "qr_id";
    public static final String COLUMN_VEHICLE_STORY_STORY = "story_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " STRING, " +
                COLUMN_PASSWORD + " STRING )";
        db.execSQL(CREATE_USERS_TABLE);

        // Create Stories table
        String CREATE_STORIES_TABLE = "CREATE TABLE " +
                TABLE_STORIES + "(" +
                COLUMN_STORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " STRING NOT NULL, " +
                COLUMN_LOCATION + " STRING NOT NULL, " +
                COLUMN_CATEGORY + " STRING NOT NULL, " +
                COLUMN_CAPTION + " STRING NOT NULL, " +
                COLUMN_LIKES + " INTEGER NOT NULL, " +
                COLUMN_RES_TYPE + " STRING NOT NULL, " +
                COLUMN_RESOURCE + " INTEGER NOT NULL, " +
                COLUMN_STORY_TYPE + " STRING NOT NULL )";
        db.execSQL(CREATE_STORIES_TABLE);

        // Create QR table
        String CREATE_QR_TABLE = "CREATE TABLE " +
                TABLE_QR + "(" +
                COLUMN_QR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QR + " INTEGER NOT NULL )";
        db.execSQL(CREATE_QR_TABLE);

        // Create Favorites table
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " +
                TABLE_FAVORITES + "(" +
                COLUMN_FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FAVORITE_STORY_ID + " INTEGER NOT NULL )";
        db.execSQL(CREATE_FAVORITES_TABLE);

        // Create Category table
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                TABLE_CATEGORIES + "(" +
                COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY_CATEGORY + " STRING NOT NULL )";
        db.execSQL(CREATE_CATEGORIES_TABLE);

        // Create Vehicle Stories table
        String CREATE_VEHICLE_STORIES_TABLE = "CREATE TABLE " +
                TABLE_VEHICLE_STORIES + "(" +
                COLUMN_VEHICLE_STORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VEHICLE_STORY_QR + " INTEGER NOT NULL, " +
                COLUMN_VEHICLE_STORY_STORY + " INTEGER NOT NULL )";
        db.execSQL(CREATE_VEHICLE_STORIES_TABLE);


        // Insert initial category entries
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('1','Adventure');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('2','Animal');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('3','Food');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('4','Music');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('5','Nature');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('6','Sport');");
        db.execSQL("INSERT INTO " + TABLE_CATEGORIES + " VALUES ('7','Water');");

        // Insert initial story entries
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('1','Germain Arena','Estero, FL','Sport'," +
                "'In addition to hosting Florida Everblades hockey, Southwest Florida’s premier entertainment venue offers " +
                "a wide variety of choice entertainment. Since the arena opened in 1998, it has hosted NHL, NBA, USBL, and Arena " +
                "football games.','2375','image','germainarena','thrifty');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('2','Naples Zoo','Naples, FL','Animal'," +
                "'Naples Zoo is a nationally accredited zoo and yet much more than a walk-through zoo. The nonprofit 501(c)(3) " +
                "charitable institution also features a full day of fun activities. The paved path winds nearly a mile past rare " +
                "and beautiful animals residing within a historic tropical garden of exotic plants first planted in 1919 with a " +
                "fascinating history.','1216','image','napleszoo','thrifty');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('3','Naples Botanical Garden','Naples, FL','Nature'," +
                "'Connecting people with plants by conserving and researching the biological diversity of our collections and " +
                "ecosystems; engaging our visitors in learning about plants, gardens and ecosystems; and inspiring our visitors " +
                "to value plants, gardens and natural habitats.','7243','image','naplesbotgarden','thrifty');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('4','Ding Darling Wildlife Preserve','Sanibel, FL','Nature'," +
                "'The J.N. Ding Darling National Wildlife Refuge is located on the subtropical barrier island of Sanibel in " +
                "the Gulf of Mexico. The refuge is part of the largest undeveloped mangrove ecosystem in the United States. It " +
                "is world famous for its spectacular migratory bird populations.','5231','image','dingdarling','thrifty');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('5','Barefoot Beach Preserve','Bonita Springs, FL','Nature'," +
                "'Collier Countys desirable coast reaches its zenith at Barefoot Beach Preserve, where numerous animal species " +
                "reside and visitors are able to enjoy the ambience of the parks natural surroundings.','2205','image','barefootbeach','thrifty');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('6','The Buddha Rock Club','Fort Myers, FL','Music'," +
                "'Youll know you are in the right place by the giant statue of Buddha, sitting in the parking lot. A couple " +
                "of things you can be sure of in this bar, the drinks are cold and cheap and the music is loud. Local bands love " +
                "to rock here as well as those on the touring circuit.','3655','image','buddhaclub','people');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('7','Sun Splash Water Park','Cape Coral, FL','Water'," +
                "'Seasonal amusement park with 14+ acres of waterslides, a lazy river and kids water play area.','2643','image','sunsplash','people');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('8','Alico Arena','Estero, FL','Sport'," +
                "'Alico Arena, also known as The Nest and Dunk City, is a 120,000 sq ft multipurpose arena on the campus " +
                "of Florida Gulf Coast University in Fort Myers, Florida. It is the home of the FGCU Eagles volleyball and " +
                "mens and womens basketball teams.','1364','image','alicoarena','people');");
        db.execSQL("INSERT INTO " + TABLE_STORIES + " VALUES ('9','Mad Hatter','Captiva, FL','Food'," +
                "'This former bungalow on the beach has been home the The Mad Hatter Restaurant for nearly 30 years, boasting " +
                "one of Sanibel and Captivas finest dining experiences.','1744','video','surfing','people');");

        // Insert initial qr entries
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('1','111111');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('2','222222');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('3','333333');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('4','444444');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('5','555555');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('6','666666');");
        db.execSQL("INSERT INTO " + TABLE_QR + " VALUES ('7','777777');");

        // Insert initial favorites entries
        // Insert initial category entries
        db.execSQL("INSERT INTO " + TABLE_FAVORITES + " VALUES ('1','1');");
        db.execSQL("INSERT INTO " + TABLE_FAVORITES + " VALUES ('2','3');");
        db.execSQL("INSERT INTO " + TABLE_FAVORITES + " VALUES ('3','7');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS stories");
        db.execSQL("DROP TABLE IF EXISTS qr");
        db.execSQL("DROP TABLE IF EXISTS favorites");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS vehicle_stories");
        onCreate(db);
    }

    // Inserts a new story into database
    public void addStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, story.getTitle());
        values.put(COLUMN_LOCATION, story.getLocation());
        values.put(COLUMN_CATEGORY, story.getCategory());
        values.put(COLUMN_CAPTION, story.getCaption());
        values.put(COLUMN_LIKES, story.getLikes());
        values.put(COLUMN_RES_TYPE, story.getResourceType());
        values.put(COLUMN_RESOURCE, story.getResource());
        values.put(COLUMN_STORY_TYPE, story.getStoryType());
        db.insert(TABLE_STORIES, null, values);
    }

    // Edits a story already in database
    public void editStory(Story story) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, story.getTitle());
        values.put(COLUMN_LOCATION, story.getLocation());
        values.put(COLUMN_CATEGORY, story.getCategory());
        values.put(COLUMN_CAPTION, story.getCaption());
        values.put(COLUMN_LIKES, story.getLikes());
        values.put(COLUMN_RES_TYPE, story.getResourceType());
        values.put(COLUMN_RESOURCE, story.getResource());
        values.put(COLUMN_STORY_TYPE, story.getStoryType());
        db.update(TABLE_STORIES, values, COLUMN_STORY_ID + " = '" + story.getId() + "'", null);
    }

    // Returns an arraylist of stories from database
    public ArrayList<Story> getStoriesByType(String type) {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STORIES + " WHERE " + COLUMN_STORY_TYPE + " = '" + type + "'";;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_ID);
            String id = cursor.getString(idIndex);

            int titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
            String title = cursor.getString(titleIndex);

            int locationIndex = cursor.getColumnIndexOrThrow(COLUMN_LOCATION);
            String location = cursor.getString(locationIndex);

            int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY);
            String category = cursor.getString(categoryIndex);

            int captionIndex = cursor.getColumnIndexOrThrow(COLUMN_CAPTION);
            String caption = cursor.getString(captionIndex);

            int likeIndex = cursor.getColumnIndexOrThrow(COLUMN_LIKES);
            String like = cursor.getString(likeIndex);

            int resourceTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_RES_TYPE);
            String resourceType = cursor.getString(resourceTypeIndex);

            int resourceIndex = cursor.getColumnIndexOrThrow(COLUMN_RESOURCE);
            String resource = cursor.getString(resourceIndex);

            int storyTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_TYPE);
            String storyType = cursor.getString(storyTypeIndex);

            Story story = new Story(Integer.parseInt(id), title, location, category, caption, Integer.parseInt(like), resourceType, resource, storyType);
            stories.add(story);

            cursor.moveToNext();
        }

        return stories;
    }

    // Returns an arraylist of stories from database
    public ArrayList<Story> getStoriesByCategory(String cat) {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STORIES + " WHERE " + COLUMN_CATEGORY + " = '" + cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_ID);
            String id = cursor.getString(idIndex);

            int titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
            String title = cursor.getString(titleIndex);

            int locationIndex = cursor.getColumnIndexOrThrow(COLUMN_LOCATION);
            String location = cursor.getString(locationIndex);

            int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY);
            String category = cursor.getString(categoryIndex);

            int captionIndex = cursor.getColumnIndexOrThrow(COLUMN_CAPTION);
            String caption = cursor.getString(captionIndex);

            int likeIndex = cursor.getColumnIndexOrThrow(COLUMN_LIKES);
            String like = cursor.getString(likeIndex);

            int resourceTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_RES_TYPE);
            String resourceType = cursor.getString(resourceTypeIndex);

            int resourceIndex = cursor.getColumnIndexOrThrow(COLUMN_RESOURCE);
            String resource = cursor.getString(resourceIndex);

            int storyTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_TYPE);
            String storyType = cursor.getString(storyTypeIndex);

            Story story = new Story(Integer.parseInt(id), title, location, category, caption, Integer.parseInt(like), resourceType, resource, storyType);
            stories.add(story);

            cursor.moveToNext();
        }

        return stories;
    }

    // Inserts a new favorite story into database
    public void addFavoriteStory(int user_id, int story_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE_STORY_ID, story_id);
        db.insert(TABLE_FAVORITES, null, values);
    }

    // Returns an arraylist of stories from database
    public ArrayList<Story> getFavoriteStories() {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT A.* FROM " + TABLE_STORIES + " A WHERE A._ID IN (SELECT B.STORY_ID FROM " + TABLE_FAVORITES + " B)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_ID);
            String id = cursor.getString(idIndex);

            int titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
            String title = cursor.getString(titleIndex);

            int locationIndex = cursor.getColumnIndexOrThrow(COLUMN_LOCATION);
            String location = cursor.getString(locationIndex);

            int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY);
            String category = cursor.getString(categoryIndex);

            int captionIndex = cursor.getColumnIndexOrThrow(COLUMN_CAPTION);
            String caption = cursor.getString(captionIndex);

            int likeIndex = cursor.getColumnIndexOrThrow(COLUMN_LIKES);
            String like = cursor.getString(likeIndex);

            int resourceTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_RES_TYPE);
            String resourceType = cursor.getString(resourceTypeIndex);

            int resourceIndex = cursor.getColumnIndexOrThrow(COLUMN_RESOURCE);
            String resource = cursor.getString(resourceIndex);

            int storyTypeIndex = cursor.getColumnIndexOrThrow(COLUMN_STORY_TYPE);
            String storyType = cursor.getString(storyTypeIndex);

            Story story = new Story(Integer.parseInt(id), title, location, category, caption, Integer.parseInt(like), resourceType, resource, storyType);
            stories.add(story);

            cursor.moveToNext();
        }

        return stories;
    }

    // Returns an arraylist of stories from database
    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_CATEGORY);
            String category = cursor.getString(categoryIndex);
            categories.add(category);

            cursor.moveToNext();
        }

        return categories;
    }

    // Deletes a favorite from database
    public void deleteFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_FAVORITE_STORY_ID + " = '" + id + "'", null);
    }

    // Inserts a new favorite story into database
    public void addFavorite(int storyId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE_STORY_ID, storyId);
        db.insert(TABLE_FAVORITES, null, values);
    }

    // Checks if story is a favorite
    public boolean isFavorite(int storyId){
        String query = "SELECT * FROM " + TABLE_FAVORITES + " WHERE " + COLUMN_FAVORITE_STORY_ID + " = '" + storyId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() == 0){
            db.close();
            return false;
        }
        else {
            db.close();
            return true;
        }
    }
}