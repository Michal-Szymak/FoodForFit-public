package com.objavieni.meals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeeklyMeals{


    private List<DailyMeals> dailyMealsList = new ArrayList<>();



    public WeeklyMeals() {
    }
    public void addDailyMeals(DailyMeals dailyMeals){
        dailyMealsList.add(dailyMeals);
    }

    public String[] getIngredients(){
        List<String> list = new ArrayList<>();
        dailyMealsList.forEach(x -> list.addAll(Arrays.asList(x.getDailyIngredients())));
        return list.toArray(new String[0]);
    }

    @Override
    public String toString() {
        if(dailyMealsList.isEmpty())return "empty list";
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
