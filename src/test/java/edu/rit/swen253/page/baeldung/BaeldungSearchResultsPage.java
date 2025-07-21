package edu.rit.swen253.page.baeldung;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

/**
 * A Page Object for the Baeldung Search Results page.
 *
 * @author Matthew Peck
 */
public class BaeldungSearchResultsPage extends SimplePage {
    
  private static final By SEARCH_RESULTS_LIST_FINDER =
    By.cssSelector("div.archive-columns");
  private static final By SEARCH_RESULT_FINDER =
    By.cssSelector("article");

  private DomElement searchResultsList;

  public BaeldungSearchResultsPage() {
    super();
    // validate basic page structure
    try {
      searchResultsList = findOnPage(SEARCH_RESULTS_LIST_FINDER);
    } catch (TimeoutException e) {
      fail("Search results not found");
    }
  }

  public List<BaeldungSearchResultView> getSearchResults() {
    return searchResultsList.findChildrenBy(SEARCH_RESULT_FINDER)
        .stream()
        .map(BaeldungSearchResultView::new)
        .toList();
  }
}
