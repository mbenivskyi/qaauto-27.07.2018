package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    /** Constructor of LinkedInPasswordResetSubmitPage
     * @param browser Webdriver variable from test
     */
    public LinkedInPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resendLinkButton, 10);
    }

    /**
     * Verifies that LinkedInPasswordResetSubmitPage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("/checkpoint/rp/request-password-reset-submit");
    }


    /**
     * Navigates to link from Email
     * @return Returns new LinkedInSetNewPasswordPage
     */
    public LinkedInSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "youngbloodvasilievna@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message,
                "To change your LinkedIn password, click <a href=\"",
                "\" style=").replace("amp;", "");

        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);
        return new LinkedInSetNewPasswordPage(browser);
    }
}
