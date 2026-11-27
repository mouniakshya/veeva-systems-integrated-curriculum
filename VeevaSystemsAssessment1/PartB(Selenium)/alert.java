import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class alert {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert a = driver.switchTo().alert();
        System.out.println(a.getText());
        a.accept();
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert b = driver.switchTo().alert();
        System.out.println(b.getText());
        b.sendKeys("Automation Test");
        b.accept();
            }
}