package com.clipboard.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    //initialization of variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ourdb";
    private static final String DATABASE_TABLE = "ourdata";
    private static final String DATABASE_KEY_ID = "id";
    private static final String DATABASE_KEY_NAME = "name";
    private static final String DATABASE_KEY_AGE = "age";
    private static final String DATABASE_KEY_CITY = "city";

    SQLiteDatabase db;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //creating tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + DATABASE_KEY_ID + "INTEGER PRIMARY AUTOINCREMENT, " + DATABASE_KEY_NAME + "TEXT," + DATABASE_KEY_AGE + "TEXT," + DATABASE_KEY_CITY + "TEXT" + ")";
        db.execSQL(query);

    }
//upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop existing database
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        //create table again
        onCreate(db);

    }

    //add new data
    public long addData(String name, String age, String city) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_KEY_NAME, name);
        values.put(DATABASE_KEY_AGE, age);
        values.put(DATABASE_KEY_CITY, city);

        return db.insert(DATABASE_TABLE, null, values);
    }

    //get single employee
    public String getEmployee() {

        db = this.getWritableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[]{DATABASE_KEY_ID, DATABASE_KEY_NAME, DATABASE_KEY_AGE, DATABASE_KEY_CITY}, null, null, null, null, null);
        int eId = cursor.getColumnIndex(DATABASE_KEY_ID);
        int eName = cursor.getColumnIndex(DATABASE_KEY_NAME);
        int eAge = cursor.getColumnIndex(DATABASE_KEY_AGE);
        int eCity = cursor.getColumnIndex(DATABASE_KEY_CITY);

        String res = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            res = res +
                    "id: " + cursor.getString(eId) + "\n" +
                    "Name: " + cursor.getString(eName) + "\n" +
                    "Age: " + cursor.getString(eAge) + "\n" +
                    "City: " + cursor.getString(eCity) + "\n";
        }
        db.close();
        return res;
    }




}
