package views;

import spark.Request;

import java.util.HashMap;
import java.util.Map;

public abstract class View {
    private String templateName;
    private Map<String, String> context = new HashMap<String, String>();

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        if (templateName.length() < 1) {
            throw new IllegalArgumentException();
        }
        this.templateName = templateName;
    }

    public Map getContext() {
        return context;
    }

    public abstract String render(Request request);
}
