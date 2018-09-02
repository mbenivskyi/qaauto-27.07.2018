package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkedInSearchPage extends BasePage{

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    public LinkedInSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        browser.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return searchResultsTotal.isDisplayed()
              && getCurrentPageTitle().contains("| Search | LinkedIn")
              && getCurrentPageUrl().contains("/search/results/");
    }

    public int getSearchResultsCount() {
        return searchResults.size();
    }

    public boolean isSearchResultsContainSearchTerm() {
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("hr")){
                return true;}
        }
        return false;
    }
}
