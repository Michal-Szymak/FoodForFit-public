import java.util.Map;

public class Recipe {

    String name;
    int calories;
    int caloriesPer100g;
    int totalWeight;
    int yield;

    Map<String, String[]> mapOfLabels;
    Map<String,Integer> mapOfNutrients;

    public Recipe(String name, int calories, int totalWeight
            , int yield, Map<String, Integer> mapOfNutrients
            , Map<String, String[]> mapOfLabels) {
        this.name = name;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.yield = yield;
        this.mapOfNutrients = mapOfNutrients;
        this.mapOfLabels = mapOfLabels;

        caloriesPer100g = (int) ((calories / (totalWeight / 100.0)) + 0.5);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder()
                .append("//////////////////////////////////////////////////////////////////" + System.lineSeparator())
                .append(name +
                        " : calories=" + calories +
                        ", caloriesPer100g=" + caloriesPer100g +
                        ", yield=" + yield +
                        ", totalWeight=" + totalWeight)
                ;
        sb.append(System.lineSeparator());

        for (String s : mapOfNutrients.keySet()) {
            sb.append(s + " : " + mapOfNutrients.get(s) + System.lineSeparator());
        }
        for (String s : mapOfLabels.keySet()) {
            sb.append(s + ": ");
            String[] tab = mapOfLabels.get(s);
            for (String s2 : tab) {
                sb.append(s2);
            }
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}