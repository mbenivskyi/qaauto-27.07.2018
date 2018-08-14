import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver browser;
    LinkedInLoginPage linkedInLoginPage;
    LinkedInLoginSubmitPage linkedInLoginSubmitPage;

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

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                { "youngbloodvasilievna@gmail.com", "Pensiya15000" },
                { "YOUNGBLOODVASILIEVNA@gmail.com", "Pensiya15000" }
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {

        linkedInLoginPage.login(userEmail, userPass);

        LinkedInHomePage linkedInHomePage = new LinkedInHomePage(browser);

        Assert.assertTrue(linkedInHomePage.isLoaded(), "Home page is not loaded.");
    }

    @Test
    public void negativeLoginTest() {

        linkedInLoginPage.login("a@b.c", "wrong");
        LinkedInLoginSubmitPage linkedInLoginSubmitPage = new LinkedInLoginSubmitPage(browser);

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                { "", "" },
                { "", "P@ssword" },
                { "someone@domain.com", "" }
        };
    }

    @Test(dataProvider = "emptyFieldsCombination")
    public void validateUserEmailAndUserPassword (String userEmail, String userPass) {
        linkedInLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "User is not on Login page.");
    }

    @Test
    public void validateShortUserEmailAndPassword () {
        linkedInLoginPage.login("a", "a");
        LinkedInLoginSubmitPage linkedInLoginSubmitPage = new LinkedInLoginSubmitPage(browser);
        Assert.assertTrue(linkedInLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page.");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");

        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "UserEmail field has wrong validation message text.");

        Assert.assertEquals(linkedInLoginSubmitPage.getUserPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "UserEmail field has wrong validation message text.");
    }

    @Test
    public void negativeLoginTestEmptyPasswordField () {
        linkedInLoginPage.login("a@b.c", "");

        Assert.assertFalse(linkedInLoginPage.isSignInButtonEnabled(), "Sign In button is enabled.");
    }

    @Test
    public void negativeLoginTestEmptyUserEmailField () {
        linkedInLoginPage.login("", "wrong");

        Assert.assertFalse(linkedInLoginPage.isSignInButtonEnabled(), "Sign In button is enabled.");
    }

    @Test
    public void negativeLoginTestSpaceCredentials () {
        linkedInLoginPage.login("     ", "     ");

        Assert.assertFalse(linkedInLoginPage.isSignInButtonEnabled(), "Sign In button is enabled.");
    }

    @Test
    public void negativeLoginTestWrongPassword () {
        linkedInLoginPage.login("youngbloodvasilievna@gmail.com", "wrong");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLoginTestLongWrongPassword () {
        linkedInLoginPage.login("youngbloodvasilievna@gmail.com", "wrongggg");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserPasswordValidationText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLoginTestWrongUserEmail () {
        linkedInLoginPage.login("a@b.c", "Pensiya15000");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                "Please enter a valid email address.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLoginTestUserEmailPasswordViceVersa () {
        linkedInLoginPage.login("Pensiya15000", "youngbloodvasilievna@gmail.com");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                "Please enter a valid email address.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLoginTestSymbolsInUserEmailField () {
        linkedInLoginPage.login("♣☺♂", "Pensiya15000");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                "Be sure to include \"+\" and your country code.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLoginTestKeyboardSymbolsInUserEmailField () {
        linkedInLoginPage.login("!@#$%^&*()", "Pensiya15000");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                "Hmm, we don't recognize that email. Please try again.",
                "Alert box has incorrect message");
    }
}
