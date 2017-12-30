package com.example.hamzaakhtar.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private Context context = null;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "user";

    // Contacts table name
    private static final String TABLE_USER_DETAIL = "userDetails";

    public final static String DATABASE_PATH = "/data/data/com.example.hamzaakhtar.fitnessapp/databases/";

    // Contacts Table Columns names

    private static final String KEY_ID = "keyID";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_DOB = "dob";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_GENDER = "gender";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        myDataBase = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAIL);

        // Create tables again
        onCreate(db);
    }

    private boolean checkDatabaseExists() {
        boolean checkDB = false;
        try {
            String PATH = DATABASE_PATH + DATABASE_NAME;
            File dbFile = new File(PATH);
            checkDB = dbFile.exists();

        } catch (SQLiteException e) {

        }
        return checkDB;
    }

    public void createDatabase() throws IOException {
        boolean dbExist1 = checkDatabaseExists();
        if (!dbExist1) {
            SQLiteDatabase db = this.getWritableDatabase();

            String CREATE_USER_DETAIL_TABLE = "CREATE TABLE " + TABLE_USER_DETAIL + "(" + KEY_ID +
                    " TEXT PRIMARY KEY," + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_DOB +
                    " TEXT," + KEY_WEIGHT + " INTEGER," + KEY_AGE + " INTEGER," + KEY_HEIGHT + " INTEGER,"
                    + KEY_GENDER + " TEXT " + ")";

            db.execSQL(CREATE_USER_DETAIL_TABLE);
        }
    }


    // Adding new Student Information
    void addNewUser(User newUser) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, newUser.getID());
        values.put(KEY_FNAME, newUser.getfName());
        values.put(KEY_LNAME, newUser.getlName());
        values.put(KEY_DOB, newUser.getDob());
        values.put(KEY_WEIGHT, newUser.getWeight());
        values.put(KEY_AGE, newUser.getAge());
        values.put(KEY_HEIGHT, newUser.getHeight());
        values.put(KEY_GENDER, newUser.getGender());


        // Inserting Row
        db.insert(TABLE_USER_DETAIL, null, values);
        db.close(); // Closing database connection
    }


    public boolean updateUserInfo(String id, int weight, int height) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_WEIGHT, weight);
        args.put(KEY_HEIGHT, height);

        return db.update(TABLE_USER_DETAIL, args, KEY_ID + "=" + id, null) > 0;
    }


    public boolean deleteUser(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_USER_DETAIL, KEY_ID + "=" + id, null) > 0;

    }


    // Getting All Students
    public List<User> getAllUsersList() {


        List<User> userList = new ArrayList<User>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                User user = new User();
                user.setfName(cursor.getString(1));
                user.setlName(cursor.getString(2));
                user.setDob(cursor.getString(3));
                user.setWeight(Integer.parseInt(cursor.getString(4)));
                user.setAge(Integer.parseInt(cursor.getString(5)));
                user.setHeight(Integer.parseInt(cursor.getString(6)));
                user.setGender(cursor.getString(7));

                // Adding contact to list
                userList.add(user);

            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }

}

