
package package1;

import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        Thread.sleep(3000);

        String title = driver.getTitle();

        String url = driver.getCurrentUrl();

        System.out.println("Page Title: " + title);

        System.out.println("Current URL: " + url);

        if(title.equalsIgnoreCase("Google")) {
            System.out.println("Title Verification Passed");
        }
        else {
            System.out.println("Title Verification Failed");
        }

        if(url.contains("google.co.in")) {
            System.out.println("Redirection to Google.co.in Successful");
        }
        else {
            System.out.println("Redirection to Google.co.in Not Detected");
        }

        driver.quit();
    }
}
