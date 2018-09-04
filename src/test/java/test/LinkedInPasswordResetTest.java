package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;

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
        Assert.assertTrue(linkedInRequestPasswordResetPage.isLoaded(),
                "RequestPasswordResetPage is not loaded.");

        LinkedInPasswordResetSubmitPage linkedInPasswordResetSubmitPage = linkedInRequestPasswordResetPage.findAccount
                ("youngbloodvasilievna@gmail.com");
        Assert.assertTrue(linkedInPasswordResetSubmitPage.isLoaded(), "PasswordResetSubmitPage is not loaded.");

        LinkedInSetNewPasswordPage linkedInSetNewPasswordPage = linkedInPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedInSetNewPasswordPage.isLoaded(), "SetNewPasswordPage is not loaded.");
        linkedInSetNewPasswordPage.typeNewPasswordAndSubmit("Test1es1", "Test1es1");

        LinkedInHomePage linkedInHomePage = linkedInSetNewPasswordPage.goToHomePage();
        Assert.assertTrue(linkedInHomePage.isLoaded(), "Home page is not loaded.");
    }
}
