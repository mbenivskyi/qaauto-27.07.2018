package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Parent page object class to inherit classes and Webelements from it
 */
public abstract class BasePage {
    /**
     * Creating Webdriver variable
     */
    protected WebDriver browser;
    /**
     * Initializing of new util with variable
     */
    protected static GMailService gMailService = new GMailService();

    /**
     * Class that waits until element is visible
     * @param webElement Element that is being waited for
     * @param timeOutInSeconds Time to wait
     * @return What class is returning
     */
    public WebElement waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){

        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    /**
     * Getting current page title
     * @return What class is returning
     */
    public String getCurrentPageTitle() { return browser.getTitle();}

    /**
     * Getting current page URL
     * @return What class is returning
     */
    public String getCurrentPageUrl() { return browser.getCurrentUrl();}

    /**
     * Verify that appropriate page is loaded
     * @return What class is returning
     */
    public abstract boolean isLoaded();
}
