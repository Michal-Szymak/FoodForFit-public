import java.util.HashMap;
import java.util.Map;

class Request {
    private Map<String, String> searchCriteria = new HashMap<>();
    private int first = 0;
    private int last = 10;

    public Request() {
        searchCriteria.put("q", "");
    }

    public void addSearchCriteria(String key, String value) {
        searchCriteria.put(key, value);
    }

    public Map<String, String> getSearchCriteria() {
        searchCriteria.put("from", String.valueOf(first));
        searchCriteria.put("to", String.valueOf(last));
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
