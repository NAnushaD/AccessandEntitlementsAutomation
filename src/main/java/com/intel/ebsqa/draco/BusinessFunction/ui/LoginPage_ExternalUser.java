package com.intel.ebsqa.draco.BusinessFunction.ui;

import org.testng.Assert;

import com.intel.ebsqa.draco.PageClasses.LoginPageClass_ExternalUser;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

public class LoginPage_ExternalUser extends TestBase {

	/**
	 * Navigate to given URL
	 * 
	 * @Author jcherukx
	 * @Since Sep 14, 2018
	 * @param sURL
	 */
	public void navigateToURL_(String sURL) {
		seleniumObj.getDriver().navigate().to(sURL);
	}

	/**
	 * 
	 * 
	 * @Description Login to community portal
	 * @Author jcherukx
	 * @Since Sep 14, 2018
	 * @param username, password, communityUrl
	 * @throws TimeOutException
	 */
	public void loginToCommunityPortal(String username, String password, String communityUrl) throws TimeOutException {
		LoginPageClass_ExternalUser objLoginPageClass = new LoginPageClass_ExternalUser();
		navigateToURL_(communityUrl);
		try {
			objLoginPageClass.enterUserName(username);
			objLoginPageClass.enterPassword(password);
			objLoginPageClass.clickOnLoginButton();
			log.info("Logged to community portal");
		} catch (AssertionError e) {
			log.error("exception - Unable to navigate to URL");
			Assert.fail("Unable to navigate to URL");
		}
	}

}
