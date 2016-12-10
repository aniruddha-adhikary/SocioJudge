package views;

public class IndexView extends TemplateView {
    public IndexView() {
        this.setTemplateName("index.mustache");
        this.getContext().put("name", "Aniruddha Adhikary");
    }
}
