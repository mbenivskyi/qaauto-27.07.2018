package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedInSearchPage;

import java.util.List;


public class LinkedInSearchTest extends BaseTest{

    /**
     * Verify successful search by searchTerm.
     *
     * Preconditions:
     * - Navigate to https://www.linkedin.com/
     *
     * Steps:
     * - Verify Login page is loaded.
     * - Login as registered user.
     * - Verify Home page is loaded.
     * - Search for "HR" searchTerm
     * - Verify Search page is loaded.
     * - Verify 10 results displayed on page.
     * - Verify each result contains searchTerm.
     */
    @Test
    public void basicSearchTest() {
        String searchTerm = "hr";
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "User is not on Login page.");

        LinkedInHomePage linkedinHomePage = linkedInLoginPage.login(
                    "youngbloodvasilievna@gmail.com",
                    "Pensiya15000");
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded.");

        LinkedInSearchPage linkedInSearchPage = linkedinHomePage.search(searchTerm);
        Assert.assertTrue(linkedInSearchPage.isLoaded(), "Search page is not loaded.");
        Assert.assertEquals(linkedInSearchPage.getSearchResultsCount(), 10,
                "There are not 10 search results on Search page.");
        Assert.assertTrue(linkedInSearchPage.isSearchResultsContainSearchTerm(),
                "Search results do not contain searchterm 'hr'.");

        List<String> searchResults = linkedInSearchPage.getSearchResultsList();

        for (String searchResult: searchResults) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm),
                    "searchTerm "+searchTerm+" not found in: \n"+searchResult);
        }
    }
}
