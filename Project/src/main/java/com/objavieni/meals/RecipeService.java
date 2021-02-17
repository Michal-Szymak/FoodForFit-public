package com.objavieni.meals;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.parsing.RecipeParser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeService {

    private final RequestManager requestManager;
    private final Request myRequest;

    PreferencesDto userPreferences;

    List<Recipe> recipeList = new ArrayList<>();

    public void load() throws InvalidApiResponseException {
        myRequest.addUserPreferences(userPreferences);
        myRequest.setOffset(0);
        Optional<String> responseOptional = requestManager.getResponse(myRequest);
        if (responseOptional.isPresent()) {
            recipeList = new RecipeParser().getRecipeListFromString(responseOptional.get());
        }
        log.info("recipe list size : " + recipeList.size());
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setPreferences(PreferencesDto preferencesDto) throws InvalidApiResponseException {
        this.userPreferences = preferencesDto;
        load();
    }
}
