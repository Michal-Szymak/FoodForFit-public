package com.objavieni.meals;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class DailyMealsTest {

    @Test
    void addDailyMeals_createNewMeal_givenOneMeal() {

        DailyMeals dailyMeals = new DailyMeals();
        dailyMeals.addMeal(Fixtures.MEAL_1);
        dailyMeals.addMeal(Fixtures.MEAL_2);
        dailyMeals.addMeal(Fixtures.MEAL_3);

        assertThat(dailyMeals.getMealList()).hasSameElementsAs(Fixtures.LIST_MEAL);
    }

    private static final class Fixtures {
        private static final Recipe RECIPE_1 = new Recipe("SNIADANIE");
        private static final Recipe RECIPE_2 = new Recipe("OBIAD");
        private static final Recipe RECIPE_3 = new Recipe("KOLACJA");
        private static final Meal MEAL_1 = new Meal(RECIPE_1);
        private static final Meal MEAL_2 = new Meal(RECIPE_2);
        private static final Meal MEAL_3 = new Meal(RECIPE_3);

        private static final List LIST_MEAL = List.of(MEAL_1, MEAL_2, MEAL_3);
    }
}