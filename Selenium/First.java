
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class First {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.cssSelector("input[id*='user']"))
              .sendKeys("student");

        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        driver.findElement(By.id("submit")).click();

        String result = driver.findElement(
            By.xpath("//h1[text()='Logged In Successfully']")
        ).getText();

        System.out.println(result);

        driver.quit();
    }
}
