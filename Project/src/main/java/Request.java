import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Request {
    private List<RequestParameter> searchCriteria = new ArrayList<>();
    private int first = 0;
    private int last = 10;

    public Request() {
        searchCriteria.add(new RequestParameter("q",""));
    }

    public void addSearchCriteria(String key, String value) {
        searchCriteria.add(new RequestParameter(key, value));
    }

    public List<RequestParameter> getSearchCriteria() {
        searchCriteria.add(new RequestParameter("from", String.valueOf(first)));
        searchCriteria.add(new RequestParameter("to", String.valueOf(last)));
        return searchCriteria;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
