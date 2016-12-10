package views;

import spark.template.mustache.MustacheTemplateEngine;

public interface Renderable {
    public static MustacheTemplateEngine mustacheTemplateEngine = new MustacheTemplateEngine();
}
