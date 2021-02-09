package com.objavieni.request;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import com.objavieni.user.Preferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    Request request;

    @BeforeEach
    void setUp() {
        request = new Request();
    }

    @Test
    void addUserPreferences_addsAllRequestParameters_givenProperPreferences() {
        //given
        PreferencesDto preferences = new PreferencesDto();
        preferences.addHealthLabelToPreferences(HealthLabel.CELERY_FREE);
        preferences.addHealthLabelToPreferences(HealthLabel.PEANUTS);
        preferences.addHealthLabelToPreferences(HealthLabel.IMMUNE_SUPPORTIVE);
        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
        preferences.setCountCaloriesPerDay(2000);
        preferences.setCountMealsPerDay(4);
        //when
        request.addUserPreferences(preferences);
        //then
        assertThat(request.getSearchCriteria().size()).isEqualTo(10);
    }
}