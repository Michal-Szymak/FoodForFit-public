package com.objavieni.mealDistribution;

import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Meal;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.WeeklyMeals;
import com.objavieni.user.Preferences;

import java.util.ArrayList;
import java.util.List;

public class MealDistributor {
    private List<Recipe> recipeList;
    private List<Meal> mealList = new ArrayList<>();
    private Preferences preferences;
    private WeeklyMeals weeklyMeals = new WeeklyMeals();

    public MealDistributor(List<Recipe> recipeList, Preferences preferences) {
        this.recipeList = recipeList;
        this.preferences = preferences;
        for (Recipe recipe : recipeList) {
            mealList.add(new Meal(recipe));
        }
    }

    public WeeklyMeals getWeeklyMeals() {
        return weeklyMeals;
    }

    public void distribute(){
        int calPerDay = preferences.getCountCaloriesPerDay()/preferences.getCountMealsPerDay();
        for (int i = 1; i <= 7; i++) {
            DailyMeals day = new DailyMeals();
            for (int j = 0; j < preferences.getCountMealsPerDay(); j++) {
                Meal meal = mealList.get(j);
                mealList.remove(j);
                if (((meal.getCalories() > (calPerDay - 100)) || (meal.getCalories() < (calPerDay + 100)))){
                    day.addMeal(meal);
                } else j--;
            }
            weeklyMeals.addDailyMeals(day);
        }

    }


}
