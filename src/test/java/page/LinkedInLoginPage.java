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
     * Method that enters userEmail/userPass and click on signIn button.
     * @param userEmail - String with user email.
     * @param userPass - String with user password.
     * @param <T> - Generic type to return corresponding pageObject.
     * @return either LinkedInHomePage or LinkedInLoginSubmitPage or LinkedInLoginPage.
     */
    public <T> T login (String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        if (getCurrentPageUrl().contains("/feed")){
            return (T) new LinkedInHomePage(browser);
        }
        if (getCurrentPageUrl().contains("/uas/login-submit")){
            return (T) new LinkedInLoginSubmitPage(browser);
        } else {
            return (T) new LinkedInLoginPage(browser);
        }
    }

    /**
     * Verifies that LinkedInLoginPage is loaded.
     * @return Returns if Webelement is displayed and page title contains appropriate value.
     */
    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up");
    }

    /**
     * Clicks on forgotPasswordLink.
     * @return Returns new LinkedInRequestPasswordResetPage.
     */
    public LinkedInRequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return new LinkedInRequestPasswordResetPage(browser);
    }
}
