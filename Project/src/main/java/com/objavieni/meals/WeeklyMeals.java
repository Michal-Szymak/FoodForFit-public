package com.objavieni.meals;

import java.util.ArrayList;
import java.util.List;

public class WeeklyMeals{

    private List<DailyMeals> dailyMealsList = new ArrayList<>();

    public WeeklyMeals() {
    }

    public void addDailyMeals(DailyMeals dailyMeals){
        dailyMealsList.add(dailyMeals);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < dailyMealsList.size(); i++) {
            text.append("\nDay " + i + " : " + dailyMealsList.get(i).toString() );
        }
        return text.toString();
    }

    public List<DailyMeals> getDailyMealsList() {
        return dailyMealsList;
    }
}
