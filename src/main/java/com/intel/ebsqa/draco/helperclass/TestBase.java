package com.intel.ebsqa.draco.helperclass;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.intel.ebsqa.databaseutils.MySqlWrapper;
import com.intel.ebsqa.draco.library.common.ExcelCommon;
import com.intel.ebsqa.draco.library.common.SFCommon;
import com.intel.ebsqa.exceptions.InvalidBrowserException;
import com.intel.ebsqa.helperclass.DateTimeUtils;
import com.intel.ebsqa.listeners.customEventListener;
import com.intel.ebsqa.wrappers.ExcelWrapper;
import com.intel.ebsqa.wrappers.LoggerWrapper;
import com.intel.ebsqa.wrappers.SeleniumWrapper;

@Listeners(customEventListener.class)
public class TestBase {
	final String sConfigFilePath = "configuration/Config_QA.cfg";
	final String sLoggerConfigFilePath = "configuration/log4j.properties";
	public static WebDriver driver;
	public static ConfigHelper configObj = null;
	public static LoggerWrapper log = null;
	public static SeleniumWrapper seleniumObj = null;
	public static MySqlWrapper mySQLObj = null;
	public static SFCommon sfcommonObj = null;
	public static BaseTest baseTestObj = null;
	public static ExcelCommon excelObj = null;
	//public static ExcelWrapper excelObj = null;
	public static HashMap<String, Object> userCredentials = new HashMap<String, Object>();

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "configpath" })
	public void initialize(@Optional(sConfigFilePath) String sCfgPath) throws IOException {
		PropertyConfigurator.configure(sLoggerConfigFilePath);
		log = new LoggerWrapper(TestBase.class);
		configObj = new ConfigHelper(sCfgPath);
		seleniumObj = new SeleniumWrapper();
		baseTestObj = new BaseTest();
		sfcommonObj = new SFCommon();
		//excelObj = new ExcelCommon(System.getProperty("user.dir") + configObj.getFilexlsxPath());
		mySQLObj = new MySqlWrapper(configObj.getTestDBServerIP(), configObj.getTestDBPORTNo(),
				configObj.getTestDBName(), configObj.getTestDBUserName(), configObj.getTestDBPassword());
	}

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser() throws InvalidBrowserException, com.intel.ebsqa.exceptions.InvalidBrowserException {
		String downloadPath = System.getProperty("user.dir") + configObj.getFileDownLoadPath();
	    if(configObj.getBrowserName().equals("CHROME")){
		seleniumObj.openBrowser(configObj.getBrowserName(), configObj.getChromeDriverPath(),
				configObj.getImplicitWaitTime(), downloadPath);
		}
		if(configObj.getBrowserName().equals("FIREFOX")){
			seleniumObj.openBrowser(configObj.getBrowserName(), configObj.getFirefoxDriverPath(),
					configObj.getImplicitWaitTime(), downloadPath);
			}
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowserAndKillUpDriver(ITestResult result) {
		try {		
		if (result.getStatus() == ITestResult.FAILURE) {
			String outputFilePath = System.getProperty("user.dir") + "\\result\\Failed_" + result.getName() + "_"
					+ DateTimeUtils.getCurrentDateAndTime() + ".png";
			seleniumObj.captureScreen(outputFilePath);
			}
		   }catch(Exception e) {
		}
		finally {
			seleniumObj.getDriver().manage().deleteAllCookies();
			seleniumObj.closeBrowser();
		}
	}

	@AfterTest(alwaysRun = true)
	public void AfterTestcase() {

	}

	@AfterSuite(alwaysRun = true)
	public void cleanUp() {

	}
}
