package com.objavieni.parsing;

import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.meals.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//TODO try to change for parsing with getForObject - opcionalnie
public class RecipeParser {

    public List<Recipe> getRecipeListFromString(String apiResponse) throws InvalidApiResponseException {
        JSONArray jsonArray = new JSONObject(apiResponse).getJSONArray("hits");
        if(jsonArray.isEmpty()) {
            throw new InvalidApiResponseException();
        }

        List<Recipe> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(getRecipeFromJsonObject(jsonArray.getJSONObject(i).getJSONObject("recipe")));
        }
        return result;
    }

    private Recipe getRecipeFromJsonObject(JSONObject recipeJson) {
        String imageSource = recipeJson.getString("image");
        String name = recipeJson.getString("label");
        int calories = recipeJson.getInt("calories");
        int totalWeight = recipeJson.getInt("totalWeight");
        int yield = recipeJson.getInt("yield");

        Map<String, Integer> mapOfNutrients = getMapOfNutrients(recipeJson);

        Map<String, List<String>> mapOfLabels = new HashMap<>();
        mapOfLabels.put("Health Labels",
                getListOfStringsFromJSONArray(recipeJson.getJSONArray("healthLabels")));
        mapOfLabels.put("Diet Labels",
                getListOfStringsFromJSONArray(recipeJson.getJSONArray("dietLabels")));

        List<String> ingredients = getListOfStringsFromJSONArray(recipeJson.getJSONArray("ingredientLines"));
        return new Recipe(imageSource, name, calories, totalWeight, yield, mapOfNutrients, mapOfLabels, ingredients);
    }

    private Map<String, Integer> getMapOfNutrients(JSONObject jsonObject) {
        Map<String, Integer> result = new HashMap<>();
        JSONObject totalNutrients = jsonObject.getJSONObject("totalNutrients");

        result.put("Fat", totalNutrients.getJSONObject("FAT").getInt("quantity"));
        result.put("Carbs", totalNutrients.getJSONObject("CHOCDF").getInt("quantity"));
        result.put("Protein", totalNutrients.getJSONObject("PROCNT").getInt("quantity"));
        return result;
    }

    private List<String> getListOfStringsFromJSONArray(JSONArray array) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            result.add(array.getString(i));
        }
        return result;
    }

}