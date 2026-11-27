import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
public class window {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/browser-windows");
        String parent = driver.getWindowHandle();
        System.out.println("Parent Window: " + driver.getTitle());
        driver.findElement(By.id("tabButton")).click();
        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(parent)) {
                driver.switchTo().window(w);
                System.out.println("Child Window: " + driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parent);
        System.out.println("Switched Back to Parent: " + driver.getTitle());
    }
}
