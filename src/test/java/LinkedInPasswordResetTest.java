import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LinkedInPasswordResetTest {

    WebDriver browser;
    LinkedInLoginPage linkedInLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedInLoginPage = new LinkedInLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test
    public void basicResetPasswordTest() {
        linkedInLoginPage.resetPasswordRequest();

        LinkedInPasswordResetPage linkedInPasswordResetPage = new LinkedInPasswordResetPage(browser);
        Assert.assertTrue(linkedInPasswordResetPage.isLoaded(), "Password reset page is not loaded.");
        linkedInPasswordResetPage.enterEmailToFindAccount("youngbloodvasilievna@gmail.com");

        LinkedInPasswordResetSubmitPage linkedInPasswordResetSubmitPage = new LinkedInPasswordResetSubmitPage(browser);
        Assert.assertEquals(linkedInPasswordResetSubmitPage.getContentHeaderText(), "We just emailed you a link",
                "There is no notification that link was sent to user email.");
        browser.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        Assert.assertTrue(linkedInPasswordResetSubmitPage.isLoaded(), "Password reset submit page is not loaded.");
        linkedInPasswordResetSubmitPage.typeNewPasswordAndSubmit("Test!es!", "Test!es!");
        Assert.assertEquals(linkedInPasswordResetSubmitPage.getContentHeaderText(),
                "Your password has been changed successfully",
                "There is no notification that password was changed successfully.");
        linkedInPasswordResetSubmitPage.goToHomepage();
        LinkedInHomePage linkedInHomePage = new LinkedInHomePage(browser);
        Assert.assertTrue(linkedInHomePage.isLoaded());
    }
}