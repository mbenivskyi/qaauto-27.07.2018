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

    /** Constructor of LinkedInSearchPage
     * @param browser Webdriver variable from test
     */
    public LinkedInSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchResultsTotal, 10);
    }

    /**
     * Verify if LinkedInSearchPage is loaded
     * @return Returns if Webelement is displayed and page's title and URL contain appropriate values
     */
    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
              && getCurrentPageTitle().contains("| Search | LinkedIn")
              && getCurrentPageUrl().contains("/search/results/");
    }

    /**
     * @return Returns search results size
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }

    /**
     * Verify if search results contain search term
     * @return Returns true/false
     */
    public boolean isSearchResultsContainSearchTerm() {
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("hr")){
                return true;}
        }
        return false;
    }
}
