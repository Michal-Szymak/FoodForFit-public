package com.objavieni.request;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Optional;

public class RequestManager {

    public Optional<String> getResponse(Request request) {
        Optional<HttpRequest> httpRequestOptional = createHttpRequest(request);
        if(httpRequestOptional.isPresent()) {
            return ResponseManager.getResponseStringFromServer(httpRequestOptional.get());
        } else return Optional.empty();
    }

    private static Optional<HttpRequest> createHttpRequest(Request request) {
        try {
            HttpRequest result = HttpRequest.newBuilder(createRequestURI(request)).GET().build();
            return Optional.of(result);
        }
        //TODO change to logs
        catch (URISyntaxException e) {
            System.out.println("URI Syntax Exception occurred");
            return Optional.empty();
        } catch (NullPointerException e) {
            System.out.println("com.objavieni.request.Request object is null");
            return Optional.empty();
        }
    }

    private static URI createRequestURI(Request request) throws URISyntaxException {
        //TODO move uri to properties
        URIBuilder uriBuilder = new URIBuilder("https://api.edamam.com/search");
        for (RequestParameter searchCriterion : request.getSearchCriteria()) {
            uriBuilder.addParameter(searchCriterion.getKey(), searchCriterion.getValue());
        }
        return uriBuilder.build();
    }
}
