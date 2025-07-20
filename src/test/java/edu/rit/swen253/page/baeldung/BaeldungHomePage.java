package edu.rit.swen253.page.baeldung;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Page Object for the Baeldung Home page.
 *
 * @author Matthew Peck
 */
public class BaeldungHomePage extends AbstractPage {
    
  /**
   * A finder to extract the main body content.
   * WARN: this is fragile code
   */
  private static final By MAIN_CONTENT_FINDER =
    By.cssSelector("body.home > #wrap");

  private DomElement mainContentPanel;

  public BaeldungHomePage() {
    super();
    // validate basic page structure
    try {
      mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
    } catch (TimeoutException e) {
      fail("Main content panel not found");
    }
  }

  public SearchButton getSearchButton() {
    //return new SearchButton(mainContentPanel.findChildBy(By.cssSelector("nav.header--menu > div.container.menu-container > div.container-inner > #menu-main-menu > li.nav--menu_item.menu-search > #menu-item-196804")));
    return new SearchButton(mainContentPanel.findChildBy(By.cssSelector("nav.header--menu > div.container.menu-container > div.container-inner > #menu-main-menu > li.nav--menu_item.menu-search")));
  }

  public Searchbar getSearchbar() {
    //return new SearchButton(mainContentPanel.findChildBy(By.cssSelector("nav.header--menu > div.container.menu-container > div.container-inner > #menu-main-menu > li.nav--menu_item.menu-search > #menu-item-196804")));
    return new Searchbar(mainContentPanel.findChildBy(By.cssSelector("#big-nav > div.container > div.big-nav-content.-sections_hidden > div.row.search-row > #menu-search > form.form-inline > fieldset > div.input-group > #search")));
  }
}
