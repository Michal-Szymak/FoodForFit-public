package com.objavieni.user;

public enum DietLabel {

    BALANCED("balanced"),
    HIGH_FIBER("high-fiber"),
    HIGH_PROTEIN("high-protein"),
    LOW_CARB("low-carb"),
    LOW_FAT("low-fat"),
    LOW_SODIUM("low-sodium");


    private final String description;

    DietLabel(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
