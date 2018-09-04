package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInRequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /** Constructor of LinkedInRequestPasswordResetPage
     * @param browser Webdriver variable from test
     */
    public LinkedInRequestPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(userEmailField, 10);

    }

    /**
     * Connects to Email client, finds account by typing user Email
     * @param userEmail UserEmail
     * @return Returns new LinkedInPasswordResetSubmitPage
     */
    public LinkedInPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService.connect();
        userEmailField.sendKeys(userEmail);
        findAccountButton.click();
        return new LinkedInPasswordResetSubmitPage(browser);
    }

    /**
     * Verify if LinkedInRequestPasswordResetPage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().equals("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }
}
