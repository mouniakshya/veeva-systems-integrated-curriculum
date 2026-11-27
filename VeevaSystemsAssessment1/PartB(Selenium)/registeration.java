
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class registeration {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("file:///E:/index.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("name")).sendKeys("mouni");
        driver.findElement(By.id("email")).sendKeys("mouni@gmail.com");
        driver.findElement(By.id("password")).sendKeys("mouni@123");

        WebElement country = driver.findElement(By.id("country"));
        Select s = new Select(country);
        s.selectByVisibleText("India");

        WebElement terms = driver.findElement(By.id("terms"));

        if (!terms.isSelected()) {
            terms.click();
        }

        driver.findElement(By.id("submitBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement msg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("successMsg"))
        );

        System.out.println(msg.getText());

        
    }
}
