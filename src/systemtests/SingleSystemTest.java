package systemtests;

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

public class SingleSystemTest {
	private static WebDriver wd;
	private static String web_address = "http://openvote-poc.appspot.com/";

	@BeforeClass
	public static void oneTimeSetup() {
		wd = new FirefoxDriver();
		wd.get(web_address);



	}
	//Simple case. Select Dory and do not change the vote
	@Test public void t0(){
		WebElement we = wd.findElement(By.id("btn_continue_not_admin"));
		we.click();

		we = wd.findElement(By.id("Email"));
		we.sendKeys("reed.cozart@gmail.com");
		we = wd.findElement(By.id("next"));
		we.click();
		WebDriverWait wait = new WebDriverWait(wd, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

		we = wd.findElement(By.id("Passwd"));
		we.sendKeys("g3931eATX");
		we = wd.findElement(By.id("signIn"));
		we.click();

		wd.findElement(By.id("btn_continue_admin")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Dory")));
		we = wd.findElement(By.id("btn_Dory"));
		we.click();

		wd.findElement(By.id("btn_submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_num")));

		WebElement id = wd.findElement(By.id("id_num"));
		String id_str = id.getText();
		System.out.println(id_str);

		WebElement candidate = wd.findElement(By.id("candidate_name"));
		String cand_str = candidate.getText();
		assertEquals("Dory", cand_str);


		we = wd.findElement(By.id("btn_goodbye"));
		we.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_gotoliveresults")));

		we = wd.findElement(By.id("btn_gotoliveresults"));
		String btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Click here to go to Live Results!");
		we.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_refresh")));

		we = wd.findElement(By.id("btn_refresh"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Refresh");
		we = wd.findElement(By.id("btn_return_login"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Return to Login");

		wd.get(web_address + "/viewSingleVote.jsp?votekey=" + id_str);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_entry")));

		assertEquals(id_str, wd.findElement(By.id("id_entry")).getText());
		assertEquals("Dory", wd.findElement(By.id("candidate_entry")).getText());
	}

	//second case, select Nemo and do not change the vote. 
	@Test public void t1(){
		wd.get(web_address);
		WebElement we;
		WebDriverWait wait = new WebDriverWait(wd, 5);;
		try{
			we = wd.findElement(By.id("btn_continue_not_admin"));
			we.click();
			//this is the second time we enter the password and google remembers the email
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

			we = wd.findElement(By.id("Passwd"));
			we.sendKeys("g3931eATX");
			we = wd.findElement(By.id("signIn"));
			we.click();
		} catch(NoSuchElementException nsee){}
		wd.findElement(By.id("btn_continue_admin")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Nemo")));
		we = wd.findElement(By.id("btn_Nemo"));
		we.click();

		wd.findElement(By.id("btn_submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_num")));

		WebElement id = wd.findElement(By.id("id_num"));
		String id_str = id.getText();
		System.out.println(id_str);

		WebElement candidate = wd.findElement(By.id("candidate_name"));
		String cand_str = candidate.getText();
		assertEquals("Nemo", cand_str);


		we = wd.findElement(By.id("btn_goodbye"));
		we.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_gotoliveresults")));

		we = wd.findElement(By.id("btn_gotoliveresults"));
		String btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Click here to go to Live Results!");
		we.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_refresh")));

		we = wd.findElement(By.id("btn_refresh"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Refresh");
		we = wd.findElement(By.id("btn_return_login"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Return to Login");

		wd.get(web_address + "/viewSingleVote.jsp?votekey=" + id_str);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_entry")));

		assertEquals(id_str, wd.findElement(By.id("id_entry")).getText());
		assertEquals("Nemo", wd.findElement(By.id("candidate_entry")).getText());

	}

	@AfterClass
	public static void oneTimeTearDown() {
		wd.close();
	}

}
