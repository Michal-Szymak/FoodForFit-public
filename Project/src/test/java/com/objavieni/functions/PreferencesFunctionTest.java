package com.objavieni.functions;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.user.Preferences;
import org.junit.jupiter.api.Test;

import static com.objavieni.functions.PreferencesFunction.dtoToPreferences;
import static com.objavieni.functions.PreferencesFunction.preferencesToDto;
import static org.junit.jupiter.api.Assertions.*;

//TODO test quality improvement
class PreferencesFunctionTest {

    @Test
    void preferencesToDtoTest(){

        PreferencesDto preferencesDto = preferencesToDto.apply(new Preferences());

        assertEquals(preferencesDto.getClass(),PreferencesDto.class);
    }
    @Test
    void  dtoToPreferencesTest(){
        Preferences preferences = dtoToPreferences.apply(new PreferencesDto());

        assertEquals(preferences.getClass(),Preferences.class);
    }

}