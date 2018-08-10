import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedInHomePage {

    private WebDriver browser;
    private WebElement profileNavigationItem;

    //конструктор для инициализации переменной browser
    public LinkedInHomePage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        profileNavigationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
    }

    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }

    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }
}
