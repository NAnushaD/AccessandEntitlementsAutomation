package com.intel.ebsqa.draco.BusinessFunction.ui;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.gson.internal.LinkedTreeMap;
import com.intel.ebsqa.draco.DataClass.InternalCustomerUIData.InternalCustomerUIDataDetails;
import com.intel.ebsqa.draco.PageClasses.NavigationPageClass;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

public class Navigation extends TestBase {

	NavigationPageClass objNavigationPageClass = null;

	public Navigation() {
		objNavigationPageClass = new NavigationPageClass();
	}

	/**
	 * 
	 * @Description - this method will help us to navigate to particular
	 *              application
	 * @Author nithes1x
	 * @Since Sep 12, 2018
	 * @param pageTitle
	 *            - title of the page to navigate
	 * @throws TimeOutException
	 * @throws InterruptedException
	 */
	public void pageNavigationUsingAppLauncher(String pageTitle) throws TimeOutException, InterruptedException {
		try {
			objNavigationPageClass.ClickOnAppLauncher();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillAllXHRCallsComplete();
			objNavigationPageClass.clickViewAllinLaunchUI();
			objNavigationPageClass.EnterAppNameToNavigate(pageTitle);
			objNavigationPageClass.ClickAppLink(pageTitle);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Able to navigate to page: " + pageTitle);
		} catch (Exception ex) {
			Assert.fail("Not able to Navigate to page :" + pageTitle + ". " + ex.getMessage());
		}
	}

	/**
	 * 
	 * @Description - validates weather login is succcessful or not
	 * @Author nithes1x
	 * @Since Sep 12, 2018
	 * @return
	 * @throws TimeOutException
	 * @throws com.intel.ebsqa.exceptions.TimeOutException
	 */
	public boolean validateLogin() throws TimeOutException, com.intel.ebsqa.exceptions.TimeOutException {
		return objNavigationPageClass.WaitTillHomePageVisible();
	}

	/**
	 * 
	 * @Description - logouts from the application
	 * @Author nithes1x
	 * @Since Sep 12, 2018
	 * @throws TimeOutException
	 */
	public void logoutFromApplication() throws TimeOutException {
		try {
			sfcommonObj.switchToDefaultContent();
			objNavigationPageClass.ClickOnUserIcon();
			sfcommonObj.deleteCookies();
			objNavigationPageClass.ClickOnLogOut();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.deleteCookies();
			sfcommonObj.waitTillLightningPageLoadComplete();
			
		} catch (Exception ex) {
			Assert.fail("Logout from application failed :" + ex.getMessage());
		}
	}

	/**
	 * @Description To Search Account Name from Global Search
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchText(String searchText, String searchResultText, String searchType, String searchIcon) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResult(searchResultText, searchType);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}

	/**
	 * @Description To Search Account Name from Global Search
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchTextAndSelect(String searchText, String searchResultText, String searchType,
			String searchIcon) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResultAndSelect(searchResultText, searchType);
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}
	
	/**
	 * @Description To Search Account Name from Global Search
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchTextAndSelectExternal(String searchText, String searchResultText, String searchType,
			String searchIcon) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxExternal(searchText);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxExternal(searchText);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResult(searchResultText, searchType);
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}

	/**
	 * 
	 * @Description Method to navigate to 'Related' tab
	 * @Author vveeranx
	 * @Since Sep 12, 2018
	 */
	public void navigateToRelatedTab() {
		try {
			objNavigationPageClass.clickRelatedTab();
		} catch (Exception ex) {
			Assert.fail("Failed to navigate to 'Related' tab. " + ex.getMessage());
		}
	}

	/**
	 * @Description Method to verify Page Header Title
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 * @param expectedPageHeaderTitle
	 */
	public void verifyPageHeaderTitle(String expectedPageHeaderTitle) {
		try {
			seleniumObj.waitForElement(objNavigationPageClass.pageHeaderTitle, 10, 10);
			String actualPageHeaderTitle = objNavigationPageClass.getPageHeaderTitle().trim();
			Assert.assertEquals(actualPageHeaderTitle, expectedPageHeaderTitle,
					"Page Header Title is not mathc : " + expectedPageHeaderTitle + " != " + actualPageHeaderTitle);
			log.info("Verified page header title : " + expectedPageHeaderTitle);
		} catch (Exception ex) {
			Assert.fail("Not able to verify page header title : " + expectedPageHeaderTitle + ". " + ex.getMessage());
		}
	}

	/**
	 * @Description Method to verify grid details view access
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 */
	public void verifyGridDetailsViewAccess(String menu) {
		try {
			seleniumObj.waitForElement(objNavigationPageClass.gridViewTable, 10, 10);
			Assert.assertTrue(sfcommonObj.CheckElementExists(objNavigationPageClass.gridViewTable),
					"Not able to access details view " + menu);
			log.info("User able to access details view in " + menu);
		} catch (Exception ex) {
			Assert.fail("Not able to access details view in " + menu + ". " + ex.getMessage());
		}
	}

	/**
	 * @Description Method to verify new button is displayed
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 */
	public void verifyNewButtonIsDisplayed() {
		try {
			seleniumObj.waitForElement(objNavigationPageClass.newBtn, 10, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objNavigationPageClass.newBtn),
					"New button is not displayed");
			log.info("New button is displayed");
		} catch (Exception ex) {
			Assert.fail("New button is not displayed. " + ex.getMessage());
		}
	}

	/**
	 * @Description Method to verify new button is not displayed
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 */
	public void verifyNewButtonIsNotDisplayed() {
		try {
			seleniumObj.waitForElement(objNavigationPageClass.newBtn, 2, 1);
			Assert.assertFalse(sfcommonObj.checkElementExists(objNavigationPageClass.newBtn),
					"New button is displayed");
			log.info("New button is not displayed");
		} catch (Exception ex) {
			Assert.fail("New button is displayed" + ex.getMessage());
		}
	}

	/**
	 * @Description Method to select menu and verify pages access
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 * @param objInternalCustomerUIDataDetails
	 */
	/*
	 * public void selectMenuAndVerifyPages(InternalCustomerUIDataDetails
	 * objInternalCustomerUIDataDetails) { try { ArrayList<Object> subMenu =
	 * objInternalCustomerUIDataDetails.getMenu(); for(int i= 0;
	 * i<subMenu.size(); i++) { LinkedTreeMap<String, Object> nm =
	 * (LinkedTreeMap<String, Object>) subMenu.get(i); String menu
	 * =nm.get("name").toString(); this.pageNavigationUsingAppLauncher(menu);
	 * this.verifyPageHeaderTitle(menu); this.verifyGridDetailsViewAccess(menu);
	 * }
	 * 
	 * } catch (Exception e) {
	 * Assert.fail("Not able to select and verify pages");
	 * log.error("Not able to select and verify pages"); } }
	 */

	/**
	 * @Description Method to select menu and verify pages access
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 * @param objInternalCustomerUIDataDetails
	 */
	@SuppressWarnings("unchecked")
	public void selectMenuAndVerifyCreateAccess(InternalCustomerUIDataDetails objInternalCustomerUIDataDetails) {
		try {
			ArrayList<Object> subMenu = objInternalCustomerUIDataDetails.getMenu();
			for (int i = 0; i < subMenu.size(); i++) {
				LinkedTreeMap<String, Object> nm = (LinkedTreeMap<String, Object>) subMenu.get(i);
				String menu = nm.get("name").toString();
				String createAccess = nm.get("viewtype").toString();
				this.pageNavigationUsingAppLauncher(menu);
				this.verifyPageHeaderTitle(menu);
				this.verifyGridDetailsViewAccess(menu);
				if (createAccess.equals("Edit"))
					this.verifyNewButtonIsDisplayed();
				else if (createAccess.equals("ReadOnly"))
					this.verifyNewButtonIsNotDisplayed();
			}
		} catch (Exception ex) {
			Assert.fail("Not able to select and verify pages. " + ex.getMessage());
		}
	}

	/**
	 * 
	 * @Description - log out from external user the application
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @throws TimeOutException
	 */
	public void logoutFromExternalUserApplication() throws TimeOutException {
		try {
			sfcommonObj.waitTillLightningPageLoadComplete();
			NavigationPageClass objNavigationPageClass = new NavigationPageClass();
			objNavigationPageClass.ClickOnUserMenu();
			log.info("Clicked on User menu");
			objNavigationPageClass.ClickOnLogOutInExternalCustomerUI();
			log.info("Successfully loged out from application");
		} catch (Exception ex) {
			Assert.fail("failed to Logout From External Application. " + ex.getMessage());
		}
	}

	/**
	 * 
	 * @Description Method to navigate to 'Details' tab
	 * @Author vveeranx
	 * @Since Sep 12, 2018
	 */
	public void navigateToDetailsTab() {
		try {
			objNavigationPageClass.clickOnDetailsTab();
		} catch (Exception ex) {
			Assert.fail("Failed to navigate to 'Details' tab. " + ex.getMessage());
		}
	}

	/**
	 * 
	 * @Description Method to select view for all the tabs
	 * @Author vveeranx
	 * @Since Oct 23, 2018
	 * @param viewName
	 */
	public void selectView(String view) {
		try {

			objNavigationPageClass.clickSelectViewLink();
			objNavigationPageClass.selectView(view);
			log.info("Selected view: " + view);
		} catch (Exception ex) {
			Assert.fail("Failed to select view. " + ex.getMessage());
		}
	}

	/**
	 * @Description Method to verify if no result found image displays after
	 *              global search
	 * @Author vveeranx
	 * @Since Apr 9 2019
	 */
	public void verifyNoResultFoundImageAfterGlobalSearch() {
		try {
			Assert.assertTrue(
					sfcommonObj.CheckElementExists(objNavigationPageClass.noResultFoundImageAfterGlobalSearch));

			log.info("Verified if no result found image displays after global search");
		} catch (Exception ex) {
			Assert.fail("Failed to verify if no result found image displays after global search " + ex.getMessage());
		}
	}

	public void verifyIfLighentingUIPageEnabled(String fieldname, boolean display) {
		try {
			boolean isDisplayed = seleniumObj.isElementDisplayed(objNavigationPageClass.lighteningUI);

			if (isDisplayed) {

				objNavigationPageClass.clickLighteningUILink();
				log.info("Lightening Link '" + fieldname + "' is displaying");
			} else {
				Assert.assertFalse(isDisplayed, fieldname + " link is displaying");
				log.info("Lightening Link '" + fieldname + "' is not displaying");
			}
		} catch (Exception ex) {
			Assert.fail("Failed to verify secondary field values " + ex.getMessage());
		}

	}

	public void ClickonNewButton() {
		try {
			objNavigationPageClass.clickOnNewButton();
		} catch (Exception ex) {
			Assert.fail("Failed to Click on New Button. " + ex.getMessage());
		}
	}

	/**
	 * @Description To Search Account Name from Global Search
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchAccountAndSelect(String accName, String searchType, String searchIcon, String cimID,
			String accID) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(cimID);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(cimID);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			List<WebElement> records = objNavigationPageClass.getselectSearchedAccounts(accName, searchType, cimID,
					accID);
			if (records.size() == 1) {
				WebElement ele = records.get(0);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			} else {
				WebElement ele = records.get(records.size() - 1);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			}

			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}
	public void globalUISearchAccountAndSelectclassic(String accName, String searchType, String searchIcon, String cimID,
			String accID) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(cimID);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(cimID);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			List<WebElement> records = objNavigationPageClass.getselectSearchedAccounts(accName, searchType, cimID,
					accID);
			if (records.size() == 1) {
				WebElement ele = records.get(0);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			} else {
				WebElement ele = records.get(records.size() - 1);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			}

			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}
	/**
	 * @Description To Search Account Name from Global Search
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchContactAndSelect(String accName, String searchType, String searchIcon, String emailID,
			String contactName) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(emailID);
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextbox(emailID);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResultSearchIcon();
			List<WebElement> records = objNavigationPageClass.getselectSearchedContact(accName, searchType, emailID,
					contactName);
			if (records.size() == 1) {
				WebElement ele = records.get(0);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			} else {
				WebElement ele = records.get(records.size() - 1);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			}

			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}
	public void globalUISearchContactAndSelectclassic(String accName, String searchType, String searchIcon, String emailID,
			String contactName) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		//seleniumObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxclassic(emailID);
			objNavigationPageClass.clickOnSearchResultSearchIconclassic();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxclassic(emailID);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objNavigationPageClass.clickOnSearchResultSearchIconclassic();
			List<WebElement> records = objNavigationPageClass.getselectSearchedContactclassic(accName, searchType, emailID,
					contactName);
			if (records.size() == 1) {
				WebElement ele = records.get(0);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			} else {
				WebElement ele = records.get(records.size() - 1);
				seleniumObj.clickByJS(ele);
				sfcommonObj.waitTillLightningPageLoadComplete();

			}

			sfcommonObj.waitTillLightningPageLoadComplete();
		}

	}
	
	/**
	 * @Description To switchToLightningExperience
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void switchToLightningExperience() {
		try {
			boolean isDisplayed = seleniumObj.isElementDisplayed(objNavigationPageClass.lighteningUI);

			if (isDisplayed) {
				objNavigationPageClass.clickLighteningUILink();
				log.info("Lightening Link switchToLightningExperience   is displaying");
			} else {
				Assert.assertFalse(isDisplayed, "switchToLightningExperience   link is displaying");
				log.info("Lightening Link  switchToLightningExperience  is not displaying");
			}
		} catch (Exception ex) {
			Assert.fail("Failed to verify secondary field values " + ex.getMessage());
		}
		

	}
	
	/**
	 * @Description To Search Account Name from Global Search IPS_External
	 * @Author kumark8x
	 * @Since Sep 13, 202
	 * @param searchText
	 * @param searchResultText
	 * @param searchType
	 * @param searchIcon
	 */
	public void globalUISearchTextAndSelectIPS_External(String searchText, String searchResultText, String searchType,
			String searchIcon) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxIPS_External(searchText);
			objNavigationPageClass.clickOnSearchResultSearchIconIPS_External();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objNavigationPageClass.setValueForGlobalSearchTextboxIPS_External(searchText);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigationPageClass.clickOnSearchResultAndSelectIPS_External(searchResultText, searchType);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	}	
		
	    /**
		 * @Description To Search Account Name from Global Search
		 * @Author arjunpnx
		
		 */
		public void globalQuoteSeach(String searchText) {
			
				//seleniumObj.waitForSeconds(15);
				objNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
				objNavigationPageClass.clickQuoteSearched();
		}	
		 /**
		 * @Description Method encounter time out 
		 * @Author arjunpnx
		
		 */
		public void timoutLogin() {
				objNavigationPageClass.clickLoginBtn();
		}	
		public void getSalesforceSearchIdContact(String username) throws InterruptedException {

			seleniumObj.waitForSeconds(10);
			try {
				objNavigationPageClass.SearchKeyContact((username));
				return;
			} catch (Exception e) {
				log.info(e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + "Faile to search co");
			}
		}
		public void logintoIntelPartnerAlliance()
		{
			seleniumObj.waitForSeconds(5);
			seleniumObj.waitForElement(objNavigationPageClass.experirncedUserBtn,5,5);
			objNavigationPageClass.experirncedUserBtn.click();
			seleniumObj.waitForSeconds(5);
			objNavigationPageClass.ipaLink.click();
			seleniumObj.waitForSeconds(5);
		}
		   public void switchWindow()
		    {
		    	
		    	for(String windowhandle:driver.getWindowHandles())
		    	{
		          driver.switchTo().window(windowhandle);
		          driver.switchTo().defaultContent();
		    	}
		    	
		    }	
		
		  public void switchToOverviewPage()
		    {
		    	try
		    	{
		    	seleniumObj.waitForElement(objNavigationPageClass.companyProfileLink, 5, 5);
		    	objNavigationPageClass.companyProfileLink.click();
				seleniumObj.waitForSeconds(15);
				switchWindow();
		    	}
		    	catch(Exception e)
		    	{
		    		seleniumObj.waitForSeconds(5);
		    	}
		    }
		  public void AddSecondaryUserEmail(String Email2) {

				seleniumObj.waitForElement(objNavigationPageClass.EPUM_Company_Profile, 2, 15);
				//JavascriptExecutor executor = (JavascriptExecutor) driver;
				//WebElement MangCompProfilel = driver.findElement(By.xpath("//a[@title='Company Profile']"));
				//executor.executeScript("arguments[0].click();", MangCompProfilel);
				seleniumObj.clickByJS(objNavigationPageClass.EPUM_Company_Profile);
				seleniumObj.waitForSeconds(5);
			    String adminSec_EmailID= Email2;
				/*WebElement MangPerButt = driver
						.findElement(By.xpath("//div/button[@class='slds-button slds-button_brand save_button hideButton']"));
				seleniumObj.waitForElement(MangPerButt, 2, 5);
				executor.executeScript("arguments[0].click();", MangPerButt);*/
				seleniumObj.clickByJS(objNavigationPageClass.MangPerButt);
				// MangPerButt.click();
				
				seleniumObj.waitForSeconds(5);
				objNavigationPageClass.epumEnterFirstName(adminSec_EmailID);
				/*WebElement emailTextBox = driver.findElement(By.xpath("//input[@name='conEmail']"));
				
				
				emailTextBox.sendKeys(adminSec_EmailID);*/
				
				
				//	DatafromConfig.strEmailId2 = adminSec_EmailID;
				

				// WebElement srchContact =
				// driver.findElement(By.xpath("//button[contains(text(), 'Search Contact')]"));
				

			}
		  public void InviteNewUser(String FirstName,String LastName,String Country) {
				// Clicking on Invite a user
				//WebElement inviteUsr = driver.findElement(By.xpath("//button[contains(text(), 'Invite a User')]"));
				objNavigationPageClass.InviteNewUserPagee(FirstName,LastName,Country);
				seleniumObj.waitForSeconds(20);
			}
		  public void InviteUserAsExistingACR(){
			  objNavigationPageClass.InviteuserAsExistingACR();
			  seleniumObj.waitTillPageLoadIsComplete();
		  }
}
