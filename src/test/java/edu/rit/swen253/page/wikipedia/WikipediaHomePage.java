package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for the Wikipedia Home Page.
 */
public class WikipediaHomePage extends AbstractPage {
    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private static final By SEARCH_INPUT_FINDER = By.id("searchInput");
    private static final By SEARCH_BUTTON_FINDER = By.cssSelector("button[type='submit']");   //The search button has type submit

    /**
     * Constructs a WikipediaHomePage object.
     */
    public WikipediaHomePage() {
        super();

        //Validate basic page structure
        try {
            findOnPage(SEARCH_INPUT_FINDER);
            findOnPage(SEARCH_BUTTON_FINDER);
        } catch (TimeoutException e) {
            fail("Wikipedia Home page elements not found: " + e.getMessage());
        }
    }

    /**
     * Navigates to the Wikipedia Home page.
     * @return a new instance of WikipediaHomePage.
     */
    public static WikipediaHomePage navigateTo() {
        return SeleniumUtils.getInstance().navigateToPage(WIKIPEDIA_URL, WikipediaHomePage::new);
    }
    
    /**
     * Enters a search phrase into the search input field and submits the search.
     * @param searchPhrase The phrase to search for.
     * @return The WikipediaSearchResultsPage object.
     */
    public WikipediaSearchResultsPage performSearch(String searchPhrase) {
        DomElement searchInput = findOnPage(SEARCH_INPUT_FINDER);   //Find the search input element after ensuring it's present
        searchInput.sendKeys(searchPhrase);   //Input the desired phrase to search
        searchInput.submit();  //Submit the form the input belongs to
        
        //Need to wait for an element on the search results page to be visible
        //This is crucial to resolving/avoiding the StaleElementReferenceException error
        //                                                          ^
        //org.openqa.selenium.StaleElementReferenceException: stale element reference: stale element not found
        SeleniumUtils.getInstance().getLongWait().until(ExpectedConditions.presenceOfElementLocated(WikipediaSearchResultsPage.SEARCH_RESULTS_LIST_FINDER));

        return new WikipediaSearchResultsPage();
    }
    
    /**
     * Finds the search input element on the page.
     * @return The DomElement representing the search input.
     */
    public DomElement getSearchInput() {
        return findOnPage(SEARCH_INPUT_FINDER);
    }

    /**
     * Finds the search button element on the page.
     * @return The DomElement representing the search button.
     */
    public DomElement getSearchButton() {
        return findOnPage(SEARCH_BUTTON_FINDER);
    }
}
