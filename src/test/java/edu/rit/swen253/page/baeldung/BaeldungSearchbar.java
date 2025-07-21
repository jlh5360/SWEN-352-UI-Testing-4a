package edu.rit.swen253.page.baeldung;

import edu.rit.swen253.utils.DomElement;


    /*
     * <input name="s" id="search" placeholder="Search" value="" class="form-control" type="text">
     */
public class BaeldungSearchbar {

    private final DomElement viewContainer;
    /**
    * The View container is the input that contains the search.
    */
    public BaeldungSearchbar(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public void click() {
        viewContainer.click();
    }

    public void sendKeys(String input) {
        viewContainer.sendKeys(input);
    }

    public void submit() {
        viewContainer.submit();
    }
}
