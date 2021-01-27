package com.objavieni.meals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


class DailyMealsTest {

    @Mock
    Meal meal;

    private static final class Fixtures {

        private static final Map<String, String[]> INGREDIENTS_MAP =
                Collections.singletonMap("Ingredients", new String[]{"ingridient1, ingridient2"});
        private static final Recipe RECIPE_WITH_INGRIDIENTS = new Recipe(INGREDIENTS_MAP);

        private static final Meal MEAL_WITH_INGRIDIENTS = new Meal(RECIPE_WITH_INGRIDIENTS);

        private static final Recipe RECIPE_1 = new Recipe("SNIADANIE");
        private static final Recipe RECIPE_2 = new Recipe("OBIAD");
        private static final Recipe RECIPE_3 = new Recipe("KOLACJA");
        private static final Meal MEAL_1 = new Meal(RECIPE_1);
        private static final Meal MEAL_2 = new Meal(RECIPE_2);
        private static final Meal MEAL_3 = new Meal(RECIPE_3);

        private static final List LIST_MEAL = List.of(MEAL_1, MEAL_2, MEAL_3);
    }

    @Test
    void addDailyMeals_createNewMeal_givenOneMeal() {

        DailyMeals dailyMeals = new DailyMeals();
        dailyMeals.addMeal(Fixtures.MEAL_1);
        dailyMeals.addMeal(Fixtures.MEAL_2);
        dailyMeals.addMeal(Fixtures.MEAL_3);

        assertThat(dailyMeals.getMealList()).hasSameElementsAs(Fixtures.LIST_MEAL);
    }

    @Test
    void addOneIngriedientToMap_ReturnCorrectIngredient() {

        DailyMeals dailyMeals = new DailyMeals();
        String[] expectedIngredients = new String[]{"ingridient1, ingridient2"};

        dailyMeals.addMeal(Fixtures.MEAL_WITH_INGRIDIENTS);

        assertThat(dailyMeals.getDailyIngredients()).hasSameElementsAs(Arrays.asList(expectedIngredients));

        // assertThat(dailyMeals.getDailyIngredients(), equalTo(expectedIngredients));          HARMCREAST

    }


}