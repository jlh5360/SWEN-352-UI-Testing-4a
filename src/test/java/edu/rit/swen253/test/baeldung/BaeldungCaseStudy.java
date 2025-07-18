package edu.rit.swen253.test.baeldung;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test that performs a search on the Baeldung website and logs the result. 
 * 
 * @author Matthew Peck
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaeldungCaseStudy extends AbstractWebTest {
/* 
  private RitHomePage homePage;
  private RitAreaOfStudyLink linkToComputingStudies;

  //
  // Test sequence
  //

  @Test
  @Order(1)
  @DisplayName("First, navigate to the RIT Home page.")
  void navigateToHomePage() {
    homePage = navigateToPage("https://rit.edu", RitHomePage::new);
  }

  @Test
  @Order(2)
  @DisplayName("Second, find out how many 'Area of Study' links are visible.")
  void exploreAreasOfStudy() {
    // guard condition
    Assumptions.assumeTrue(homePage != null,
      "Failed to navigate to the RIT Home page.");

    final List<RitAreaOfStudyLink> studyLinks = homePage.getStudyLinks();
    assertEquals(12, studyLinks.size(), "Number of links should be 12");

    // prepare for the next test
    Optional<RitAreaOfStudyLink> hasLink = studyLinks.stream()
      .filter(link -> link.getTitle().equals("Computing and Information Sciences"))
      .findFirst();
    hasLink.ifPresent(link -> linkToComputingStudies = link);
  }

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
