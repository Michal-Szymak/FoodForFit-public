package user;

import java.util.ArrayList;
import java.util.List;

public class Preferences {
    List<HealthLabel> alergy = new ArrayList<>();
    List<DietLabel> dietLabel = new ArrayList<>();

    private int countMealsPerDay;
    private int countColouriesPerDay;

    public Preferences(int countMealsPerDay, int countColouriesPerDay) {
        this.countMealsPerDay = countMealsPerDay;
        this.countColouriesPerDay = countColouriesPerDay;
    }

    public int getCountMealsPerDay() {
        return countMealsPerDay;
    }

    public int getCountColouriesPerDay() {
        return countColouriesPerDay;
    }
}
