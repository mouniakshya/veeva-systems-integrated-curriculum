
package package1;

import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTitle {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

        if(title != null && !title.isEmpty()) {
            System.out.println("Title is displayed");
        }
        else {
            System.out.println("Title is not displayed");
        }

        if(title.equalsIgnoreCase("Google")) {
            System.out.println("Title Verification Passed");
        }
        else {
            System.out.println("Title Verification Failed");
        }

        driver.quit();
    }
}
