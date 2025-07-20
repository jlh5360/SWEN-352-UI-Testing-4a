package edu.rit.swen253.test.wikipedia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.wikipedia.WikipediaHomePage;
import edu.rit.swen253.page.wikipedia.WikipediaSearchResultView;
import edu.rit.swen253.page.wikipedia.WikipediaSearchResultsPage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Wikipedia Search Test")
public class WikipediaSearchTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(WikipediaSearchTest.class.getName());
    private static final String SEARCH_PHRASE = "page object model";

    private WikipediaHomePage wikipediaHomePage;
    private WikipediaSearchResultsPage wikipediaSearchResultsPage;

    @Test
    @Order(1)
    @DisplayName("Navigate to Wikipedia Home Page")
    void navigateToHomePage() {
        logger.info("Navigating to Wikipedia Home Page.");
        wikipediaHomePage = WikipediaHomePage.navigateTo();

        assertNotNull(wikipediaHomePage, "Wikipedia Home Page should be loaded.");

        logger.info("Successfully navigated to Wikipedia Home Page.");
    }

    @Test
    @Order(2)
    @DisplayName("Perform search for 'page object model' and list results")
    void performSearchAndListResults() {
        assertNotNull(wikipediaHomePage, "Wikipedia Home Page should be initialized from previous step.");
        
        wikipediaSearchResultsPage = wikipediaHomePage.performSearch(SEARCH_PHRASE);
        
        assertNotNull(wikipediaSearchResultsPage, "Search results page should be loaded.");
        assertFalse(wikipediaSearchResultsPage.areSearchResultsEmpty(), "Search results should not be empty.");

        List<WikipediaSearchResultView> searchResults = wikipediaSearchResultsPage.getSearchResults();
        assertFalse(searchResults.isEmpty(), "Search results should not be empty.");

        for (int i = 0; i < searchResults.size(); i++) {
            WikipediaSearchResultView result = searchResults.get(i);
            logger.info(String.format("  Result %d: Title='%s', URL='%s'", i + 1, result.getTitle(), result.getUrl()));
        }
    }
}
