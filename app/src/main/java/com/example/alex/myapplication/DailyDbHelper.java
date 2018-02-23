package com.example.alex.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 2/4/2018.
 */

public class DailyDbHelper  extends SQLiteOpenHelper {

    //information of database
    Date now = new Date();

    DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
    String day = dateFormatter.format(now);
   // String yesterday = getYesterdayDateString();
    //String nextDay=getNextDateString();
    CustomAdapter customAdapter;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dailyFoodDB.db";
    public  final String TABLE_NAME = day;
    public static final String COLUMN_ID = "foodID";
    public static final String COLUMN_NAME1 = "foodName";
    public static final String COLUMN_NAME2 = "foodKcal";
    public static final String COLUMN_NAME3 = "foodGram";
    public static final String COLUMN_NAME4 = "foodProtein";
    public static final String COLUMN_NAME5 = "foodCarbs";
    public static final String COLUMN_NAME6 = "foodFats";


    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    //initialize the database
    public DailyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory , DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS"+" "+ TABLE_NAME + "("
                + COLUMN_ID +" "+ "INTEGER PRIMARY KEY ,"
                + COLUMN_NAME1 +" "+"TEXT,"
                + COLUMN_NAME2 +" "+ "INTEGER,"
                + COLUMN_NAME3 +" "+ "INTEGER,"
                + COLUMN_NAME4 +" "+ "INTEGER,"
                + COLUMN_NAME5 +" "+ "INTEGER,"
                + COLUMN_NAME6 +" "+ "INTEGER)";
        db.execSQL(CREATE_TABLE);



    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public String loadHandler() {
        String result = "";
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
            result += String.valueOf(result_0) + " " +
                    result_1 +" "+
                    String.valueOf(result_2) + " " +
                    String.valueOf(result_3) + " " +
                    String.valueOf(result_4) + " " +
                    String.valueOf(result_5) + " " +
                    String.valueOf(result_6) + " " +
                    System.getProperty("line.separator");

        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<Food> loadHandler2() {
        ArrayList<Food> foodList = new ArrayList<>();
        String query = "Select * FROM " + day;
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

    public ArrayList<String> loadHandler3() {
        String result = "";
        ArrayList<String> list = new ArrayList<>();

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
            result = String.valueOf(result_0) + " " +
                    result_1 +" "+
                    String.valueOf(result_2) + " " +
                    String.valueOf(result_3) + " " +
                    String.valueOf(result_4) + " " +
                    String.valueOf(result_5) + " " +
                    String.valueOf(result_6) + " " +
                    System.getProperty("line.separator");
                   list.add(result);
        }

        cursor.close();
        db.close();
        return list;
    }


    public Cursor loadHandler4() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();
        return cursor;
    }
    public Food loadHandler5(String date) {
        int kcal=0;
        int gram=0;
        int prot=0;
        int carbs=0;
        int fats=0;
        Food food= new Food();
        String query = "Select * FROM " + date;
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


    public void addHandler(Food food) {
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
    public Food findHandler(String foodname) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME1 + " = " + "'" + foodname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            //  food.setID(Integer.parseInt(cursor.getString(0)));
            food.setName(cursor.getString(1));

            cursor.close();
        } else {
            food = null;
        }
        db.close();
        return food;
    }
    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            food.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(food.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean deleteHandler(String name) {
        boolean result = false;
        String query = "delete FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME1 + "= '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            food.setName(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_NAME1 + "=?",
                    new String[] {
                            String.valueOf(food.getName())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        customAdapter.notifyDataSetChanged();

        return result;
    }

    public boolean deleteHandler2(Integer id) {
        boolean result = false;
        String query = "delete FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();
        if (cursor.moveToFirst()) {
            food.setID(cursor.getInt(0));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(food.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean deleteHandler3(int id) {
        boolean result = false;
        String query1 = "delete FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query1, null);
        Food food = new Food();
        if (cursor.moveToPosition(id)) {
            db.delete(TABLE_NAME, COLUMN_ID + "="+id+"",null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }



    public boolean updateHandler(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);



        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;


    }




    public void makeTable(String day,DailyDbHelper dailyDbHelper){
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS"+" "+ day + "("
                + dailyDbHelper.COLUMN_ID +" "+ "INTEGER PRIMARY KEY ,"
                + dailyDbHelper.COLUMN_NAME1 +" "+"TEXT,"
                + dailyDbHelper.COLUMN_NAME2 +" "+ "INTEGER,"
                + dailyDbHelper.COLUMN_NAME3 +" "+ "INTEGER,"
                + dailyDbHelper.COLUMN_NAME4 +" "+ "INTEGER,"
                + dailyDbHelper.COLUMN_NAME5 +" "+ "INTEGER,"
                + dailyDbHelper.COLUMN_NAME6 +" "+ "INTEGER)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }





    public ArrayList<Food> loadYesterdayHandler2(String date) {

        ArrayList<Food> foodList = new ArrayList<>();
        String query = "Select * FROM " + date;
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

    public ArrayList<Food> loadNextDayHandler(String nextDay) {

        ArrayList<Food> foodList = new ArrayList<>();
        String query = "Select * FROM " + nextDay;
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



    boolean tableExists( String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }


}
