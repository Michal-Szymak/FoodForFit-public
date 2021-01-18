package request;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ResponseManager {

    public static List<String> getResponse(HttpRequest request) {
        Optional<String> response = getResponseStringFromServer(request);
        return response.isPresent() ? separateResponseToList(response.get()) : Collections.emptyList();
    }

    private static List<String> separateResponseToList(String response) {
        List<String> result = new ArrayList<>();
        String[] separatedResponse = response.split("\"recipe\"");
        for (int i = 1; i < separatedResponse.length; i++) {
            result.add(separatedResponse[i]);
        }
        return result;
    }

    private static Optional<String> getResponseStringFromServer(HttpRequest request) {
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response.body());
        } catch (IOException e) {
            System.out.println(e.getClass().getName() + "occured");
            return Optional.empty();
        } catch (InterruptedException e) {
            System.out.println(e.getClass().getName() + "occured");
            return Optional.empty();
        }
    }

    //    public static List<String> getResponse(HttpRequest request) {
//        ArrayList<String> result = new ArrayList<>();
//        Optional<String> response = getResponseStringFromServer(request);
//        if(response.isPresent()) {
//            String[] separatedResponse = response.get().split("\"recipe\"");
//            for (int i = 1; i < separatedResponse.length; i++) {
//                result.add(separatedResponse[i]);
//            }
//        }
//        return result;
//    }

    //    public static List<String> getResponse(HttpRequest request) {
//        Optional<String> response = getResponseStringFromServer(request);
//        if (response.isPresent()) {
//            return separateResponseToList(response.get());
//        } else {
//            return Collections.emptyList();
//        }
//    }

}
