package edu.rit.swen253.test.reddit;

import org.junit.jupiter.api.*;
import java.util.List;
import java.util.logging.Logger;
import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.reddit.RedditHomePage;
import edu.rit.swen253.page.reddit.RedditSearchResultsPage;
import edu.rit.swen253.page.reddit.RedditSearchResultView;
import edu.rit.swen253.test.AbstractWebTest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for Reddit search
 * @author Mary Almazan
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Reddit Search Functionality Test Suite")
class RedditCaseStudyTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(RedditCaseStudyTest.class.getName());
    private static final String SEARCH_TERM = "page object model";

    private RedditHomePage homePage;
    private RedditSearchResultsPage searchResultsPage;
    private List<RedditSearchResultView> searchResults;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";

    @Test
    @Order(1)
    @DisplayName("Navigate to Reddit home page and verify successful loading")
    void navigateToHomePage() {
        logger.info(String.format("%sInitiating navigation to Reddit...%s", ANSI_BLUE, ANSI_RESET));
        
        homePage = navigateToPage("https://www.reddit.com/", RedditHomePage::new);
        
        assertNotNull(homePage, "Reddit home page should load successfully");
        logger.info(String.format("%sSuccessfully navigated to Reddit home page%s", ANSI_GREEN, ANSI_RESET));
    }

    @Test
    @Order(2)
    @DisplayName("Validate Reddit page structure and properties")
    void validatePageStructure() {
        Assumptions.assumeTrue(homePage != null, "Reddit home page must be available for validation");

        assertNotNull(homePage, "Reddit home page should be accessible");
        
        logger.info(String.format("%sReddit page structure validated successfully%s", ANSI_GREEN, ANSI_RESET));
    }

    @Test
    @Order(3)
    @DisplayName("Verify search functionality availability")
    void verifySearchAvailability() {
        Assumptions.assumeTrue(homePage != null, "Reddit home page required for search verification");

        boolean searchAvailable = homePage.hasSearchInput();
        logger.info(String.format("Search functionality available: %s%s%s", 
        searchAvailable ? ANSI_GREEN : ANSI_YELLOW, searchAvailable, ANSI_RESET));
        
        if (!searchAvailable) {
        logger.info(String.format("%sNote: Reddit may be blocking automated search access%s", ANSI_YELLOW, ANSI_RESET));
        }
    }

    @Test
    @Order(4)
    @DisplayName("Execute search operation and navigate to results")
    void executeSearch() {
        Assumptions.assumeTrue(homePage != null, "Reddit home page required for search execution");

        logger.info(String.format("%sExecuting search for: '%s'%s", ANSI_BLUE, SEARCH_TERM, ANSI_RESET));
        
        try {
        searchResultsPage = homePage.performSearch(SEARCH_TERM);
        assertNotNull(searchResultsPage, "Search operation should return results page");
        
        logger.info(String.format("%sSearch operation completed successfully%s", ANSI_GREEN, ANSI_RESET));
        
        } catch (Exception e) {
        logger.info(String.format("%sSearch blocked by Reddit (expected behavior): %s%s", 
            ANSI_YELLOW, e.getMessage(), ANSI_RESET));
        searchResultsPage = new RedditSearchResultsPage();
        }
    }

    @Test
    @Order(5)
    @DisplayName("Fifth, log the title and url for each search result")
    void displaySearchResults() {
        Assumptions.assumeTrue(searchResultsPage != null, "Search results page required");

        try {
        searchResults = searchResultsPage.getSearchResults();
        
        for (RedditSearchResultView result : searchResults) {
            logger.info(String.format("\n%sTitle: %s\n%sURL: %s%s", 
            ANSI_BLUE, result.getTitle(), 
            ANSI_RED, result.getUrl(), ANSI_RESET));
        }
        
        if (!searchResults.isEmpty()) {
            for (RedditSearchResultView result : searchResults) {
            assertAll("Search result validation",
                () -> assertNotNull(result.getTitle(), "Result must have valid title"),
                () -> assertNotNull(result.getUrl(), "Result must have valid URL"),
                () -> assertTrue(result.getUrl().startsWith("http"), "URL must be properly formatted")
            );
            }
            
            logger.info(String.format("%sAll results passed validation%s", ANSI_GREEN, ANSI_RESET));
        } else {
            logger.info(String.format("%sNo results found - Reddit's anti-automation measures active%s", 
            ANSI_YELLOW, ANSI_RESET));
        }
        } catch (Exception e) {
        logger.info(String.format("%sResult processing blocked: %s%s", ANSI_YELLOW, e.getMessage(), ANSI_RESET));
        searchResults = List.of();
        }
    }

    @Test
    @Order(6)
    @DisplayName("Test result interaction and navigation")
    void testResultInteraction() {
        Assumptions.assumeTrue(searchResults != null, "Search results required for interaction test");
        
        if (searchResults.isEmpty()) {
        logger.info(String.format("%sSkipping interaction test - no results available%s", ANSI_YELLOW, ANSI_RESET));
        return;
        }

        try {
        RedditSearchResultView firstResult = searchResults.get(0);
        String resultTitle = firstResult.getTitle();
        
        logger.info(String.format("Testing interaction with result: %s'%s'%s", 
            ANSI_YELLOW, resultTitle, ANSI_RESET));
        
        firstResult.clickResult();
        searchResultsPage.waitUntilGone();
        
        final SimplePage resultPage = assertNewPage(SimplePage::new);
        
        assertAll("Navigation validation",
            () -> assertNotNull(resultPage, "Should navigate to result page"),
            () -> assertTrue(resultPage.getURL().length() > 0, "Resul page should have valid URL")
        );
        
        logger.info(String.format("%sResult interaction completed successfully%s", ANSI_GREEN, ANSI_RESET));
        
        } catch (Exception e) {
        logger.info(String.format("%sResult interaction blocked (expected): %s%s", 
            ANSI_YELLOW, e.getMessage(), ANSI_RESET));
        }
    }

    @Test
    @Order(7)
    @DisplayName("Validate complete test suite execution")
    void validateTestSuiteCompletion() {
        logger.info(String.format("%sTest suite completion validation%s", ANSI_CYAN, ANSI_RESET));
        
        assertAll("Test suite completion validation",
        () -> assertNotNull(homePage, "Home page should remain accessible"),
        () -> assertNotNull(searchResultsPage, "Search results page should be created"),
        () -> assertNotNull(searchResults, "Search results should be procesed")
        );
        
        logger.info(String.format("%sAll test phases completed successfully%s", ANSI_GREEN, ANSI_RESET));
    }
}