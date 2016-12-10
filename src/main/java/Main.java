import views.IndexView;

import java.awt.*;
import java.net.URI;

import social.FacebookInteractor;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        threadPool(8, 2, 300000);
        port(9000);
        staticFiles.location("/public");

        get("/", (req, res) -> new IndexView().render());
        get("/api/getCode/", (req, res) -> FacebookInteractor.getCode());

        // Launch Browser when application is launched.
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://127.0.0.1:" + "9000"));
            } catch (Exception e) {
            }
        }
    }
}
