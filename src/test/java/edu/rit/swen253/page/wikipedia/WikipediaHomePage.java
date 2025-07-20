package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class WikipediaHomePage extends AbstractPage {
    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private static final By SEARCH_INPUT_FINDER = By.id("searchInput");
    private static final By SEARCH_BUTTON_FINDER = By.cssSelector("button[type='submit']"); // The search button has type submit

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

    public static WikipediaHomePage navigateTo() {
        return SeleniumUtils.getInstance().navigateToPage(WIKIPEDIA_URL, WikipediaHomePage::new);
    }
    
    public DomElement getSearchInput() {
        return findOnPage(SEARCH_INPUT_FINDER);
    }

    public DomElement getSearchButton() {
        return findOnPage(SEARCH_BUTTON_FINDER);
    }
}
