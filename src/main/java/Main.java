import static spark.Spark.*;

import views.*;
import views.social.*;

import java.awt.*;
import java.net.URI;

public class Main {

//    public static void main(String[] args) {
//        NameDetector nameDetector = new NameDetector();
//    }

    public static void main(String[] args) {
        threadPool(8, 2, 300000);
        port(9000);
        staticFiles.location("/public");

        get("/", (req, res) -> new IndexView().render(req));
        get("/api/getCode/", (req, res) -> FacebookInteractor.getCode());
        get("/api/verifyCode/:code", (req, res) -> {
            String token = FacebookInteractor.getAccessToken(req.params("code"));
            res.redirect("/score/?q=" + token);
            return null;
        });
        get("/score/", (req, res) -> new ScoringView().render(req));

        // Launch Browser when application is launched.
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://127.0.0.1:" + "9000"));
            } catch (Exception e) {
            }
        }
    }
}
