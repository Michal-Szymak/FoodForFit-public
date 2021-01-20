package com.objavieni.controller;

import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Meal;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.WeeklyMeals;
import com.objavieni.parsing.Parser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import com.objavieni.user.Preferences;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @GetMapping("/calendar")
    public String getIndex(Model model){

        Request myRequest = new Request();
        Preferences userPreferences = new Preferences();
        userPreferences.addDietLabelToPreferences(DietLabel.BALANCED);
        userPreferences.addHealthLabelToPreferences(HealthLabel.CELERY_FREE);
        userPreferences.addHealthLabelToPreferences(HealthLabel.GLUTEN);
        userPreferences.setCountCaloriesPerDay(1500);
        userPreferences.setCountMealsPerDay(3);
        myRequest.setFirst(0);
        myRequest.setLast(100);
        myRequest.addUserPreferences(userPreferences);
        List<String> responseList = RequestManager.getResponse(myRequest);
        List<Recipe> recipeList = new ArrayList<>();
        for (String s : responseList) {
            recipeList.add(Parser.recipeParser(s));
        }
        MealDistributor mealDistributor = new MealDistributor(recipeList,userPreferences);
        mealDistributor.distribute();
        WeeklyMeals weeklyMeals = mealDistributor.getWeeklyMeals();
        List<DailyMeals> list = weeklyMeals.getWeeklyMeals();
        model.addAttribute("weeklyMeals",list);
        return "calendar";
    }
}
