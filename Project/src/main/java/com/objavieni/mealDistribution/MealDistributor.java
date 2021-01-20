package com.objavieni.mealDistribution;

import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Meal;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.WeeklyMeals;
import com.objavieni.user.Preferences;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MealDistributor {
    public static final int DAYS_IN_WEEK = 7;
    public static final int ACCEPTABLE_CALORIES_DIFF = 100;
    private List<Meal> mealList;
    private Preferences preferences;
    private WeeklyMeals weeklyMeals;

    public MealDistributor(List<Recipe> recipeList, Preferences preferences) {
log.info("loading preferendes to distributor");
        this.preferences = preferences;
        log.info("loading recipes to meallist");
        this.mealList = recipesToMeals(recipeList);
        log.info("distribute to weeklymeals");
        this.weeklyMeals = distribute();
    }


    private List<Meal> recipesToMeals(List<Recipe> recipeList) {
        List<Meal> mealList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            mealList.add(new Meal(recipe));
        }
        List<Meal> list = mealList.stream().sorted(Comparator.comparingInt(x -> x.getCalories())).collect(Collectors.toList());
        return list;
    }

    public WeeklyMeals getWeeklyMeals() {
        return weeklyMeals;
    }

    public WeeklyMeals distribute(){
        WeeklyMeals weeklyMeals = new WeeklyMeals();
        int caloriesPerDay = preferences.getCountCaloriesPerDay()/preferences.getCountMealsPerDay();
        int startIndex = 0;
        Meal meal;
        do {
            meal = mealList.get(startIndex);
            startIndex++;
        }
        while (!meal.isInCaloricDiff(ACCEPTABLE_CALORIES_DIFF,caloriesPerDay));
        log.info("meals until range : " + startIndex);
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            DailyMeals day = new DailyMeals();

            for (int j = 0; j < preferences.getCountMealsPerDay(); j++) {

                Meal meal2 = mealList.get(j + startIndex);
                mealList.remove(j + startIndex);
                 day.addMeal(meal2);
                    log.info("cal : " + meal2.getCalories() + ",  name : " + meal2.getName() + " /// added to day : " + i);

            }
            weeklyMeals.addDailyMeals(day);
        }
        return weeklyMeals;
    }


}
