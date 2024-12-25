package pages;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import projspecbase.ProjectSpecificBase;

public class HomePage extends ProjectSpecificBase {
	
	

	public HomePage(RemoteWebDriver driver, ExtentTest node) {
		
		this.driver = driver;
		this.node = node;
		
	}
	
	public void verifyHomepage() throws IOException {
		if(verifyElementisDisplayed(getLocator("HomePage", "title"))) {
			reportStep("The dashboard title is displayed", "pass");
		}else {
			reportStep("The dashboard title is not displayed", "fail");
		}

	}

}
