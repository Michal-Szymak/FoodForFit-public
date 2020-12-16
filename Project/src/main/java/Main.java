import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        //ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = CreateRequest.request();


        String s = jsonNode.getObject().getJSONArray("hits").toString();
        System.out.println(s);
        Parser.recipeParser(s);

        //System.out.println(s);





        //System.out.println(jsonNode.getObject().);
    }

}
