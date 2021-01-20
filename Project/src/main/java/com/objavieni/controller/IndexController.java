package com.objavieni.controller;
import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.RecipeService;
import com.objavieni.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {


    Preferences preferences = setPreferences();
    User user = setUser(preferences);
    RecipeService recipeService = setRecipeService(preferences);
    MealDistributor mealDistributor = setMealDistributor(recipeService.getRecipeList(),preferences);


    private Preferences setPreferences(){
        log.info("setting preferences");
        Preferences preferences = new Preferences();
        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
        preferences.addHealthLabelToPreferences(HealthLabel.KETO);
        preferences.setCountMealsPerDay(3);
        preferences.setCountCaloriesPerDay(1500);
        log.info("preferences setted");
        return preferences;
    }

    public User setUser(Preferences preferences){
        log.info("setting user");
        User user = new User("Paweł", Gender.MALE, 30, preferences);
        log.info("user setted");
        return user;
    }
    private RecipeService setRecipeService(Preferences preferences) {
        log.info("setting service");
        RecipeService recipeService = new RecipeService();
        recipeService.setUserPreferences(preferences);
        log.info("service setted");
        return recipeService;
    }

    private MealDistributor setMealDistributor(List<Recipe> recipeList, Preferences preferences) {
        log.info("setting distributor, recipe list size " + recipeList.size());
        MealDistributor mealDistributor =
                new MealDistributor(recipeList,preferences);
        log.info("distributor setted");
        return mealDistributor;
    }






    @GetMapping("/calendar")
    public String getIndex(Model model){

        List<DailyMeals> list = mealDistributor.getWeeklyMeals().getDailyMealsList();
        model.addAttribute("weeklyMeals",list);
        return "calendar";
    }
    @GetMapping("/profile")
    public String getProfile(Model model){
        return "profile";
    }

    @GetMapping("/diet")
    public String getDiet(Model model){
        model.addAttribute("preferences",new Preferences());
        return "diet";
    }
    @PostMapping("savePreferences")
    public String savePreferences(@ModelAttribute Preferences preferences){
        this.preferences = preferences;
        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
        preferences.addHealthLabelToPreferences(HealthLabel.KETO);
        user = setUser(preferences);
        recipeService = setRecipeService(preferences);
        mealDistributor = setMealDistributor(recipeService.getRecipeList(),preferences);

        return "redirect:calendar";
    }

}
