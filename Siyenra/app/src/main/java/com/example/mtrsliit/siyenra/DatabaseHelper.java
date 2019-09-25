package com.example.mtrsliit.siyenra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Siyenra";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HallBooking = "Halls";
    private static final String KEY_ID = "id";
    private static final String KEY_HallType = "HallType";
    private static final String KEY_Checkin= "checkinDate";
    private static final String KEY_Checkout = "checkoutDate";
    private static final String KEY_Time = "Time";


    /*CREATE TABLE Halls ( id INTEGER PRIMARY KEY AUTOINCREMENT, HallType TEXT, CheckinDate TEXT......);*/

    private static final String CREATE_TABLE_HallBooking = "CREATE TABLE "
            + TABLE_HallBooking + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_HallType + " TEXT, "+ KEY_Checkin + " TEXT, "+ KEY_Checkout + " TEXT , "+ KEY_Time + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_HallBooking);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HallBooking);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_HallBooking + "'");
        onCreate(db);
    }

    public long addBookingDetail(String HallType, String checkinDate,String checkoutDate,String Time) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_HallType, HallType);
        values.put(KEY_Checkin, checkinDate);
        values.put(KEY_Checkout, checkoutDate);
        values.put(KEY_Time, Time);
        // insert row in halls table
        long insert = db.insert(TABLE_HallBooking, null, values);

        return insert;
    }

    public ArrayList<HallBookingModel> getAllhallooking() {
        ArrayList<HallBookingModel> hallBookingModelArrayList = new ArrayList<HallBookingModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_HallBooking;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                HallBookingModel hallBookingModel = new HallBookingModel();
                hallBookingModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                hallBookingModel.setHall(c.getString(c.getColumnIndex(KEY_HallType)));
                hallBookingModel.setCheckindate(c.getString(c.getColumnIndex(KEY_Checkin)));
                hallBookingModel.setCheckoutdate(c.getString(c.getColumnIndex(KEY_Checkout)));
                hallBookingModel.setTime(c.getString(c.getColumnIndex(KEY_Time)));
                // adding to hall booking list
                hallBookingModelArrayList.add(hallBookingModel);
            } while (c.moveToNext());
        }
        return hallBookingModelArrayList;
    }

    public int updatebooking(int id, String HallType, String checkinDate,String checkoutDate,String Time) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_HallType, HallType);
        values.put(KEY_Checkin, checkinDate);
        values.put(KEY_Checkout, checkoutDate);
        values.put(KEY_Time, Time);
        // update row in halls table base on halls.is value
        return db.update(TABLE_HallBooking, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deletebooking(int id) {

        // delete row in halls table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HallBooking, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}

