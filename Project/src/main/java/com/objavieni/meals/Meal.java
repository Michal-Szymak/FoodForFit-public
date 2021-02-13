package com.objavieni.meals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal{
    Recipe recipe;

    public Meal(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getCalories(){
        return recipe.getCaloriesPerMeal();
    }

    public Map<String,Integer> getMapOfNutrients(){
        Map<String,Integer> map = new HashMap<>();
        for (String s : recipe.getMapOfNutrients().keySet()){
            map.put(s,recipe.getMapOfNutrients().get(s)/ recipe.getYield());
        }
        return map;
    }
    public List<String> getDietLabels(){
        return recipe.getMapOfLabels().get("Diet Labels");
    }
    public List<String> getIngredients(){
        return recipe.getMapOfLabels().get("Ingredients");
    }

    @Override
    public String toString() {
        return "Meal : " + recipe.getName()
                + ", calories = "
                + getCalories() + ", nutrients : "
                + getMapOfNutrients();
    }

    public Boolean isInCaloricDiff(int caloriesDiff,int caloriesPerDay){
        if ((getCalories() > (caloriesPerDay - caloriesDiff)) && (getCalories() < (caloriesPerDay + caloriesDiff))){
            return true;
        }
        return false;
    }

    public Recipe getRecipe() {
        return recipe;
    }
    public String getName(){
        return recipe.getName();
    }
    public String getImageSource(){
        return  recipe.getImgSrc();
    }
}
