package pk.dietmonitor.database.model;

public class Food extends FoodAbstractModel {

    private String name;
    private Float portion;
    private Float energy;
    private Float carbs;
    private Float protein;
    private Float fat;

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

    public Float getPortion() {
        return portion;
    }

    public Float getEnergy() {
        return energy;
    }

    public Float getCarbs() {
        return carbs;
    }

    public Float getProtein() {
        return protein;
    }

    public Float getFat() {
        return fat;
    }
}
