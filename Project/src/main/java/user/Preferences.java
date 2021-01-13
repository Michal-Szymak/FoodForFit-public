package user;

import java.util.ArrayList;
import java.util.List;

public class Preferences {
    private List<HealthLabel> alergy = new ArrayList<>();
    private List<DietLabel> dietLabel = new ArrayList<>();

    private int countMealsPerDay;
    private int countColouriesPerDay;

    public List<HealthLabel> getAlergy() {
        return alergy;
    }

    public List<DietLabel> getDietLabel() {
        return dietLabel;
    }

    public int getCountMealsPerDay() {
        return countMealsPerDay;
    }

    public int getCountColouriesPerDay() {
        return countColouriesPerDay;
    }
}
