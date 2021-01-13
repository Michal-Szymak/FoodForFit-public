package mealDistribution;

import meals.DailyMeals;
import meals.Meal;
import meals.Recipe;
import meals.WeeklyMeals;
import user.Preferences;
import java.util.List;

public class MealDistributor {
    private List<Recipe> recipeList;
    private List<Meal> mealList;
    private Preferences preferences;
    private WeeklyMeals weeklyMeals;

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

    void distribute(){
        int calPerDay = preferences.getCountColouriesPerDay() / preferences.getCountMealsPerDay();
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
