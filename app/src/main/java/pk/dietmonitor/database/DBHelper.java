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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food.db";
    private final SQLiteDatabase db;

    private FoodDAO foodDAO;
    private FoodConsumedDAO foodConsumedDAO;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = getWritableDatabase();
        this.foodDAO = new FoodDAOImpl(db);
        this.foodConsumedDAO = new FoodConsumedDAOImpl(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        foodDAO.getCreateTableSQL();
        foodConsumedDAO.getCreateTableSQL();
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
        return foodDAO.getAll();
    }

    public List<String> getAllFoodNames() {
        List<String> foodNameList = new ArrayList<>();
        List<Food> foodList = foodDAO.getAll();
        for(Food food : foodList) {
            foodNameList.add(food.getName());
        }
        return foodNameList;
    }

    public List<Food> getFoodByName(String name) {
        return foodDAO.getFoodByName(name);
    }

    public void insertFoodConsumed(FoodConsumed foodConsumed) {
        foodConsumedDAO.insert(foodConsumed);
    }

}
