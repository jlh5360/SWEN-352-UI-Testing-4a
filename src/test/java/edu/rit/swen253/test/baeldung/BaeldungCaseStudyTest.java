package edu.rit.swen253.test.baeldung;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.baeldung.BaeldungHomePage;
import edu.rit.swen253.page.baeldung.SearchButton;
import edu.rit.swen253.page.baeldung.Searchbar;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A test that performs a search on the Baeldung website and logs the result. 
 * 
 * @author Matthew Peck
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaeldungCaseStudyTest extends AbstractWebTest {

  private BaeldungHomePage homePage;
  private SearchButton searchButton;
  private Searchbar searchbar;

  //
  // Test sequence
  //

  @Test
  @Order(1)
  @DisplayName("First, navigate to the Baeldung Home page.")
  void navigateToHomePage() {
    homePage = navigateToPage("https://www.baeldung.com/", BaeldungHomePage::new);
  }

  @Test
  @Order(2)
  @DisplayName("Second, find search button.")
  void findSearchButton() {
    // guard condition
    Assumptions.assumeTrue(homePage != null,
      "Failed to navigate to the RIT Home page.");

    searchButton = homePage.getSearchButton();
    assertEquals("search", searchButton.getTitle());
  }

  @Test
  @Order(3)
  @DisplayName("Third, click on the search button and confirm it brings you to the search page.")
  void clickSearchButton() {
    // guard condition
    Assumptions.assumeTrue(searchButton != null,
      "No 'search' link found");

    searchButton.clickLink();
  }

  @Test
  @Order(4)
  @DisplayName("Fourth, enter search phrase into search bar.")
  void enterSearchPhrase() {
    searchbar = homePage.getSearchbar();
    // guard condition
    Assumptions.assumeTrue(searchbar != null,
      "No 'search' link found");

    searchbar.click();
    searchbar.sendKeys("page object model");
    searchbar.submit();

    homePage.waitUntilGone();
  }
}
