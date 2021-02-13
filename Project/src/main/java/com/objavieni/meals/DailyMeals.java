package com.objavieni.meals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyMeals {
    private List<Meal> mealList = new ArrayList<>();

    public DailyMeals() {
    }

    public String[] getDailyIngredients() {
        List<String> list = new ArrayList<>();
//        mealList.forEach(x -> list.addAll(Arrays.asList(x.getIngredients())));
        mealList.forEach(x -> list.addAll(x.getIngredients()));
        return list.toArray(new String[0]);
    }

    public void addMeal(Meal meal) {
        mealList.add(meal);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < mealList.size(); i++) {
            text.append(mealList.get(i).recipe.getName() + ", ");
        }
        return text.toString();
    }

    public List<Meal> getMealList() {
        return mealList;
    }
}
