package generator.utils;

/**
 * @author maks
 */
public enum RequestParameter {
    ID("id"),
    PAGE("page"),
    SEARCH_WORD("search");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
