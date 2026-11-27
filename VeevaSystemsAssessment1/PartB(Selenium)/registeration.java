import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class registeration {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("file:///E:/index.html");
            driver.manage().window().maximize();
            driver.findElement(By.id("name")).sendKeys("mouni");
            driver.findElement(By.id("email")).sendKeys("mouni@gmail.com");
            driver.findElement(By.id("password")).sendKeys("mouni@123");

            WebElement countryDropdown = driver.findElement(By.id("coutry"));
            Select countrySelect = new Select(countryDropdown);
            countrySelect.selectByVisibleText("India");
            WebElement termsCheckbox = driver.findElement(By.id("terms"));
            if (!termsCheckbox.isSelected()) {
                termsCheckbox.click();
            }
            driver.findElement(By.id("submitBtn")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successMsg"))
            );
            String actualText = successMessage.getText();
            if ("Registration Successful!".equals(actualText)) {
                System.out.println("Test Passed: " + actualText);
            } else {
                System.out.println("Test Failed: Unexpected text -> " + actualText);
            }
        } finally {
            driver.quit();
        }
    }
}
