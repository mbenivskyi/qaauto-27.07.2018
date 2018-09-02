package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    public LinkedInPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.  | LinkedIn")
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }


    public LinkedInSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "youngbloodvasilievna@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message,
                "To change your LinkedIn password, click <a href=\\\"<a href=&quot;",
                "&quot;>[text]</a>").replace("amp;", "");

        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);

        //ToDo
        return new LinkedInSetNewPasswordPage(browser);
    }
}
