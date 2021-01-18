package com.objavieni.request;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestManager {

    public static List<String> getResponse(Request request) {
        List<String> result = new ArrayList<>();
        Optional<HttpRequest> httpRequestOptional = createHttpRequest(request);
        if(httpRequestOptional.isPresent()) {
            result = ResponseManager.getResponse(httpRequestOptional.get());
        }
        return result;
    }

    private static Optional<HttpRequest> createHttpRequest(Request request) {
        try {
            HttpRequest result = HttpRequest.newBuilder(createRequestURI(request)).GET().build();
            return Optional.of(result);
        } catch (URISyntaxException e) {
            System.out.println("URI Syntax Exception occurred");
            return Optional.empty();
        }
    }

    private static URI createRequestURI(Request request) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://api.edamam.com/search");
        for (RequestParameter searchCriterion : request.getSearchCriteria()) {
            uriBuilder.addParameter(searchCriterion.getKey(), searchCriterion.getValue());
        }
        return uriBuilder.build();
    }

}
