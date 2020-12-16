import java.util.List;

public class Parser {

    public static Recipe recipeParser (String s){


        System.out.println(getLabel(s, "label"));
        //System.out.println(getLabel(s, "Energy\",\"quantity"));
        System.out.println(getLabel(s, "dietLabels"));


        return null;
    }

    private static String getLabel(String s,String searchFraze) {

        String ss = s.split(searchFraze + "\":\"",2)[1].split("\"")[0];
        //System.out.println(ss);
        return ss;
    }


}
