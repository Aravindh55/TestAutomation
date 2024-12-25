package pages;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import projspecbase.ProjectSpecificBase;

public class LoginPage extends ProjectSpecificBase {

	public LoginPage(RemoteWebDriver driver, ExtentTest node) {

		this.driver = driver;
		this.node = node;
	}

	public void enterUsername(String username) throws IOException {

		try {
			enterInput(getLocator("Login", "usernamefield"), "Admin");
			reportStep("The user has entered the name", "pass");
		} catch (IOException e) {
		reportStep("The user was not able to enter the name", "fail");

		}
	}

	public void enterpassword(String password) throws IOException {
		try {
			enterInput(getLocator("Login", "passwordfield"), "admin123");
			reportStep("The user has entered the password", "pass");
		} catch (IOException e) {
			reportStep("The user was not able to enter the password", "fail");
		}
	}

	public void clickLogin() throws IOException {
		try {
			clickElement(getLocator("Login", "loginfield"));
			reportStep("The user has clicked", "pass");
		} catch (IOException e) {
			
		reportStep("The user was not able to click login", "fail");
			
		}
	}

}
