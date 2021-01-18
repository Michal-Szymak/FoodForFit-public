package com.objavieni.request;

import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import com.objavieni.user.Preferences;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private static final String DIET_LABEL_KEY = "diet";
    private static final String HEALTH_LABEL_KEY = "healt";
    private static final String CALORIES_KEY = "calories";
    private List<RequestParameter> searchCriteria = new ArrayList<>();
    private int first = 0;
    private int last = 10;

    public Request() {
        searchCriteria.add(new RequestParameter("app_id", "900da95e"));
        searchCriteria.add(new RequestParameter("app_key", "40698503668e0bb3897581f4766d77f9"));
        searchCriteria.add(new RequestParameter("q",""));
    }

    public void addUserPreferences(Preferences preferences) {
        for (HealthLabel healthLabel : preferences.getAllergies()) {
            addSearchCriteria(HEALTH_LABEL_KEY, healthLabel.getDescription());
        }
        for (DietLabel dietLabel : preferences.getDietLabels()) {
            addSearchCriteria(DIET_LABEL_KEY, dietLabel.getDescription());
        }
    }

    private void addCaloriesMin (int min){
        addSearchCriteria(CALORIES_KEY, String.valueOf(min+"%2B"));
    }

    private void addCaloriesMax (int max){
        addSearchCriteria(CALORIES_KEY, String.valueOf(max));
    }

    private void addCaloriesMinMax (int min, int max){
        addSearchCriteria(CALORIES_KEY, String.valueOf(min) + "-" + String.valueOf(max));
    }
    
    //@TODO make private when method is no longer used in Main.class
    public void addSearchCriteria(String key, String value) {
        searchCriteria.add(new RequestParameter(key, value));
    }

    public List<RequestParameter> getSearchCriteria() {
        List<RequestParameter> result = new ArrayList<>(searchCriteria);
        result.add(new RequestParameter("from", String.valueOf(first)));
        result.add(new RequestParameter("to", String.valueOf(last)));
        return result;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
