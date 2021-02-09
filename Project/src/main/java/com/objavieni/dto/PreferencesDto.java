package com.objavieni.dto;

import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesDto {

    private long id;


    private UserDto userDto;


    private List<HealthLabel> allergies = new ArrayList<>();


    private List<DietLabel> dietLabels = new ArrayList<>();

    private int countMealsPerDay;

    private int countCaloriesPerDay;

    public PreferencesDto( List<HealthLabel> allergies, List<DietLabel> dietLabels, int countMealsPerDay, int countCaloriesPerDay) {
        //this.id = id;
        this.allergies = allergies;
        this.dietLabels = dietLabels;
        this.countMealsPerDay = countMealsPerDay;
        this.countCaloriesPerDay = countCaloriesPerDay;
    }

    public void addHealthLabelToPreferences(HealthLabel healthLabel) {
        allergies.add(healthLabel);
    }

    public void addDietLabelToPreferences(DietLabel dietLabel) {
        dietLabels.add(dietLabel);
    }
}
