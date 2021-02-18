package com.objavieni.request;

import com.objavieni.configuration.PropertiesConfiguration;
import com.objavieni.dto.PreferencesDto;
import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Request {

    public final PropertiesConfiguration propertiesConfiguration;

    private static final String DIET_LABEL_KEY = "diet";
    //Not a typo! API uses "healt" as query key for Health Labels
    private static final String HEALTH_LABEL_KEY = "healt";
    private static final String CALORIES_KEY = "calories";
    private static final int ACCEPTABLE_CALORIES_DIFFERENCE = 100;
    private List<RequestParameter> searchCriteria = new ArrayList<>();
    private int recipesToDownload = 100;
    private int offset = 0;

    public void addUserPreferences(PreferencesDto preferencesDto) {

        for (HealthLabel healthLabel : preferencesDto.getAllergies()) {
            addSearchCriteria(HEALTH_LABEL_KEY, healthLabel.getDescription());
        }
        for (DietLabel dietLabel : preferencesDto.getDietLabels()) {
            addSearchCriteria(DIET_LABEL_KEY, dietLabel.getDescription());
        }
        int caloriesPerMeal = preferencesDto.getCountCaloriesPerDay() / preferencesDto.getCountMealsPerDay();
        addCalories(caloriesPerMeal, ACCEPTABLE_CALORIES_DIFFERENCE);
    }

    private void addCalories(int expectedCalories, int acceptableDifference) {
        String queryValue = (expectedCalories - acceptableDifference) + "-" + (expectedCalories + acceptableDifference);
        removeOldCaloriesRequestParameter();
        addSearchCriteria(CALORIES_KEY, queryValue);
    }

    private void removeOldCaloriesRequestParameter() {
        Optional<Integer> caloriesParameterIndex = searchCriteria.stream()
                .filter(requestParameter -> requestParameter.getKey().equals(CALORIES_KEY))
                .map(requestParameter -> searchCriteria.indexOf(requestParameter))
                .findFirst();
        if(caloriesParameterIndex.isPresent()) searchCriteria.remove(caloriesParameterIndex.get().intValue());
    }

    private void addSearchCriteria(String key, String value) {
        searchCriteria.add(new RequestParameter(key, value));
    }

    public List<RequestParameter> getSearchCriteria() {
        List<RequestParameter> result = new ArrayList<>(searchCriteria);
        loadBasicRequestParameters(result);
        return result;
    }

    private void loadBasicRequestParameters(List<RequestParameter> result) {
        result.add(new RequestParameter("app_id", propertiesConfiguration.getAppID()));
        result.add(new RequestParameter("app_key", propertiesConfiguration.getAppKey()));
        result.add(new RequestParameter("q", propertiesConfiguration.getQuery()));
        result.add(new RequestParameter("from", String.valueOf(offset)));
        result.add(new RequestParameter("to", String.valueOf(recipesToDownload + offset)));
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
