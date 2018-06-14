package pk.dietmonitor;

import android.content.Context;

import pk.dietmonitor.database.DatabaseHelper;

public class FoodModel{

    public FoodModel() {
        this.mass = 1;
    }

    public FoodModel(float m) {
        this.mass = m / 100;
    }

    private String name;
    private float mass, portion, energy, carbs, protein, fat;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPortion() {
        return portion;
    }

    public void setPortion(float portion) {
        this.portion = portion * mass;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy * mass;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs * mass;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein * mass;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat * mass;
    }
}
