package pk.dietmonitor.database.model;

import java.sql.Timestamp;

public class FoodConsumed {

    private int id;
    private Float mass;
    private Timestamp time;
    private String foodNameFK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getMass() {
        return mass;
    }

    public void setMass(Float mass) {
        this.mass = mass;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFoodNameFK() {
        return foodNameFK;
    }

    public void setFoodNameFK(String foodNameFK) {
        this.foodNameFK = foodNameFK;
    }

}
