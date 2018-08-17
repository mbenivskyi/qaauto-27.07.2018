import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LinkedinLoginTest {

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

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                { "youngbloodvasilievna@gmail.com", "Pensiya15000" },
                { "YOUNGBLOODVASILIEVNA@gmail.com", "Pensiya15000" }
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {

        LinkedInHomePage linkedInHomePage = linkedInLoginPage.loginReturnHomePage(userEmail, userPass);

        Assert.assertTrue(linkedInHomePage.isLoaded(), "Home page is not loaded.");
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
        linkedInLoginPage.loginReturnLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedInLoginPage.isLoaded(), "User is not on Login page.");
    }

    @DataProvider
    public Object[][] invalidDataFieldsCombinations() {
        return new Object[][]{
                { "a", "a",
                        "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                        "The password you provided must have at least 6 characters."},
                { "youngbloodvasilievna@gmail.com", "wrongggg", "",
                        "Hmm, that's not the right password. Please try again or request a new one." },
                {"Pensiya15000", "youngbloodvasilievna@gmail.com",
                        "Please enter a valid email address.", ""},
                {"♣☺♂", "Pensiya15000",
                        "Be sure to include \"+\" and your country code.", ""},
                {"!@#$%^&*()", "Pensiya15000",
                        "Hmm, we don't recognize that email. Please try again.", ""}
        };
    }

    @Test(dataProvider = "invalidDataFieldsCombinations")
    public void validateUserEmailAndPassword (String userEmail,
                                                   String userPass,
                                                   String userEmailValidationText,
                                                   String userPassValidationText) {
        LinkedInLoginSubmitPage linkedInLoginSubmitPage = linkedInLoginPage.loginReturnLoginSubmitPage(userEmail, userPass);
        Assert.assertTrue(linkedInLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page.");

        Assert.assertEquals(linkedInLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");

        Assert.assertEquals(linkedInLoginSubmitPage.getUserEmailValidationText(),
                userEmailValidationText,
                "UserEmail field has wrong validation message text.");

        Assert.assertEquals(linkedInLoginSubmitPage.getUserPasswordValidationText(),
                userPassValidationText,
                "UserPass field has wrong validation message text.");
    }
}
