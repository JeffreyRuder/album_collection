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
}
