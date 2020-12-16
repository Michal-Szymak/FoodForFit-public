import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class CreateRequest {


    public static String request(int to,int from){

        // UNIREST DOCUMENTATION http://kong.github.io/unirest-java/#requests

    String name = "soup";

        HttpResponse<JsonNode> response = Unirest.get(
                //"https://edamam-recipe-search.p.rapidapi.com/search?to=" + to + "&from=" + from)
                "https://edamam-recipe-search.p.rapidapi.com/search?q=" + name + "&to=" + to + "&from=" + from)
                .header("x-rapidapi-key", "7b9dae1264mshdb56ed59fe4a2ebp101ca9jsn43fd774e7354")
                .header("x-rapidapi-host", "edamam-recipe-search.p.rapidapi.com")
                .asJson();
        JsonNode jsonNode = response.getBody();
        String s = jsonNode.getObject().getJSONArray("hits").toString();
        //String s = jsonNode.getObject().toString();
        return s;

    }


}
