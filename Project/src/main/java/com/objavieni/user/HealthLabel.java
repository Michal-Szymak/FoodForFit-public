package com.objavieni.user;


import javax.persistence.Table;

@Table(name = "health_label")
public enum HealthLabel {

    ALCOHOL_FREE("alcohol-free"),
    IMMUNE_SUPPORTIVE("immuno-supportive"),
    CELERY_FREE("celery-free"),
    CRUSTCEAN_FREE("crustacean-free"),
    DAIRY("dairy-free"),
    EGGS("egg-free"),
    FISH("fish-free"),
    FODMAP_FREE("fodmap-free"),
    GLUTEN("gluten-free"),
    KETO("keto-friendly"),
    KIDNEY_FRIENDLY("kidney-friendly"),
    KOSHER("kosher"),
    LOW_POTASSIUM("low-potassium"),
    LUPINE_FREE("lupine-free"),
    MUSTARD_FREE("mustard-free"),
    LOW_FAT_ABS("low-fat-abs"),
    NO_OIL_ADDED("No-oil-added"),
    NO_SUGAR("low-sugar"),
    PALEO("paleo"),
    PEANUTS("peanut-free"),
    PESCATARIAN("pecatarian"),
    PORK_FREE("pork-free"),
    RED_MEAT_FREE("red-meat-free"),
    SESAME_FREE("sesame-free"),
    SHELLFISH("shellfish-free"),
    SOY("soy-free"),
    SUGAR_CONSCIOUS("sugar-conscious"),
    TREE_NUTS("tree-nut-free"),
    VEGAN("vegan"),
    VEGETARIAN("vegetarian"),
    WHEAT_FREE("wheat-free");

    private final String description;

    HealthLabel(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
