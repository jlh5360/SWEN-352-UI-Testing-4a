package edu.rit.swen253.page.wikipedia;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import org.openqa.selenium.By;

public class WikipediaSearchResultView {
    private final DomElement resultElement;
    private static final By HEADING_FINDER = By.cssSelector("div.mw-search-result-heading a");
    private static final By EXCERPT_FINDER = By.cssSelector("div.searchresult");

    public WikipediaSearchResultView(DomElement resultElement) {
        this.resultElement = resultElement;
    }
    
    public String getTitle() {
        return resultElement.findChildBy(HEADING_FINDER).getText();
    }
    
    public String getUrl() {
        return resultElement.findChildBy(HEADING_FINDER).getAttribute(HtmlUtils.HREF_ATTR);
    }
    
    public String getExcerpt() {
        return resultElement.findChildBy(EXCERPT_FINDER).getText();
    }
    
    public SimplePage clickResult() {
        resultElement.findChildBy(HEADING_FINDER).click();
        
        return new SimplePage();
    }
}
