package com.objavieni.controller;
import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.RecipeService;
import com.objavieni.user.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class IndexController {


    Preferences preferences = setPreferences();
    User user = setUser(preferences);
    RecipeService recipeService = setRecipeService(preferences);
    MealDistributor mealDistributor = setMealDistributor(recipeService.getRecipeList(),preferences);


    private Preferences setPreferences(){
        Preferences preferences = new Preferences();
        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
        preferences.addHealthLabelToPreferences(HealthLabel.KETO);
        preferences.setCountMealsPerDay(3);
        preferences.setCountCaloriesPerDay(1500);
        return preferences;
    }

    public User setUser(Preferences preferences){
        User user = new User("Paweł", Gender.MALE, 30, preferences);
        return user;
    }
    private RecipeService setRecipeService(Preferences preferences) {
        RecipeService recipeService = new RecipeService();
        recipeService.setUserPreferences(preferences);
        return recipeService;
    }

    private MealDistributor setMealDistributor(List<Recipe> recipeList, Preferences preferences) {
        MealDistributor mealDistributor =
                new MealDistributor(recipeList,preferences);
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
        return "diet";
    }

}
