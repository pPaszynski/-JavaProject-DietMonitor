package pk.dietmonitor.database.model;

public abstract class FoodAbstractModel {
    public abstract String getName();
    public abstract Float getPortion();
    public abstract Float getEnergy();
    public abstract Float getCarbs();
    public abstract Float getProtein();
    public abstract Float getFat();

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nPortion: " + this.getPortion() + "Energy: " + this.getEnergy() +
                "Carbs: " + this.getCarbs() + "Protein: " + this.getProtein() + "Fat: " + this.getFat();
    }
}
