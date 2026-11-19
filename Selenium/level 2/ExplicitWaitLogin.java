
package package1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitLogin {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        // Wait until username field is visible
        WebElement username = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("username")
            )
        );

        username.sendKeys("student");

        // Enter password
        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        // Wait until login button is clickable
        WebElement login = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("submit")
            )
        );

        login.click();

        // Wait until dashboard header appears
        WebElement heading = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.tagName("h1")
            )
        );

        System.out.println("Dashboard: " + heading.getText());

        driver.quit();
    }
}
