package pk.dietmonitor.database.model;

public class Food {

    private String name;
    private Float portion;
    private Float energy;
    private Float carbs;
    private Float protein;
    private Float fat;

    public Food() {}

    public Food(String name, float portion, float energy, float carbs, float protein, float fat) {
        this.name = name;
        this.portion = portion;
        this.energy = energy;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPortion() {
        return portion;
    }

    public void setPortion(Float portion) {
        this.portion = portion;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getCarbs() {
        return carbs;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }
}
