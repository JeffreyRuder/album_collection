import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
      staticFileLocation("/public");
      String layout = "templates/layout.vtl";

        //RESTful ARCHITECTURE
        //Use POST to create something on the server
        //Use GET to retrieve something from the server
        //Use PUT to change or update something on the server
        //Use DELETE to remove or delete something on the server
        //Keep URLs intuitive
        //Each request from client contains all info necessary for that request

        //ROUTES: Home Page

        get("/", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/home.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //ROUTES: Identification of Resources

        get("artists/new", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/artists-new.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //ROUTES: Changing Resources

        post("/artists", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          String userArtistName = request.queryParams("artistname");
          boolean alreadyInArtists = false;

          for (Artist artist : Artist.all()) {
            String name = artist.getName();
            if (name.equals(userArtistName)) {
              alreadyInArtists = true;
            }
          }

          if (!alreadyInArtists) {
            Artist newArtist = new Artist(userArtistName);
          }

          model.put("failedToAdd", alreadyInArtists);
          model.put("artists", Artist.all());

          model.put("template", "templates/artists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
