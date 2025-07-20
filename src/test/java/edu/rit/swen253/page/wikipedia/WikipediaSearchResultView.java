package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import org.openqa.selenium.By;

/**
 * View Object for a single search result on the Wikipedia Search Results Page.
 */
public class WikipediaSearchResultView {
    private final DomElement resultElement;
    private static final By HEADING_FINDER = By.cssSelector("div.mw-search-result-heading a");
    private static final By EXCERPT_FINDER = By.cssSelector("div.searchresult");

    /**
     * Constructs a WikipediaSearchResultView with the given DomElement.
     * @param resultElement The DomElement representing the individual search result.
     */
    public WikipediaSearchResultView(DomElement resultElement) {
        this.resultElement = resultElement;
    }
    
    /**
     * Gets the title of the search result.
     * @return The text of the search result's title.
     */
    public String getTitle() {
        //Returns the text of the search result's title
        return resultElement.findChildBy(HEADING_FINDER).getText();
    }
    
    /**
     * Gets the URL of the search result.
     * @return The href attribute of the search result's title link.
     */
    public String getUrl() {
        return resultElement.findChildBy(HEADING_FINDER).getAttribute(HtmlUtils.HREF_ATTR);
    }
    
    /**
     * Gets the excerpt (summary) of the search result.
     * @return The text of the search result's excerpt.
     */
    public String getExcerpt() {
        return resultElement.findChildBy(EXCERPT_FINDER).getText();
    }
    
    /**
     * Clicks on the search result link.
     * @return A SimplePage object representing the navigated page.
     */
    public SimplePage clickResult() {
        resultElement.findChildBy(HEADING_FINDER).click();
        
        return new SimplePage();
    }
}
