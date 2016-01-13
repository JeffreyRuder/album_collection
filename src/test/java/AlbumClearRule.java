import org.junit.rules.ExternalResource;

public class AlbumClearRule extends ExternalResource {

  protected void before() { }

  protected void after() {
    Album.clear();
    Artist.clear();
  }
}
