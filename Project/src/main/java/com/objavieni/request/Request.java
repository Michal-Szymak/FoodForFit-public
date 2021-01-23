package com.objavieni.request;

import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import com.objavieni.user.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Request {
    private static final String DIET_LABEL_KEY = "diet";
    // "healt" is not a typo! This is query parameter defined in API
    private static final String HEALTH_LABEL_KEY = "healt";
    private static final String CALORIES_KEY = "calories";
    private static final int ACCEPTABLE_CALORIES_DIFFERENCE = 100;
    private List<RequestParameter> searchCriteria = new ArrayList<>();
    // max 100 recipes in one request are allowed by API
    private int recipesToDownload = 10;
    private int offset = 0;

    public Request() {
        searchCriteria.add(new RequestParameter("app_id", "900da95e"));
        searchCriteria.add(new RequestParameter("app_key", "40698503668e0bb3897581f4766d77f9"));
        searchCriteria.add(new RequestParameter("q", ""));
    }

    public void addUserPreferences(Preferences preferences) {
        for (HealthLabel healthLabel : preferences.getAllergies()) {
            addSearchCriteria(HEALTH_LABEL_KEY, healthLabel.getDescription());
        }
        for (DietLabel dietLabel : preferences.getDietLabels()) {
            addSearchCriteria(DIET_LABEL_KEY, dietLabel.getDescription());
        }
        int caloriesPerMeal = preferences.getCountCaloriesPerDay() / preferences.getCountMealsPerDay();
        addCalories(caloriesPerMeal, ACCEPTABLE_CALORIES_DIFFERENCE);
    }

    private void addCaloriesMin(int min) {
        addSearchCriteria(CALORIES_KEY, String.valueOf(min + "%2B"));
    }

    private void addCaloriesMax(int max) {
        addSearchCriteria(CALORIES_KEY, String.valueOf(max));
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

    private void addCaloriesMinMax(int min, int max) {
        addSearchCriteria(CALORIES_KEY, String.valueOf(min) + "-" + String.valueOf(max));
    }

    //@TODO make private when method is no longer used in Main.class
    private void addSearchCriteria(String key, String value) {
        searchCriteria.add(new RequestParameter(key, value));
    }

    public List<RequestParameter> getSearchCriteria() {
        List<RequestParameter> result = new ArrayList<>(searchCriteria);
        result.add(new RequestParameter("from", String.valueOf(offset)));
        result.add(new RequestParameter("to", String.valueOf(recipesToDownload + offset)));
        return result;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRecipesToDownload() {
        return recipesToDownload;
    }

    public void setRecipesToDownload(int recipesToDownload) {
        this.recipesToDownload = recipesToDownload;
    }
}
