package meals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyMeals implements IngredientsCollector{
    List<Meal> mealList = new ArrayList<>();

    public DailyMeals() {
    }



    public DailyMeals(List<Meal> mealList) {
        this.mealList = mealList;
    }
    public String[] getIngredients(){
        List<String> list = new ArrayList<>();
        mealList.forEach(x -> list.addAll(Arrays.asList(x.getIngredients())));
        return list.toArray(new String[0]);
    }
    public void addMeal(Meal meal){
        mealList.add(meal);
    }
    public int getCalories(){
        int cal = 0;
        for (Meal meal : mealList) {
            cal += meal.getCalories();
        }
        return cal;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < mealList.size(); i++) {
            text.append(mealList.get(i).recipe.name + ", ");
        }
        return text.toString();
    }
}
