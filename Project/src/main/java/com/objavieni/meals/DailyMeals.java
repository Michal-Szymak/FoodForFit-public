package com.objavieni.meals;

import java.util.ArrayList;
import java.util.List;

public class DailyMeals {
    private List<Meal> mealList = new ArrayList<>();

    public DailyMeals() {
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
