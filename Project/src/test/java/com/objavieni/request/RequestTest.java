//package com.objavieni.request;
//
//import com.objavieni.configuration.PropertiesConfiguration;
//import com.objavieni.dto.PreferencesDto;
//import com.objavieni.user.DietLabel;
//import com.objavieni.user.HealthLabel;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//class RequestTest {
//
//    @Autowired
//    public Request request;
//
//    private static final int CONSTANT_NUMBER_OF_PREFERENCES = 4;
//
//
//
//
//
//    @Test
//    void addUserPreferences_addsAllRequestParameters_givenProperPreferences() {
//        //given
//        PreferencesDto preferences = new PreferencesDto();
//        //adding 6 more preferences
//        preferences.addHealthLabelToPreferences(HealthLabel.CELERY_FREE);
//        preferences.addHealthLabelToPreferences(HealthLabel.PEANUTS);
//        preferences.addHealthLabelToPreferences(HealthLabel.IMMUNE_SUPPORTIVE);
//        preferences.addDietLabelToPreferences(DietLabel.LOW_CARB);
//        preferences.setCountCaloriesPerDay(2000);
//        preferences.setCountMealsPerDay(4);
//        //when
//        request.addUserPreferences(preferences);
//        //then
//        assertThat(request.getSearchCriteria().size()).isEqualTo(6 + CONSTANT_NUMBER_OF_PREFERENCES);
//    }
//}