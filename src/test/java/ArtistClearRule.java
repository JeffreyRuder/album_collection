import org.junit.rules.ExternalResource;

public class ArtistClearRule extends ExternalResource {

  protected void before() { }

  protected void after() {
    Artist.clear();
  }
}
