package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.HtmlReporter;

public class BaseClass extends HtmlReporter {



	public void launchBrowser(String browser) {

		switch (browser.toLowerCase()) {
		case "chrome":

			ChromeOptions op = new ChromeOptions();
			op.addArguments("--disable-notifications");
			op.addArguments("--incognito");

			driver = new ChromeDriver(op);

			break;
		case "edge":

			EdgeOptions op1 = new EdgeOptions();
			op1.addArguments("--disable-notifications");
			op1.addArguments("-inprivate");

			driver = new EdgeDriver(op1);

		default:
			throw new RuntimeException("The browser type did not match");
		}

		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		

	}

	public HashMap<String, String> getLocator(String modulename, String key) throws IOException {

		FileInputStream file = new FileInputStream("./ObjectRepositories/" + modulename + ".properties");
		Properties prop = new Properties();
		prop.load(file);
		String property = prop.getProperty(key);
		String[] split = property.split("=", 2);
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("locatortype", split[0]);
		map.put("locatorvalue", split[1]);
		return map;

	}

	public WebElement locateElement(HashMap<String, String> locator) {

		switch (locator.get("locatortype").toLowerCase()) {
		case "id":
			return driver.findElement(By.id(locator.get("locatorvalue")));
		case "xpath":
			return driver.findElement(By.xpath(locator.get("locatorvalue")));
		case "name":
			return driver.findElement(By.name(locator.get("locatorvalue")));
		default:
			break;
		}
		return null;

	}

	public void waituntilElementIsClickable(HashMap<String, String> map) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		switch (map.get("locatortype")) {
		case "id":

			wait.until(ExpectedConditions.elementToBeClickable(By.id(map.get("locatorvalue"))));
			break;
		case "name":

			wait.until(ExpectedConditions.elementToBeClickable(By.name(map.get("locatorvalue"))));
			break;
		case "xpath":

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(map.get("locatorvalue"))));
			break;

		default:
			break;
		}

	}

	public void waituntilElementIsVisible(HashMap<String, String> map) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		switch (map.get("locatortype")) {
		case "id":

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(map.get("locatorvalue"))));
			break;
		case "name":

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(map.get("locatorvalue"))));
			break;
		case "xpath":

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(map.get("locatorvalue"))));
			break;

		default:
			break;
		}

	}

	public void clickElement(HashMap<String, String> map) {
		waituntilElementIsClickable(map);
		locateElement(map).click();
	}

	public void enterInput(HashMap<String, String> map, String value) {
		waituntilElementIsVisible(map);
		locateElement(map).sendKeys(value);
	}

	public void quitBrowser() {

		driver.quit();
	}

	public boolean verifyElementisDisplayed(HashMap<String, String> map) {
		waituntilElementIsVisible(map);
		boolean displayed = locateElement(map).isDisplayed();

		return displayed;

	}

	public String readtestdata(String excelname, String module, String testcasename, String testdataname)
			throws IOException {

		FileInputStream fis = new FileInputStream("./uploadfiles/" + excelname + ".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(module);
		HashMap<String, Integer> row = new HashMap<String, Integer>();
		HashMap<String, Integer> column = new HashMap<String, Integer>();

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {

			String value = sheet.getRow(i).getCell(0).getStringCellValue();
			row.put(value, i);
		}
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {

			String value = sheet.getRow(0).getCell(i).getStringCellValue();
			column.put(value, i);
		}
		System.out.println(row);
		System.out.println(column);
		
		String output = sheet.getRow(row.get("admin")).getCell(column.get("password")).getStringCellValue();
         return output;
	}

}