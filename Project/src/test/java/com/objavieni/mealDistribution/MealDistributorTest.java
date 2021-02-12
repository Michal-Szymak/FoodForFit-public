package com.objavieni.mealDistribution;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Meal;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.WeeklyMeals;
import com.objavieni.user.Preferences;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@Slf4j
class MealDistributorTest {

    private final int CALORIES_DIFF = 200;
    List<Recipe> recipes = new ArrayList<>();
    @BeforeEach
    void fillRecipeList(){
        for (int i = 0; i< 3000; i++){
            Recipe recipe = new Recipe(i,1);
            recipes.add(recipe);
        }
    }

    boolean isInCaloriesDiff(int countCalories,int expectedCalories){
        log.info("/////////////////////////////////////////" +
                "///////////////////      "+ (expectedCalories - countCalories) );
        if (countCalories > expectedCalories - CALORIES_DIFF
                && countCalories < expectedCalories + CALORIES_DIFF){
            return true;
        }
        return false;
    }

    @Test
    void distributeProperMeals_givenProperRecipes(){

        for (int i = 1; i < 5; i++){
            test(i,i * 500);
        }

    }



        void test(int mealsPerDay,int caloriesPerDay){
        //given
        PreferencesDto preferencesDto = new PreferencesDto();
        preferencesDto.setCountMealsPerDay(mealsPerDay);
        preferencesDto.setCountCaloriesPerDay(caloriesPerDay);
        MealDistributor mealDistributor = new MealDistributor(recipes,preferencesDto);


        //when
        WeeklyMeals result =  mealDistributor.distributeThree();
        boolean isCorrectQuantity = true;
        boolean isCorrectCalories = true;
        for (DailyMeals d : result.getDailyMealsList()) {
            int caloriesCount = 0;
            if (d.getMealList().size() != mealsPerDay){
                isCorrectQuantity = false;
            }
            for (Meal meal : d.getMealList()) {
                caloriesCount += meal.getCalories();
            }
            if (!isInCaloriesDiff(caloriesCount,caloriesPerDay)){
                isCorrectCalories = false;
            }
        }
        //then
        assertTrue(isCorrectQuantity);
        assertTrue(isCorrectCalories);

    }

}