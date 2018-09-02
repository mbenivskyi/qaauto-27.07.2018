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

    public LinkedInSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public LinkedInPasswordResetSubmitPage typeNewPasswordAndSubmit (String newPass, String confirmNewPass){
        newPasswordField.sendKeys(newPass);
        confirmNewPasswordField.sendKeys(confirmNewPass);
        submitNewPasswordButton.click();
        return new LinkedInPasswordResetSubmitPage(browser);
    }

    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed()
                && getCurrentPageTitle().contains("Reset Your Password | LinkedIn")
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }
}
