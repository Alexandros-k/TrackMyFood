package com.example.alex.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alex.myapplication.Models.Food;

import java.util.ArrayList;

/**
 * Created by Alex on 2/3/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodDB.db";
    public static final String TABLE_NAME = "Food";
    public static final String COLUMN_ID = "foodID";
    public static final String COLUMN_NAME1 = "foodName";
    public static final String COLUMN_NAME2 = "foodKcal";
    public static final String COLUMN_NAME3 = "foodGram";
    public static final String COLUMN_NAME4 = "foodProtein";
    public static final String COLUMN_NAME5 = "foodCarbs";
    public static final String COLUMN_NAME6 = "foodFats";

    //initialize the database
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE" + " " + TABLE_NAME + "("
                + COLUMN_ID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_NAME1 + " " + "TEXT,"
                + COLUMN_NAME2 + " " + "INTEGER,"
                + COLUMN_NAME3 + " " + "INTEGER,"
                + COLUMN_NAME4 + " " + "INTEGER,"
                + COLUMN_NAME5 + " " + "INTEGER,"
                + COLUMN_NAME6 + " " + "INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public ArrayList<Food> loadFoodsHandler() {
        ArrayList<Food> foodList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_NAME;
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
            Food food = new Food(result_0, result_1, result_2, result_3, result_4, result_5, result_6);
            foodList.add(food);
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public void addFoodHandler(Food food) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME1, food.getName());
        values.put(COLUMN_NAME2, food.getKcal());
        values.put(COLUMN_NAME3, food.getGram());
        values.put(COLUMN_NAME4, food.getProtein());
        values.put(COLUMN_NAME5, food.getCarbs());
        values.put(COLUMN_NAME6, food.getFats());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Food findFoodHandler(String foodname) {
        String query = "Select * FROM " + " " + TABLE_NAME + " " + "WHERE" + " " + COLUMN_NAME1 + " " + " = " + "'" + foodname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            food.setID(Integer.parseInt(cursor.getString(0)));
            food.setName(cursor.getString(1));
            food.setGram(Integer.parseInt(cursor.getString(2)));
            food.setKcal(Integer.parseInt(cursor.getString(3)));
            food.setProtein(Integer.parseInt(cursor.getString(4)));
            food.setCarbs(Integer.parseInt(cursor.getString(5)));
            food.setFats(Integer.parseInt(cursor.getString(6)));
            cursor.close();
        } else {
            food = null;
        }
        db.close();
        return food;
    }

    public boolean deleteFoodHandler(int id) {
        boolean result = false;
        String query1 = "delete FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query1, null);
        Food food = new Food();
        if (cursor.moveToPosition(id)) {
            db.delete(TABLE_NAME, COLUMN_ID + "=" + id + "", null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME1, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;

    }
}


