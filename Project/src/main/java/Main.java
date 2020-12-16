import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static final int MAX=10;

    public static void main(String[] args) {


        //String s = CreateRequest.request(1,0);

        List<Recipe> recipeList = new ArrayList<>();

        for(int i = 0; i < MAX; i++){
            String s = CreateRequest.request(i+1,i);
            Recipe recipe = Parser.recipeParser(s);
            recipeList.add(recipe);
        }

        recipeList.forEach(System.out::println);


        //Recipe recipe = Parser.recipeParser(s);








        //System.out.println(jsonNode.getObject().);
    }

}
