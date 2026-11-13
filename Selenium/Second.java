
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Second {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Raju");

        driver.findElement(By.id("lastName")).sendKeys("Ramu");

        driver.findElement(By.id("userEmail"))
              .sendKeys("Rajuramu@gmail.com");

        driver.findElement(By.cssSelector(
            "label[for='gender-radio-1']"
        )).click();

        driver.findElement(By.id("userNumber"))
              .sendKeys("9348352550");

        driver.findElement(By.id("currentAddress"))
              .sendKeys("abc street!");

        Thread.sleep(4000);

        driver.quit();
    }
}
