package com.objavieni.meals;

import java.util.Map;

public class Recipe {

    private String name;
    private int calories;
    private int caloriesPer100g;
    private int totalWeight;
    private int yield;
    private String imgSrc;

    private Map<String, String[]> mapOfLabels;
    private Map<String,Integer> mapOfNutrients;

    public Recipe(String imgSrc,String name, int calories, int totalWeight
            , int yield, Map<String, Integer> mapOfNutrients
            , Map<String, String[]> mapOfLabels) {
        this.name = name;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.yield = yield;
        this.mapOfNutrients = mapOfNutrients;
        this.mapOfLabels = mapOfLabels;
        this.imgSrc = imgSrc;
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
//            sb.append(s + ": ");
            sb.append(s + ": " + mapOfLabels.get(s).length + " ");
            String[] tab = mapOfLabels.get(s);
            for (String s2 : tab) {
                sb.append(s2);
            }
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getYield() {
        return yield;
    }

    public Map<String, String[]> getMapOfLabels() {
        return mapOfLabels;
    }

    public Map<String, Integer> getMapOfNutrients() {
        return mapOfNutrients;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public int getCaloriesPerMeal() {
        return calories/yield;
    }
}