import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test public void homePageTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Album Collection");
  }

  @Test
  public void addNewArtistPageTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Artist"));
    assertThat(pageSource()).contains("Add an Artist");
  }

  @Test
  public void listsArtistsPageTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Artist"));
    fill("#artistname").with("The Beatles");
    submit(".btn");
    assertThat(pageSource()).contains("The Beatles");
  }

  @Test
  public void listsMultipleArtistsPageTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Rolling Stones");
    submit(".btn");
    assertThat(pageSource()).contains("The Beatles");
    assertThat(pageSource()).contains("The Rolling Stones");
  }

  @Test
  public void listsSameArtistTwiceErrorTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    assertThat(pageSource()).contains("This artist is already entered.");
  }

  @Test
  public void rendersArtistPageTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    click("a", withText("The Beatles"));
    assertThat(pageSource()).contains("The Beatles");
  }

  @Test
  public void listsAlbumsTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    click("a", withText("The Beatles"));
    fill("#albumname").with("The White Album");
    submit("#albumsubmit");
    assertThat(pageSource()).contains("The White Album");
  }

  @Test
  public void listsSameAlbumTwiceErrorTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    click("a", withText("The Beatles"));
    fill("#albumname").with("The White Album");
    submit("#albumsubmit");
    fill("#albumname").with("The White Album");
    submit("#albumsubmit");
    assertThat(pageSource()).contains("This album is already entered.");
  }

  @Test
  public void rendersAlbumPageTest() {
    goTo("http://localhost:4567/artists/new");
    fill("#artistname").with("The Beatles");
    submit(".btn");
    click("a", withText("The Beatles"));
    fill("#albumname").with("The White Album");
    submit("#albumsubmit");
    click("a", withText("The White Album"));
    assertThat(pageSource()).contains("Album Details: The White Album");
  }
}
