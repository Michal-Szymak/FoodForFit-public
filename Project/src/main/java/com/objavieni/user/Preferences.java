package com.objavieni.user;

import java.util.ArrayList;
import java.util.List;

public class Preferences {
    private List<HealthLabel> allergies = new ArrayList<>();
    private List<DietLabel> dietLabels = new ArrayList<>();

    private int countMealsPerDay;
    private int countCaloriesPerDay;


    public void addHealthLabelToPreferences(HealthLabel healthLabel) {
        allergies.add(healthLabel);
    }

    public void addDietLabelToPreferences(DietLabel dietLabel) {
        dietLabels.add(dietLabel);
    }

    public List<HealthLabel> getAllergies() {
        return allergies;
    }

    public List<DietLabel> getDietLabels() {
        return dietLabels;
    }

    public int getCountMealsPerDay() {
        return countMealsPerDay;
    }

    public int getCountCaloriesPerDay() {
        return countCaloriesPerDay;
    }
}
