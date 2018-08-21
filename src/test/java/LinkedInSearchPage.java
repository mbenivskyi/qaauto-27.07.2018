import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LinkedInSearchPage extends BasePage{

    @FindBy(xpath = "//*[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> searchResults;

    public LinkedInSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return searchResults.isEmpty()
              && getCurrentPageTitle().contains("\"hr\" | Search | LinkedIn")
              && getCurrentPageUrl().contains("/search/results/index/?keywords=hr&origin=GLOBAL_SEARCH_HEADER");
    }

    public boolean areThere10ResultsOnSearchPage() {
        if (searchResults.size() == 10) {
            return true;
        }
        return false;
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
