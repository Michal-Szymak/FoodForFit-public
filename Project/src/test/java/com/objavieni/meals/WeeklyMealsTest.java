package com.objavieni.meals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeeklyMealsTest {

    @Mock
    DailyMeals dailyMeals;

    @Test
    void addedDaylyMeal_increseDaylyMealList() {

        //given
        List<DailyMeals> dailyMealsList = new ArrayList<>();
            dailyMealsList.add(dailyMeals);
        //when

        boolean daylyMeal = dailyMealsList.contains(dailyMeals);

        //then
        assertThat(daylyMeal).isTrue();
    }
}