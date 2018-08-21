import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LinkedInSearchTest {

    WebDriver browser;
    LinkedInLoginPage linkedInLoginPage;
    LinkedInHomePage linkedInHomePage;
    LinkedInSearchPage linkedInSearchPage;

    @Test
    public void linkedInSearchTest() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedInLoginPage = new LinkedInLoginPage(browser);
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "User is not on Login page.");
        linkedInLoginPage.loginReturnLoginPage("youngbloodvasilievna@gmail.com", "Pensiya15000");
        linkedInHomePage = new LinkedInHomePage(browser);
        Assert.assertTrue(linkedInHomePage.isLoaded(), "Home page is not loaded.");
        linkedInSearchPage = new LinkedInSearchPage(browser);
        linkedInHomePage.searchForItem("hr");
        JavascriptExecutor scrollDown = (JavascriptExecutor) browser;
        scrollDown.executeScript("scrollBy(0, 2500)");
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertFalse(linkedInSearchPage.isLoaded(), "Search page is not loaded.");
        Assert.assertTrue(linkedInSearchPage.areThere10ResultsOnSearchPage(), "There are not 10 search results on Search page.");
        Assert.assertTrue(linkedInSearchPage.isSearchResultsContainSearchTerm(), "Search results do not contain searchterm 'hr'.");
    }
}
