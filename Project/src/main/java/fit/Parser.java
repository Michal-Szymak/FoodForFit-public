package fit;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Recipe recipeParser (String text){
        if (text != null) {
            String label = getLabel(text, "label");
            int calories = getIntLabels(text, "calories");
            int weight = getIntLabels(text, "totalWeight");
            int fat = getIntLabels(text, "Fat\",\"quantity");
            int carbs = getIntLabels(text, "Carbs\",\"quantity");
            int protein = getIntLabels(text, "Protein\",\"quantity");
            int yield = getIntLabels(text,"yield");

            Map<String, Integer> mapOfNutrients = new HashMap<>();
            mapOfNutrients.put("Fat", fat);
            mapOfNutrients.put("Carbs", carbs);
            mapOfNutrients.put("Protein", protein);

            String[] arrayOfHealthLabels = getArrayOfLabel(text, "healthLabels");
            String[] arrayOfDietLabels = getArrayOfLabel(text, "dietLabels");
            String[] arrayOfIngredients = getArrayOfLabel(text, "ingredientLines");

            Map<String, String[]> mapOfLabels = new HashMap<>();
            mapOfLabels.put("Ingredients", arrayOfIngredients);
            mapOfLabels.put("Health Labels", arrayOfHealthLabels);
            mapOfLabels.put("Diet Labels", arrayOfDietLabels);

            return new Recipe(label, calories, weight, yield, mapOfNutrients, mapOfLabels);
        }
        return null;
    }

    private static String getLabel (String text, String searchFraze) {
        String label = "";
        if (text.contains(searchFraze + "\":\"")) {
            label = text.split(searchFraze + "\":\"", 2)[1].split("\"")[0];
        }
        return label;
    }

        private static int getIntLabels (String text, String searchFraze) {
        String label = "0";
        if (text.contains(searchFraze + "\":")) {
            label = text.split(searchFraze + "\":", 2)[1].split(",")[0];
        }
        return (int) Double.parseDouble(label);
    }
    private static String[] getArrayOfLabel(String text,String searchFraze){
        String[] array = new String[0];
        if (text.contains(searchFraze)) {
            array = text.split("\"" + searchFraze + "\":\\[", 2)[1]
                    .split("],", 2)[0]
                    .split(",");
        }
        return array;
    }
}
