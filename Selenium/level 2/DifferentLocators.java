
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DifferentLocators {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        // 1. ID Locator
        WebElement username = driver.findElement(
            By.id("username")
        );

        System.out.println("ID: " + username.getAttribute("id"));

        // 2. Name Locator
        WebElement password = driver.findElement(
            By.name("password")
        );

        System.out.println("NAME: " + password.getAttribute("name"));

        // 3. XPath Locator
        WebElement login = driver.findElement(
            By.xpath("//button[@id='submit']")
        );

        System.out.println("XPATH: " + login.getText());

        // 4. CSS Selector
        WebElement css = driver.findElement(
            By.cssSelector("input#username")
        );

        System.out.println("CSS: " + css.getAttribute("id"));

        // Enter details
        username.sendKeys("student");

        password.sendKeys("Password123");

        login.click();

        driver.quit();
    }
}
