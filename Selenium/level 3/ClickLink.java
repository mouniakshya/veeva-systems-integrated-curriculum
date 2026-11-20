
package package1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickLink {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        // Find all links
        List<WebElement> links = driver.findElements(
            By.tagName("a")
        );

        System.out.println("Total Links: " + links.size());

        // Click specific link using Link Text
        driver.findElement(
            By.linkText("Form Authentication")
        ).click();

        System.out.println("Form Authentication page opened");

        driver.quit();
    }
}
