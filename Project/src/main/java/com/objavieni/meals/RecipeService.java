package com.objavieni.meals;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.parsing.Parser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class RecipeService {
    Request myRequest = new Request();
    PreferencesDto userPreferences;
    RequestManager requestManager = new RequestManager();
    List<String> responseList;
    List<Recipe> recipeList = new ArrayList<>(1000);

    public RecipeService(PreferencesDto userPreferences) {
        this.userPreferences = userPreferences;
        load();
    }

    public void load(){
        myRequest.addUserPreferences(userPreferences);
        myRequest.setOffset(0);
        myRequest.setRecipesToDownload(100);
        responseList = new ArrayList<>();
        responseList = requestManager.getResponse(myRequest);
        for (String s : responseList) {
            recipeList.add(new Parser().recipeParser(s));
        }
        log.info("recipe list size : " + recipeList.size());
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setNumberOfRecipiesToBeDownloaded(int number){
        myRequest.setRecipesToDownload(number);
    }

}
