package generator.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maks
 */

public class PagerLink {
    private Map<String, String> parameters = new HashMap<String, String>();

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    public String buildParametersLink() {
        StringBuilder link = new StringBuilder();
        boolean first = true;
        for (String paramName : parameters.keySet()) {
            if (first) {
                link.append("?");
                first = false;
            } else {
                link.append("&");
            }
            link.append(paramName)
                .append("=")
                .append(parameters.get(paramName));
        }
        return link.toString();
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PagerLink{");
        sb.append("parameters=").append(parameters);
        sb.append('}');
        return sb.toString();
    }
}

