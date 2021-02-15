package com.objavieni.meals;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.error.InvalidApiResponseException;
import com.objavieni.parsing.RecipeParser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RecipeService {
    Request myRequest = new Request();
    PreferencesDto userPreferences;
    RequestManager requestManager = new RequestManager();
    List<String> responseList;
    List<Recipe> recipeList = new ArrayList<>(1000);

    public RecipeService(PreferencesDto userPreferences) throws InvalidApiResponseException {
        this.userPreferences = userPreferences;
        load();
    }

//    public void load(){
//        myRequest.addUserPreferences(userPreferences);
//        myRequest.setOffset(0);
//        myRequest.setRecipesToDownload(100);
//        responseList = new ArrayList<>();
//        responseList = requestManager.getResponse(myRequest);
//        for (String s : responseList) {
//            recipeList.add(new Parser().recipeParser(s));
//        }
//        log.info("recipe list size : " + recipeList.size());
//    }

    public void load() throws InvalidApiResponseException {
        myRequest.addUserPreferences(userPreferences);
        myRequest.setOffset(0);
        myRequest.setRecipesToDownload(100);
        Optional<String> responseOptional = requestManager.getResponseTwo(myRequest);
        if (responseOptional.isPresent()) {
            recipeList = new RecipeParser().getRecipeListFromString(responseOptional.get());
        }
        log.info("recipe list size : " + recipeList.size());
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setNumberOfRecipiesToBeDownloaded(int number) {
        myRequest.setRecipesToDownload(number);
    }

}
