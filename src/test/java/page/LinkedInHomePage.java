package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static java.lang.Thread.sleep;

public class LinkedInHomePage extends BasePage {

    @FindBy (xpath = "//*[@id='profile-nav-item']")
    private WebElement profileNavigationItem;

    @FindBy (xpath = "//input[@role='combobox']")
    private WebElement searchField;

    /**
     * Constructor of LinkedInHomePage
     * @param browser Webdriver variable from test
     */
    public LinkedInHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(profileNavigationItem, 10);
    }

    /**
     * Verifies that LinkedInHomePage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }

    /**
     * Searches for an appropriate term
     * @param SearchTerm Appropriate term
     * @return Returning new LinkedInSearchPage
     */
    public LinkedInSearchPage search (String SearchTerm) {
        searchField.sendKeys(SearchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedInSearchPage(browser);
    }
}
