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

        get("/artists", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("artists", Artist.all());
          model.put("template", "templates/artists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/artists/:id", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          Artist artist = Artist.find(Integer.parseInt(request.params(":id")));
          model.put("artist", artist);
          model.put("albums", Album.all());
          model.put("template", "templates/albums.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/artists/:id1/:id2", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          Album album = Album.find(Integer.parseInt(request.params(":id2")));
          model.put("album", album);
          model.put("template", "templates/album-details.vtl");
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

        post("/artists/:id", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          Artist artist = Artist.find(Integer.parseInt(request.params(":id")));

          String userAlbumName = request.queryParams("albumname");
          boolean alreadyInAlbums = false;

          for (Album album : Album.all()) {
            String name = album.getName();
            if (name.equals(userAlbumName)) {
              alreadyInAlbums = true;
            }
          }

          if (!alreadyInAlbums) {
            Album newAlbum = new Album(userAlbumName, artist);
          }

          model.put("artist", artist);
          model.put("failedToAdd", alreadyInAlbums);
          model.put("albums", Album.all());

          model.put("template", "templates/albums.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
