package com.objavieni.functions;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.user.Preferences;

import java.util.function.Function;

public class PreferencesFunction {
    public static final Function<Preferences, PreferencesDto> preferencesToDto = preferences -> new PreferencesDto(
            preferences.getAllergies(),
            preferences.getDietLabels(),
            preferences.getCountMealsPerDay(),
            preferences.getCountCaloriesPerDay()
    );
    public static final Function<PreferencesDto,Preferences> dtoToPreferences = preferencesDto -> new Preferences(
            preferencesDto.getAllergies(),
            preferencesDto.getDietLabels(),
            preferencesDto.getCountMealsPerDay(),
            preferencesDto.getCountCaloriesPerDay()
    );
}
