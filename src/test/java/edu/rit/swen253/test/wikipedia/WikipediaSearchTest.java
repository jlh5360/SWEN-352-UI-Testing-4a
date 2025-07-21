package edu.rit.swen253.test.wikipedia;

import static edu.rit.swen253.utils.SeleniumUtils.getInstance;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.SimplePage;
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
    private static final String ANSI_RED = "\u001B[31m";   //Red for result
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_ORANGE = "\033[38;2;255;165;0m";   //Orange for result
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
        
        logger.info(String.format("%sPerforming search for: '%s%s%s'", ANSI_BLUE, ANSI_RESET, ANSI_BLUE, SEARCH_PHRASE));
        wikipediaSearchResultsPage = wikipediaHomePage.performSearch(SEARCH_PHRASE);
        
        assertNotNull(wikipediaSearchResultsPage, "Search results page should be loaded.");
        assertFalse(wikipediaSearchResultsPage.areSearchResultsEmpty(), "Search results should not be empty.");

        logger.info(String.format("%sRetrieving search results...%s", ANSI_BLUE, ANSI_RESET));
        List<WikipediaSearchResultView> searchResults = wikipediaSearchResultsPage.getSearchResults();
        assertFalse(searchResults.isEmpty(), "Search results should not be empty.");

        for (int i = 0; i < searchResults.size(); i++) {
            WikipediaSearchResultView result = searchResults.get(i);
            
            //Log with ANSI colors to easily see what to look for
            logger.info(String.format("  %sResult %s%s: %sTitle='%s'%s, %sURL='%s'%s",
                    ANSI_GREEN, (i + 1), ANSI_RESET,   //Result in green
                    ANSI_YELLOW, result.getTitle(), ANSI_RESET,   //Title in yellow
                    ANSI_CYAN, result.getUrl(), ANSI_RESET   //URL in cyan
                )
            );
        }
    }

    @Test
    @Order(3)
    @DisplayName("Click first search result and validate navigation")
    void clickFirstSearchResultAndValidateNavigation() {
        assertNotNull(wikipediaSearchResultsPage, "Wikipedia Search Results Page should be initialized from previous step.");

        List<WikipediaSearchResultView> results = wikipediaSearchResultsPage.getSearchResults();
        assertFalse(results.isEmpty(), "Search results should not be empty to click the first one.");

        WikipediaSearchResultView firstResult = results.get(0);
        String expectedTitle = firstResult.getTitle();
        String expectedUrlContains = firstResult.getUrl();   //Partial URL validation

        logger.info(String.format("%sClicking the first search result: '%s%s%s'%s",
                ANSI_BLUE, ANSI_RESET, expectedTitle, ANSI_BLUE, ANSI_RESET
            )
        );
        SimplePage targetPage = firstResult.clickResult();

        //Wait for the URL to change to indicate navigation
        getInstance().getLongWait().until(ExpectedConditions.urlContains(expectedUrlContains));

        String actualTitle = targetPage.getTitle();
        String actualUrl = targetPage.getURL();

        logger.info(String.format("%sNavigated to page. %sActual Title: '%s'%s, %sActual URL: '%s'%s",
                ANSI_BLUE,
                ANSI_YELLOW, actualTitle, ANSI_RESET,
                ANSI_CYAN, actualUrl, ANSI_RESET
            )
        );

        //Assertions for validation
        assertTrue(actualUrl.contains(expectedUrlContains), "Navigated URL should contain the expected URL segment.");
    }
}
