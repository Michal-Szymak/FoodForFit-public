package com.objavieni.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preferences")
public class Preferences {

    @Id
    @GeneratedValue
    private long id;
    private User user;
    private List<HealthLabel> allergies = new ArrayList<>();
    private List<DietLabel> dietLabels = new ArrayList<>();

    private int countMealsPerDay;
    private int countCaloriesPerDay;

    public Preferences(List<HealthLabel> allergies, List<DietLabel> dietLabels, int countMealsPerDay, int countCaloriesPerDay) {
        this.allergies = allergies;
        this.dietLabels = dietLabels;
        this.countMealsPerDay = countMealsPerDay;
        this.countCaloriesPerDay = countCaloriesPerDay;
    }

    public Preferences() {
    }

    public void setCountMealsPerDay(int countMealsPerDay) {
        this.countMealsPerDay = countMealsPerDay;
    }

    public void setCountCaloriesPerDay(int countCaloriesPerDay) {
        this.countCaloriesPerDay = countCaloriesPerDay;
    }

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

    public void setAllergies(List<HealthLabel> allergies) {
        this.allergies = allergies;
    }

    public void setDietLabels(List<DietLabel> dietLabels) {
        this.dietLabels = dietLabels;
    }
}
