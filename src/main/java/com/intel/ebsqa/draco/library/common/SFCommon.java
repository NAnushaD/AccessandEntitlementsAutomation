/*
 * Copyright (c) 2018 EBS Automation Team. All rights reserved.
 */

package com.intel.ebsqa.draco.library.common;

import static org.testng.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matchers;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.intel.ebsqa.draco.BusinessFunction.ui.LoginPage_ExternalUser;
import com.intel.ebsqa.draco.BusinessFunction.ui.Navigation;
import com.intel.ebsqa.draco.DataClass.Users;
import com.intel.ebsqa.draco.PageClasses.LoginPage;
import com.intel.ebsqa.draco.PageClasses.NavigationPageClass;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.UserRole;
import com.intel.ebsqa.draco.enums.CommonEnum.AutomationPrefixes;
import com.intel.ebsqa.draco.enums.CommonEnum.CustomDateFormat;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.draco.utility.MongoDBRepository;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.intel.ebsqa.helperclass.StringUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.salesforce.lpop.feature.component.PageErrorImpl;
import com.salesforce.lpop.feature.global.panel.DesktopPanelModal;
import com.salesforce.lpop.feature.listview.ListViewManager;
import com.salesforce.lpop.feature.listview.ObjectHomePlace;
import com.salesforce.lpop.feature.records.form.RecordForm;
import com.salesforce.lpop.feature.records.form.RecordTypePicker;
import com.salesforce.lpop.feature.records.home.DetailPanel;
import com.salesforce.lpop.feature.records.home.RecordHomePlace;
import com.salesforce.lpop.framework.markers.ApplicationType;

/**
 * 
 * @author natarajs
 * 
 */
public class SFCommon extends TestBase {
	public String dateFormat = "MM/dd/yyyy";
	public String dateFormat1 = "MMM d, yyyy";
	public String dateFormat2 = "MM_dd_yyyy_HH_mm_ss";
	public String dateFormat3 = "yyyy-MM-dd";
	public String dateFormat4 = "d MMM, yyyy";
	public String dateFormat5 = "dd-MMM-yyyy";
	public String dateFormat6 = "M/d/yyyy";
	Navigation objNavigation = null;

	public SFCommon() {
		objNavigation = new Navigation();
	}

	/**
	 * - Fetches user credentials from automation test DB. <br>
	 * - Sets the application URL & credentials to LPOP page class. <br>
	 * 
	 * @param sApplicationName
	 * @param userRole
	 * @throws IOException
	 */
	public void loginToApplicationAs(String sApplicationName, String userRole) throws IOException {
		// HashMap<String, String> userCredentials = getUserDetails(userRole);
		String filepath = System.getProperty("user.dir") + configObj.getFilexlsxPath();
		String userName = "";
		String passWord = "";

		ArrayList<String> userRow = sfcommonObj.GetRowFromExcelSheet(filepath, "LoginCredentials", userRole, "User");

		if (configObj.getEnvironment().equals("DEVINT")) {
			userName = userRow.get(2);
			passWord = userRow.get(3);

		}
		if (configObj.getEnvironment().equals("QA")) {
			userName = userRow.get(2);
			passWord = userRow.get(3);
		}
		if (!userName.isEmpty() && !passWord.isEmpty()) {
			baseTestObj.utilities().login(userName, passWord);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.switchToLightningExperience();
			log.info("Logged in as " + userName);
		} else {
			Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
					+ userName.toString());
		}
	}

	/**
	 * Returns the user credentials from database - Mapping should be done with
	 * application name and user role
	 * 
	 * @param userRole
	 * @return
	 */
	public HashMap<String, Object> getUserDetails(String sApplicationName, UserRole userRole) {

		if (sApplicationName == null || userRole == null) {
			Assert.fail("Either application name or user role is missing, in order to fetch user credentials");
		}

		List<HashMap<String, Object>> _allUserCredentials = new ArrayList<HashMap<String, Object>>();
		String sQuery = "select username, pwd from login_info where application_name='" + sApplicationName
				+ "' and user_role='" + userRole.toString() + "'";

		_allUserCredentials = mySQLObj.GetRecords(sQuery);

		if (_allUserCredentials.size() == 0) {
			Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
					+ userRole.toString());
		}

		return _allUserCredentials.get(0);
	}

	public HashMap<String, String> getUserDetails(String role) {

		if (role == null) {
			Assert.fail("Role is empty");
		}
		Users objUserData = new Users();
		HashMap<String, String> creadientials = new HashMap<String, String>();
		MongoDBRepository<Users> objMongoDBRepository = new MongoDBRepository<Users>("Users");
		DBCollection objUsers = objMongoDBRepository._collection;
		BasicDBObject search = new BasicDBObject();
		List<BasicDBObject> userDetails = new ArrayList<BasicDBObject>();
		userDetails.add(new BasicDBObject("Role", role));
		userDetails.add(new BasicDBObject("Environment", configObj.getEnvironment()));
		search.put("$and", userDetails);
		DBCursor users = objUsers.find(search);

		while (users.hasNext()) {
			DBObject userMongoObj = users.next();

			objUserData.set_id("" + userMongoObj.get("_id"));
			objUserData.setEnvironment("" + userMongoObj.get("Environment"));
			objUserData.setUserName("" + userMongoObj.get("UserName"));
			objUserData.setPassword("" + userMongoObj.get("Password"));
			objUserData.setRole("" + userMongoObj.get("Role"));
			objUserData.setStatus("" + userMongoObj.get("Status"));
			objUserData.setAGSRole("" + userMongoObj.get("AGSRole"));
			objUserData.setRoleMatch("" + userMongoObj.get("RoleMatch"));
			objUserData.setFullUserName("" + userMongoObj.get("FullUserName"));
			objUserData.setLastCheck("" + userMongoObj.get("LastCheck"));
		}

		creadientials.put("userName", objUserData.getUserName());
		creadientials.put("password", objUserData.getPassword());
		creadientials.put("userFullName", objUserData.getFullUserName());

		return creadientials;

	}

	public void waitTillLightningPageLoadComplete() {
		Assert.assertTrue(seleniumObj.waitTillPageLoadIsComplete(),
				"Application is slow. Page load didn't complete for more than 30 seconds");
		Assert.assertNotNull(isPageUsingAuraFramework(), "Page is not using Lightning Framework");
		Assert.assertTrue(waitTillAllXHRCallsComplete(), "Lightning components didnt get load for 30 seconds");
	}

	public boolean isPageUsingAuraFramework() {
		int iRetryCount = 1;

		while (iRetryCount > 0) {
			String isAuraPage = seleniumObj.executeJavaScript("return document.getElementById('auraAppcacheProgress')");
			if (isAuraPage != null) {
				log.debug("Current page is using Aura framework");
				return true;
			}
			seleniumObj.waitForSeconds(1);
			iRetryCount--;
		}
		return false;
	}

	public boolean waitTillAllXHRCallsComplete() {
		Integer iLastInFlightRequestCount = 1;
		int iMaxTries = 15;

		// Validate inFlightXHR's are complete

		while (iLastInFlightRequestCount != 0) {
			Integer inFlightXHRs = 0;
			try {
				inFlightXHRs = Integer
						.parseInt(seleniumObj.executeJavaScript("return window.$A.clientService.inFlightXHRs()"));
			} catch (Exception e) {
				log.debug("Exception - " + e.getMessage());
			}

			if (inFlightXHRs == 0) {
				iLastInFlightRequestCount--;
			} else {
				iLastInFlightRequestCount = 1;
			}
			seleniumObj.waitForSeconds(2);
			if (--iMaxTries == 0 && iLastInFlightRequestCount == 5) {
				log.debug("XHR requests are still processing even after 30 seconds");
				return false;
			}
		}
		log.debug("All XHR requests processed completed");
		return true;
	}

	public void scrollTillLastElement(String element) throws InterruptedException {

		List<String> beforeScroll = new ArrayList<String>();
		beforeScroll.add("" + 0);
		JavascriptExecutor js = (JavascriptExecutor) seleniumObj.getDriver();
		List<WebElement> list = seleniumObj.getDriver().findElements(By.xpath(element));
		log.debug(list.size());
		for (int i = 0; i < list.size(); i++) {
			list = seleniumObj.getDriver().findElements(By.xpath(element));
			beforeScroll.add("" + list.size());
			if (beforeScroll.get(i).equals(beforeScroll.get(i + 1))) {

				log.info("Scroll down to last element is completed");
				break;

			} else {

				js.executeScript("arguments[0].scrollIntoView(true);", list.get(list.size() - 1));
				seleniumObj.waitForSeconds(3);

			}

		}

	}

	public boolean checkElementExists(WebElement element) {
		boolean visible = false;
		try {
			if (element != null && element.isDisplayed() && element.isEnabled()) {
				visible = true;
			}
		} catch (Exception ex) {
			visible = false;
			log.info("Element is not visible on the screen!");
			log.info(ex.getMessage());

		}
		return visible;
	}

	/**
	 * 
	 * @Description Method to upload a document
	 * @Author vveeranx
	 * @Since Sep 12, 2018
	 * @param filePath
	 * @throws IOException
	 */
	public void handleFileUpload(String[] filePath) throws IOException {
		try {
			Process process = Runtime.getRuntime().exec(filePath, null);
			process.waitFor();
		} catch (Exception ex) {
			Assert.fail("Failed to upload the documents");
		}
	}

	/**
	 * @Description
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param sApplicationName
	 * @param userRole
	 * @throws IOException
	 */
	public void loginToExternalCustomerApplicationAs(String sApplicationName, String userRole) throws IOException {

		LoginPage objLoginPage = new LoginPage(seleniumObj.getDriver());
		// HashMap<String, String> userCredentials = getUserDetails(userRole);
		String filepath = System.getProperty("user.dir") + configObj.getFilexlsxPath();
		String userName = "";
		String passWord = "";

		ArrayList<String> userRow = sfcommonObj.GetRowFromExcelSheet(filepath, "LoginCredentials", userRole, "User");

		try {
			if (!userRow.isEmpty()) {
				String s = configObj.getApplicationURL(sApplicationName);
				seleniumObj.getDriver().navigate().to(s);
				this.waitTillLightningPageLoadComplete();
				if (configObj.getEnvironment().equals("DEVINT")) {
					userName = userRow.get(2);
					passWord = userRow.get(3);
					if (seleniumObj.isElementExists(objLoginPage.externalAlternateSignIn)) {

						objLoginPage.clickAlternateSignIn();
						objLoginPage.setExternalUsername(userName);
						objLoginPage.setExternalPassword(passWord);
						objLoginPage.clickSignIn();
					} else {
						objLoginPage.setExternalCustomerEmailId(userName);
						objLoginPage.setExternalCustomerPassword(passWord);
						objLoginPage.clickExternalCustomerLoginButton();
					}
				}

				if (configObj.getEnvironment().equals("QA")) {
					userName = userRow.get(2);
					passWord = userRow.get(3);
					if (seleniumObj.isElementExists(objLoginPage.externalAlternateSignIn)) {

						objLoginPage.clickAlternateSignIn();
						objLoginPage.setExternalUsername(userName);
						objLoginPage.setExternalPassword(passWord);
						objLoginPage.clickSignIn();
					} else {
						objLoginPage.setExternalCustomerEmailId(userName);
						objLoginPage.setExternalCustomerPassword(passWord);
						objLoginPage.clickExternalCustomerLoginButton();
					}
				}

				this.waitTillLightningPageLoadComplete();
				log.info("Logged in as " + userRole);
			} else {
				Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
						+ userRole.toString());
			}
		} catch (Exception ex) {
			Assert.fail("Not able to login to the application");
		}
	}

	/**
	 * Log into External Customer Application as
	 * 
	 * @param sApplicationName
	 * @param userRole
	 * @param env
	 */
	public void loginToExternalCustomerApplicationAs_withoutCredentials(String sApplicationName, String userRole) {

		LoginPage objLoginPage = new LoginPage(seleniumObj.getDriver());
	//	HashMap<String, String> userCredentials = getUserDetails(userRole);
		try {
			if (!userRole.isEmpty()) {

				if (configObj.getEnvironment().equals("QA")) {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);
					/*
					 * objLoginPage.clickAlternateSignIn();
					 * objLoginPage.setExternalUsername(userCredentials.get(
					 * "userName"));
					 * objLoginPage.setExternalPassword(userCredentials.get(
					 * "password")); objLoginPage.clickSignIn();
					 */
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				} else {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);

					/*
					 * objLoginPage.setExternalCustomerEmailId(userCredentials.
					 * get("userName"));
					 * objLoginPage.setExternalCustomerPassword(userCredentials.
					 * get("password"));
					 * objLoginPage.clickExternalCustomerLoginButton();
					 */
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				}
			} else {
				Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
						+ userRole.toString());
			}
		} catch (Exception ex) {
			Assert.fail("Not able to login to the application");
		}
	}

	/**
	 * @Description Method to scroll to a particular element
	 * @since Sep 12, 2018
	 * @author prachivx
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) seleniumObj.getDriver();
		jse.executeScript("arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"nearest\"});",
				element);
	}

	/**
	 * @Description Method is to set date to PST timezone and it returns date
	 *              according to no of days and no of years
	 * @Author prachivx
	 * @Since Sep 14, 2018
	 * @param noOfDays
	 * @param NoOfYears
	 * @return
	 */
	public String getDate(Integer noOfDays, Boolean NoOfYears) {
		TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		formatter.setTimeZone(zone);
		Calendar cal = Calendar.getInstance();
		if (noOfDays == 0) {
			return formatter.format(new Date());
		} else {
			if (!NoOfYears)
				cal.add(Calendar.DATE, noOfDays);
			else
				cal.add(Calendar.YEAR, noOfDays);
		}

		Date date = cal.getTime();
		String dateStr = formatter.format(date);

		return dateStr;
	}

	public boolean CheckElementExists(WebElement element) {
		boolean visible = false;
		try {
			if (element.isDisplayed()) {
				visible = true;
			}
		} catch (Exception ex) {
			visible = false;
			log.info("Element is not visible on the screen!");
			log.info(ex.getMessage());

		}
		return visible;
	}

	/**
	 * 
	 * @Description - generate the random date form the current date
	 * @Author nithes1x
	 * @Since Sep 17, 2018
	 * @return
	 */
	public String randomDategenerator() {
		String date = null;
		Random random = new Random();
		String pattern = CustomDateFormat.MMDDYYYY.getDescription();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		date = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, random.nextInt(800));

		date = simpleDateFormat.format(c.getTime());

		return date;
	}

	/**
	 * 
	 * @Description - Scrolls till last element in list view
	 * @Author nithes1x
	 * @Since Sep 17, 2018
	 * @return
	 */
	public void ScrollTillLastElement(String element) throws InterruptedException {

		List<String> beforeScroll = new ArrayList<String>();
		beforeScroll.add("" + 0);
		JavascriptExecutor js = (JavascriptExecutor) seleniumObj.getDriver();
		List<WebElement> list = seleniumObj.getDriver().findElements(By.xpath(element));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			list = seleniumObj.getDriver().findElements(By.xpath(element));
			beforeScroll.add("" + list.size());
			if (beforeScroll.get(i).equals(beforeScroll.get(i + 1))) {

				log.info("Scroll down to last element is completed");
				break;

			} else {

				js.executeScript("arguments[0].scrollIntoView(true);", list.get(list.size() - 1));
				seleniumObj.waitForSeconds(3);

			}

		}

	}

	/**
	 * - Fetches user credentials from automation test DB. <br>
	 * - Sets the application URL & credentials to LPOP page class. <br>
	 * 
	 * @param sApplicationName
	 * @param userRole
	 */
	public void loginToCommunityApplicationAs(String sApplicationName, String userRole) throws TimeOutException {

		HashMap<String, String> userCredentials = getUserDetails(userRole);
		LoginPage_ExternalUser objLoginPageClass = new LoginPage_ExternalUser();
		if (!userCredentials.isEmpty()) {
			try {
				baseTestObj.utilities().login(userCredentials.get("userName"), userCredentials.get("password"));
				sfcommonObj.waitTillLightningPageLoadComplete();
				objLoginPageClass.loginToCommunityPortal(userCredentials.get("userName"),
						userCredentials.get("password"), configObj.getApplicationURL());
				log.info("Logged into community portal");
			} catch (AssertionError e) {
				log.info("Unable to login to community portal" + e.getMessage());
			}
		} else {
			Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
					+ userRole.toString());
		}
	}

	/**
	 * @Description Method to double click element
	 * @Author gmathavx
	 * @Since 28-Sep-2018
	 * @param driver
	 * @param element
	 */
	public void doubleClickElement(WebElement element) {
		Actions builder = new Actions(seleniumObj.getDriver());
		builder.doubleClick(element).build().perform();
	}

	/**
	 * @Description Method to Get Random String
	 * @Author mohseenx
	 * @Since Oct 08 2018
	 */
	public String getRandomString() {
		return RandomStringUtils.randomAlphabetic(8).toUpperCase();
	}

	/**
	 * @Description Method is to set date to PST timezone and it returns date
	 *              according to no of days and no of years
	 * @Author prachivx
	 * @Since Sep 14, 2018
	 * @param noOfDays
	 * @param NoOfYears
	 * @param
	 * @return
	 */
	public String getDate(Integer noOfDays, Boolean NoOfYears, String format) {
		TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(zone);
		Calendar cal = Calendar.getInstance();
		if (noOfDays == 0) {
			return formatter.format(new Date());
		} else {
			if (!NoOfYears)
				cal.add(Calendar.DATE, noOfDays);
			else
				cal.add(Calendar.YEAR, noOfDays);
		}

		Date date = cal.getTime();
		String dateStr = formatter.format(date);

		return dateStr;
	}

	/**
	 * @Description Method is to set date to PST timezone and it returns date
	 *              according to no of days and no of years and particular date.
	 * @Author prachivx
	 * @Since 31 oct, 2018
	 * @param noOfDays
	 * @param NoOfYears
	 * @param format
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public String getDate(Integer noOfDays, Boolean noOfYears, String format, String strDate) throws ParseException {
		TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(zone);
		Calendar cal = Calendar.getInstance();

		if (noOfDays == 0) {

			if (!StringUtils.isNullOrBlank(strDate)) {
				Date date1 = new SimpleDateFormat(format).parse(strDate);
				return formatter.format(date1);
			} else
				return formatter.format(new Date());
		} else {
			if (!noOfYears) {
				if (!StringUtils.isNullOrBlank(strDate)) {
					Date date1 = new SimpleDateFormat(format).parse(strDate);
					cal.setTime(date1);
					cal.add(Calendar.DATE, noOfDays);
				}

				else
					cal.add(Calendar.DATE, noOfDays);
			} else {
				if (!StringUtils.isNullOrBlank(strDate)) {
					Date date1 = new SimpleDateFormat(format).parse(strDate);
					cal.setTime(date1);
					cal.add(Calendar.YEAR, noOfDays);
				} else
					cal.add(Calendar.YEAR, noOfDays);
			}
		}

		Date date = cal.getTime();
		String dateStr = formatter.format(date);

		return dateStr;
	}

	/**
	 * @Description Method is to verify element is enabled or disabled
	 * @Author prachivx
	 * @Since 3 dec, 2018
	 * @param element
	 * @return boolean
	 */
	public boolean isElementEnabledOrDisabled(WebElement element) {
		boolean enabled = false;
		try {
			if (element.isEnabled()) {
				enabled = true;
			}
		} catch (Exception ex) {
			return enabled;
		}
		return enabled;
	}

	/**
	 * @Description Method is to get convert the input string date with given
	 *              input.
	 * @Author csingamx
	 * @Since Dec 05, 2018
	 * @param strDate
	 * @return Date object
	 */
	public Date getDate(String format, String strDate) throws ParseException {
		if (strDate != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(strDate);
		}
		return null;
	}

	/**
	 * @Description Method is to get the date string with input date format .
	 * @Author csingamx
	 * @Since Dec 05, 2018
	 * @param strDate
	 * @return String
	 */
	public String getDateFormat(String format, Date date) throws ParseException {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(date);
		}
		return null;
	}

	/**
	 * Method to get date format
	 * 
	 * @Author gmathavx
	 * @Since 31-Dec-2018
	 * @param actualFormat
	 *            actual format of the date
	 * @param expectedForamt
	 *            expected format of the date
	 * @param strDate
	 *            actual date
	 * @return String expected date
	 */
	public String getDateFormat(String actualFormat, String expectedForamt, String strDate) {
		String dateForamt = "";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(actualFormat);
			Date date = simpleDateFormat.parse(strDate);
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(expectedForamt);
			dateForamt = simpleDateFormat2.format(date);
		} catch (Exception e) {
			Assert.fail("Date parsing failed. Actual Format : " + actualFormat + "  Expected Foramt : " + expectedForamt
					+ " Date : " + strDate);
		}
		return dateForamt;
	}

	/**
	 * Log into External Customer Application as
	 * 
	 * @param sApplicationName
	 * @param userRole
	 * @param env
	 */
	public void loginToExternalCustomerApplicationAs(String sApplicationName, String userRole, String env) {

		LoginPage objLoginPage = new LoginPage(seleniumObj.getDriver());
		HashMap<String, String> userCredentials = getUserDetails(userRole);
		try {
			if (!userCredentials.isEmpty()) {

				if (env.equals("QA")) {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);
					objLoginPage.clickAlternateSignIn();
					objLoginPage.setExternalUsername(userCredentials.get("userName"));
					objLoginPage.setExternalPassword(userCredentials.get("password"));
					objLoginPage.clickSignIn();
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				} else {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);

					objLoginPage.setExternalCustomerEmailId(userCredentials.get("userName"));
					objLoginPage.setExternalCustomerPassword(userCredentials.get("password"));
					objLoginPage.clickExternalCustomerLoginButton();
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				}
			} else {
				Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
						+ userRole.toString());
			}
		} catch (Exception ex) {
			Assert.fail("Not able to login to the application");
		}
	}

	/**
	 * Method to Get Random number of a specified number
	 * 
	 * @author ubijux
	 * @Since Dec 27 2018
	 * @param count
	 *            The number of numeric characters to be returned
	 */
	public String getRandomNumber(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	/**
	 * Method to delete cookies
	 * 
	 * @Author gmathavx
	 * @Since 09-Jan-2019
	 */
	public void deleteCookies() {
		seleniumObj.getDriver().manage().deleteAllCookies();
	}

	/**
	 * Method to get random name
	 * 
	 * @Author gmathavx
	 * @Since 18-Jan-2019
	 * @param prefixName
	 *            Name of the feature name
	 * @return String it will return the random name
	 */
	public String getRandomName(AutomationPrefixes prefixName) {
		String randomName = "";
		switch (prefixName) {
		case BUDGET:
			randomName = prefixName.getDescription()
					+ getDate(0, false, CustomDateFormat.MMDDYYYYHHMMSS.getDescription());
			break;
		case CLAIM:
			randomName = prefixName.getDescription() + getRandomNumber(5);
			break;
		case RECEIPT:
			randomName = prefixName.getDescription() + getRandomNumber(5);
			break;
		case ACCOUNT:
			randomName = prefixName.getDescription()
					+ getDate(0, false, CustomDateFormat.MMDDYYYHHMMSS.getDescription());
		case BANK:
			randomName = prefixName.getDescription()
					+ getDate(0, false, CustomDateFormat.MMDDYYYHHMMSS.getDescription());
			break;
		case CAMPAIGN:
			randomName = prefixName.getDescription()
					+ getDate(0, false, CustomDateFormat.MMDDYYYHHMMSS.getDescription());
			break;

		case ESCALATION:
			randomName = prefixName.getDescription()
					+ getDate(0, false, CustomDateFormat.MMDDYYYHHMMSS.getDescription());
			break;

		}
		return randomName;
	}

	/**
	 * Method to click on given button in Object Home
	 * 
	 * @Author mohseenx Since Jan 28, 2018
	 * @param actionName
	 *            Name of button to perform action
	 */
	@SuppressWarnings("unchecked")
	public void clickOnOHButton(CommonEnum.ObjectHome actionName) {
		String actionLable = "";
		try {
			actionLable = actionName.getDescription();
			baseTestObj.getApp().load(ObjectHomePlace.class).getActions().clickAction(actionLable);

			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(actionName + " Button is Clicked");
		} catch (Exception ex) {
			log.info("Failed to click " + actionLable + " button");
			Assert.fail("Failed to click " + actionLable + " button");
		}
	}

	/**
	 * Method to select record type in Record Type Picker
	 * 
	 * @Author mohseenx Since Jan 28, 2018
	 * @param recordType
	 *            Name of Record Type to select
	 */
	@SuppressWarnings("unchecked")
	public void selectRecordType(String recordType) {
		try {

			RecordTypePicker recordTypePicker = baseTestObj.getApp().load(RecordTypePicker.class);
			recordTypePicker.selectRecordType(recordType);
			recordTypePicker.confirm();
			log.info(recordType + " Record Type is Select");
		} catch (Exception ex) {
			log.info("Failed to select record type " + recordType);
			Assert.fail("Failed to select record type " + recordType);
		}
	}

	/**
	 * Method to click on save button in Record form
	 * 
	 * @Author mohseenx Since Jan 29, 2018
	 */
	@SuppressWarnings("unchecked")
	public void clickOnRFSaveButton() {
		try {
			baseTestObj.getApp().load(DesktopPanelModal.class).getRecordForm().save();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Save Button is Clicked");
		} catch (Exception ex) {
			log.info("Failed to click on save button");
			Assert.fail("Failed to click on save button");
		}
	}

	/**
	 * 
	 * Method will help us to navigate to particular application
	 * 
	 * @Author mohseen
	 * @Since 25-Jan-2018
	 * @param pageTitle
	 *            - title of the page to navigate
	 * @throws TimeOutException
	 * @throws InterruptedException
	 */
	public void pageAppNavigation(CommonEnum.GlobalNavigator pageTitle) throws TimeOutException, InterruptedException {
		String moduleName = "";
		try {
			moduleName = pageTitle.getDescription();
			baseTestObj.utilities().findAppLauncherItem(moduleName);// .navigateToObjectHome(moduleName);
			log.info("Navigated to: " + moduleName);
		} catch (Exception e) {
			log.info("Failed to navigate to " + moduleName + " page");
			Assert.fail("Failed to navigate to " + moduleName + " page");
		}
	}

	/**
	 * 
	 * Method to get Record Form xml file
	 * 
	 * @Author mohseen
	 * @Since 25-Jan-2018
	 * @param recordFormName
	 *            - Name of the module to get lables
	 * @throws IOException
	 *             - Throws IO Exception
	 */
	public InputStream getRecordFormXMLFile(String recordFormName) throws IOException {
		InputStream inputStream = null;
		try {
			String d = System.getProperty("user.dir") + configObj.getRecordFormFilePath();
			inputStream = new FileInputStream(
					System.getProperty("user.dir") + configObj.getRecordFormFilePath() + recordFormName + ".xml");
			log.info("Opened Record form - " + recordFormName);
		} catch (IOException ex) {
			log.info("Failed to get Record form - " + recordFormName + " and Exception is : " + ex.getMessage());
			Assert.fail("Failed to get Record form - " + recordFormName + " and Exception is : " + ex.getMessage());
		}
		return inputStream;
	}

	/**
	 * Method to write content into the file
	 * 
	 * @Author gmathavx
	 * @Since 12-Feb-2019
	 * @param file
	 *            file object with details
	 * @param fileContent
	 *            file content
	 */
	public void writeFile(File file, String fileContent) {
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write(fileContent);
			output.close();
		} catch (IOException ex) {
			Assert.fail("Not able to write text into the file. " + ex.getMessage());
		}
	}

	/**
	 * Method to searhc text in global search textbox
	 * 
	 * @Author gmathavx
	 * @Since 13-Feb-2019
	 * @param searchText
	 *            Text to be searched and clicked
	 */
	public void globalSearchText(String searchText) {
		try {
			baseTestObj.getApp().header().getSearch().enterSearchText(searchText).clickResult(searchText);
			log.info("Searched text in the Global search textbox " + searchText);
		} catch (Exception ex) {
			Assert.fail("Not able to search text in global textbox :" + searchText + ". " + ex.getMessage());
		}
	}

	/**
	 * Method to click on given button in Object Home
	 * 
	 * @Author mohseenx Since Jan 28, 2018
	 * @param actionName
	 *            Name of button to perform action
	 */
	@SuppressWarnings("unchecked")
	public void clickOnRHButton(CommonEnum.RecordHome actionName) {
		String actionLable = "";
		try {
			actionLable = actionName.getDescription();
			baseTestObj.getApp().load(RecordHomePlace.class).getActions().clickAction(actionLable);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(actionName + " Button is Clicked");
		} catch (Exception ex) {
			Assert.fail("Failed to click " + actionLable + " button");
		}
	}

	/**
	 * Method to select the view in object home page
	 * 
	 * @Author gmathavx
	 * @Since 18-Feb-2019
	 * @param view
	 *            Name of the view
	 */
	@SuppressWarnings("unchecked")
	public void selectView(String viewName) {
		try {
			baseTestObj.getApp().load(ObjectHomePlace.class).getListViewManager().getListViewPicker().open()
					.selectItem(viewName);
			log.info("Selected view from list view manager: " + viewName);
		} catch (Exception ex) {
			Assert.fail("Failed to select " + viewName + " view from list view manager. " + ex.getMessage());
		}
	}
	/*
		*//**
			 * Method to search for case in 'Search List'
			 * 
			 * @Author vveeranx
			 * @Since 14-Dec-2018
			 * @param claimID
			 *            is used to search for a claim in 'Cases' page
			 *//*
			 * @SuppressWarnings("unchecked") public void
			 * searchTextInistViewManager(String searchText) { try {
			 * DesktopActions desktopAction =
			 * baseTestObj.getApp().load(ObjectHomePlace.class).
			 * getListViewManager() .getButtonBar().getListViewActions();
			 * desktopAction.getElement(By.xpath("//input[@name='search-input']"
			 * )).enterText (searchText);
			 * desktopAction.getElement(By.xpath("//input[@name='search-input']"
			 * )).sendKeys( Keys.RETURN); log.info("Searched text  : " +
			 * searchText);
			 * 
			 * } catch (Exception ex) {
			 * Assert.fail("Not able to search for claim in 'Search List'. " +
			 * ex.getMessage());
			 * 
			 * } }
			 */

	/**
	 * Method to click on edit in object home page
	 * 
	 * @Author mohseenx
	 * @Since Feb 20 2019
	 * @param view
	 *            Name of the view
	 */
	@SuppressWarnings("unchecked")
	public void clickEditButton(CommonEnum.ObjectHome actionName) {
		String actionLable = "";
		try {
			actionLable = actionName.getDescription();
			RecordHomePlace home = baseTestObj.getApp().load(RecordHomePlace.class);

			home.getActions().clickAction(actionLable, RecordForm.class);
			sfcommonObj.waitTillLightningPageLoadComplete();

			log.info("Click on edit button in Record Home Place");
		} catch (Exception ex) {
			Assert.fail("Failed to click edit button in Record Home Place" + ex.getMessage());
		}
	}

	/**
	 * Method to click on Cancel button in Record form
	 * 
	 * @Author rranga3x Since Apr 23, 2019
	 */
	@SuppressWarnings("unchecked")
	public void clickOnRFCancelButton() {
		try {
			baseTestObj.getApp().load(DesktopPanelModal.class).getRecordForm().clickButton("Cancel");
			log.info("Clicked on Cancel Button ");
		} catch (Exception ex) {
			Assert.fail("Failed to click on Cancel button");
		}
	}

	/**
	 * Method to close and relaunch browser
	 * 
	 * @Author ubijux
	 * @Since 21-Feb-2019
	 */
	public void closeAndReLaunchBrowser() {
		String downloadPath = System.getProperty("user.dir") + configObj.getFileDownLoadPath();
		seleniumObj.getDriver().manage().deleteAllCookies();
		seleniumObj.getDriver().close();
		seleniumObj.openBrowser(configObj.getBrowserName(), configObj.getChromeDriverPath(),
				configObj.getImplicitWaitTime(), downloadPath);

	}

	/**
	 * Method to initialize default browser and timeout
	 * 
	 * @Author vveeranx
	 * @Since 15-Mar-2019
	 * @param type
	 *            application type
	 */
	public void setUp(ApplicationType type) {
		baseTestObj.setup(seleniumObj.getDriver(), type, configObj.getApplicationURL());
	}

	/**
	 * Method to search in list view
	 * 
	 * @Author yvelu
	 * @Since 22-Mar-2019
	 * @param enum
	 *            pageTitle - title of the page
	 * @param string
	 *            searchText - search string
	 */

	public void searchListView(CommonEnum.GlobalNavigator pageTitle, String searchText, String listviewOption)
			throws TimeOutException, InterruptedException {
		String moduleName = "";
		try {
			moduleName = pageTitle.getDescription();
			ListViewManager list = baseTestObj.utilities().navigateToObjectHome(moduleName).getListViewManager();
			sfcommonObj.waitTillLightningPageLoadComplete();
			this.selectView(listviewOption);
			list.getListViewSearchBar().clearSearch();
			list.getListViewSearchBar().focusSearch().setSearchString(searchText).executeSearch();

			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Navigated to: " + searchText);
		} catch (Exception e) {
			log.info("Failed to search  " + searchText + " in " + pageTitle + " page");
			Assert.fail("Failed to search  " + searchText + " in " + pageTitle + " page");
		}
	}

	/**
	 * Method to verify Record is created or not
	 * 
	 * @author prachivx
	 * @Since 28-Mar-2019
	 * @param accountName
	 *            Name of the Account
	 */
	@SuppressWarnings("unchecked")
	public void verifyRecordIsCreatedInDetailsPage(String fieldValue) {
		try {
			baseTestObj.getApp().load(RecordHomePlace.class).getHighlightsPanel()
					.getPrimaryField(Matchers.containsString(fieldValue));

			log.info("Created record : " + fieldValue);
		} catch (Exception ex) {
			Assert.fail("Failed to verify the record is created" + ex.getMessage());
		}
	}

	/**
	 * Method to verify Record is created or not
	 * 
	 * @author prachivx
	 * @Since 28-Mar-2019
	 * @param accountName
	 *            Name of the Account
	 */
	@SuppressWarnings("unchecked")
	public String getToasterErrorMessage() {
		List<String> message = null;
		try {
			message = baseTestObj.getApp().load(PageErrorImpl.class).getErrorsList();

			log.info("getting message : " + message);
		} catch (Exception ex) {
			Assert.fail("Failed to verify the record is created" + ex.getMessage());
		}
		return message.get(0);
	}

	/**
	 * Method to click on Details tab in Record form
	 * 
	 * @Author prachivx Since Apr 3, 2019
	 */
	@SuppressWarnings("unchecked")
	public void switchToTab(String tabName) {
		try {
			sfcommonObj.waitTillLightningPageLoadComplete();
			baseTestObj.getApp().load(RecordHomePlace.class).getMainTabSet().clickTab(tabName);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Clicked on " + tabName + " tab in details page ");
		} catch (Exception ex) {
			Assert.fail("Failed to click on tab" + tabName);
		}
	}

	public void verifyDatesFromUI(String fieldName) throws ParseException {

		@SuppressWarnings("unchecked")
		String dt = baseTestObj.getApp().load(RecordHomePlace.class).getTabBody(DetailPanel.class).getRecordView()
				.getFieldValue(fieldName);

	}

	/**
	 * @Description Method to verify details in details page
	 * @Author prachivx
	 * @Since Apr 5 2019
	 */
	@SuppressWarnings("unchecked")
	public String getAmountInDetailsTab(String fieldName) {
		String amount = "";
		String newAmount = "";
		try {
			amount = baseTestObj.getApp().load(RecordHomePlace.class).getTabBody(DetailPanel.class).getRecordView()
					.getFieldValue(fieldName);
			newAmount = amount.replace("$", "").replace(",", "").replace(".00", "");

			log.info("verified other detail values in details tab");
		} catch (Exception ex) {
			Assert.fail("Failed to verify other detail values in details tab. " + ex.getMessage());
		}
		return newAmount;
	}

	/**
	 * 
	 * Method to verify secondary field values
	 * 
	 * @Author prachi
	 * @Since Apr 10,2019
	 * @param fieldName
	 * @param status
	 */
	public void verifySecondaryFieldsInDetailsTab(String fieldName, String fieldValue) {
		try {
			@SuppressWarnings("unchecked")
			String value = baseTestObj.getApp().load(RecordHomePlace.class).getHighlightsPanel()
					.getSecondaryField(fieldName);

			Assert.assertEquals(value, fieldValue, "Mismatch with secondary field values");
			log.info("Verified secondary field values: " + fieldValue);
		} catch (Exception ex) {
			Assert.fail("Failed to verify secondary field values " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Method to verify if buttons like edit,cancel are displaying or not in
	 * details page
	 * 
	 * @Author vveeranx
	 * @Since Apr 12,2019
	 * @param fieldName
	 *            to verify
	 * @param display
	 *            if the field should be displayed or not
	 */
	@SuppressWarnings("unchecked")
	public void verifyIfButonsDisplayingOrNotInDetailsPage(String fieldName, String display) {
		try {
			boolean isDisplayed = baseTestObj.getApp().load(RecordHomePlace.class).getActions().hasAction(fieldName);

			if (display.contains("true")) {
				Assert.assertTrue(isDisplayed, fieldName + " button is not displaying");
				log.info("Button '" + fieldName + "' is displaying");
			} else {
				Assert.assertFalse(isDisplayed, fieldName + " button is displaying");
				log.info("Button '" + fieldName + "' is not displaying");
			}
		} catch (Exception ex) {
			Assert.fail("Failed to verify secondary field values " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Method to navigate to ext application
	 * 
	 * @Author prachivx
	 * @Since May 2,2019
	 * @param sApplicationName
	 *            to verify
	 */
	public void navigateToApplicationExtApplication(String sApplicationName) {
		String s = configObj.getApplicationURL(sApplicationName);
		seleniumObj.getDriver().navigate().to(s);
	}

	/**
	 * 
	 * Method to logout from internal application
	 * 
	 * @Author yvelu
	 * @Since May 14,2019
	 * 
	 */
	public void logout() {
		baseTestObj.utilities().logout();
	}

	/**
	 * @Description Method to click to a particular element
	 * @since May 31, 2019
	 * @author kanurix
	 * @param element
	 */
	public void clickOnElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) seleniumObj.getDriver();
		jse.executeScript("arguments[0].click();", element);
	}

	/**
	 * @Description Method to switch to a frame on page
	 * @since Nov 31, 2020
	 * @author kumark8x
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForElement(element, 5, 5);
		seleniumObj.getDriver().switchTo().frame(element);
	}
	
	public void switchToFrame2() {
		JavascriptExecutor jse = (JavascriptExecutor) seleniumObj.getDriver();
		WebElement element=(WebElement) jse.executeScript("return document.querySelector('#brandBand_2 > div > div > div > div > force-aloha-page > div > iframe')");
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForElement(element, 5, 5);
		seleniumObj.getDriver().switchTo().frame(element);
	}

	/**
	 * @Description Method to switch to default content on page
	 * @since Nov 31, 2020
	 * @author kumark8x
	 * @param element
	 */
	public void switchToDefaultContent() {
		seleniumObj.getDriver().switchTo().defaultContent();
	}

	/**
	 * Log into External Customer Application as
	 * 
	 * @param sApplicationName
	 * @param userRole
	 * @param env
	 */
	public void loginToExternalCustomerApplicationAs_Prop(String sApplicationName, String userRole, String env) {

		LoginPage objLoginPage = new LoginPage(seleniumObj.getDriver());
		HashMap<String, String> userCredentials = getUserDetails(userRole);
		try {
			if (!userCredentials.isEmpty()) {

				if (env.equals("QA")) {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);
					/*
					 * objLoginPage.clickAlternateSignIn();
					 * objLoginPage.setExternalUsername(userCredentials.get(
					 * "userName"));
					 * objLoginPage.setExternalPassword(userCredentials.get(
					 * "password")); objLoginPage.clickSignIn();
					 */
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				} else {
					String s = configObj.getApplicationURL(sApplicationName);
					seleniumObj.getDriver().navigate().to(s);

					/*
					 * objLoginPage.setExternalCustomerEmailId(userCredentials.
					 * get("userName"));
					 * objLoginPage.setExternalCustomerPassword(userCredentials.
					 * get("password"));
					 * objLoginPage.clickExternalCustomerLoginButton();
					 */
					this.waitTillLightningPageLoadComplete();
					log.info("Logged in as " + userRole);
				}
			} else {
				Assert.fail("User credentails not foud for application - " + sApplicationName + " with user role - "
						+ userRole.toString());
			}
		} catch (Exception ex) {
			Assert.fail("Not able to login to the application");
		}
	}

	/**
	 * 
	 * Method to VerifyDownloadedFile
	 * 
	 * @Author kumark8x
	 * @Since Mar 29, 2021
	 */
	public void deleteDownloadedFile() {
		try {
			String downloadPath = System.getProperty("user.dir") + configObj.getFileDownLoadPath();
			File path = new File(downloadPath);
			File[] files = path.listFiles();
			for (File file : files) {
				if (file.getName().contains(".xls") || file.getName().contains(".csv")) {
					file.delete();
				}

			}
		} catch (Exception ex) {
			Assert.fail("Not able to delete Downloaded File  " + ex.getMessage());
		}
	}

	public void switchToNewWindow() throws Exception {
		try {
			String parentWindow = seleniumObj.getDriver().getWindowHandle();
			Set<String> handles = seleniumObj.getDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					seleniumObj.getDriver().switchTo().window(parentWindow).close();
					seleniumObj.getDriver().switchTo().window(windowHandle);
					log.info("Switched the Window Focus to New Window");
				}
			}
		} catch (Exception ex) {
			log.error("Unable to Switch child Window");
			throw ex;
		}
	}
	
	public void switchToNewWindowWithoutClosingParentWindow() throws Exception {
		try {
			String parentWindow = seleniumObj.getDriver().getWindowHandle();
			Set<String> handles = seleniumObj.getDriver().getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					//seleniumObj.getDriver().switchTo().window(parentWindow).close();
					seleniumObj.getDriver().switchTo().window(windowHandle);
					log.info("Switched the Window Focus to New Window");
				}
			}
		} catch (Exception ex) {
			log.error("Unable to Switch child Window");
			throw ex;
		}
	}
	
	

	/**
	 * @Description Method to scroll by position using JavascriptExecutor
	 * @Author gmathavx
	 * @Since 04-Oct-2018
	 * @param horizontal
	 * @param vertical
	 */
	public void scrollByPosition(String horizontal, String vertical) {
		JavascriptExecutor jse = (JavascriptExecutor) seleniumObj.getDriver();
		jse.executeScript("scroll(" + horizontal + ", " + vertical + ");");
	}

	/**
	 * @Description Method for scroll up
	 * @Author gmathavx
	 * @Since 07-Oct-2018
	 */
	public void scrollByUp() {
		this.scrollByPosition("0", "-400");
	}

	/**
	 * @Description Method for scroll down
	 * @Author gmathavx
	 * @Since 07-Oct-2018
	 */
	public void scrollByDown() {
		this.scrollByPosition("0", "400");
	}

	public ArrayList<String> readExcel(String filePath, String sheetName) throws IOException {

		// Create a object of File class to open xlsx file
		File file = new File(filePath);// new File(filePath+"\\"+fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wrkbk = new XSSFWorkbook(inputStream);

		// Read sheet inside the workbook by its name
		Sheet sheet = wrkbk.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Array list to Store the Data
		ArrayList<String> Data_Array = new ArrayList<String>();

		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);

			// Create a loop to print cell values in a row
			for (int j = 0; j < row.getLastCellNum(); j++) {

				// Print excel data in console
				System.out.print(row.getCell(j).getStringCellValue() + "|| ");

				Data_Array.add(row.getCell(j).getStringCellValue());

			}

			System.out.println();

		}

		return Data_Array;

	}

	/**
	 * To get entire row from excel sheet
	 * 
	 * @param -
	 *            filePath - file path of the excel sheet
	 * @param sheetName
	 *            - Name of the excel sheet from which we want to retrieve data
	 * @param rowName
	 *            - Name of the row to retrieve the data
	 * @param columnName
	 *            - Name of the column which will act as search element(column
	 *            header)
	 * @author unatarax
	 * @throws IOException
	 * @since 2019-12-12
	 */
	public ArrayList<String> GetRowFromExcelSheet(String filePath, String sheetName, String rowName, String columnName)
			throws IOException {
		ArrayList<String> arrayList = null;
		int k = 0;
		int column = 0;
		boolean sheetExists = false;
		try {
			arrayList = new ArrayList<String>();
			FileInputStream filestream = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(filestream);
			int sheets = workbook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
					sheetExists = true;
					XSSFSheet sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row firstrow = rows.next();
					Iterator<Cell> cellvalue = firstrow.cellIterator();
					while (cellvalue.hasNext()) {
						Cell value = cellvalue.next();
						if (!value.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
							k++;
						}
						column = k;
						log.info("column Name - '" + columnName + "' Not Available in Sheet");
						break;
					}
					log.info("column index is : " + column);
					while (rows.hasNext()) {
						Row r = rows.next();
						CellType cell = r.getCell(column).getCellType();
						switch (cell) {
						case STRING:
							if (r.getCell(column).getStringCellValue().equalsIgnoreCase(rowName)) {
								Iterator<Cell> celldata = r.cellIterator();
								while (celldata.hasNext()) {
									Cell c = celldata.next();
									arrayList.add(getCellValuebyType(c, column));
								}
							}
							break;
						case NUMERIC:
							String numValue = NumberToTextConverter.toText(r.getCell(column).getNumericCellValue());
							if (rowName.contains(numValue)) {
								Iterator<Cell> celldata = r.cellIterator();
								while (celldata.hasNext()) {
									Cell c = celldata.next();
									arrayList.add(getCellValuebyType(c, column));
								}
							}
							break;
						default:
							break;
						}
					}
				}
				if (!sheetExists) {
					log.info("Shee Name - '" + sheetName + "' not exists in file");
				}
				workbook.close();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return arrayList;
	}

	/**
	 * To check the cell type
	 * 
	 * @param cell
	 * @param cellIndex
	 * @return cellvalue
	 * @author unatarax
	 * @since 2019-12-12
	 */
	private String getCellValuebyType(Cell cell, int CellIndex) {
		String cellValue = null;
		try {
			CellType type = cell.getCellType();
			if (type.name().equals("STRING")) {
				log.info(cell.getStringCellValue());
				cellValue = cell.getStringCellValue();
			} else if (DateUtil.isCellDateFormatted(cell)) {
				log.info(cell.getDateCellValue());
				cellValue = cell.getDateCellValue().toString();
			} else if (type.name().equals("NUMERIC")) {
				log.info(cell.getNumericCellValue());
				cellValue = Double.toString(cell.getNumericCellValue());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return cellValue;
	}

	/**
	 * To get single cell value from excel sheet
	 * 
	 * @param sheetName
	 *            - Name of the excel sheet from which we want to retrieve data
	 * @param colName
	 *            - Name of the column name which will act as search
	 *            element(column header)
	 * @param rowNum
	 *            - Row Number from where the cell value will be extracted
	 * @author unatarax
	 * @since 2019-12-10
	 */

	public String GetCellValue(String filePath, String sheetName, String colName, int rowNum) {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		int colNum = -1;
		try {
			FileInputStream filestream = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(filestream);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
					colNum = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum);
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				// String cellValue =
				// String.valueOf(cell.getNumericCellValue());
				String cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					log.info(cell.getDateCellValue());
					cellValue = cell.getDateCellValue().toString();
				}
				return cellValue;
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return "No Matched Value";
		}
	}

	public int getRowCount(String filePath, String sheetName) throws IOException {

		// Create a object of File class to open xlsx file
		File file = new File(filePath);// new File(filePath+"\\"+fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wrkbk = new XSSFWorkbook(inputStream);

		// Read sheet inside the workbook by its name
		Sheet sheet = wrkbk.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum();

		/*
		 * // Array list to Store the Data ArrayList<String> Data_Array = new
		 * ArrayList<String>( );
		 * 
		 * //Create a loop over all the rows of excel file to read it for (int i
		 * = 1; i < rowCount+1; i++) { Row row = sheet.getRow(i);
		 * 
		 * //Create a loop to print cell values in a row for (int j = 0; j <
		 * row.getLastCellNum(); j++) {
		 * 
		 * //Print excel data in console
		 * System.out.print(row.getCell(j).getStringCellValue()+"|| ");
		 * 
		 * Data_Array.add(row.getCell(j).getStringCellValue());
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * }
		 */

		return rowCount;

	}

	/**
	 * @Description Method to add cookies
	 * @since Nov 31, 2020
	 * @author kumark8x
	 * @param element
	 */
	public void addCookies() {

		Set<Cookie> cookies = seleniumObj.getDriver().manage().getCookies();
		for (Cookie cookie : cookies) {
			Cookie cookieNew = new Cookie.Builder(cookie.getName(), cookie.getValue()).expiresOn(cookie.getExpiry())
					.isHttpOnly(cookie.isHttpOnly()).isSecure(cookie.isSecure()).path(cookie.getPath()).build();
			seleniumObj.getDriver().manage().addCookie(cookieNew);
		}
	}

	/**
	 * @Description Method to handle alert
	 * @since Nov 31, 2020
	 * @author kumark8x
	 * @param element
	 */
	public void acceptPopUpsAndAlerts() {
		if (seleniumObj.isAlertPresent()) {
			seleniumObj.acceptAlert();
		}

	}
	
	public void mouseOverByJS(WebElement ele)
	{
	    try 
	     {
	         String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
	         ((JavascriptExecutor) seleniumObj.getDriver()).executeScript(mouseOverScript,ele);
	         

	    } catch (Exception ex) {
			log.error(ex.getMessage());
	    }
	}


	/**
	 * @Description Method to pageRefresh 
	 * @since Nov 31, 2020
	 * @author kumark8x
	 * @param element
	 */
	public void pageRefresh() {
		seleniumObj.pageRefresh();
        this.waitTillLightningPageLoadComplete();
	}
	/**
	 * @Description
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param sApplicationName
	 * @param userRole
	 * @throws IOException
	 */
	public void loginToIPS_ExternalCustomerApplicationAs(String sApplicationName) throws IOException {

		LoginPage objLoginPage = new LoginPage(seleniumObj.getDriver());
		
		try {
			
				String s = configObj.getIPS_ApplicationURL(sApplicationName);
				seleniumObj.getDriver().navigate().to(s);
				this.waitTillLightningPageLoadComplete();
				this.waitTillLightningPageLoadComplete();
				log.info("Logged in as IPS User");
				
		} catch (Exception ex) {
			Assert.fail("Not able to login to the IPS application");
		}
	}
	
	public void switchToDesiredWindow(String Window) throws Exception {
		try {
			
			seleniumObj.getDriver().switchTo().window(Window);
			
		} catch (Exception ex) {
			log.error("Unable to Switch to Window");
			throw ex;
		}
	}

}
