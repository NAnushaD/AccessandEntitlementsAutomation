package com.intel.ebsqa.draco.PageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

/**
 * 
 * @author jcherukx
 *
 */
public class LoginPageClass_ExternalUser extends TestBase {
	@FindBy(xpath = "//div[@id='sfdc_password_container']/preceding:: input")
	private WebElement userName;

	@FindBy(xpath = "//div[@id='sfdc_username_container']/following:: input")
	private WebElement passWord;

	@FindBy(xpath = "//button/span[text()='Log in']")
	private WebElement loginButton;

	public LoginPageClass_ExternalUser() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}

	public void enterUserName(String uName) throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(userName, 10);
			userName.sendKeys(uName);
			log.info("Entered Username");
		} catch (AssertionError e) {
			log.error("exception - Unable to enter User name");
			Assert.fail("Unable to enter User name");
		}
	}

	public void enterPassword(String pwd) throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(passWord, 10);
			passWord.sendKeys(pwd);
			log.info("Entered Password");
		} catch (AssertionError e) {
			log.error("exception - Unable to enter password");
			Assert.fail("Unable to enter password");
		}
	}

	public void clickOnLoginButton() throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(loginButton, 10);
			loginButton.click();
			log.info("Clicked on login button");
		} catch (AssertionError e) {
			log.error("exception - Unable to click on login button");
			Assert.fail("Unable to click on login button");
		}
	}
}
