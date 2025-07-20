package edu.rit.swen253.page.baeldung;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

import static edu.rit.swen253.utils.HtmlUtils.ANCHOR_FINDER;

    /**
     * A View Object that contains a search button in Home page.
     *
     * <a id="menu-item-196804" class="nav--menu_item_anchor" title="search" aria-label="search" href="#search">
     *      <svg width="490px" height="490px" class="sprite--search injected-svg" viewBox="0 0 490 490" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" role="img" id="Search" aria-label="search" data-src="/wp-content/uploads/aqua-svg-sprite/aqua-svg-general-sprite.svg#search">
     *          <path fill="none" stroke="#fff" stroke-width="36" stroke-linecap="round" d="m280,278a153,153 0 1,0-2,2l170,170m-91-117 110,110-26,26-110-110" role="presentation" style="stroke-dasharray: 1547, 1549; stroke-dashoffset: 0;"></path>
     *      </svg>
     * </a>
     */
public class SearchButton {
    private final DomElement viewContainer;
    private final DomElement link;
    /**
    * The View container is the li that holds the link {@code <li><a> ...LINK... </a></li>}.
    */
    public SearchButton(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
        this.link = viewContainer.findChildBy(ANCHOR_FINDER);
    }

    public void clickLink() {
        link.click();
    }

    // Not working at the moment, will fix later
    public String getTitle() {
        return link.getAttribute("title");
    }
}

