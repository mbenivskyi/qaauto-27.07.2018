package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedInLoginPage;
import page.LinkedInPasswordResetSubmitPage;
import page.LinkedInRequestPasswordResetPage;
import page.LinkedInSetNewPasswordPage;

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
    public void successfulResetPasswordTest(){
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "Login page is not loaded.");
        LinkedInRequestPasswordResetPage linkedInRequestPasswordResetPage = linkedInLoginPage.clickOnForgotPasswordLink();

        LinkedInPasswordResetSubmitPage linkedInPasswordResetSubmitPage = linkedInRequestPasswordResetPage.findAccount
                ("youngbloodvasilievna@gmail.com");
        Assert.assertTrue(linkedInPasswordResetSubmitPage.isLoaded(), "Password reset submit page is not loaded.");

        //Navigate to URL from email manually
        LinkedInSetNewPasswordPage linkedInSetNewPasswordPage = linkedInPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedInSetNewPasswordPage.isLoaded(), "SetNewPasswordPage is not loaded.");

    }
}
