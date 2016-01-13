import org.junit.*;
import static org.junit.Assert.*;

public class AlbumTest {

  @Rule
  public AlbumClearRule clearRule = new AlbumClearRule();

  @Test
  public void album_instantiatesCorrectly_true() {
    Album album = new Album("The White Album", new Artist("The Beatles"));
    assertEquals(true, album instanceof Album);
  }

  @Test
  public void album_hasName_WhiteAlbum() {
    Album album = new Album("The White Album", new Artist("The Beatles"));
    String expected = "The White Album";
    assertEquals(expected, album.getName());
  }

  @Test
  public void album_hasArtist_Beatles() {
    Artist expected = new Artist("The Beatles");
    Album album = new Album("The White Album", expected);
    assertEquals(expected, album.getArtist());
  }

  @Test
  public void all_returnsAllAlbums_true() {
    Album album1 = new Album("The White Album", new Artist("The Beatles"));
    Album album2 = new Album("Out of Our Heads", new Artist("The Rolling Stones"));
    assertTrue(Album.all().contains(album1));
    assertTrue(Album.all().contains(album2));
  }

  @Test
  public void find_returnsAlbumWithCorrectID_album2() {
    Album album1 = new Album("The White Album", new Artist("The Beatles"));
    Album album2 = new Album("Out of Our Heads", new Artist("The Rolling Stones"));
    assertEquals(Album.find(album2.getId()), album2);
  }

  @Test
  public void find_returnsNullWhenNoInstances_null() {
    assertTrue(Album.find(1) == null);
  }

  @Test
  public void clear_emptiesAlbumFromArrayList() {
    Album album1 = new Album("The White Album", new Artist("The Beatles"));
    Album.clear();
    assertEquals(Album.all().size(), 0);
  }

}
