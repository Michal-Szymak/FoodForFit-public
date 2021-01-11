package meals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeeklyMeals implements IngredientsCollector{
    List<DailyMeals> weeklyMeals;

    public WeeklyMeals() {
    }
    void addDailyMeals(DailyMeals dailyMeals){
        weeklyMeals.add(dailyMeals);
    }

    public String[] getIngredients(){
        List<String> list = new ArrayList<>();
        weeklyMeals.forEach(x -> list.addAll(Arrays.asList(x.getIngredients())));
        return list.toArray(new String[0]);
    }
}
