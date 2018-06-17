package pk.dietmonitor.database.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pk.dietmonitor.database.model.FoodConsumed;

public interface FoodConsumedDAO {

    long insert(FoodConsumed food, SQLiteDatabase sqLiteDatabase);
    String getCreateTableSQL();
    String getTableName();
    List<FoodConsumed> getAll(SQLiteDatabase sqLiteDatabase);

}
