package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class LinkedInPasswordResetTest extends BaseTest{

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
        linkedInSetNewPasswordPage.typeNewPasswordAndSubmit("Pensiya15000", "Pensiya15000");

        LinkedInHomePage linkedInHomePage = linkedInSetNewPasswordPage.goToHomePage();
        Assert.assertTrue(linkedInHomePage.isLoaded(), "Home page is not loaded.");
    }
}
