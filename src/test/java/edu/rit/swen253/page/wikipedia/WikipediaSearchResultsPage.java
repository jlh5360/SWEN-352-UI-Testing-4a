package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;

public class WikipediaSearchResultsPage extends AbstractPage {
    public static final By SEARCH_RESULTS_LIST_FINDER = By.cssSelector("ul.mw-search-results");
    private static final By SEARCH_RESULT_ITEM_FINDER = By.cssSelector("li.mw-search-result");

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

    public List<WikipediaSearchResultView> getSearchResults() {
        DomElement searchResultsList = findOnPage(SEARCH_RESULTS_LIST_FINDER);

        return searchResultsList.findChildrenBy(SEARCH_RESULT_ITEM_FINDER)
                .stream()
                .map(WikipediaSearchResultView::new)
                .collect(Collectors.toList());
    }
    
    public boolean areSearchResultsEmpty() {
        return getSearchResults().isEmpty();
    }
}
