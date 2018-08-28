import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmNewPasswordField;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement submitNewPasswordButton;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    public LinkedInPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.  | LinkedIn")
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }
    public LinkedInPasswordResetSubmitPage typeNewPasswordAndSubmit (String newPass, String confirmNewPass){
        newPasswordField.sendKeys(newPass);
        confirmNewPasswordField.sendKeys(confirmNewPass);
        submitNewPasswordButton.click();
        return new LinkedInPasswordResetSubmitPage(browser);
    }


    public LinkedInSetNewPasswordPage navigateToLinkFromEmail() {
        //ToDo
        return new LinkedInSetNewPasswordPage(browser);
    }
}
