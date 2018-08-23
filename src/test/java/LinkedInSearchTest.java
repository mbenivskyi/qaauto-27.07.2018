import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LinkedInSearchTest {

    WebDriver browser;
    LinkedInLoginPage linkedInLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedInLoginPage = new LinkedInLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

        @Test
    public void basicSearchTest() {
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "User is not on Login page.");
        LinkedInHomePage linkedinHomePage = linkedInLoginPage.loginReturnHomePage(
                    "youngbloodvasilievna@gmail.com",
                    "Pensiya15000");
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded.");
        LinkedInSearchPage linkedInSearchPage = linkedinHomePage.search("hr");
        Assert.assertTrue(linkedInSearchPage.isLoaded(), "Search page is not loaded.");
        Assert.assertEquals(linkedInSearchPage.getSearchResultsCount(), 10,
                "There are not 10 search results on Search page.");
        Assert.assertTrue(linkedInSearchPage.isSearchResultsContainSearchTerm(),
                "Search results do not contain searchterm 'hr'.");
    }
}
