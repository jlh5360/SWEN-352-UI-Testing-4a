package edu.rit.swen253.test.baeldung;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.baeldung.BaeldungHomePage;
import edu.rit.swen253.page.baeldung.SearchButton;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A test that performs a search on the Baeldung website and logs the result. 
 * 
 * @author Matthew Peck
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaeldungCaseStudyTest extends AbstractWebTest {

  private BaeldungHomePage homePage;

  //
  // Test sequence
  //

  @Test
  @Order(1)
  @DisplayName("First, navigate to the Baeldung Home page.")
  void navigateToHomePage() {
    homePage = navigateToPage("https://www.baeldung.com/", BaeldungHomePage::new);
    assertNotNull(homePage);
  }

  @Test
  @Order(2)
  @DisplayName("Second, find search button.")
  void findSearchButton() {
    // guard condition
    Assumptions.assumeTrue(homePage != null,
      "Failed to navigate to the RIT Home page.");

    final SearchButton searchButton = homePage.getSearchButton();
    assertEquals("search", searchButton.getTitle());
  }

  /*

  @Test
  @Order(3)
  @DisplayName("Third, inspect the content of the 'Computing and Information Sciences' link.")
  void inspectComputingLink() {
    // guard condition
    Assumptions.assumeTrue(linkToComputingStudies != null,
      "No 'Computing and Information Sciences' link found");

    // validate page content
    assertAll("group assertions"
      , () -> assertEquals("phone-laptop", linkToComputingStudies.getFirstIconName())
      , () -> assertEquals("code", linkToComputingStudies.getSecondIconName())
    );
  }

  @Test
  @Order(4)
  @DisplayName("Finally, navigate to the Computing area of study.")
  void navigateToComputing() {
    // guard condition
    Assumptions.assumeTrue(linkToComputingStudies != null,
      "No 'Computing and Information Sciences' link found");

    // attempt to navigate to the Computing area of study page
    linkToComputingStudies.clickLink();

    // expect the Home page to go away
    homePage.waitUntilGone();

    // expect navigation to the 'area of study' page
    final SimplePage page = assertNewPage(SimplePage::new);
    // validate simple page content
    assertAll("group assertions"
      , () -> assertEquals("Computing and Information Sciences Degrees | RIT", page.getTitle())
      , () -> assertEquals("https://www.rit.edu/study/computing-and-information-sciences", page.getURL())
    );
  }
 */
}
