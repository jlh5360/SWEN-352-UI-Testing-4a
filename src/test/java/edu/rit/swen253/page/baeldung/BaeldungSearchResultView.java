package edu.rit.swen253.page.baeldung;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class BaeldungSearchResultView {
    private DomElement viewContainer;
    private DomElement link;

    private static final By LINK_FINDER =
        By.cssSelector("div.post-inner > h3.post-title > a");

    public BaeldungSearchResultView(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
        this.link = viewContainer.findChildBy(LINK_FINDER);
    }

    public String getTitle() {
        return link.getAttribute("title");
    }

    public String getURL() {
        return link.getAttribute("href");
    }

    public void clickLink() {
        link.click();
    }
}
