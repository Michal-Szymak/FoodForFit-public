package fit;

public class RequestParameter {
    public String key;
    public String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public RequestParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
