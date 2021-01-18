package aplication;

import meals.Meal;
import meals.Recipe;
import parsing.Parser;
import request.Request;
import request.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int MAX = 10;

    public static void main(String[] args) {

        Request myRequest = new Request();
        myRequest.addSearchCriteria("calories", "600");
        myRequest.addSearchCriteria("nutrients%5BCHOCDF%5D", "2");
        myRequest.addSearchCriteria("nutrients%5BFAT%5D", "40-50");
        myRequest.addSearchCriteria("nutrients%5BPROCNT%5D", "50%2B");
        myRequest.setFirst(1);
        myRequest.setLast(10);

        List<String> responseList = new ArrayList<>();
        responseList = RequestManager.getResponse(myRequest);

        List<Recipe> recipeList = new ArrayList<>();
        for (String s : responseList) {
            recipeList.add(Parser.recipeParser(s));
        }
        for (Recipe recipe : recipeList){
            System.out.println(recipe);
            System.out.println(new Meal(recipe));
        }
    }
}