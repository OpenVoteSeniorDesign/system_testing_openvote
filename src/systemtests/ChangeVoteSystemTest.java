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

public class ChangeVoteSystemTest {
	private static WebDriver wd;
	private static String web_address = "http://openvote-poc.appspot.com/";

	@BeforeClass
	public static void oneTimeSetup() {
		wd = new FirefoxDriver();
		wd.get(web_address);
	}
	//Simple case. Select Dory and change the vote to Nemo
	@Test public void t0(){
		//Login
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
		//vote for Dory, test it
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
		//change vote to nemo, test it
		wd.findElement(By.id("btn_changevote")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_Nemo")));
		wd.findElement(By.id("btn_Nemo")).click();
		wd.findElement(By.id("btn_submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_num")));
		WebElement id2 = wd.findElement(By.id("id_num"));
		String id2_str = id2.getText();
		System.out.println(id2_str);
		WebElement candidate2 = wd.findElement(By.id("candidate_name"));
		String cand2_str = candidate2.getText();
		assertEquals("Nemo", cand2_str);
		// leave the page, go to end page
		we = wd.findElement(By.id("btn_goodbye"));
		we.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_gotoliveresults")));
		we = wd.findElement(By.id("btn_gotoliveresults"));
		String btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Click here to go to Live Results!");
		we.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_refresh")));
		//test we got to the all results page
		we = wd.findElement(By.id("btn_refresh"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Refresh");
		we = wd.findElement(By.id("btn_return_login"));
		btn_str = we.getAttribute("value");
		assertEquals(btn_str, "Return to Login");
		
		//test we got the votes recorded correctly in the singlevote page
		//first vote, dory
		wd.get(web_address + "/viewSingleVote.jsp?votekey=" + id_str);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_entry")));
		assertEquals(id_str, wd.findElement(By.id("id_entry")).getText());
		assertEquals("Dory", wd.findElement(By.id("candidate_entry")).getText());
		//change vote to nemo
		wd.get(web_address + "/viewSingleVote.jsp?votekey=" + id2_str);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_entry")));
		assertEquals(id2_str, wd.findElement(By.id("id_entry")).getText());
		assertEquals("Nemo", wd.findElement(By.id("candidate_entry")).getText());
		
	}


	@AfterClass
	public static void oneTimeTearDown() {
		wd.close();
	}

}
