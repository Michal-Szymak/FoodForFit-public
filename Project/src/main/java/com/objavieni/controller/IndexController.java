package com.objavieni.controller;
import com.objavieni.dto.PreferencesDto;
import com.objavieni.dto.UserDto;
import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.error.UserNotFoundException;
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

    public PreferencesDto preferencesDto = new PreferencesDto();
    public RecipeService recipeService;
    public MealDistributor mealDistributor = new MealDistributor();
    public Ingredients ingredients;
    public UserDto loggedUser;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    private RecipeService setRecipeService(PreferencesDto preferencesDto) throws InvalidApiResponseException {
        log.info("setting service");
        RecipeService recipeService = new RecipeService(preferencesDto);
        //TODO magic number to constant or preferences
        recipeService.setNumberOfRecipiesToBeDownloaded(1000);
        log.info("service set");
        return recipeService;
    }

    private MealDistributor setMealDistributor(List<Recipe> recipeList, PreferencesDto preferences) {
        log.info("setting distributor, recipe list size " + recipeList.size());
        MealDistributor mealDistributor = new MealDistributor(recipeList,preferences);
        log.info("distributor set");
        return mealDistributor;
    }

    @GetMapping("/calendar")
    public String getIndex(Model model) throws InvalidApiResponseException {
        List<DailyMeals> list = new ArrayList<>();
        if (loggedUser.getPreferencesDto().getCountMealsPerDay() != 0){
            recipeService = setRecipeService(loggedUser.getPreferencesDto());
            mealDistributor = setMealDistributor(recipeService.getRecipeList(),loggedUser.getPreferencesDto());
            list = mealDistributor.getWeeklyMeals().getDailyMealsList();
        }
        model.addAttribute("weeklyMeals",list);
        model.addAttribute("ingredients",new Ingredients());
        return "calendar";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
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
        Arrays.asList(HealthLabel.values().clone());
        List<List<HealthLabel>> healthLabelListToShow = new ArrayList<>();
        //TODO 3 = numberOfColumns
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
        model.addAttribute("myPreferences",preferencesDto);
        return "diet";
    }

    @PostMapping("savePreferences")
    public String savePreferences(@ModelAttribute PreferencesDto preferencesDto) throws UserNotFoundException {
        this.preferencesDto = preferencesDto;
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
}
