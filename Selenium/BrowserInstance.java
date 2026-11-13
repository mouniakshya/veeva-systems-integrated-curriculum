
package package1;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserInstance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter browser name:");
        String browser = sc.nextLine();

        WebDriver driver = null;

        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else {
            System.out.println("Invalid browser name");
            sc.close();
            return;
        }

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        System.out.println("Browser launched successfully");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();

        sc.close();
    }
}
