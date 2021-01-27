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
import java.util.Optional;
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
        this.weeklyMeals = distributeThree();
    }


    private List<Meal> recipesToMeals(List<Recipe> recipeList) {
        List<Meal> mealList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            mealList.add(new Meal(recipe));
        }
        List<Meal> list = mealList.stream().sorted(Comparator.comparingInt(x -> x.getCalories())).collect(Collectors.toList());
        return list;
    }

    public WeeklyMeals distributeThree() {
        WeeklyMeals weeklyMeals = new WeeklyMeals();
        int caloriesPerMeal = preferences.getCountCaloriesPerDay() / preferences.getCountMealsPerDay();
        List<Meal> acceptableMeals = mealList.stream()
                .filter(meal -> meal.isInCaloricDiff(ACCEPTABLE_CALORIES_DIFF, caloriesPerMeal))
                .collect(Collectors.toList());

        getDailyMealsForWeek(weeklyMeals, acceptableMeals);
        printWeeklyMealsLog(weeklyMeals);
        return weeklyMeals;
    }

    private void getDailyMealsForWeek(WeeklyMeals weeklyMeals, List<Meal> acceptableMeals) {
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            DailyMeals day = new DailyMeals();
            getMealsForDayExceptLastOne(acceptableMeals, day);
            handleLastMealOfTheDay(acceptableMeals, day);
            weeklyMeals.addDailyMeals(day);
        }
    }

    private void getMealsForDayExceptLastOne(List<Meal> acceptableMeals, DailyMeals day) {
        for (int j = 1; j < preferences.getCountMealsPerDay(); j++) {
            int randomIndex = (int) (Math.random() * acceptableMeals.size());
            day.addMeal(acceptableMeals.get(randomIndex));
            acceptableMeals.remove(randomIndex);
        }
    }

    private void handleLastMealOfTheDay(List<Meal> acceptableMeals, DailyMeals day) {
        Meal lastMeal = getLastMealOfTheDay(day, acceptableMeals);
        day.addMeal(lastMeal);
        acceptableMeals.remove(lastMeal);
    }

    private Meal getLastMealOfTheDay(DailyMeals dailyMeals, List<Meal> meals) {
        int caloriesFromEarlierMeals = 0;
        for (Meal meal : dailyMeals.getMealList()) {
            caloriesFromEarlierMeals += meal.getCalories();
        }
        int expectedCaloriesForLastMeal = preferences.getCountCaloriesPerDay() - caloriesFromEarlierMeals;
        Optional<Meal> lastMealOptional = meals.stream()
                .sorted(Comparator.comparingInt(Meal::getCalories))
                .filter(meal -> meal.getCalories() >= expectedCaloriesForLastMeal)
                .findFirst();

        if(lastMealOptional.isPresent()) return lastMealOptional.get();
        else return meals.get(meals.size() - 1);
    }

    private void printWeeklyMealsLog(WeeklyMeals weeklyMeals) {
        for (DailyMeals dailyMeals : weeklyMeals.getDailyMealsList()) {
            log.info("DAILY MEALS");
            for (Meal meal : dailyMeals.getMealList()) {
                log.info(meal.getName() + ": " + meal.getCalories());
            }
        }
    }

    public WeeklyMeals getWeeklyMeals() {
        return weeklyMeals;
    }

    /*
    public WeeklyMeals distribute() {
        WeeklyMeals weeklyMeals = new WeeklyMeals();
        int caloriesPerDay = preferences.getCountCaloriesPerDay() / preferences.getCountMealsPerDay();
        int startIndex = 0;
        Meal meal;
        do {
            meal = mealList.get(startIndex);
            startIndex++;
        }
        while (!meal.isInCaloricDiff(ACCEPTABLE_CALORIES_DIFF, caloriesPerDay));
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

    public WeeklyMeals distributeTwo() {
        WeeklyMeals weeklyMeals = new WeeklyMeals();
        int caloriesPerMeal = preferences.getCountCaloriesPerDay() / preferences.getCountMealsPerDay();
        List<Meal> acceptableMeals = mealList.stream()
                .filter(meal -> meal.isInCaloricDiff(ACCEPTABLE_CALORIES_DIFF, caloriesPerMeal))
                .collect(Collectors.toList());

        log.info("mealList: " + mealList.size() + " # acceptable: " + acceptableMeals.size());
        mealList.forEach(meal -> log.info("Added " + meal.getName() + ": " + meal.getCalories()));

        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            DailyMeals day = new DailyMeals();
            for (int j = 0; j < preferences.getCountMealsPerDay(); j++) {

                if (j == preferences.getCountMealsPerDay() - 1) {
                    Meal lastMeal = getLastMealOfTheDay(day, acceptableMeals);
                    day.addMeal(lastMeal);
                    acceptableMeals.remove(lastMeal);
                    break;
                }

                int randomIndex = (int) (Math.random() * acceptableMeals.size());
                day.addMeal(acceptableMeals.get(randomIndex));
                acceptableMeals.remove(randomIndex);
            }
            weeklyMeals.addDailyMeals(day);
        }

        for (DailyMeals dailyMeals : weeklyMeals.getDailyMealsList()) {
            log.info("DAILY MEALS");
            for (Meal meal : dailyMeals.getMealList()) {
                log.info(meal.getName() + ": " + meal.getCalories());
            }
        }
        return weeklyMeals;
    }

     */
}
