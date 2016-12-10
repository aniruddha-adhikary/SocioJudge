import views.IndexView;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        threadPool(8, 2, 30000);
        port(9000);
        staticFiles.location("/public");

        get("/:name/", (req, res) -> new IndexView().render());
    }
}
