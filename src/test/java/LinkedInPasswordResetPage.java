import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Enter your email or phone']")
    private WebElement emailOrPhoneField;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public LinkedInPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public LinkedInPasswordResetPage enterEmailToFindAccount(String userEmail) {
        emailOrPhoneField.sendKeys(userEmail);
        resetPasswordSubmitButton.click();
        return new LinkedInPasswordResetPage(browser);
    }

    public boolean isLoaded() {
        return emailOrPhoneField.isDisplayed()
                && getCurrentPageTitle().contains("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }
}
