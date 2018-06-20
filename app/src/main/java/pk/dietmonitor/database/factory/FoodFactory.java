package pk.dietmonitor.database.factory;

import pk.dietmonitor.database.model.Food;

public class FoodFactory {
    public static Food getFood(String name, float portion, float energy, float carbs, float protein, float fat) {
        return new Food(name, portion, energy, carbs, protein, fat);
    }
}
