package fit;

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
        myRequest.setFirst(2);
        myRequest.setLast(7);

        List<String> responseList = new ArrayList<>();
        responseList = RequestComunicator.getResponse(myRequest);

        List<Recipe> recipeList = new ArrayList<>();
        for (String s : responseList) {
            recipeList.add(Parser.recipeParser(s));
        }
        recipeList.forEach(System.out::println);
    }
}
