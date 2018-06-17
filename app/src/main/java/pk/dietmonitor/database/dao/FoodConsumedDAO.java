package pk.dietmonitor.database.dao;

import java.util.List;

import pk.dietmonitor.database.model.FoodConsumed;

public interface FoodConsumedDAO {

    long insert(FoodConsumed food);
    String getCreateTableSQL();
    String getTableName();
    List<FoodConsumed> getAll();

}
