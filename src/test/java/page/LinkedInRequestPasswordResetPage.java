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

    public LinkedInRequestPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public LinkedInPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService.connect();
        userEmailField.sendKeys(userEmail);
        findAccountButton.click();
        return new LinkedInPasswordResetSubmitPage(browser);
    }

    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().equals("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }
}
