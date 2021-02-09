package com.objavieni.service;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.repository.PreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.objavieni.functions.PreferencesFunction.dtoToPreferences;
import static com.objavieni.functions.PreferencesFunction.preferencesToDto;

@RequiredArgsConstructor
@Service
public class PreferencesService {
    private final PreferencesRepository preferencesRepository;

    public PreferencesDto save(PreferencesDto preferencesDto){
        return preferencesToDto.apply(preferencesRepository.save(dtoToPreferences.apply(preferencesDto)));
    }
}
