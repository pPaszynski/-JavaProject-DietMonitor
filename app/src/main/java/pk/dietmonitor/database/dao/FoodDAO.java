package pk.dietmonitor.database.dao;

import java.util.List;

import pk.dietmonitor.database.model.Food;

public interface FoodDAO {

    long insert(Food food);
    String getCreateTableSQL();
    String getTableName();
    List<Food> getAll();

}
