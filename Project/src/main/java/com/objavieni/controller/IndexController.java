package com.objavieni.controller;
import com.objavieni.dto.UserDto;
import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.RecipeService;
import com.objavieni.service.UserService;
import com.objavieni.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j

public class IndexController {

    private final UserService userService;


    public Preferences preferences;// = setPreferences();
  //  public UserDto userDto = setUser(preferences);
    public RecipeService recipeService;// = setRecipeService(preferences);
    public MealDistributor mealDistributor;// = setMealDistributor(recipeService.getRecipeList(),preferences);
    public Ingredients ingredients;
    public UserDto loggedUser;

    public IndexController(UserService userService) {
        this.userService = userService;
    }


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

    public UserDto setUser(Preferences preferences){
        log.info("setting user");
        UserDto userDto = new UserDto("Paweł", "male", 30, preferences);
        //userDto = userService.saveUser(userDto);
        log.info("user setted");
        return userDto;
    }
    private RecipeService setRecipeService(Preferences preferences) {
        log.info("setting service");
        RecipeService recipeService = new RecipeService(preferences);
//        recipeService.setUserPreferences(preferences);
        recipeService.setNumberOfRecipiesToBeDownloaded(1000);
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
        model.addAttribute("ingredients",new Ingredients());
        return "calendar";
    }
    @GetMapping("/profile")
    public String getProfile(Model model){
        model.addAttribute("user", loggedUser);
        model.addAttribute("dietLabels", loggedUser.getPreferences().getDietLabels());
        model.addAttribute("allergies", loggedUser.getPreferences().getAllergies());
        model.addAttribute("countMealsPerDay", loggedUser.getPreferences().getCountMealsPerDay());
        model.addAttribute("countCaloriesPerDay", loggedUser.getPreferences().getCountCaloriesPerDay());
        return "profile";
    }

    @GetMapping("/diet")
    public String getDiet(Model model){
        Arrays.asList(HealthLabel.values().clone());
        List<List<HealthLabel>> healthLabelListToShow = new ArrayList<List<HealthLabel>>();
        for (int i = 0; i < HealthLabel.values().length - 3; i+=3){
            List<HealthLabel> list = new ArrayList<>();
            list.add(HealthLabel.values()[i]);
            list.add(HealthLabel.values()[i+1]);
            list.add(HealthLabel.values()[i+2]);
            healthLabelListToShow.add(list);
        }

        List<DietLabel> dietLabelListToShow = new ArrayList<>(Arrays.asList(DietLabel.values()));
        model.addAttribute("healthListsList",healthLabelListToShow);
        model.addAttribute("diet",dietLabelListToShow);
        model.addAttribute("preferences",new Preferences());
        model.addAttribute("myPreferences",preferences);


        return "diet";
    }
    @PostMapping("savePreferences")
    public String savePreferences(@ModelAttribute Preferences preferences){
        this.preferences = preferences;
        log.info("preferences loaded  : " + preferences.getDietLabels() + "\n" + preferences.getAllergies() );
        loggedUser.setPreferences(preferences);
        loggedUser = userService.updateUser(loggedUser);
        recipeService = setRecipeService(preferences);
        mealDistributor = setMealDistributor(recipeService.getRecipeList(),preferences);

        return "redirect:calendar";
    }

    @PostMapping("/getIngredients")
    public String getIngredients(@ModelAttribute Ingredients ingredients){
        log.info("loading ingredients : " + ingredients);
        this.ingredients = ingredients;
        log.info("loaded ingredients : " + this.ingredients);
        return "redirect:ingredients";
    }

    @GetMapping("/ingredients")
    public String showIngredients(Model model){
        log.info("showing ingreedients : " + ingredients.toString());
        model.addAttribute("ingredients",ingredients.getList());
        return "ingredients";
    }

    @GetMapping("/login")
    public String loginWindow(Model model){
        model.addAttribute("user",new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String registerWindow(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(UserDto userDto){
        UserDto userDto1 = userService.saveUser(userDto);
        if (userDto1 != null) {
            log.info("registered, redirecting to login");
            return "redirect:login";
        } else {
            log.info("not registered, redirecting to register");
            return "redirect:register";
        }
    }

    @PostMapping("loginUser")
    public String loginP(UserDto userDto){
        loggedUser = userService.findByName(userDto.getName());
        loggedUser.setPreferences(new Preferences());
        if (loggedUser != null) {
            log.info("logged , redirecting to diet");
            return "redirect:diet";
        } else {
            log.info("not logged , redirecting to login");
            return "login";
        }
    }



}
