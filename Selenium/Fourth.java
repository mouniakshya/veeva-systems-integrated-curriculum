package package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Fourth {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
		WebElement dropDown = driver.findElement(By.id("dropdowm-menu-1"));
		dropDown.click();
		
		Select s = new Select(dropDown);
		s.selectByVisibleText("SQL");
//		s.selectByIndex(1);
//		s.selectByValue("python");

	}

}