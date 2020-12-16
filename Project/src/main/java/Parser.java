import java.util.List;

public class Parser {

    public static Recipe recipeParser (String s){

        String label = getLabel(s, "label");
        int calories = getIntLabels(s, "calories");
        int weight =  getIntLabels(s, "totalWeight");
        String[] tab = getArrayOfLabel(s, "healthLabels");
        int fat = getIntLabels(s, "Fat\",\"quantity");
        return new Recipe(label,calories,weight,fat,tab);
    }

    private static String getLabel(String s,String searchFraze) {

        String label = s.split(searchFraze + "\":\"", 2)[1].split("\"")[0];

        return label;
    }
        private static int getIntLabels(String s,String searchFraze) {

            String label = s.split(searchFraze + "\":",2)[1].split(",")[0];

            return (int) Double.parseDouble(label);
    }
    private static String[] getArrayOfLabel(String s,String searchFraze){

        String[] array;
            String ss = s.split("\"" + searchFraze + "\":\\[",2)[1]
                    .split("],", 2)[0];
            array = ss.split(",");


        return array;
    }


}
