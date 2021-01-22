package com.objavieni.meals;

import com.objavieni.parsing.Parser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import com.objavieni.user.Preferences;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class RecipeService {
    Request myRequest = new Request();
    Preferences userPreferences = new Preferences();
    RequestManager requestManager = new RequestManager();
    List<String> responseList;
    List<Recipe> recipeList = new ArrayList<>(1000);

    public RecipeService(Request myRequest, RequestManager requestManager) {
        this.myRequest = myRequest;

        this.requestManager = requestManager;
        load();
        
    }

    public RecipeService() {
        load();
    }

    public void load(){
        myRequest.addCaloriesMinMax(200,10000);
        myRequest.addUserPreferences(userPreferences);
        myRequest.setFirst(0);
        myRequest.setLast(100);
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

    public Preferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(Preferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void setNumberOfRecipiesToBeDownloaded(int number){
        myRequest.setLast(number);
    }

    public void setNumberOfRecipiesToBeDownloadedWithOffset(int number, int offset){
        myRequest.setFirst(number);
        myRequest.setLast(number + offset);
    }
}
