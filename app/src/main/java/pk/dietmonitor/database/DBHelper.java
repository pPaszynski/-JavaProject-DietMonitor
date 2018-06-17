package pk.dietmonitor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.database.dao.FoodConsumedDAO;
import pk.dietmonitor.database.dao.FoodConsumedDAOImpl;
import pk.dietmonitor.database.dao.FoodDAO;
import pk.dietmonitor.database.dao.FoodDAOImpl;
import pk.dietmonitor.database.model.Food;
import pk.dietmonitor.database.model.FoodConsumed;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper dbHelper;
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "food.db";

    private FoodDAO foodDAO;
    private FoodConsumedDAO foodConsumedDAO;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.foodDAO = new FoodDAOImpl();
        this.foodConsumedDAO = new FoodConsumedDAOImpl();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(foodDAO.getCreateTableSQL());
        db.execSQL(foodConsumedDAO.getCreateTableSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + foodDAO.getTableName());
        db.execSQL("DROP TABLE IF EXISTS " + foodConsumedDAO.getTableName());
        onCreate(db);
    }

    public static DBHelper getInstance(Context context) {
        if(dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public List<Food> getAllFood() {
        return foodDAO.getAll(this.getWritableDatabase());
    }

    public List<String> getAllFoodNames() {
        List<String> foodNameList = new ArrayList<>();
        List<Food> foodList = foodDAO.getAll(this.getWritableDatabase());
        for(Food food : foodList) {
            foodNameList.add(food.getName());
        }
        return foodNameList;
    }

    public List<Food> getFoodByName(String name) {
        return foodDAO.getFoodByName(name, this.getWritableDatabase());
    }

    public void insertFoodConsumed(FoodConsumed foodConsumed) {
        foodConsumedDAO.insert(foodConsumed, this.getWritableDatabase());
    }

    public void insertFood(Food food) {
        foodDAO.insert(food, this.getWritableDatabase());
    }
}
