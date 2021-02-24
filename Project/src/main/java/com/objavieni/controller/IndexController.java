package com.objavieni.controller;

import com.objavieni.configuration.PropertiesConfiguration;
import com.objavieni.dto.PreferencesDto;
import com.objavieni.dto.UserDto;
import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.error.UserNotFoundException;
import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.RecipeService;
import com.objavieni.service.UserService;
import com.objavieni.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public RecipeService recipeService;
    public MealDistributor mealDistributor;
    public final PropertiesConfiguration propertiesConfiguration;

    public Ingredients ingredients;
    public UserDto loggedUser;

    public IndexController(UserService userService, RecipeService recipeService, MealDistributor mealDistributor, PropertiesConfiguration propertiesConfiguration) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.mealDistributor = mealDistributor;
        this.propertiesConfiguration = propertiesConfiguration;
    }


    @GetMapping("/calendar")
    public String getIndex(Model model) throws InvalidApiResponseException {
        List<DailyMeals> list = new ArrayList<>();
        if (loggedUser.getPreferencesDto().getCountMealsPerDay() != 0){
            recipeService.setPreferences(loggedUser.getPreferencesDto());
            mealDistributor.setDataToCompute(recipeService.getRecipeList(),loggedUser.getPreferencesDto());
            list = mealDistributor.getWeeklyMeals().getDailyMealsList();
        }
        model.addAttribute("weeklyMeals",list);
        model.addAttribute("ingredients",new Ingredients());
        return "calendar";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        log.warn("PRINCIPAL: " + user.getUsername());
        log.info("Logged user: " + loggedUser.toString());
        log.info(model.toString());
        model.addAttribute("user", loggedUser);
        model.addAttribute("dietLabels", loggedUser.getPreferencesDto().getDietLabels());
        model.addAttribute("allergies", loggedUser.getPreferencesDto().getAllergies());
        model.addAttribute("countMealsPerDay", loggedUser.getPreferencesDto().getCountMealsPerDay());
        model.addAttribute("countCaloriesPerDay", loggedUser.getPreferencesDto().getCountCaloriesPerDay());
        return "profile";
    }

    @GetMapping("/diet")
    public String getDiet(Model model){
        model.addAttribute("healthListsList",getHealthLabelListToShow());
        model.addAttribute("diet",Arrays.asList(DietLabel.values()));
        model.addAttribute("preferences",new Preferences());
        model.addAttribute("myPreferences",loggedUser.getPreferencesDto());
        return "diet";
    }

    @PostMapping("savePreferences")
    public String savePreferences(@ModelAttribute PreferencesDto preferencesDto) throws UserNotFoundException {
        log.info("preferences loaded  : " + preferencesDto.getDietLabels() + " ### " + preferencesDto.getAllergies() );
        loggedUser = userService.updateUser(loggedUser.getId(),preferencesDto);
        return "redirect:profile";
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
        UserDto savedUserDto = userService.saveUser(userDto);
        if (savedUserDto != null) {
            log.info("registered, redirecting to login");
            return "redirect:login";
        } else {
            log.info("not registered, redirecting to register");
            return "redirect:register";
        }
    }

    @PostMapping("loginUser")
    public String loginP(@ModelAttribute UserDto userDto){
        loggedUser = userService.findByName(userDto.getName());
        log.info("Logged user: " + loggedUser);
        if (loggedUser != null) {
            log.info("Logged user pref: " + loggedUser.getPreferencesDto());
            log.info("logged , redirecting to profile");
            return "redirect:profile";
        } else {
            log.info("not logged , redirecting to login");
            return "login";
        }
    }

    private List<List<HealthLabel>> getHealthLabelListToShow() {
        HealthLabel[] arrayOfLabels = HealthLabel.values();
        List<List<HealthLabel>> healthLabelListToShow = new ArrayList<>();
        for (int i = 0; i < arrayOfLabels.length - 3; i+=3){         //divide list into 3 columns
            List<HealthLabel> list = new ArrayList<>();
            list.add(arrayOfLabels[i]);
            list.add(arrayOfLabels[i+1]);
            list.add(arrayOfLabels[i+2]);
            healthLabelListToShow.add(list);
        }
        return healthLabelListToShow;
    }
}
