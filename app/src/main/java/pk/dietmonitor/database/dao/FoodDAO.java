package pk.dietmonitor.database.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pk.dietmonitor.database.model.Food;

public interface FoodDAO {

    long insert(Food food, SQLiteDatabase sqLiteDatabase);
    String getCreateTableSQL();
    String getTableName();
    List<Food> getAll(SQLiteDatabase sqLiteDatabase);
    List<Food> getFoodByName(String name, SQLiteDatabase sqLiteDatabase);
}
