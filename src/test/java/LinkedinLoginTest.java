import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver browser;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test                                       //аннотация для компилятора о том, что метод можно выполнить
    public void successfulLoginTest() throws InterruptedException {

        LinkedInLoginPage linkedInLoginPage = new LinkedInLoginPage(browser);
        linkedInLoginPage.login("youngbloodvasilievna@gmail.com", "Pensiya15000");

        LinkedInHomePage linkedInHomePage = new LinkedInHomePage(browser);

        sleep(3000);

        String pageUrl = browser.getCurrentUrl();
        String pageTitle = browser.getTitle();

        Assert.assertEquals(pageUrl, "https://www.linkedin.com/feed/", "Home page URL is wrong");
        Assert.assertEquals(pageTitle,"LinkedIn", "Home page title is wrong.");
        Assert.assertTrue(linkedInHomePage.isProfileNavigationItemDisplayed(), "'profileNavigationItem' is not displayed on Home page");
        //Assert.assertEquals(profileNavigationItem.isDisplayed(), true);
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        WebElement userEmailField = browser
                .findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = browser
                .findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = browser
                .findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        sleep(3000);

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
    }
}
