
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class HiddenElement {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.sreenidhirajakrishnan.com/practice");

        // Locate hidden button
        WebElement hidden = driver.findElement(
            By.xpath("//button[contains(text(),'I am hidden via display:none')]")
        );

        // Check visibility
        System.out.println("Is displayed: " + hidden.isDisplayed());

        // Create JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Click hidden element using JavaScript
        js.executeScript("arguments[0].click();", hidden);

        System.out.println("Hidden element clicked using JavaScript");

        driver.quit();
    }
}
