package request;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class RequestComunicator {

    public static List<String> getResponse(Request request) {
        String responseString = getResponseString(request);
        return separateResponseToList(responseString);
    }

    private static String getResponseString(Request request) {
        String searchParameters = getRequestParametersAsString(request);

        HttpResponse<JsonNode> response = Unirest.get(
                "https://edamam-recipe-search.p.rapidapi.com/search?" + searchParameters)
                .header("x-rapidapi-key", "7b9dae1264mshdb56ed59fe4a2ebp101ca9jsn43fd774e7354")
                .header("x-rapidapi-host", "edamam-recipe-search.p.rapidapi.com")
                .asJson();
        JsonNode jsonNode = response.getBody();
        String s = jsonNode.getObject().getJSONArray("hits").toString();
        return s;
    }

    private static String getRequestParametersAsString(Request request) {
        StringBuilder result = new StringBuilder();
        for(RequestParameter r : request.getSearchCriteria()){
            result.append(r.getKey());
            result.append("=");
            result.append(r.getValue());
            result.append("&");
        }
        return result.toString();
    }

    private static List<String> separateResponseToList (String response) {
        ArrayList<String> result = new ArrayList<>();
        String[] separatedResponse = response.split("\"recipe\"");
        for(int i = 1; i < separatedResponse.length; i++) {
            result.add(separatedResponse[i]);
        }
        return result;
    }

}
