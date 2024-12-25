package projspecbase;

import java.io.IOException;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import Base.BaseClass;
import Utils.dataread;

public class ProjectSpecificBase extends BaseClass {
	
	@Parameters({"reportname"})@BeforeSuite(alwaysRun = true)
	public void initialiseReport(String suite) {
		startReport(suite);
		
	}


public String dataSheetName;
	
	@DataProvider(name="fetchdata")
public Object[][] fetchdata() throws IOException {
	
	return dataread.readData(dataSheetName);
}
	
	
	@BeforeMethod(alwaysRun = true)
	public void preCondition() {
		
	report();
	launchBrowser("chrome");
	
	node = test.createNode(testcasename);

	}

	@AfterMethod(alwaysRun = true)
	public void postCondition() {
        quitBrowser();
	}
	
	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		stopReport();
	}

}
