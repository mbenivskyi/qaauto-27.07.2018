import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInHomePage extends BasePage {

    @FindBy (xpath = "//*[@id='profile-nav-item']")
    private WebElement profileNavigationItem;

    @FindBy (xpath = "//input[@role='combobox']")
    private WebElement searchField;

    //конструктор для инициализации переменной browser
    public LinkedInHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }

    public LinkedInSearchPage searchForItem (String SearchText) {
        searchField.sendKeys(SearchText);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedInSearchPage(browser);
    }
}
