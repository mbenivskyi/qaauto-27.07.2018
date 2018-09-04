package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInSetNewPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmNewPasswordField;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement submitNewPasswordButton;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    /** Constructor of LinkedInSetNewPasswordPage
     * @param browser Webdriver variable from test
     */
    public LinkedInSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(goToHomepageButton, 10);
    }

    /**
     * Types new password and submit it.
     * @param newPass New password
     * @param confirmNewPass Confirmation of new password
     * @return Returns new LinkedInSetNewPasswordPage
     */
    public LinkedInSetNewPasswordPage typeNewPasswordAndSubmit (String newPass, String confirmNewPass){
        newPasswordField.sendKeys(newPass);
        confirmNewPasswordField.sendKeys(confirmNewPass);
        submitNewPasswordButton.click();
        return new LinkedInSetNewPasswordPage(browser);
    }

    /**
     * Click on goToHomepageButton
     * @return Returns new LinkedInHomePage
     */
    public LinkedInHomePage goToHomePage (){
        goToHomepageButton.click();
        return new LinkedInHomePage(browser);
    }

    /**
     * Verify if LinkedInSetNewPasswordPage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed()
                && getCurrentPageTitle().contains("Reset Your Password | LinkedIn")
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }
}
