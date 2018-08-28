import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

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
        gMailService = new GMailService(userEmail, "Pensiya15000");
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();
        String messageSubject = "here's the link to reset your password";
        String messageTo = "youngbloodvasilievna@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);
        return new LinkedInPasswordResetSubmitPage(browser);
    }

    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().equals("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }
}
