package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HtmlReporter {
	

	public RemoteWebDriver driver;
	public WebDriverWait wait;

	
  public static ExtentSparkReporter reporter;
  public static ExtentReports extent;
  
  public ExtentTest test, node;
  
  public String testcasename, testdescription, author, category;
  
  public static String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
  public static ITestContext testcontext;
  public static String suitename;
  
  public void startReport(String suite) {
	
	suitename = suite;
	
	  reporter = new ExtentSparkReporter("./reports"+suitename+""+date+"/result.html");
	  extent = new ExtentReports();
	  extent.attachReporter(reporter);
  }
  
  public void report() {
	  test = extent.createTest(testcasename, testdescription);
	  test.assignAuthor(author);
	  test.assignCategory(category);
  }
  
  public String takeScreenshot() throws IOException {
	  
	long snapnumber =(long) Math.floor(Math.random()*900000000L)+100000000L;
	File src = driver.getScreenshotAs(OutputType.FILE);
	File target = new File("./reports"+suitename+""+date+"/images/"+snapnumber+".png");
	FileUtils.copyFile(src, target);
	String absolutePath = target.getAbsolutePath();
	return absolutePath;

}
  
  public void reportStep(String desc,String status, boolean bSnap) throws IOException {
	Media img = null;
	if (bSnap && !status.equalsIgnoreCase("info")) {
		img = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build();
	}
	
	if (status.equalsIgnoreCase("pass")) {
		node.pass(desc,img);
	}
	if (status.equalsIgnoreCase("fail")) {
		node.fail(desc,img);
	}

}
  
  public void reportStep(String desc,String status) throws IOException {
	  
	  reportStep(desc, status, true);
	
}
  

  public void stopReport() {
	  
	  extent.flush();
  }

}
