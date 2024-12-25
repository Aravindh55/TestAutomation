package testcases;

import java.io.IOException;

import java.util.NoSuchElementException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseClass;
import Utils.RetryEngine;
import Utils.dataread;
import pages.HomePage;
import pages.LoginPage;
import projspecbase.ProjectSpecificBase;

@Test(groups = {"regression"})
public class TC001 extends ProjectSpecificBase{

	
	
	@BeforeTest
	public void setValue() {
		
		testcasename = "TC001";
		testdescription = "testing login";
		author = "Aravindh";
		category = "regression";
		dataSheetName = "test";
		
		
		
	}
	

	@Test
	public void run_TCOO1() throws IOException {
		
		if (driver == null) {
			System.out.println("The driver is null");
		}
		
		System.out.println("");
	
		LoginPage log = new LoginPage(driver,node);
		log.enterUsername("Admin");
		log.enterpassword("admin123");
		
		log.clickLogin();
		
		HomePage home = new HomePage(driver,node);
		home.verifyHomepage();

	
	
	}
}
