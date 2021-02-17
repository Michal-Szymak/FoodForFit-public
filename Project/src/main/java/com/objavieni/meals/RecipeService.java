package com.objavieni.meals;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.parsing.RecipeParser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class RecipeService {
    //TODO change to DI
    //TODO magic numbers!!! :)
    Request myRequest = new Request();
    PreferencesDto userPreferences;
    RequestManager requestManager = new RequestManager();
    List<String> responseList;
    List<Recipe> recipeList = new ArrayList<>(100);

//    public RecipeService(PreferencesDto userPreferences) throws InvalidApiResponseException {
//        this.userPreferences = userPreferences;
//        load();
//    }

    public RecipeService() throws InvalidApiResponseException {
       // load();
    }

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
