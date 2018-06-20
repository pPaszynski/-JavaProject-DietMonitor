package pk.dietmonitor.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.database.factory.FoodFactory;
import pk.dietmonitor.database.model.Food;

public class FoodDAOImpl implements FoodDAO {

//    private SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_NAME = "food";
    private static final String[] COLUMN_NAMES = {"Name", "Portion", "Energy", "Carbs", "Protein", "Fat"};

    private int nameIndex, portionIndex, energyIndex, carbsIndex, proteinIndex, fatIndex;

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

    public void setColumnIndexes(Cursor cursor) {
        nameIndex = cursor.getColumnIndex(COLUMN_NAMES[0]);
        portionIndex = cursor.getColumnIndex(COLUMN_NAMES[1]);
        energyIndex = cursor.getColumnIndex(COLUMN_NAMES[2]);
        carbsIndex = cursor.getColumnIndex(COLUMN_NAMES[3]);
        proteinIndex = cursor.getColumnIndex(COLUMN_NAMES[4]);
        fatIndex = cursor.getColumnIndex(COLUMN_NAMES[5]);
    }

    @Override
    public List<Food> getAll(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, COLUMN_NAMES, null, null, null, null, null);
        List<Food> foodList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            String name;
            Float portion, energy, carbs, protein, fat;

            setColumnIndexes(cursor);

            while (cursor.moveToNext()) {
                name = cursor.getString(nameIndex);
                portion = cursor.getFloat(portionIndex);
                energy = cursor.getFloat(energyIndex);
                carbs = cursor.getFloat(carbsIndex);
                protein = cursor.getFloat(proteinIndex);
                fat = cursor.getFloat(fatIndex);

                foodList.add(FoodFactory.getFood(name, portion, energy, carbs, protein, fat));
            }
        }
        return foodList;
    }

    @Override
    public List<Food> getFoodByName(String name, SQLiteDatabase sqLiteDatabase) {
        List<Food> foodList = new ArrayList<>();

        String[] selectionArgs = {"%" + name + "%"};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, COLUMN_NAMES, "NAME LIKE ?", selectionArgs, null, null, null);

        if(cursor.moveToFirst()) {
            Float portion, energy, carbs, protein, fat;

            setColumnIndexes(cursor);

            do {
                name = cursor.getString(nameIndex);
                portion = cursor.getFloat(portionIndex);
                energy = cursor.getFloat(energyIndex);
                carbs = cursor.getFloat(carbsIndex);
                protein = cursor.getFloat(proteinIndex);
                fat = cursor.getFloat(fatIndex);

                foodList.add(FoodFactory.getFood(name, portion, energy, carbs, protein, fat));
            } while(cursor.moveToNext());
        }

        return  foodList;

    }
}
