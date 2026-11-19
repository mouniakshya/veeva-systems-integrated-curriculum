
package package1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollPage {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        // Create JavascriptExecutor object
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        Thread.sleep(2000);

        System.out.println("Page scrolled down successfully");

        // Scroll up
        js.executeScript("window.scrollTo(0, 0)");

        Thread.sleep(2000);

        driver.quit();
    }
}
