package systemtests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleSystemTest {
	private static WebDriver wd;

	@BeforeClass public static void oneTimeSetup(){
		wd = new FirefoxDriver();
		wd.get("localhost:8888/login.jsp");
	}

	@Test public void t0() { 
		// execute the test <x=four, y=four, z=four, computeButton=click() > and check the output message is correct
		wd.navigate().refresh();
		WebElement we = wd.findElement(By.id("login"));
		we.sendKeys("login");
		we = wd.findElement(By.id("password"));
		we.sendKeys("password");
		we = wd.findElement(By.id("btn_login"));
		we.click();
		WebDriverWait wdw = new WebDriverWait(wd, 5);
		WebElement greeting = wd.findElement(By.className("panel-heading"));
		String output = greeting.getText(); // read the output text
		assertEquals("Cast Your Vote!", output);
	}
}
