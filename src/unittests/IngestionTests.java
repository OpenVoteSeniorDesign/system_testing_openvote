package unittests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;

public class IngestionTests {
	private static WebDriver wd;
	private static String web_address = "http://openvote-poc.appspot.com/";

	@BeforeClass
	public static void oneTimeSetup() {
		wd = new FirefoxDriver();
		wd.get(web_address);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		wd.close();
	}

}
