import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import static java.lang.Thread.sleep;

public class BadCodeExample {
    private static Object sendKeys;

    //main() is bad
    public static void main(String args[]) throws InterruptedException {
    System.out.println("Hello world!!!"); //all prints are bad
        WebDriver browser = new FirefoxDriver();
        browser.get("https://google.com");
        WebElement queryField = browser.findElement(By.name("q"));
        queryField.sendKeys ("Selenium");
        queryField.sendKeys(Keys.ENTER);

        //Verify that results list contains 10 elements
        //sleep is bad
        sleep(3000);
        List<WebElement> searchResults = browser.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Results count: "+searchResults.size());
        if (searchResults.size() == 10){
            System.out.println("Results count is correct");
        } else{
            System.out.println("Results count is incorrect");
        }

        //Verify that each result item contain searchterm
        //for each searchResult in searchResults List
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("selenium")){
                System.out.println("Searchterm found");
            }else{
                System.out.println("Searchterm not found");
            }
        }

        sleep(3000);
        browser.close();
    }
}
