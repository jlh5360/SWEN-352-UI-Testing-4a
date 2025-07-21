package edu.rit.swen253.test.baeldung;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.baeldung.BaeldungHomePage;
import edu.rit.swen253.page.baeldung.BaeldungSearchResultsPage;
import edu.rit.swen253.page.baeldung.BaeldungSearchResultView;
import edu.rit.swen253.page.baeldung.BaeldungSearchButton;
import edu.rit.swen253.page.baeldung.BaeldungSearchbar;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test that performs a search on the Baeldung website and logs the result. 
 * 
 * @author Matthew Peck
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaeldungCaseStudyTest extends AbstractWebTest {
  private static final Logger logger = Logger.getLogger(BaeldungCaseStudyTest.class.getName());

  private BaeldungHomePage homePage;
  private BaeldungSearchResultsPage searchResultsPage;
  private BaeldungSearchButton searchButton;
  private BaeldungSearchbar searchbar;
  private List<BaeldungSearchResultView> searchResults;

  //coloring codes for logger
  private static final String ANSI_RESET = "\u001B[0m";   //Resets color to default
  private static final String ANSI_RED = "\u001B[31m";   //Red for result
  private static final String ANSI_BLUE = "\u001B[34m";

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
      "No 'search' bar found");

    searchbar.click();
    searchbar.sendKeys("page object model");
    searchbar.submit();

    homePage.waitUntilGone();

    // expect navigation to the 'search results' page
    searchResultsPage = assertNewPage(BaeldungSearchResultsPage::new);
    // validate simple page content
    assertAll("group assertions"
      , () -> assertEquals("page object model | Baeldung", searchResultsPage.getTitle())
      , () -> assertEquals("https://www.baeldung.com/?s=page+object+model", searchResultsPage.getURL())
    );
  }

  @Test
  @Order(5)
  @DisplayName("Fifth, log the title and url for each search result")
  void displaySearchResults() {
    searchResults = searchResultsPage.getSearchResults();
    for (BaeldungSearchResultView baeldungSearchResultView : searchResults) {
      logger.info(String.format("\n%sTitle: %s\n%sURL: %s%s", 
        ANSI_BLUE,
        baeldungSearchResultView.getTitle(), 
        ANSI_RED,
        baeldungSearchResultView.getURL(),
        ANSI_RESET));
    }
  }

  @Test
  @Order(6)
  @DisplayName("Sixth, click the first search result and validate that the browser navigates to the expected page")
  void clickFirstSearchResult() {
    String title = searchResults.get(0).getTitle();
    String url = searchResults.get(0).getURL();

    searchResults.get(0).clickLink();

    searchResultsPage.waitUntilGone();

    // expect navigation to the first search result page
    final SimplePage page = assertNewPage(SimplePage::new);
    // validate simple page content
    assertAll("group assertions"
      , () -> assertEquals(title + " | Baeldung", page.getTitle())
      , () -> assertEquals(url, page.getURL())
    );
  }
}
