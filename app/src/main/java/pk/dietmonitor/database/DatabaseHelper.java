package pk.dietmonitor.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.FoodModel;

/**
 * Klasa pomocnicza do zarzdzania baza danych.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "food.db";
    private static final int DB_VER = 1;
    private static final String TABLE_NAME = "food";
    private static final String[] allColumnsNames = {"Name", "Portion", "Energy", "Carbs", "Protein", "Fat"};

    private int nameIndex, portionIndex, energyIndex, carbsIndex, proteinIndex, fatIndex;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);

        setColumnIndexes();
    }

    public void setColumnIndexes() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABLE_NAME);

        Cursor cursor = qb.query(db, allColumnsNames, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            nameIndex = cursor.getColumnIndex("Name");
            portionIndex = cursor.getColumnIndex("Portion");
            energyIndex = cursor.getColumnIndex("Energy");
            carbsIndex = cursor.getColumnIndex("Carbs");
            proteinIndex = cursor.getColumnIndex("Protein");
            fatIndex = cursor.getColumnIndex("Fat");
        }
    }

    public void setFoodProperties(FoodModel food, Cursor cursor) {
        food.setName(cursor.getString(nameIndex));
        food.setPortion(cursor.getInt(portionIndex));
        food.setEnergy(cursor.getInt(energyIndex));
        food.setCarbs(cursor.getInt(carbsIndex));
        food.setProtein(cursor.getInt(proteinIndex));
        food.setFat(cursor.getInt(fatIndex));
    }

    public List<FoodModel> getAllFood() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABLE_NAME);

        Cursor cursor = qb.query(db, allColumnsNames, null, null, null, null, null);
        List<FoodModel> allFoodList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                FoodModel food = new FoodModel();

                setFoodProperties(food, cursor);

                allFoodList.add(food);
            } while(cursor.moveToNext());
        }

        return allFoodList;
    }

    public List<String> getAllFoodNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name"};

        qb.setTables(TABLE_NAME);

        Cursor cursor = qb.query(db, sqlSelect, null, null, null ,null, null);
        List<String> allFoodNamesList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                allFoodNamesList.add(cursor.getString(nameIndex));
            } while(cursor.moveToNext());
        }

        return allFoodNamesList;
    }

    public List<FoodModel> getFoodByName(String foodName) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABLE_NAME);

        String[] selectionArgs = {"%" + foodName + "%"};

        Cursor cursor = qb.query(db, allColumnsNames, "Name LIKE ?", selectionArgs, null, null, null);
        List<FoodModel> selectedFoodList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                FoodModel food = new FoodModel();

                setFoodProperties(food, cursor);

                selectedFoodList.add(food);
            } while(cursor.moveToNext());
        }

        return selectedFoodList;
    }
}
