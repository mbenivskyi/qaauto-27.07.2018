package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object class for LinkedInLoginPage.
 */
public class LinkedInLoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor of LinkedInLoginPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedInLoginPage (WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(userEmailField, 10);
    }

    /**
     * Returns LinkedInLoginSubmitPage after login
     * @param userEmail UserEmail
     * @param userPass UserPassword
     * @return Returns new LiginSubmitPage
     */
    public LinkedInLoginSubmitPage loginReturnLoginSubmitPage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedInLoginSubmitPage(browser);
    }

    /**
     * Returns LinkedInHomePage after login
     * @param userEmail UserEmail
     * @param userPass UserPassword
     * @return Returns new LinkedInHomePage
     */
    public LinkedInHomePage loginReturnHomePage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedInHomePage(browser);
    }

    /**
     * Returns LinkedInLoginPage after login
     * @param userEmail UserEmail
     * @param userPass UserPassword
     * @return Returns new LinkedInLoginPage
     */
    public LinkedInLoginPage loginReturnLoginPage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedInLoginPage(browser);
    }

    /**
     * Verifies that LinkedInLoginPage is loaded
     * @return Returns if Webelement is displayed and page title contains appropriate value
     */
    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up");
    }

    /**
     * Clicks on forgotPasswordLink
     * @return Returns new LinkedInRequestPasswordResetPage
     */
    public LinkedInRequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return new LinkedInRequestPasswordResetPage(browser);
    }
}
