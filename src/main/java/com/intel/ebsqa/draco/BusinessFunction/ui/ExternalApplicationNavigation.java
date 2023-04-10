/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.BusinessFunction.ui;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.gson.internal.LinkedTreeMap;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData.ExternalCustomerUIDataDetails;
import com.intel.ebsqa.draco.PageClasses.ExternalApplicationNavigationPageClass;
import com.intel.ebsqa.draco.enums.AccountEnum;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

/**
 * @Description
 * @Author gmathavx
 * @Since 14-Sep-2018
 */

public class ExternalApplicationNavigation extends TestBase {

	ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = null;

	public ExternalApplicationNavigation() {
		objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
		// objPartnerMarketingBudget = new PartnerMarketingBudget();
	}

	/**
	 * Method to validate the login
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void validateLogin() throws TimeOutException {
		try {
			sfcommonObj.waitTillAllXHRCallsComplete();
			Assert.assertTrue(objExternalApplicationNavigationPageClass.WaitTillHomePageVisibleFroExternalApplication(),
					"Not able to verify the home page");
			log.info("Verified the home page after login");
		} catch (Exception ex) {
			Assert.fail("Not able to verify the home page " + ex.getMessage());
		}
	}

	/**
	 * Method to click on Program Menu
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnProgramMenu() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.myProgramMenu, 4, 3);
			Assert.assertTrue(seleniumObj.isElementExists(objExternalApplicationNavigationPageClass.myProgramMenu),
					"Program menu does not exist");
			objExternalApplicationNavigationPageClass.clickOnProgramMenu();
			log.info("Clicked on program menu");
		} catch (Exception ex) {
			Assert.fail("Not able to click 'My Program' menu " + ex.getMessage());
		}
	}

	/**
	 * Method to click on submenu
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param menu The sub menu name to click
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnsubMenu(String menu) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.subMenu(menu), 10, 5);
			objExternalApplicationNavigationPageClass.clickOnSubMenu(menu);
			log.info("Clicked on sub menu: " + menu);
		} catch (Exception ex) {
			Assert.fail("Not able to clcik on sub menu " + ex.getMessage());
		}
	}

	/**
	 * Method to validate menu and sub menu pages
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param externalCustomerUIData       The external customer data
	 * @param objExternalCustomerUIDetails The external customer details data
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	@SuppressWarnings("unchecked")
	public void vaidateMenuAndSubMenuPages(ExternalCustomerUIData externalCustomerUIData,
			ExternalCustomerUIDataDetails objExternalCustomerUIDetails) throws TimeOutException {
		try {
			ArrayList<Object> subMenu = objExternalCustomerUIDetails.getSubMenu();
			for (int i = 0; i < subMenu.size(); i++) {
				this.clickOnProgramMenu();
				LinkedTreeMap<String, Object> nm = (LinkedTreeMap<String, Object>) subMenu.get(i);
				String menu = nm.get("name").toString();
				String viewtype = nm.get("viewtype").toString();
				objExternalApplicationNavigationPageClass.clickOnSubMenu(menu);
				log.info("Clicked on sub menu : " + subMenu.get(i).toString());
				if (menu.equals("Co-Marketing Activities") || menu.equals("Claims")) {
					seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.actvtynewButton, 10, 1);
				} else {
					seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.newButton, 10, 1);
				}
				if (viewtype.equals("ReadOnly")) {
					Assert.assertFalse(objExternalApplicationNavigationPageClass.isNewButtonVisible(menu),
							"New button is visibled");
					log.info("Page is read only mode");
				} else if (viewtype.equals("Edit")) {
					Assert.assertTrue(objExternalApplicationNavigationPageClass.isNewButtonVisible(menu),
							"New button is visibled ");
					log.info("Page is edit mode");
				}
			}
		} catch (Exception ex) {
			Assert.fail("Page is not read only mode " + ex.getMessage());
		}
	}

	/**
	 * Method o validate visibility of menu and submenu
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param externalCustomerUIData       The external Customer UI Data
	 * @param objExternalCustomerUIDetails The external Customer UI Details
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	@SuppressWarnings("unchecked")
	public void vaidateMenuAndSubMenuVisible(ExternalCustomerUIData externalCustomerUIData,
			ExternalCustomerUIDataDetails objExternalCustomerUIDetails) throws TimeOutException {
		try {
			ArrayList<Object> subMenuList = objExternalCustomerUIDetails.getSubMenu();

			this.clickOnProgramMenu();
			for (int i = 0; i < subMenuList.size(); i++) {
				LinkedTreeMap<String, Object> subMenu = (LinkedTreeMap<String, Object>) subMenuList.get(i);
				String menu = subMenu.get("name").toString();
				String visible = subMenu.get("visible").toString();

				boolean isExist = sfcommonObj
						.checkElementExists(objExternalApplicationNavigationPageClass.subMenu(menu));
				if (visible.equals("false")) {
					Assert.assertFalse(isExist, "Menu is visibled : " + menu);
					log.info("Menu is not visible " + menu);
				} else if (visible.equals(Boolean.toString(isExist))) {
					Assert.assertTrue(isExist, "Menu is not visibled : " + menu);
					log.info("Menu is visible " + menu);
				}
			}
		} catch (Exception ex) {
			Assert.fail("Page is not read only mode " + ex.getMessage());
		}
	}

	/**
	 * Method to click on user menu link
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnUserMenuLink() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.userMenuLink, 10, 1);
			Assert.assertTrue(sfcommonObj.CheckElementExists(objExternalApplicationNavigationPageClass.userMenuLink),
					"User menu link is not displayed");
			objExternalApplicationNavigationPageClass.clickOnUserMenuLink();
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on user menu link");
		} catch (Exception ex) {
			Assert.fail("Not able to click on user menu link " + ex.getMessage());
		}
	}

	/**
	 * Method to click on dynamic drop down link
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 * @param menu The menu name
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnDynamicDropDownValueLink(String menu) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.dynamicDropDownValueLink(menu), 10, 1);
			Assert.assertTrue(
					sfcommonObj.CheckElementExists(
							objExternalApplicationNavigationPageClass.dynamicDropDownValueLink(menu)),
					"dynamic dropdown link is not displayed");
			objExternalApplicationNavigationPageClass.selectDynamicDropDownValueLink(menu);
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on user dopdown menu : " + menu);
		} catch (Exception ex) {
			Assert.fail("Not able to click on user dropdown menu : " + menu + " " + ex.getMessage());
		}
	}

	/**
	 * Method to logout from the application
	 * 
	 * @Author gmathavx
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void logoutFromApplication() throws TimeOutException {
		try {
			this.clickOnUserMenuLink();
			this.clickOnDynamicDropDownValueLink("Logout");
			log.info("Logout from application completed");
		} catch (Exception e) {
			Assert.fail("Not able to logout " + e.getMessage());
		}
	}

	/**
	 * Method for click on new button
	 * 
	 * @Author gmathavx
	 * @Since 21-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnNewButton() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.newButton, 15, 10);
			Assert.assertTrue(sfcommonObj.CheckElementExists(objExternalApplicationNavigationPageClass.newButton),
					"New Button is not displayed");
			objExternalApplicationNavigationPageClass.clickOnNewButton();
			log.info("Clicked on new button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on new button " + ex.getMessage());
		}
	}

	/**
	 * Method to select main menu tab
	 * 
	 * @Author mohseenx
	 * @Since Sep 21, 2018
	 * @param menuName
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void selectMenu(String menuName) throws TimeOutException {
		try {
			objExternalApplicationNavigationPageClass.menuName(menuName).click();
			log.info("Selected " + menuName + " from menu tab");
		} catch (Exception ex) {
			Assert.fail("Not able Select " + menuName + " from menu tab " + ex.getMessage());

		}
	}

	/**
	 * Method to validate if budget name displayed under partner marketing budget
	 * 
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @param budgetName  The budget name to be verified
	 * @param isDisplayed A true or false value , whether to check displayed or not
	 *                    displayed
	 */
	public void validateIfBudgetNameDisplayedUnderPartnermarketingBudgetMenu(String budgetName, Boolean isDisplayed) {
		// ExternalApplicationNavigationPageClass
		// objExternalApplicationNavigationPageClass = new
		// ExternalApplicationNavigationPageClass();
		try {
			Boolean isExist = objExternalApplicationNavigationPageClass
					.isBudgetNameDisplayedInPartnerMarketingBudget(budgetName);

			if (isDisplayed)
				Assert.assertTrue(isExist, "Budget " + budgetName + " displayed under Partner marketing Budget");
			else
				Assert.assertFalse(isExist, "Budget " + budgetName + " not displayed under Partner marketing Budget");
		} catch (Exception ex) {
			Assert.fail("Failed to verify budget name " + budgetName
					+ " displayed or not displayed in partner marketing budget menu " + ex.getMessage());
		}
	}

	/**
	 * Method to validate if budget name displayed under partner fund allocation
	 * 
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @param budgetName  The budget name to be verified
	 * @param isDisplayed A true or false value , whether to check displayed or not
	 *                    displayed
	 */
	public void validateIfBudgetNameDisplayedUnderPartnerFundAllocationMenu(String budgetName, Boolean isDisplayed) {
		// ExternalApplicationNavigationPageClass
		// objExternalApplicationNavigationPageClass = new
		// ExternalApplicationNavigationPageClass();
		try {
			Boolean isExist = objExternalApplicationNavigationPageClass
					.isBudgetNameDisplayedInPartnerFundAllocation(budgetName);

			if (isDisplayed)
				Assert.assertTrue(isExist, "Budget " + budgetName + " displayed under Partner Fund Allocation");
			else
				Assert.assertFalse(isExist, "Budget " + budgetName + " not displayed under Partner Fund Allocation");
		} catch (Exception ex) {
			Assert.fail("Failed to verify budget name " + budgetName
					+ " displayed or not displayed in partner Fund Allocation menu " + ex.getMessage());
		}
	}

	

	/**
	 * Method to validate the modified budget amount is displayed in UI
	 * 
	 * @Author ubijux
	 * @Since Sep 26, 2018
	 * @param budgetName  The budget name to be verified
	 * @param isDisplayed A true or false value , whether to check displayed or not
	 *                    displayed
	 */
	public void validateIfBudgetAmountUpdated(String budgetName, String budgetAmount) {
		ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
		double budgetAmountDouble = Double.parseDouble(budgetAmount);
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		String currency = formatter.format(budgetAmountDouble);

		try {
			Boolean isExist = objExternalApplicationNavigationPageClass
					.isBudgetAmountDisplayedInPartnerFundAllocation(budgetName, currency);

			Assert.assertTrue(isExist, "Budget " + budgetAmount + " displayed under Partner Fund Allocation");

		} catch (Exception ex) {
			Assert.fail("Failed to verify budget name " + budgetAmount
					+ "  not displayed in partner Fund Allocation menu " + ex.getMessage());
		}
	}

	/**
	 * Method to click on 'All Campaigns' link
	 * 
	 * @Author vveeranx
	 * @Since Sep 14,2018
	 */
	public void clickOnAllCampaignsLink() {
		try {
			objExternalApplicationNavigationPageClass.clickAllCampaignsLink();
			log.info("Clicked on 'All Campaigns' link");
		} catch (Exception ex) {
			Assert.fail("Not able to click on 'All Campaigns' link " + ex.getMessage());
		}
	}

	/**
	 * Method to search in 'All Campaigns' tab
	 * 
	 * @Author vveeranx
	 * @Since Sep 14,2018
	 * @param searchText The text to be searched
	 */
	public void searchInAllCampaignsTab(String searchText) {
		try {
			log.info("Search in 'All Campaigns' tab: " + searchText);
			objExternalApplicationNavigationPageClass.enterTextInSearchTextBoxInAllCampaignsTab(searchText);
			objExternalApplicationNavigationPageClass.sendEnterKeyToSearchTextBoxInAllCampaignsTab();
		} catch (Exception ex) {
			Assert.fail("Not able to search in 'All Campaigns' tab " + ex.getMessage());
		}
	}

	/**
	 * Method to verify if a campaign is displaying in 'All Campaigns' table
	 * 
	 * @Author vveeranx
	 * @Since Sep 14,2018
	 * @param campaignName The Campaign name to be verified
	 * @param isDisplayed  A true or false value , whether to check displayed or not
	 *                     displayed
	 */
	public void verifyIfCampaignDisplayingInAllCampaignsTable(String campaignName, Boolean isDisplay) {
		try {
			if (!isDisplay) {
				ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
				Assert.assertEquals(0, objExternalApplicationNavigationPageClass.getRowCountInAllCampaignTable(),
						"Campaign: " + campaignName + " is displaying in 'All Campaigns' link");
				log.info("Campaign:" + campaignName + " is not displaying in 'All Campaigns' table");
			} else {
				Assert.assertEquals(1, objExternalApplicationNavigationPageClass.getRowCountInAllCampaignTable(),
						"Campaign: " + campaignName + " is not displaying in 'All Campaigns' link");
				log.info("Campaign:" + campaignName + " is displaying in 'All Campaigns' table");
			}

		} catch (Exception ex) {
			Assert.fail("Failed to verify if a campaign is displaying in 'All Campaigns' table " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Method to select view in All campaigns tab
	 * 
	 * @Author vveeranx
	 * @Since Sep 21, 2018
	 * @param viewName
	 *//*
		 * public void selectViewInAllCampaignsTab(String viewName) { try {
		 * objExternalApplicationNavigationPageClass.clickSelectViewLink();
		 * objExternalApplicationNavigationPageClass.selectView(viewName);
		 * seleniumObj.waitTillPageLoadIsComplete(); log.info("Selected view: " +
		 * viewName); } catch (Exception ex) {
		 * Assert.fail("Failed to select view in All campaign tab"); } }
		 */

	/**
	 * Method to verify account name field is displayed
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyAccountNameIsDisplayed() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.accountName, 10, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.accountName),
					"Account name field is not displayed");
			log.info("Account name field is displayed");
		} catch (Exception ex) {
			Assert.fail("Account name field is not displayed " + ex.getMessage());
		}
	}

	/**
	 * Method to verify Parent Fund Account field is displayed
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyParentFundAccountIsDisplayed() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.parentFundAccount, 10, 10);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.parentFundAccount),
					"Parent Fund Account field is not displayed");
			log.info("Parent Fund Account field is displayed");
		} catch (Exception ex) {
			Assert.fail("Parent Fund Account field is not displayed " + ex.getMessage());
		}
	}

	/**
	 * Method to verify Channel Program field is displayed
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyChannelProgramIsDisplayed() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.channelProgram, 10, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.channelProgram),
					"Channel Program field is not displayed");
			log.info("Channel Program field is displayed");
		} catch (Exception ex) {
			Assert.fail("Channel Program field is not displayed " + ex.getMessage());
		}
	}

	/**
	 * Method to verify Parent Account field is displayed
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyParentAccountIsDisplayed() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.parentAccount, 10, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.parentAccount),
					"Account name field is not displayed");
			log.info("Parent Account field is displayed");
		} catch (Exception ex) {
			Assert.fail("Parent Account field is not displayed " + ex.getMessage());
		}
	}

	/**
	 * Method to verify Account Record Type field is displayed
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyAccountRecordTypeIsDisplayed() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.accountRecordType, 10, 10);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.accountRecordType),
					"Account Record Type field is not displayed");
			log.info("Account Record Type field is displayed");
		} catch (Exception ex) {
			Assert.fail("Account Record Type field is not displayed " + ex.getMessage());
		}
	}

	/**
	 * Method to click on cancel button
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnCancelButton() throws TimeOutException {
		try {
			ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.cancelButton, 5, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.cancelButton),
					"Cancel button is not displayed");
			objExternalApplicationNavigationPageClass.clickOnCancelButton();
			log.info("Clicked on cancel button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on cancel button " + ex.getMessage());
		}
	}

	/**
	 * Method to enter account name
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 * @param accountName The text to be entered as account name
	 */
	public void enterAccountName(String accountName) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.accountName, 30, 10);
			objExternalApplicationNavigationPageClass.setAccountName(accountName);
			log.info("Entered account name value : " + accountName);
		} catch (Exception ex) {
			Assert.fail("Not able to enter account name " + ex.getMessage());
		}
	}

	/**
	 * Method to enter channel program
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @param channelProgram The value to be selected as channel program
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void enterChannelProgram(String channelProgram) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.accountName, 30, 10);
			objExternalApplicationNavigationPageClass.setChannelProgram(channelProgram);
			log.info("Entered channel program value : " + channelProgram);
		} catch (Exception ex) {
			Assert.fail("Not able to enter channel program " + ex.getMessage());
		}
	}

	/**
	 * Method to enter and select channel program
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @param channelProgram The text to be entered in the Channel program
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void enterAndSelectChannelProgram(String channelProgram) throws TimeOutException {
		try {
			this.enterChannelProgram(channelProgram);
			objExternalApplicationNavigationPageClass.selectDynamicPopupDropdown(channelProgram);
			log.info("Selected channel program name value : " + channelProgram);
		} catch (Exception ex) {

			Assert.fail("Not able to select channel program");

		}
	}

	/**
	 * Method to enter parent fund account
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @param parentFundAccount The text to be entered as Parent Fund Account
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void enterParentFundAccount(String parentFundAccount) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.accountName, 30, 10);
			objExternalApplicationNavigationPageClass.setParentFundAccountValue(parentFundAccount);
			objExternalApplicationNavigationPageClass.selectDynamicPopupDropdown(parentFundAccount);
			log.info("Entered parent fund account value : " + parentFundAccount);
		} catch (Exception ex) {
			Assert.fail("Not able to enter parent fund account " + ex.getMessage());
		}
	}

	/**
	 * Method to verify parent fund account
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @param parentFundAccount The parent fund account to be entered to be
	 *                          validated
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void verifyParentFundAccount(String parentFundAccount) throws TimeOutException {
		try {
			seleniumObj.waitForElement(
					objExternalApplicationNavigationPageClass.dynamicPopupDropdown(parentFundAccount), 5, 10);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(
							objExternalApplicationNavigationPageClass.dynamicPopupDropdown(parentFundAccount)),
					"Parent Fund Account is not displayed");
			log.info("Parent Fund Account is displayed : " + parentFundAccount);
		} catch (Exception ex) {
			Assert.fail("Not able to verify parent fund account " + ex.getMessage());
		}
	}

	/**
	 * Method to click on save button
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnSaveButton() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.saveButton, 5, 10);
			Assert.assertTrue(sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.saveButton),
					"Save button is not displayed");
			objExternalApplicationNavigationPageClass.clickOnSaveButton();
			log.info("Clicked on save button");
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on save button " + ex.getMessage());
		}
	}

	/**
	 * 
	 * Method to verify the error message at the top in campaign window
	 * 
	 * @Author sharmata
	 * @Since Sep 27, 2018
	 * @param errorMessage The error message to be verified
	 */
	public void validateHeaderErrorMessageInCoMarketingWindow(String expectedErrorMessage) {
		try {
			String actualMessage = objExternalApplicationNavigationPageClass.getHeaderErrorMessageInCoMarketingWindow();

			Assert.assertEquals(expectedErrorMessage, actualMessage, "Mismatch with marketing error messages");
		} catch (Exception ex) {
			Assert.fail("Not able to click save button in campaign view page " + ex.getMessage());
		}
	}

	/**
	 * Method to click on View Duplicate Link
	 * 
	 * @Author sharmata
	 * @Since 27-Sep-2018
	 * @throws TimeOutException Exception thrown when a blocking operation times
	 *                          out.
	 */
	public void clickOnViewDuplicateLink() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.viewDuplicateLink, 5, 10);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objExternalApplicationNavigationPageClass.viewDuplicateLink),
					"View Duplicate Link is not displayed");
			objExternalApplicationNavigationPageClass.clickOnViewDuplicateLink();
			log.info("Clicked on View Duplicate Link");
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on View Duplicate Link " + ex.getMessage());
		}
	}

	/**
	 * Method to validate if red asterisk mark is visible for any new Co Marketing
	 * Window Page
	 * 
	 * @Author sharmata
	 * @Since Sep 27, 2018
	 * @param fields    The list of fields or label
	 * @param isVisible The true or false value whether to check visible or not
	 *                  visible
	 */
	public void validateIfRedAsteriskVisibleForNewCampaignField(List<String> fields, Boolean isVisible) {
		;
		try {
			for (String field : fields) {
				Boolean isExist = objExternalApplicationNavigationPageClass
						.isRedAsteriskMarkVisibleForNewCampaignField(field);

				if (isVisible)
					Assert.assertTrue(isExist, "Red asterisk is not visible for " + field);
				else
					Assert.assertFalse(isExist, "Red asterisk is visible for " + field);
			}
		} catch (Exception ex) {
			Assert.fail("Failed to check the red asterisk mark for field: " + ex.getMessage());
		}
	}

	/**
	 * Method to verify the error message at the top in campaign window
	 * 
	 * @Author sharmata
	 * @Since Sep 27, 2018
	 * @param errorMessage The error message to be verified
	 */
	public void validateMandatoryFieldErrorMessageInCoMarketingWindow(String expectedErrorMessage) {
		try {
			String actualMessage = objExternalApplicationNavigationPageClass
					.getMandatoryFieldErrorMessageInCoMarketingWindow();

			Assert.assertEquals(expectedErrorMessage, actualMessage, "Mismatch with marketing error messages");
		} catch (Exception ex) {
			Assert.fail("Not able to click save button in campaign view page " + ex.getMessage());
		}
	}

	/**
	 * Method to Click On Campaign Name
	 * 
	 * @Author sharmata
	 * @param campaignName The campaign name to be clicked
	 * @Since Oct 7, 2018
	 */
	public void clickOnCampaignName(String campaignName) {
		try {
			objExternalApplicationNavigationPageClass.clickOnCampaignName(campaignName);
		} catch (Exception ex) {
			Assert.fail("Not able to click new button for creating campaign " + ex.getMessage());
		}
	}

	/**
	 * Method to verify campaign status
	 * 
	 * @Author sharmata
	 * @param expectedStatus The status to be verified
	 * @Since Oct 7, 2018
	 */
	public void verifyCampaignStatus(String expectedStatus) {
		try {

			String actualCampainStatus = objExternalApplicationNavigationPageClass.getCampaignStatus();
			Assert.assertEquals(actualCampainStatus, expectedStatus, "Mismatch with campaign status");
			log.info("Verified campaign status: " + expectedStatus);
		} catch (Exception ex) {
			Assert.fail("Failed to verify campaign status " + ex.getMessage());
		}
	}

	/**
	 * Method to Validate Login To External Application by Program Menu
	 * 
	 * @Author gmathavx
	 * @Since 14-Sep-2018
	 */
	public void validateLoginToExternalApplicationByProgramMenu() {
		try {

			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.myProgramMenu, 10, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objExternalApplicationNavigationPageClass.myProgramMenu),
					"My Program menu is not displayed");
			log.info("Successfully logged into the application");
		} catch (Exception ex) {
			Assert.fail("Not able to validate program menu " + ex.getMessage());
		}
	}

	/**
	 * Method to verify channel program name under the Channel Program column in the
	 * Co-marketing account view
	 * 
	 * @Author ubijux
	 * @Since 19-Oct-2018
	 * @param channelProgram The value in the channel program column
	 */
	public void verifyValuesInChannelProgramColumn(String channelProgram) {
		try {
			int columnIndex = getColumnIndex("Channel Program");
			if (columnIndex > 0) {
				int rowIndex = objExternalApplicationNavigationPageClass.coMarketingAccouctViewRows().size();
				if (rowIndex > 0) {
					boolean isDisplayed = objExternalApplicationNavigationPageClass
							.verifyChannelProgramValue(columnIndex, channelProgram);
					Assert.assertTrue(isDisplayed, "Failed to verify Channel program value as " + channelProgram);
				} else {
					Assert.fail("No rows displayed");
				}
			} else {
				Assert.fail("Column 'Channel Program' does not exist");
			}
		} catch (Exception ex) {
			Assert.fail("Failed to verify Channel program value as " + channelProgram + " " + ex.getMessage());
		}
	}

	/**
	 * Method to Click on Co-Marketing activity
	 * 
	 * @Author vveeranx
	 * @param activityName The activity name to be clicked
	 * @Since Oct 22, 2018
	 */
	public void clickOnCoMarketingActivityName(String activityName) {
		try {
			objExternalApplicationNavigationPageClass.clickOnCoMarketingActivityName(activityName);
		} catch (Exception ex) {
			Assert.fail("Not able to click Co-Marketing activity " + ex.getMessage());
		}
	}

	/**
	 * Method to verify if activity is displaying after search
	 * 
	 * @Author vveeranx
	 * @param activityName The activity name to be verified
	 * @param display      True/false whether to check displayed or not displayed
	 * @Since Oct 22,2018
	 */
	public void verifyIfActivityDisplayingInAllCampaignsTable(String activityName, Boolean isDisplayed) {
		try {
			if (!isDisplayed) {
				ExternalApplicationNavigationPageClass objExternalApplicationNavigationPageClass = new ExternalApplicationNavigationPageClass();
				Assert.assertEquals(0, objExternalApplicationNavigationPageClass.getRowCountInAllCampaignTable(),
						"Campaign: " + activityName + " is displaying in 'All Campaigns' link");
				log.info("Activity:" + activityName + " is not displaying after search");
			} else {
				Assert.assertEquals(1, objExternalApplicationNavigationPageClass.getRowCountInAllCampaignTable(),
						"Campaign: " + activityName + " is not displaying in 'All Campaigns' link");
				log.info("Activity:" + activityName + " is displaying after search");
			}

		} catch (Exception ex) {
			Assert.fail("Failed to verify if activity is displaying after search " + ex.getMessage());
		}
	}

	/**
	 * Method to get the column index of a particular column
	 * 
	 * @Author ubijux
	 * @Since Oct 19, 2018
	 * @param columnName The name of the column to which the index to be retrieved
	 */
	public int getColumnIndex(String columnName) {
		int index = 0;
		List<WebElement> columnHeader = objExternalApplicationNavigationPageClass.coMarketingAccouctColumnHeader();
		int headerCount = columnHeader.size();
		if (headerCount > 0) {
			for (int i = 0; i < headerCount; i++) {
				if ((columnHeader.get(i).getAttribute("title")).equals(columnName)) {
					index = i + 1;
					if (seleniumObj.isElementExists(objExternalApplicationNavigationPageClass.selectColumnHeader()))
						index++;
					break;
				}
			}
		} else {
			Assert.fail("No columns displayed");
		}
		return index;
	}

	/**
	 * Method to verify the Channel Program value displayed for all rows in the
	 * Co-Marketing Account view
	 * 
	 * @Author ubijux
	 * @Since Oct 19, 2018
	 * @param columnIndex index of Channel Program column
	 * @param columnValue to verify
	 */
	public boolean verifyChannelProgramValue(int columnIndex, String columnValue) {
		boolean isDiplayed = false;
		List<WebElement> rows = objExternalApplicationNavigationPageClass.coMarketingAccouctViewRows();
		int rowCount = rows.size();
		int count = 0;
		if (rowCount > 0) {
			for (int i = 1; i <= rowCount; i++) {
				String actualValue = objExternalApplicationNavigationPageClass
						.coMarketingAccouctViewRows(i, columnIndex).getAttribute("title").trim();
				if (actualValue.equals(columnValue)) {
					count++;
					log.info("verified column value : " + columnValue + " in row : " + i);
				}
			}
			if (count > 0) {
				log.info("Channel program column shows '" + columnValue + "'");
				isDiplayed = true;
			}
		} else {
			Assert.fail("No rows displayed ");
		}
		return isDiplayed;
	}

	/**
	 * Method to verify channel program name value displayed in the budget details
	 * page
	 * 
	 * @Author ubijux
	 * @Since 23-Oct-2018
	 * @param channelProgram The channel program value to be verified
	 */
	public void verifyChannelProgramValueDisplayedInDetailsPage(String channelProgram) {
		{
			String actualprogram = null;
			try {

				actualprogram = objExternalApplicationNavigationPageClass.getChannelProgram();
				Assert.assertEquals(actualprogram, channelProgram,
						"Mismatch with Program value actual: " + actualprogram + " but expected :" + channelProgram);
				log.info("Verified Program value: " + actualprogram);
			} catch (Exception ex) {
				Assert.fail("Mismatch with Program value actual: " + actualprogram + " but expected :" + channelProgram
						+ " " + ex.getMessage());
			}
		}

	}

	/**
	 * Method to click on a particular budget name
	 * 
	 * @Author ubijux
	 * @Since 23-Oct-2018
	 * @param budgetName The name of the budget to be clicked
	 */
	public void clickOnBudgetName(String budgetName) {

		try {
			objExternalApplicationNavigationPageClass.SearchBudget(budgetName);
			log.info("Entered budget name : " + budgetName + " in the search text box");

			Assert.assertTrue(
					sfcommonObj.CheckElementExists(objExternalApplicationNavigationPageClass.selectBudget(budgetName)),
					"Budget name  : " + budgetName + " does not exist");
			objExternalApplicationNavigationPageClass.SeleclBudget(budgetName);
			log.info("clicked On BudgetName :" + budgetName);

		} catch (Exception e) {
			Assert.fail("Failed to click on Budget name  : " + budgetName + " " + e.getMessage());
		}
	}

	

	/**
	 * Method to search for text
	 * 
	 * @Author vveeranx
	 * @Since Sep 14,2018
	 * @param searchText The text to be searched
	 */
	public void searchForText(String searchText) {
		try {
			log.info("Search for text: " + searchText);
			objExternalApplicationNavigationPageClass.enterTextInSearchTextBox(searchText);
		} catch (Exception ex) {
			Assert.fail("Not able to search in 'All Campaigns' tab " + ex.getMessage());
		}
	}

	/**
	 * Method to search for text
	 * 
	 * @Author vveeranx
	 * @Since Sep 14,2018
	 * @Param searchedText The text to be searched
	 */
	public void clickOnSearchResult(String searchedText) {
		try {
			objExternalApplicationNavigationPageClass.clickSearchResult(searchedText);
			log.info("Clicked on: " + searchedText);
		} catch (Exception ex) {
			Assert.fail("Not able to search in 'All Campaigns' tab " + ex.getMessage());
		}
	}

	/**
	 * Method to get account balance sum field corresponding to each account in
	 * 'Account Balances' Report
	 * 
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @param budgetName The budget name to which the account to be retrieved
	 * @return String[]
	 */
	public String[] getAccountAndCorrespondingAccountBalanceSumInAccountBalancesReport(String budgetName) {
		String[] accountAndAmount = null;
		try {
			accountAndAmount = objExternalApplicationNavigationPageClass
					.getAccountAndCorrespondingAccountBalanceSumInAccountBalancesReport(budgetName);
		} catch (Exception e) {
			Assert.fail("Failed to validate account balance sum field in 'Account Balances' Report " + e.getMessage());
		}

		return accountAndAmount;
	}

	/**
	 * Method to click on budget name in 'Account Balances' Report
	 * 
	 * @Author vveeranx
	 * @Since Nov 19 ,2018
	 * @Param budgetName the name of the budget to be clicked
	 */
	public void clickOnBudgetNameInAccountBalanceReport(String budgetName) {
		try {
			objExternalApplicationNavigationPageClass.clickOnBudgetNameInAccountBalanceReport(budgetName);
			log.info("Clicked on: " + budgetName);
		} catch (Exception ex) {
			Assert.fail("Not able to click on budget name in 'Account Balances' Report " + ex.getMessage());
		}
	}

	/**
	 * Method to click on related tab
	 * 
	 * @Author vveeranx
	 * @Since Nov 19 ,2018
	 */
	public void clickOnRelatedTab() {
		try {
			objExternalApplicationNavigationPageClass.clickOnRelatedTab();
			log.info("Clicked on related tab");
		} catch (Exception ex) {
			Assert.fail("Not able to click on related tab " + ex.getMessage());
		}
	}

	/**
	 * Method to verify the budget name corresponding to parent account displayed in
	 * the account balance table
	 * 
	 * @Author vveeranx
	 * @Since Nov 21, 2018
	 * @param budgetName The budget name to be verified
	 * @param account    The account name corresponding to budget name
	 */
	public void verifyBudgetNameCorrespondingToParentAccountDisplayedInAccountBalanceTable(String budgetName,
			String account) {
		try {
			Boolean isDisplayed = objExternalApplicationNavigationPageClass
					.checkIfBudgetNameCorrespondingToAccountDisplayingInAccountBalanceReport(budgetName, account);
			Assert.assertTrue(isDisplayed, "Budget:" + budgetName + " is not displayed in the Account Balance Report");
			log.info("Budget: " + budgetName + " is displayed in the Account Balance Report");
		} catch (Exception e) {
			Assert.fail("Budget:" + budgetName + " is not displayed in the Account Balance Report " + e.getMessage());
		}
	}

	/**
	 * Method to validate users mapped with an account under 'Account Users Report'
	 * 
	 * @Author ubijux
	 * @Since Nov 21, 2018
	 * @param accountName The account name to be validated
	 */
	public void validateUserMappedWithAnAccountInAccountUsersReport(String accountName) {
		List<String> actualUsersList = null;
		List<String> expectedUsersList = null;
		try {
			actualUsersList = getUserListForAccountInAccountUsersReport(accountName);
			clickOnAccountNameAccountUsersReport(accountName);
			clickOnRelatedTab();
			sfcommonObj.waitTillAllXHRCallsComplete();
			expectedUsersList = getUserListInAccountPage();
			Collections.sort(actualUsersList);
			Collections.sort(expectedUsersList);
			Assert.assertEquals(actualUsersList, expectedUsersList,
					"Mismatch with users mapped to account: " + accountName);
			log.info("Validated users mapped with account: " + accountName);
		} catch (Exception e) {
			Assert.fail(
					"Failed to validate users mapped with an account under 'Account Users Report' " + e.getMessage());
		}
	}

	/**
	 * Method to get user list based on account name in 'Account Users Report'
	 * 
	 * @Author ubijux
	 * @Since Nov 21, 2018
	 * @param accountName The account name to which user list to be retrieved
	 * @return List<String>
	 */
	public List<String> getUserListForAccountInAccountUsersReport(String accountName) {

		seleniumObj.scrollToElement(objExternalApplicationNavigationPageClass.headerElement(accountName));
		String headerID = objExternalApplicationNavigationPageClass.headerElement(accountName).getAttribute("id") + " ";
		List<WebElement> elements = objExternalApplicationNavigationPageClass.userList(headerID);
		List<String> userList = new ArrayList<>();
		log.info("Users for " + accountName + " in 'Account Users Report':\n");
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getText();
			userList.add(text);
			log.info(userList.get(i) + "\n");
		}

		return userList;
	}

	/**
	 * Method to click on account name in 'Account Users Report'
	 * 
	 * @Author ubijux
	 * @Since Nov 21, 2018
	 * @param accountName The account name to be clicked
	 */
	public void clickOnAccountNameAccountUsersReport(String accountName) {

		objExternalApplicationNavigationPageClass.accountName(accountName).click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * Method to get user list based on account name in 'Account' page
	 * 
	 * @Author ubijux
	 * @Since Nov 21, 2018
	 * @return List<String>
	 */
	public List<String> getUserListInAccountPage() {
		objExternalApplicationNavigationPageClass.accountUserLink().click();
		sfcommonObj.waitTillAllXHRCallsComplete();
		List<WebElement> accountWebelements = objExternalApplicationNavigationPageClass.accountList();
		List<String> userList = new ArrayList<>();
		log.info("Users in 'Account' page:\n");
		for (int i = 0; i < accountWebelements.size(); i++) {
			userList.add(accountWebelements.get(i).getText());
			log.info(userList.get(i) + "\n");
		}

		return userList;
	}

	/**
	 * Method to get header text for full name
	 * 
	 * @Author ubijux
	 * @Since Nov 21, 2018
	 * @return List<String>
	 */
	public List<String> getHeaderTextForAccountUsersReportTable() {
		List<String> headerText = new ArrayList<>();

		String text = "";
		WebElement elementforFullName = objExternalApplicationNavigationPageClass.fullName();
		text = elementforFullName.getText();
		headerText.add(text);
		log.info("Header name: " + text);

		WebElement elementForAccount = objExternalApplicationNavigationPageClass.accountName();
		text = elementForAccount.getText();
		headerText.add(text);
		log.info("Header name: " + text);

		return headerText;
	}

	/**
	 * Method to get account field in 'Account Balances' Report
	 * 
	 * @Author csingamx
	 * @Since Nov 26, 2018
	 * @param budgetName The budget name to which account to be retrieved
	 * @return String[]
	 */
	public String[] getAccountInAccountBalancesReport(String budgetName) {
		String[] account = null;
		try {
			account = objExternalApplicationNavigationPageClass.getAccountInAccountBalancesReport(budgetName);
		} catch (Exception e) {
			Assert.fail("Failed to validate account field in 'Account Balances' Report " + e.getMessage());
		}

		return account;
	}

	/**
	 * Method to click on new button in external portal
	 * 
	 * @Author csingamx
	 * @Since Dec 14, 2018
	 */
	public void clickNewButton_ext() {
		try {
			seleniumObj.waitForElement(objExternalApplicationNavigationPageClass.actvtynewButton, 5, 10);
			Assert.assertTrue(objExternalApplicationNavigationPageClass.checkElementEnabled(
					objExternalApplicationNavigationPageClass.actvtynewButton), "'New' button is not enabled");
			objExternalApplicationNavigationPageClass.clickOnNewButton_ext();
			log.info("Clicked on new button");
		} catch (Exception ex) {
			Assert.fail("Not able to click new button for creating claims " + ex.getMessage());
		}
	}

	/**
	 * To Search Account Name from Global Search
	 * 
	 * @Author csingamx
	 * @Since Dec 18, 2018
	 * @param searchText       Search activityName
	 * @param searchResultText Search activityName
	 * @param searchType       coMarketing Activity
	 * @param searchIcon       search icon near the global search text box
	 */
	public void globalUISearchTextExternal(String searchText, String searchResultText, String searchType,
			String searchIcon) {
		sfcommonObj.waitTillLightningPageLoadComplete();
		if (searchIcon.equals("false")) {
			objExternalApplicationNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			objExternalApplicationNavigationPageClass.clickOnSearchResultSearchIcon();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} else if (searchIcon.equals("true")) {
			objExternalApplicationNavigationPageClass.setValueForGlobalSearchTextbox(searchText);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objExternalApplicationNavigationPageClass.clickOnSearchResult(searchResultText, searchType);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	}

	/**
	 * Method to verify Account List fields Displayed
	 * 
	 * @Author mohseenx
	 * @Since 11-Jan-2019
	 * @param accountListFieldsName Name of the field
	 */
	public void verifyAccountListFieldDisplayed(String accountListFieldsName) {

		try {
			sfcommonObj.waitTillAllXHRCallsComplete();
			if (AccountEnum.AccountListFieldsName.ACCOUNT_NAME.getDescription().equals(accountListFieldsName)) {
				verifyAccountNameIsDisplayed();
			}
			if (AccountEnum.AccountListFieldsName.CHANNEL_PROGRAM.getDescription().equals(accountListFieldsName)) {
				verifyChannelProgramIsDisplayed();
			}
			if (AccountEnum.AccountListFieldsName.PARENT_ACCOUNT.getDescription().equals(accountListFieldsName)) {
				verifyParentAccountIsDisplayed();
			}
			if (AccountEnum.AccountListFieldsName.PARENT_FUND_ACCOUNT.getDescription().equals(accountListFieldsName)) {
				verifyParentFundAccountIsDisplayed();
			}
			if (AccountEnum.AccountListFieldsName.ACCOUNT_RECORD_TYPE.getDescription().equals(accountListFieldsName)) {
				verifyAccountRecordTypeIsDisplayed();
			}
			if (AccountEnum.AccountListFieldsName.ALL.getDescription().equals(accountListFieldsName)) {
				verifyAccountNameIsDisplayed();
				verifyParentFundAccountIsDisplayed();
				verifyChannelProgramIsDisplayed();
				verifyParentAccountIsDisplayed();
				verifyAccountRecordTypeIsDisplayed();
			}
			log.info("Succussfully Verified Account list " + accountListFieldsName + " field is displayed");
		} catch (Exception e) {
			Assert.fail(
					"Unable to Verify Account list " + accountListFieldsName + " field is displayed " + e.getMessage());
		}
	}
}
