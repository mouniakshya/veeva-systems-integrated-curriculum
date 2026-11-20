
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaAlerts {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 1. Simple Alert
        driver.findElement(
            By.xpath("//button[text()='Click for JS Alert']")
        ).click();

        Alert alert = driver.switchTo().alert();

        System.out.println("Alert Text: " + alert.getText());

        alert.accept();

        // 2. Confirmation Alert - Dismiss
        driver.findElement(
            By.xpath("//button[text()='Click for JS Confirm']")
        ).click();

        alert = driver.switchTo().alert();

        System.out.println("Confirm Text: " + alert.getText());

        alert.dismiss();

        // 3. Prompt Alert
        driver.findElement(
            By.xpath("//button[text()='Click for JS Prompt']")
        ).click();

        alert = driver.switchTo().alert();

        System.out.println("Prompt Text: " + alert.getText());

        alert.sendKeys("Hello Selenium");

        alert.accept();

        System.out.println("Alert handling completed");

        driver.quit();
    }
}
