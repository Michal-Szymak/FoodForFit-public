package com.objavieni.request;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    Request request;

    private static final int CONSTANT_NUMBER_OF_PREFERENCES = 4;

    @BeforeEach
    void setUp() {
        request = new Request();
    }

    @Test
    void addUserPreferences_addsAllRequestParameters_givenProperPreferences() {
        //given
        PreferencesDto preferences = new PreferencesDto();
        //adding 6 more preferences
        preferences.addHealthLabelToPreferences(HealthLabel.CELERY_FREE);
        preferences.addHealthLabelToPreferences(HealthLabel.PEANUTS);
        preferences.addHealthLabelToPreferences(HealthLabel.IMMUNE_SUPPORTIVE);
        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
        preferences.setCountCaloriesPerDay(2000);
        preferences.setCountMealsPerDay(4);
        //when
        request.addUserPreferences(preferences);
        //then
        assertThat(request.getSearchCriteria().size()).isEqualTo(6 + CONSTANT_NUMBER_OF_PREFERENCES);
    }
}