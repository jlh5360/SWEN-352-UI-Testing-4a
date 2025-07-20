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

    //ANSI escape codes for coloring terminal output
    private static final String ANSI_RESET = "\u001B[0m";   //Resets color to default
    private static final String ANSI_GREEN = "\u001B[32m";  // Green for result
    private static final String ANSI_YELLOW = "\u001B[33m";   //Yellow for title
    private static final String ANSI_CYAN = "\u001B[36m";   //Cyan for URL

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
            
            //Log with ANSI colors to easily see what to look for
            logger.info(String.format("  %sResult %s%s: %sTitle='%s'%s, %sURL='%s'%s",
                    ANSI_GREEN, (i + 1), ANSI_RESET,   //Result in green
                    ANSI_YELLOW, result.getTitle(), ANSI_RESET,   //Title in yellow
                    ANSI_CYAN, result.getUrl(), ANSI_RESET));   //URL in cyan
        }
    }
}
