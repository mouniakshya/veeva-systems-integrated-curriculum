
package package1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CountLinks {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        // Find all links
        List<WebElement> links = driver.findElements(
            By.tagName("a")
        );

        // Print total links
        System.out.println("Total Links: " + links.size());

        // Print each link text
        for(WebElement link : links) {

            System.out.println(link.getText());

        }

        driver.quit();
    }
}
