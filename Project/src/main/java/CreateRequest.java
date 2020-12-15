import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class CreateRequest {


    public static JsonNode request(){

        // UNIREST DOCUMENTATION http://kong.github.io/unirest-java/#requests

        HttpResponse<JsonNode> response = Unirest.get("https://edamam-recipe-search.p.rapidapi.com/search?q=chicken")
                .header("x-rapidapi-key", "7b9dae1264mshdb56ed59fe4a2ebp101ca9jsn43fd774e7354")
                .header("x-rapidapi-host", "edamam-recipe-search.p.rapidapi.com")
                .asJson();

        return response.getBody();

    }


}
