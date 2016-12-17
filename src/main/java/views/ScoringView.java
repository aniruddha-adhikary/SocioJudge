package views;

import core.Profile;
import core.Score;
import spark.ModelAndView;
import spark.Request;
import views.social.FacebookInteractor;

import java.util.Map;

public class ScoringView extends TemplateView {

    @Override
    public String render(Request request) {
        this.setTemplateName("score.mustache");
        String accessToken =  request.queryParams("q");

        Profile profile = FacebookInteractor.getProfile(accessToken);
        Score score = profile.score();
        this.getContext().putAll(score.getScoreMap());
        this.getContext().put("name", profile.getName());

        return super.render(request);
    }
}
