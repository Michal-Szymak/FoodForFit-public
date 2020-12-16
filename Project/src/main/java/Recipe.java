import java.util.Arrays;

public class Recipe {

    String name;
    int calories;
    int caloriesPer100g;
    int totalWeight;
    int fat;
    String[] healthLabels;

    public Recipe(String name, int calories, int totalWeight,int fat, String[] healthLabels) {
        this.name = name;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.healthLabels = healthLabels;
        this.fat = fat;
        caloriesPer100g = (int) ((calories / (totalWeight / 100.0)) + 0.5);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", caloriesPer100g=" + caloriesPer100g +
                ", totalWeight=" + totalWeight +
                ", fat=" + fat +
                ", healthLabels=" + Arrays.toString(healthLabels) +
                '}';
    }
}