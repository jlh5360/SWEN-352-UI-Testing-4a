package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for the Wikipedia Search Results Page.
 */
public class WikipediaSearchResultsPage extends AbstractPage {
    public static final By SEARCH_RESULTS_LIST_FINDER = By.cssSelector("ul.mw-search-results");
    private static final By SEARCH_RESULT_ITEM_FINDER = By.cssSelector("li.mw-search-result");

    /**
     * Constructs a WikipediaSearchResultsPage object.
     */
    public WikipediaSearchResultsPage() {
        super();

        //Validate basic page structure
        try {
            findOnPage(SEARCH_RESULTS_LIST_FINDER);
            findOnPage(SEARCH_RESULT_ITEM_FINDER);
        } catch (TimeoutException e) {
            fail("Wikipedia Search Results page elements not found: " + e.getMessage());
        }
    }

    /**
     * Gets a list of all search result view objects on the page.
     * @return A list of WikipediaSearchResultView objects.
     */
    public List<WikipediaSearchResultView> getSearchResults() {
        //Find the search results list element after ensuring it's present
        DomElement searchResultsList = findOnPage(SEARCH_RESULTS_LIST_FINDER);

        return searchResultsList.findChildrenBy(SEARCH_RESULT_ITEM_FINDER)
                .stream()
                .map(WikipediaSearchResultView::new)
                .collect(Collectors.toList());
    }
    
    /**
     * Checks if the search results are empty.
     * @return true if there are no search results, false otherwise.
     */
    public boolean areSearchResultsEmpty() {
        return getSearchResults().isEmpty();
    }
}
