package pk.dietmonitor;

public class FoodModel {

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

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy * mass;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs * mass;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein * mass;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat * mass;
    }
}
