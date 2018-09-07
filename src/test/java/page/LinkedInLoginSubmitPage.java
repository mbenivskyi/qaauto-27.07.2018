package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedInLoginSubmitPage extends BasePage {

    @FindBy (xpath = "//*[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    @FindBy (xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    /**
     * Constructor of LinkedInLoginSubmitPage
     * @param browser Webdriver variable from test
     */
    public LinkedInLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(alertBox, 10);
    }

    /**
     * @return Returns text from alertbox
     */
    public String getAlertBoxText() {
        return alertBox.getText();
    }

    /**
     * @return Returns Email validation text
     */
    public String getUserEmailValidationText() {
        return userEmailValidationText.getText();
    }

    /**
     * @return Returns Password validation text
     */
    public String getUserPasswordValidationText () {
        return userPasswordValidationText.getText();
    }

    /**
     * Verifies that LinkedInLoginSubmitPage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return alertBox.isDisplayed()
                && getCurrentPageUrl().contains("/uas/login-submit");
    }
}
