import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int MAX=10;

    public static void main(String[] args) {

        List<Recipe> recipeList = new ArrayList<>();

        for(int i = 0; i < MAX; i++){
            String s = CreateRequest.request(i+1,i);
            Recipe recipe = Parser.recipeParser(s);
            recipeList.add(recipe);
        }
        recipeList.forEach(System.out::println);
    }
}
