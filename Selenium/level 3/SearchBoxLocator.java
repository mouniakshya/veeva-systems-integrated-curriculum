
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchBoxLocator {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        WebElement search;

        // 1. ID Locator
        search = driver.findElement(By.id("ti6dpd"));
        search.sendKeys("Selenium Java");
        search.clear();

        // 2. Name Locator
        search = driver.findElement(By.name("q"));
        search.sendKeys("Selenium Java");
        search.clear();

        // 3. XPath Locator
        search = driver.findElement(
            By.xpath("//textarea[@name='q']")
        );
        search.sendKeys("Selenium Java");
        search.clear();

        // 4. CSS Selector
        search = driver.findElement(
            By.cssSelector("textarea[name='q']")
        );
        search.sendKeys("Selenium Java");

        System.out.println("Search query entered successfully");

        driver.quit();
    }
}
