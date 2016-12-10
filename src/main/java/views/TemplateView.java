package views;

import spark.ModelAndView;

public class TemplateView extends View implements MustacheRenderable {

    @Override
    public String render() {
        return mustacheTemplateEngine.render(new ModelAndView(this.getContext(), this.getTemplateName()));
    }

}
