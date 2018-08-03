import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    @Test                                       //аннотация для компилятора о том, что метод можно выполнить
    public void successfulLoginTest() {
    }

    @Test
    public void negativeLoginTest() {
        WebDriver browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        WebElement userEmailField = browser
                .findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = browser
                .findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = browser
                .findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
    }
}
