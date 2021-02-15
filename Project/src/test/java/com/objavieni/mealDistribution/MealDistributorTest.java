package com.objavieni.mealDistribution;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.meals.DailyMeals;
import com.objavieni.meals.Meal;
import com.objavieni.meals.Recipe;
import com.objavieni.meals.WeeklyMeals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MealDistributorTest {

    private static final int CALORIES_DIFF = 200;

    List<Recipe> recipes = new ArrayList<>();

    @BeforeEach
    void fillRecipeList(){
        for (int i = 0; i< 3000; i++){
            Recipe recipe = new Recipe(i,1);
            recipes.add(recipe);
        }
    }

    boolean isInCaloriesDiff(int countCalories,int expectedCalories){
        return (countCalories > expectedCalories - CALORIES_DIFF) && (countCalories < expectedCalories + CALORIES_DIFF);
    }

    //TODO use parametrized test
    @Test
    @ParameterizedTest
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