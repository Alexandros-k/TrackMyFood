package com.example.alex.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 2/4/2018.
 */

public class DailyDbHelper  extends SQLiteOpenHelper {

    //information of database
    Date now = new Date();
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    String day = dateFormatter.format(now);
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dailyFoodRecordDB.db";
    private static final String TABLE_NAME = "todayFood";
    private static final String COLUMN_ID = "foodID";
    private static final String COLUMN_NAME1 = "foodName";
    private static final String COLUMN_NAME2 = "foodKcal";
    private static final String COLUMN_NAME3 = "foodGram";
    private static final String COLUMN_NAME4 = "foodProtein";
    private static final String COLUMN_NAME5 = "foodCarbs";
    private static final String COLUMN_NAME6 = "foodFats";
    private static final String COLUMN_NAME7 = "consumptionDate";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS"+" "+ TABLE_NAME + "("
                + COLUMN_ID +" "+ "INTEGER PRIMARY KEY ,"
                + COLUMN_NAME1 +" "+"TEXT,"
                + COLUMN_NAME2 +" "+ "INTEGER,"
                + COLUMN_NAME3 +" "+ "INTEGER,"
                + COLUMN_NAME4 +" "+ "INTEGER,"
                + COLUMN_NAME5 +" "+ "INTEGER,"
                + COLUMN_NAME6 +" "+ "INTEGER,"
                + COLUMN_NAME7 +" "+ "TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    //initialize the database
    public DailyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory , DATABASE_VERSION);
    }
    public void addTodayFoodHandler(Food food) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME1, food.getName());
        values.put(COLUMN_NAME2, food.getKcal());
        values.put(COLUMN_NAME3, food.getGram());
        values.put(COLUMN_NAME4, food.getProtein());
        values.put(COLUMN_NAME5, food.getCarbs());
        values.put(COLUMN_NAME6, food.getFats());
        values.put(COLUMN_NAME7, day);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<Food> loadFoodHandler(String requestedDate) {
        ArrayList<Food> foodList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_NAME+ " where consumptionDate = '"+requestedDate+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            int result_4 = cursor.getInt(4);
            int result_5 = cursor.getInt(5);
            int result_6 = cursor.getInt(6);
            Food food = new Food(result_0,result_1,result_2,result_3,result_4,result_5,result_6);
            foodList.add(food);
        }
        cursor.close();
        db.close();
        return foodList;
    }
    public Food loadTodaysFoodTotalNutrientsHandler(String requestedDate) {
        int kcal=0;
        int gram=0;
        int prot=0;
        int carbs=0;
        int fats=0;
        Food food= new Food();
        String query = "Select * FROM " + TABLE_NAME+ " where consumptionDate = '"+requestedDate+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            int result_4 = cursor.getInt(4);
            int result_5 = cursor.getInt(5);
            int result_6 = cursor.getInt(6);
            String result_7 = cursor.getString(7);
            kcal += result_2;
            gram +=result_3;
            prot +=result_4;
            carbs +=result_5;
            fats +=result_6;
            food = new Food(kcal,gram,prot,carbs,fats);
        }
        cursor.close();
        db.close();
        return food;
    }
    public void deleteFoodHandler(int id) {
        String query1 = "delete FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query1, null);
        if (cursor.moveToPosition(id)) {
            db.delete(TABLE_NAME, COLUMN_ID + "="+id+"",null);
            cursor.close();
        }
        db.close();
    }
    public String getFirstRecord(){
        String query = "Select * FROM " + TABLE_NAME+ " where "+ COLUMN_ID +" = 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String result_7 = null;
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            int result_4 = cursor.getInt(4);
            int result_5 = cursor.getInt(5);
            int result_6 = cursor.getInt(6);
            result_7 = cursor.getString(7);
        }
        cursor.close();
        db.close();
        return result_7;
    }
}
