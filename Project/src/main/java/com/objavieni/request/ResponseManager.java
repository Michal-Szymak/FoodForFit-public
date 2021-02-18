package com.objavieni.request;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Slf4j
public class ResponseManager {

    public static Optional<String> getResponseStringFromServer(HttpRequest request) {
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response.body());
        } catch (IOException e) {
            log.info(e.getClass().getName() + "occured");
            return Optional.empty();
        } catch (InterruptedException e) {
            log.info(e.getClass().getName() + "occured");
            return Optional.empty();
        }
    }
}
