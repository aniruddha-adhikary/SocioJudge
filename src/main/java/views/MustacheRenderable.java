package views;

import spark.template.mustache.MustacheTemplateEngine;

public interface MustacheRenderable {
    public static MustacheTemplateEngine mustacheTemplateEngine = new MustacheTemplateEngine();
}
