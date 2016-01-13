import org.junit.*;
import static org.junit.Assert.*;

public class ArtistTest {

  @Rule
  public ArtistClearRule clearRule = new ArtistClearRule();

  @Test
  public void artist_instantiatesCorrectly_true() {
    Artist artist = new Artist("The Beatles");
    assertEquals(true, artist instanceof Artist);
  }

  @Test
  public void artist_hasName_Beatles() {
    Artist artist = new Artist("The Beatles");
    String expected = "The Beatles";
    assertEquals(expected, artist.getName());
  }

  @Test
  public void all_returnsAllArtists_true() {
    Artist artist1 = new Artist("The Beatles");
    Artist artist2 = new Artist("Michael Jackson");
    assertTrue(Artist.all().contains(artist1));
    assertTrue(Artist.all().contains(artist2));
  }

  @Test
  public void find_returnsArtistWithCorrectID_artist2() {
    Artist artist1 = new Artist("The Beatles");
    Artist artist2 = new Artist("Michael Jackson");
    assertEquals(Artist.find(artist2.getId()), artist2);
  }

  @Test
  public void find_returnsNullWhenNoInstances_null() {
    assertTrue(Artist.find(1) == null);
  }

  @Test
  public void clear_emptiesArtistsFromArrayList() {
    Artist artist = new Artist("The Beatles");
    Artist.clear();
    assertEquals(Artist.all().size(), 0);
  }

}
