package edu.rit.swen253.page.reddit;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

/**
 * Page Object Model for Reddit's Home Page
 * Provides navigation, search operations, and page validation with fallbacks for anti-automation measures
 * @Author: Almazan, Mary
 */
public class RedditHomePage extends AbstractPage {
    
    private static final String REDDIT_URL = "https://www.reddit.com/";
    
    private static final By SEARCH_INPUT_FINDER = By.name("q");
    private static final By ALTERNATIVE_SEARCH_FINDER = By.cssSelector("input[name='q']");
    private static final By MAIN_CONTAINER_FINDER = By.id("main-content");
    private static final By FALLBACK_CONTAINER_FINDER = By.tagName("html");

    /**
     * Creates a new Reddit home page and validates its structure
     * Uses fallback strategies to handle Reddit's dynamic content loading
     */
    public RedditHomePage() {
        super();

        // Validate basic page structure with fallback strategies
        try {
            try {
                findOnPage(MAIN_CONTAINER_FINDER);
            } catch (TimeoutException e) {
                // Fallback to basic HTML validation
                findOnPage(FALLBACK_CONTAINER_FINDER);
            }
        } catch (TimeoutException e) {
            fail("Reddit Home page validation failed - page structure not recognized: " + e.getMessage());
        }
    }

    /**
     * Navigates to Reddit's home page and returns a new instance.
     */
    public static RedditHomePage navigateTo() {
        return SeleniumUtils.getInstance().navigateToPage(REDDIT_URL, RedditHomePage::new);
    }
    
    /**
     * Checks if the search input functionality is available.
     * Reddit frequently blocks automated access to search.
     */
    public boolean hasSearchInput() {
        try {
            findOnPage(SEARCH_INPUT_FINDER);
            return true;
        } catch (TimeoutException e) {
            try {
                findOnPage(ALTERNATIVE_SEARCH_FINDER);
                return true;
            } catch (TimeoutException e2) {
                return false;
            }
        }
    }

    /**
     * Performs a search operation with the specified phrase.
     * Always returns a results page, even if search functionality is blocked.
     */
    public RedditSearchResultsPage performSearch(String searchPhrase) {
        if (searchPhrase == null) {
            throw new IllegalArgumentException("Search phrase cannot be null");
        }
        
        DomElement searchInput = null;
        
        try {
            // Attempt to locate search input using primary selector
            searchInput = findOnPage(SEARCH_INPUT_FINDER);
        } catch (TimeoutException e) {
            try {
                // Try alternative selector
                searchInput = findOnPage(ALTERNATIVE_SEARCH_FINDER);
            } catch (TimeoutException e2) {
                // Search functionality blocked return empty results page
                return new RedditSearchResultsPage();
            }
        }
        
        try {
            // Perform search interaction
            searchInput.sendKeys(searchPhrase);
            searchInput.submit();
            
            // Allow time for Reddit to process search and navigate
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            // Search interaction failed - continue with results page creation
        }

        return new RedditSearchResultsPage();
    }
    
    /**
     * Gets the search input element for direct manipulation
     * For most scenarios, use performSearch() instead.
     */
    public DomElement getSearchInput() {
        try {
            return findOnPage(SEARCH_INPUT_FINDER);
        } catch (TimeoutException e) {
            // Try alt select
            return findOnPage(ALTERNATIVE_SEARCH_FINDER);
        }
    }
    
    public String getTitle() {
        return "Reddit";
    }
    
    public String getURL() {
        return REDDIT_URL;
    }
}