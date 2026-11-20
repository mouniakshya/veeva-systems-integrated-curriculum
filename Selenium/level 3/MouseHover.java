
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseHover {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/hovers");

        // Create Actions object
        Actions action = new Actions(driver);

        // Locate first image
        WebElement image = driver.findElement(
            By.xpath("(//div[@class='figure'])[1]")
        );

        // Perform mouse hover
        action.moveToElement(image).perform();

        // Find hidden text after hover
        WebElement text = driver.findElement(
            By.xpath("(//div[@class='figcaption']/h5)[1]")
        );

        System.out.println("Hover Text: " + text.getText());

        // Click View Profile link
        driver.findElement(
            By.xpath("(//a[text()='View profile'])[1]")
        ).click();

        System.out.println("Profile link clicked");

        driver.quit();
    }
}
