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
  private static final By SEARCH_BUTTON_FINDER =
    By.id("menu-item-196804");
  private static final By SEARCH_BAR_FINDER =
    By.id("search");

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


  public BaeldungSearchButton getSearchButton() {
    return new BaeldungSearchButton(findOnPage(SEARCH_BUTTON_FINDER));
  }

  public BaeldungSearchbar getSearchbar() {
    return new BaeldungSearchbar(findOnPage(SEARCH_BAR_FINDER));
  }
}
