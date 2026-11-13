
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username"))
              .sendKeys("student");

        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        driver.findElement(By.id("submit")).click();

        String result = driver.findElement(By.tagName("h1"))
                              .getText();

        System.out.println("Login Result: " + result);

        if(result.equals("Logged In Successfully")) {
            System.out.println("Login Test Passed");
        }
        else {
            System.out.println("Login Test Failed");
        }

        driver.quit();
    }
}
