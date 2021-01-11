package meals;

import java.util.HashMap;
import java.util.Map;

public class Meal {
    Recipe recipe;

    public Meal(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getCalories(){
        return recipe.getCaloriesPerMeal();
    }
    public Map<String,Integer> getMapOfNutrients(){
        Map<String,Integer> map = new HashMap<>();
        for (String s : recipe.mapOfNutrients.keySet()){
            map.put(s,recipe.mapOfNutrients.get(s)/ recipe.yield);
        }
        return map;
    }
    public String[] getHealthLabels(){
        return recipe.mapOfLabels.get("Health Labels");
    }
    public String[] getDietLabels(){
        return recipe.mapOfLabels.get("Diet Labels");
    }
    public String[] getIngredients(){
        return recipe.mapOfLabels.get("Ingredients");
    }

    @Override
    public String toString() {
        return "Meal : " + recipe.name
                + ", calories = "
                + getCalories() + ", nutrients : "
                + getMapOfNutrients();
    }
}
