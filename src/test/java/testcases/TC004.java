package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import projspecbase.ProjectSpecificBase;

public class TC004 extends ProjectSpecificBase {
	@BeforeTest
	public void setValue() {
		
		testcasename = "TC004";
		testdescription = "testing login";
		author = "Aravindh";
		category = "regression";
		dataSheetName = "test";
		
		
		
	}
	

	@Test
	public void run_TCOO4() throws IOException {
		
		LoginPage log = new LoginPage(driver, node);
		log.enterUsername("Admin");
		log.enterpassword("admin123");
		
		log.clickLogin();
		
		HomePage home = new HomePage(driver, node);
		home.verifyHomepage();
//	String readtestdata = readtestdata("test", "Sheet1", username, password);
//	System.out.println(readtestdata);
	
	
	}
}
