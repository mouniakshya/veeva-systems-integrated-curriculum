
package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Third {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("file:///C:/Users/MOUNI/index.html");

        String heading = driver.findElement(
            By.xpath("//h1[contains(text(), 'Welcome')]")
        ).getText();

        System.out.println(heading);

        driver.findElement(By.id("firstName"))
              .sendKeys("Raju");

        driver.findElement(By.id("lastName"))
              .sendKeys("Ramu");

        Thread.sleep(3000);

        driver.quit();
    }
}
