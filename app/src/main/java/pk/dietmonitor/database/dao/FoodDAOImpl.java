package pk.dietmonitor.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.database.model.Food;

public class FoodDAOImpl implements FoodDAO {

//    private SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_NAME = "food";
    private static final String[] COLUMN_NAMES = {"Name", "Portion", "Energy", "Carbs", "Protein", "Fat"};

    public FoodDAOImpl() {
//        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_NAMES[0] + " TEXT PRIMARY KEY,"
                + COLUMN_NAMES[1] + " REAL,"
                + COLUMN_NAMES[2] + " REAL,"
                + COLUMN_NAMES[3] + " REAL,"
                + COLUMN_NAMES[4] + " REAL,"
                + COLUMN_NAMES[5] + " REAL,"
                + "UNIQUE(" + COLUMN_NAMES[0] + "));";
    }

    @Override
    public long insert(Food food, SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES[0], food.getName());
        values.put(COLUMN_NAMES[1], food.getPortion());
        values.put(COLUMN_NAMES[2], food.getEnergy());
        values.put(COLUMN_NAMES[3], food.getCarbs());
        values.put(COLUMN_NAMES[4], food.getProtein());
        values.put(COLUMN_NAMES[5], food.getFat());

        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }



    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Food> getAll(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, COLUMN_NAMES, null, null, null, null, null);
        List<Food> foodList = new ArrayList<>();
        while(cursor.moveToNext()) {
            Food food = new Food();
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[0]);
            int portionColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[1]);
            int energyColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[2]);
            int carbsColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[3]);
            int proteinColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[4]);
            int fatColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[5]);
            food.setName(cursor.getString(nameColumnIndex));
            food.setPortion(cursor.getFloat(portionColumnIndex));
            food.setEnergy(cursor.getFloat(energyColumnIndex));
            food.setCarbs(cursor.getFloat(carbsColumnIndex));
            food.setProtein(cursor.getFloat(proteinColumnIndex));
            food.setFat(cursor.getFloat(fatColumnIndex));
            foodList.add(food);
        }
        return foodList;
    }

    @Override
    public List<Food> getFoodByName(String name, SQLiteDatabase sqLiteDatabase) {
        List<Food> foodList = new ArrayList<>();

        String[] selectionArgs = {"%" + name + "%"};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, COLUMN_NAMES, "NAME LIKE ?", selectionArgs, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                Food food = new Food();
                int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[0]);
                int portionColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[1]);
                int energyColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[2]);
                int carbsColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[3]);
                int proteinColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[4]);
                int fatColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[5]);
                food.setName(cursor.getString(nameColumnIndex));
                food.setPortion(cursor.getFloat(portionColumnIndex));
                food.setEnergy(cursor.getFloat(energyColumnIndex));
                food.setCarbs(cursor.getFloat(carbsColumnIndex));
                food.setProtein(cursor.getFloat(proteinColumnIndex));
                food.setFat(cursor.getFloat(fatColumnIndex));
                foodList.add(food);
            } while(cursor.moveToNext());
        }

        return  foodList;

    }
}
