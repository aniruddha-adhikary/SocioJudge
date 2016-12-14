package views;

import spark.ModelAndView;
import spark.Request;

public class TemplateView extends View implements MustacheRenderable {

    @Override
    public String render(Request request) {
        return mustacheTemplateEngine.render(new ModelAndView(this.getContext(), this.getTemplateName()));
    }

}
