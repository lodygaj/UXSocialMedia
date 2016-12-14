package com.gtoz.uxsocialmedia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GtoZ on 12/13/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
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
    private static final String COLUMN_WEBSITE = "website";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_RESOURCE = "resource";
    private static final String COLUMN_LIKES = "likes";

    // Table QR with columns
    private static final String TABLE_QR = "qr";
    private static final String COLUMN_QR_ID = "_id";
    private static final String COLUMN_STRING = "string";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " STRING NOT NULL, " +
                COLUMN_PASSWORD + " STRING NOT NULL )";
        db.execSQL(CREATE_USERS_TABLE);

        // Create Stories table
        String CREATE_STORIES_TABLE = "CREATE TABLE " +
                TABLE_STORIES + "(" +
                COLUMN_STORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " STRING, " +
                COLUMN_LOCATION + " STRING, " +
                COLUMN_WEBSITE + " STRING, " +
                COLUMN_TYPE + " STRING, " +
                COLUMN_CATEGORY + " STRING, " +
                COLUMN_TEXT + " STRING, " +
                COLUMN_RESOURCE + " INTEGER, " +
                COLUMN_LIKES + " INTEGER )";
        db.execSQL(CREATE_STORIES_TABLE);

        // Create QR table
        String CREATE_QR_TABLE = "CREATE TABLE " +
                TABLE_QR + "(" +
                COLUMN_QR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STRING + " STRING NOT NULL )";
        db.execSQL(CREATE_QR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS stories");
        db.execSQL("DROP TABLE IF EXISTS qr");
        onCreate(db);
    }
}
