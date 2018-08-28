import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInSetNewPasswordPage extends BasePage {

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    public LinkedInSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed()
                && getCurrentPageTitle().contains("Reset Your Password | LinkedIn")
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }
}
