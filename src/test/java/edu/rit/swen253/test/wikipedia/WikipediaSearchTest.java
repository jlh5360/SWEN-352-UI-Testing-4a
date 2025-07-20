package edu.rit.swen253.test.wikipedia;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.wikipedia.WikipediaHomePage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Wikipedia Search Test")
public class WikipediaSearchTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(WikipediaSearchTest.class.getName());

    private WikipediaHomePage wikipediaHomePage;

    @Test
    @Order(1)
    @DisplayName("Navigate to Wikipedia Home Page")
    void navigateToHomePage() {
        logger.info("Navigating to Wikipedia Home Page.");
        wikipediaHomePage = WikipediaHomePage.navigateTo();

        assertNotNull(wikipediaHomePage, "Wikipedia Home Page should be loaded.");
        
        logger.info("Successfully navigated to Wikipedia Home Page.");
    }
}
