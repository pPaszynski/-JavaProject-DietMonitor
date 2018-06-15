package pk.dietmonitor.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pk.dietmonitor.database.model.FoodConsumed;

public class FoodConsumedDAOImpl implements FoodConsumedDAO{

    private SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_NAME = "food_consumed";
    private static final String TABLE_NAME_FOOD = "food";
    private static final String TABLE_NAME_FOOD_PK = "Name";
    private static final String[] COLUMN_NAMES = {"Id", "Mass", "Time", "FoodNameFK"};

    public FoodConsumedDAOImpl(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_NAMES[0] + " INTEGER PRIMARY KEY,"
                + COLUMN_NAMES[1] + " REAL,"
                + COLUMN_NAMES[2] + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + COLUMN_NAMES[3] + " TEXT,"
                + " FOREIGN KEY(" + COLUMN_NAMES[3] + ") REFERENCES "
                + TABLE_NAME_FOOD + "(" + TABLE_NAME_FOOD_PK + "));";
    }

    @Override
    public long insert(FoodConsumed foodConsumed) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMES[1], foodConsumed.getMass());
        values.put(COLUMN_NAMES[3], foodConsumed.getFoodNameFK());
        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<FoodConsumed> getAll() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, COLUMN_NAMES, null, null, null, null, null);
        List<FoodConsumed> foodConsumedList = new ArrayList<>();
        while(cursor.moveToNext()) {
//            Food food = new Food();
//            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[0]);
//            int portionColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[1]);
//            int energyColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[2]);
//            int carbsColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[3]);
//            int proteinColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[4]);
//            int fatColumnIndex = cursor.getColumnIndex(COLUMN_NAMES[5]);
//            food.setName(cursor.getString(nameColumnIndex));
//            food.setPortion(cursor.getFloat(portionColumnIndex));
//            food.setEnergy(cursor.getFloat(energyColumnIndex));
//            food.setCarbs(cursor.getFloat(carbsColumnIndex));
//            food.setProtein(cursor.getFloat(proteinColumnIndex));
//            food.setFat(cursor.getFloat(fatColumnIndex));
//            foodConsumedList.add(food);
        }
        return foodConsumedList;
    }
}
