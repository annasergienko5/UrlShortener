package UrlShortener;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        post("/urlShortener", (request, response) -> {
            String body = request.body();
            Service service = new Service();
            return service.processShortener(body);
        });

        get("/url/:uuid", (request, response) -> {
            Service service = new Service();
            String uuid = request.params(":uuid");
            response.redirect(service.processRedirect(uuid));
            return null;
        });
    }
}