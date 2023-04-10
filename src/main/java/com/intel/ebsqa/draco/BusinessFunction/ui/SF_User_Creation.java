/*
* Copyright (c) 2021 EBS Automation Team. All rights reserved.
*/
/*
* Copyright (c) 2021 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.BusinessFunction.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.PageClasses.SF_User_CreationPageClass;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.intel.ebsqa.helperclass.StringUtils;

/**
 * @Description
 * @Author csingamx
 * @Since 06-Nov-2021
 */

public class SF_User_Creation extends TestBase {
	SF_User_CreationPageClass objSF_User_CreationPageClass = null;

	public SF_User_Creation() {
		objSF_User_CreationPageClass = new SF_User_CreationPageClass();
	}

	/**
	 * @Description Method to click on Set up icon
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void clickOnSetup() throws TimeOutException {
		try {

			objSF_User_CreationPageClass.clickOnSetup();
			log.info("Succussfully clicked on Set up icon");
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on Set up icon. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to click on Home Tab
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void clickOnHomeTab() throws TimeOutException {
		try {

			objSF_User_CreationPageClass.clickOnHomeTab();
			log.info("Succussfully clicked on Home Tab");
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on Home Tab " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to click on Set up Tool icon
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void clickOnSetupTool() throws TimeOutException {
		try {

			objSF_User_CreationPageClass.clickOnSetupTool();
			sfcommonObj.switchToNewWindow();
			log.info("Succussfully clicked on Set up Tool icon");
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on Set up Tool icon " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to searchAndSelectSetupObject
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void searchAndSelectSetupObject(String objectName) throws TimeOutException {
		try {
			sfcommonObj.switchToDefaultContent();
			seleniumObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objSF_User_CreationPageClass.searchInQuickFindBox(objectName);
			objSF_User_CreationPageClass.selectSearchValue(objectName);
			log.info("Succussfully searchAndSelectSetupObject");
		}

		catch (Exception ex) {
			Assert.fail("Not able to searchAndSelectSetupObject. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to verifySetuoObjectPage
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void verifySetupObjectPage(String object) throws TimeOutException {
		try {
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.returnObjHeader(object)));
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully verifySetuoObjectPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifySetuoObjectPage. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to click on verifyPublicGroupName
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public void verifyPublicGroupName(String grpName) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.verifyPublicGroupName(grpName);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully verifyPublicGroupName");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyPublicGroupName. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to returnIframe
	 * @Author kumark8x
	 * @Since 06-May-2021
	 * @throws TimeOutException
	 */
	public WebElement returnIframe() throws TimeOutException {
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();	
		seleniumObj.waitForElement(objSF_User_CreationPageClass.objectIFrame, 5, 5);
		return objSF_User_CreationPageClass.objectIFrame;

	}

	/**
	 * 
	 * Method to enter user first name
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param claimName to enter value for claim name
	 * @throws TimeOutException
	 */
	public void enterUserFirstName(String firstName) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterUserFirstName(firstName);
			log.info("Entered first name: " + firstName);
		} catch (Exception e) {
			Assert.fail("Not able to enter first name " + e.getMessage());
		}
	}

	/**
	 * 
	 * Method to enter user last name
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param claimName to enter value for claim name
	 * @throws TimeOutException
	 */
	public void enterUserLastName(String lastName) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterUserLastName(lastName);
			log.info("Entered user last name: " + lastName);
		} catch (Exception e) {
			Assert.fail("Not able to enter user last name " + e.getMessage());
		}
	}

	/**
	 * Method to select user View
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectUsersView(String view) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.selectUsersView, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.selectUsersView),
					"'User view' is not enabled");
			objSF_User_CreationPageClass.selectUsersView(view);
			log.info("Selected User view: " + view);
		} catch (Exception ex) {
			Assert.fail("Not able to select User view " + ex.getMessage());
		}
	}

	/**
	 * Method to select user role
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectUserRole(String role) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.roleUser, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.roleUser),
					"'roleUser' is not enabled");
			objSF_User_CreationPageClass.selectUserLicense(role);
			log.info("Selected user role User: " + role);
		} catch (Exception ex) {
			Assert.fail("Not able to select role User " + ex.getMessage());
		}
	}

	/**
	 * Method to select user License
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectUserLicense(String license) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.userLicense, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.userLicense),
					"'user License' is not enabled");
			objSF_User_CreationPageClass.selectUserLicense(license);
			log.info("Selected user License: " + license);
		} catch (Exception ex) {
			Assert.fail("Not able to select user License " + ex.getMessage());
		}
	}

	/**
	 * Method to select user Profile
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectUserProfile(String profile) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.profileUser, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.profileUser),
					"'user Profile' is not enabled");
			objSF_User_CreationPageClass.selectUserProfile(profile);
			log.info("Selected user profile: " + profile);
		} catch (Exception ex) {
			Assert.fail("Not able to select user profile " + ex.getMessage());
		}
	}

	/**
	 *
	 * Method to click on Checkbox to generate Password
	 *
	 * @Author sdhasadx
	 * @Since March 3, 2021
	 * @throws TimeOutException
	 */
	public void clickOnPasswordCheckboxInUserCreatePage() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.passwordCheckbox, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.passwordCheckbox),
					"Checkbox to generate Password is not enabled");
			objSF_User_CreationPageClass.clickOnPasswordCheckboxInUserCreatePage();
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on Checkbox to generate Password");
		} catch (Exception e) {
			Assert.fail("Not able click on Checkbox to generate Password" + e.getMessage());
		}
	}

	/**
	 *
	 * Method to click on new user
	 *
	 * @Author sdhasadx
	 * @Since March 3, 2021
	 * @throws TimeOutException
	 */
	public void clickOnNewUserInUserCreatePage() throws TimeOutException {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.newUser, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.newUser),
					"new user is not enabled");
			objSF_User_CreationPageClass.clickOnNewUser();
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on new user");
		} catch (Exception e) {
			Assert.fail("Not able click on new user" + e.getMessage());
		}
	}

	/**
	 *
	 * Method to enter Alias name
	 *
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Alias Name to enter value for Alias name
	 * @throws TimeOutException
	 */
	public void enterAliasName(String aliasname) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterAliasName(aliasname);
			log.info("Entered Alias name: " + aliasname);
		} catch (Exception e) {
			Assert.fail("Not able to enter Alias name " + e.getMessage());
		}
	}

	/**
	 *
	 * Method to enter Email Address
	 *
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Email Address to enter value for Email Address
	 * @throws TimeOutException
	 */
	public void enterEmailAddress(String email) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterEmailAddress(email);
			log.info("Entered Email Address: " + email);
		} catch (Exception e) {
			Assert.fail("Not able to enter Email Address " + e.getMessage());
		}
	}

	/**
	 *
	 * Method to enter Username
	 *
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Username to enter value for Username
	 * @throws TimeOutException
	 */
	public void enterUsername(String username) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterUsername(username);
			log.info("Entered Username: " + username);
		} catch (Exception e) {
			Assert.fail("Not able to enter Username " + e.getMessage());
		}
	}

	/**
	 *
	 * Method to enter Nickname
	 *
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Nickname to enter value for Nickname
	 * @throws TimeOutException
	 */
	public void enterNickname(String nickname) throws TimeOutException {
		try {
			objSF_User_CreationPageClass.enterNickname(nickname);
			log.info("Entered Nickname: " + nickname);
		} catch (Exception e) {
			Assert.fail("Not able to enter Nickname " + e.getMessage());
		}
	}

	/**
	 * Method to click on save button
	 *
	 * @Author sdhasadx
	 * @Since May 14 2021
	 */
	public void clickonSaveUserButton() {
		try {
			objSF_User_CreationPageClass.clickOnSaveUserButton();
			log.info("Clicked on save button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on save button" + ex.getMessage());
		}
	}

	/**
	 * Method to create new user
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @param objClaimDetails
	 *            to enter values for creating a claim
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void createSFUser(SF_User_CreationDetails objSF_User_CreationDetails) {

		try {
			this.searchAndSelectSetupObject(CommonEnum.SFSetupObjectNames.USERS.getDescription());
			sfcommonObj.switchToFrame(this.returnIframe());
			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getUsersView())) {
				this.selectUsersView(objSF_User_CreationDetails.getUsersView());
			}
			this.clickOnNewUserInUserCreatePage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.switchToFrame(this.returnIframe());
			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getFirstName())) {
				this.enterUserFirstName(objSF_User_CreationDetails.getFirstName());
			}
			/*
			 * if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.
			 * getUserRole())) {
			 * this.selectUserRole(objSF_User_CreationDetails.getUserRole()); }
			 */

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getLastName())) {
				this.enterUserLastName(objSF_User_CreationDetails.getLastName());
			}

			/*
			 * if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.
			 * getAliasName())) {
			 * this.enterAliasName(objSF_User_CreationDetails.getAliasName()); }
			 */

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getEmail())) {
				this.enterEmailAddress(objSF_User_CreationDetails.getEmail());
			}

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getUserName())) {
				this.enterUsername(objSF_User_CreationDetails.getUserName());
			}

			
		    if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getNickName()))
			  {
			  this.enterNickname(objSF_User_CreationDetails.getNickName()); 
			  }
			 

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getUserLicense())) {
				this.selectUserLicense(objSF_User_CreationDetails.getUserLicense());
			}

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getProfile())) {
				this.selectUserProfile(objSF_User_CreationDetails.getProfile());
			}

			if (objSF_User_CreationDetails.checkbox.equalsIgnoreCase("FALSE")) {
				if (sfcommonObj.checkElementExists(objSF_User_CreationPageClass.passwordCheckbox)) {
					this.clickOnPasswordCheckboxInUserCreatePage();
				}
			}

			this.clickonSaveUserButton();

		} catch (Exception ex) {
			Assert.fail("Not able to click new button for creating new user " + ex.getMessage());
		}
	}

	/**
	 * Method to click on EditPermissionSets button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickonEditPermissionSetsButton() {
		try {
			objSF_User_CreationPageClass.clickOnEditPermissionSetsBtn();
			log.info("Clicked on EditPermissionSets button");
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on EditPermissionSets button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on RightArrowAddBtn button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnRightArrowAddBtn() {
		try {
			objSF_User_CreationPageClass.clickOnRightArrowAddBtn();
			log.info("Clicked on RightArrowAddBtn button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on RightArrowAddBtn button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on savePermissionSetsBtn button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnsavePermissionSetsBtn() {
		try {
			objSF_User_CreationPageClass.clickOnsavePermissionSetsBtn();
			log.info("Clicked on savePermissionSetsBtn button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on savePermissionSetsBtn button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on navigateToPageUsingAlphabets button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void navigateToPageUsingAlphabets(char alp) {
		try {
			objSF_User_CreationPageClass.navigateToPageUsingAlphabets(alp);
			sfcommonObj.switchToFrame(this.returnIframe());
			log.info("Clicked on navigateToPageUsingAlphabets button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on navigateToPageUsingAlphabets button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on NextPageBtn button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickNextPageBtn() {
		try {
			objSF_User_CreationPageClass.clickNextPageBtn();
			sfcommonObj.switchToFrame(this.returnIframe());
			log.info("Clicked on NextPageBtn button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on NextPageBtn button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on PublicGroupName button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnPublicGroupName(String publicgrp) {
		try {
			objSF_User_CreationPageClass.clickOnPublicGroupName(publicgrp);
			log.info("Clicked on PublicGroupName button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on PublicGroupName button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on EditOnPubicGroup button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickEditOnPubicGroup() {
		try {
			objSF_User_CreationPageClass.clickEditOnPubicGroup();
			log.info("Clicked on EditOnPubicGroup button");
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on EditOnPubicGroup button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on FindInPublicGroup button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnFindInPublicGroup() {
		try {
			objSF_User_CreationPageClass.clickOnFindInPublicGroup();
			log.info("Clicked on FindInPublicGroup button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on FindInPublicGroup button" + ex.getMessage());
		}
	}

	/**
	 * Method to click on SaveInPublicGroup button
	 *
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnSaveInPublicGroup() {
		try {
			objSF_User_CreationPageClass.clickOnSaveInPublicGroup();
			log.info("Clicked on SaveInPublicGroup button");
			sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to click on SaveInPublicGroup button" + ex.getMessage());
		}
	}

	/**
	 * Method to select user AvailablePermissionSet
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectAvailablePermissionSet(String permissionSet) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.selectAvailablePermissionSets, 5, 5);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objSF_User_CreationPageClass.selectAvailablePermissionSets),
					"'User view' is not enabled");
			objSF_User_CreationPageClass.selectAvailablePermissionSet(permissionSet);
			log.info("Selected User AvailablePermissionSet: " + permissionSet);
		} catch (Exception ex) {
			Assert.fail("Not able to select User AvailablePermissionSet " + ex.getMessage());
		}
	}

	/**
	 * Method to select user PublicGroupView
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectPublicGroupView(String publicGrpView) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.selectPublicGroupView, 5, 5);
			Assert.assertTrue(sfcommonObj.checkElementExists(objSF_User_CreationPageClass.selectPublicGroupView),
					"'User view' is not enabled");
			objSF_User_CreationPageClass.selectPublicGroupView(publicGrpView);
			log.info("Selected User PublicGroupView: " + publicGrpView);
		} catch (Exception ex) {
			Assert.fail("Not able to select User PublicGroupView " + ex.getMessage());
		}
	}

	/**
	 * Method to select user SearchSharingInPublicGroup
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void SelectSearchSharingInPublicGroup(String searchSharing) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.selectSearchSharingInPublicGroup, 5, 5);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objSF_User_CreationPageClass.selectSearchSharingInPublicGroup),
					"'SearchSharingInPublicGroup' is not enabled");
			objSF_User_CreationPageClass.SelectSearchSharingInPublicGroup(searchSharing);
			log.info("Selected User SearchSharingInPublicGroup: " + searchSharing);
		} catch (Exception ex) {
			Assert.fail("Not able to select User SearchSharingInPublicGroup " + ex.getMessage());
		}
	}

	/**
	 * Method to searchValueInFindInPublicGroup
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void searchValueAndFindInPublicGroup(String value) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.searchValueInFindInPublicGroup, 5, 5);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objSF_User_CreationPageClass.searchValueInFindInPublicGroup),
					"'searchValueInFindInPublicGroup' is not enabled");
			objSF_User_CreationPageClass.searchValueInFindInPublicGroup(value);
			this.clickOnFindInPublicGroup();
			log.info("searched ValueInFindInPublicGroup: " + value);
		} catch (Exception ex) {
			Assert.fail("Not able to select User searchValueInFindInPublicGroup " + ex.getMessage());
		}
	}

	/**
	 * Method to select user AvailableMembersInPublicGroup
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType value of record type
	 */
	public void selectAvailableMembersInPublicGroup(String availableMembers) {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.selectAvailableMembersInPublicGroup, 5, 5);
			Assert.assertTrue(
					sfcommonObj.checkElementExists(objSF_User_CreationPageClass.selectAvailableMembersInPublicGroup),
					"'AvailableMembersInPublicGroup' is not enabled");
			objSF_User_CreationPageClass.selectAvailableMembersInPublicGroup(availableMembers);
			log.info("Selected User AvailableMembersInPublicGroup: " + availableMembers);
		} catch (Exception ex) {
			Assert.fail("Not able to select User AvailableMembersInPublicGroup " + ex.getMessage());
		}
	}

	/**
	 * Method to addPermissionSetsTo_SFUser
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void addPermissionSetsTo_SFUser(SF_User_CreationDetails objSF_User_CreationDetails) {

		try {
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.switchToFrame(this.returnIframe());
			sfcommonObj.waitTillAllXHRCallsComplete();
			this.clickonEditPermissionSetsButton();
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.switchToFrame(this.returnIframe());
			List<String> enabledPermissionSets = new ArrayList<>();
			List<WebElement> enabledPermSetsXpaths = objSF_User_CreationPageClass.getEnabledPermissionSets();
			for (int i = 0; i < enabledPermSetsXpaths.size(); i++) {
				WebElement ele = enabledPermSetsXpaths.get(i);
				enabledPermissionSets.add(ele.getText());
			}
			for (String perSet : objSF_User_CreationDetails.getPermissionSets()) {
				if(enabledPermissionSets.contains(perSet))
				{
					continue;
				}
				else
				{
					this.selectAvailablePermissionSet(perSet);
					this.clickOnRightArrowAddBtn();
				}
				
			}

			this.clickOnsavePermissionSetsBtn();

		} catch (Exception ex) {
			Assert.fail("Not able to add PermissionSetsTo_SFUser " + ex.getMessage());
		}
	}

	/**
	 * Method to addSFUserToPublicGroups
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void addSFUserToPublicGroups(SF_User_CreationDetails objSF_User_CreationDetails) {

		try {
			for (String publicGroup : objSF_User_CreationDetails.getPublicGroups()) {
				if(publicGroup.equals(null))
				{
					break;
				}
				this.searchAndSelectSetupObject(CommonEnum.SFSetupObjectNames.PUBLIC_GROUPS.getDescription());
				sfcommonObj.waitTillAllXHRCallsComplete();
				sfcommonObj.switchToFrame(this.returnIframe());
				this.selectPublicGroupView(objSF_User_CreationDetails.getPublicGroupView());
				this.navigateToPageUsingAlphabets(publicGroup.charAt(0));
				List<String> publicGroupsOnPage = new ArrayList<>();

				do {
					List<WebElement> records = objSF_User_CreationPageClass.getPublicGroupsNameOnPage();
					publicGroupsOnPage.clear();
					for (int i = 0; i < records.size(); i++) {
						WebElement ele = records.get(i);
						publicGroupsOnPage.add(ele.getText());
					}
					if (publicGroupsOnPage.contains(publicGroup)) {
						break;
					} else {
						this.clickNextPageBtn();
					}

				} while (!publicGroupsOnPage.contains(publicGroup));

				this.clickOnPublicGroupName(publicGroup);
				sfcommonObj.switchToFrame(this.returnIframe());
				this.clickEditOnPubicGroup();
				sfcommonObj.switchToFrame(this.returnIframe());
				this.SelectSearchSharingInPublicGroup(objSF_User_CreationDetails.getSearchSharingInPublicGroup());
				String searchUser = objSF_User_CreationDetails.getFirstName() + " "
						+ objSF_User_CreationDetails.getLastName();
				String selectUser = "User: " + searchUser;
				this.searchValueAndFindInPublicGroup(searchUser);
				this.selectAvailableMembersInPublicGroup(selectUser);
				this.clickOnRightArrowAddBtn();
				this.clickOnSaveInPublicGroup();
			}

		} catch (Exception ex) {
			Assert.fail("Not able to add SFUserToPublicGroup " + ex.getMessage());
		}

	}

	/**
	 * Method to click on contact record type
	 * 
	 * @Author kumark8x
	 * @Since 21-May-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void selectContactRecordType(String contact) throws TimeOutException {
		try {
			sfcommonObj.waitTillAllXHRCallsComplete();
			objSF_User_CreationPageClass.selectRecordTypeContact(contact);

			log.info("Clicked on contact record type button");

		} catch (Exception ex) {
			Assert.fail("Not able to click on contact record type button " + ex.getMessage());
		}
	}

	/**
	 * Method to click on contact Salutation
	 * 
	 * @Author kumark8x
	 * @Since 21-May-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickonContactSaluatation() throws TimeOutException {
		try {
			objSF_User_CreationPageClass.clickOnSalutation();

			log.info("Clicked on contact Salutation");

		} catch (Exception ex) {
			Assert.fail("Not able to click on contact Salutation" + ex.getMessage());
		}
	}

	/**
	 * Method to select salutation for contact
	 * 
	 * @Author gmathavx
	 * @Since 24-Sep-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void selectSalutationForContact(String salutation) throws TimeOutException {
		try {
			this.clickonContactSaluatation();
			objSF_User_CreationPageClass.selectSalutation(salutation);
			log.info("Selected salutation for contact: " + salutation);
		} catch (Exception ex) {
			Assert.fail("Not able to select salutation for contact " + ex.getMessage());
		}
	}

	/**
	 * Method to enter first name contact
	 * 
	 * @Author gmathavx
	 * @Since 24-Sep-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterFirstNameContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.firstNameContact, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objSF_User_CreationPageClass.firstNameContact),
					"first name contact does not exist in the UI");
			objSF_User_CreationPageClass.enterFirstNameContact(name);
			log.info("Entered first name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter first name contact " + ex.getMessage());
		}
	}

	/**
	 * Method to enter last name contact
	 * 
	 * @Author gmathavx
	 * @Since 24-Sep-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterLastNameContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.lastNameContact, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objSF_User_CreationPageClass.lastNameContact),
					"last name contact does not exist in the UI");
			objSF_User_CreationPageClass.enterLastNameContact(name);
			log.info("Entered last name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter last name contact " + ex.getMessage());
		}
	}

	/**
	 * Method to enter email contact
	 * 
	 * @Author gmathavx
	 * @Since 24-Sep-2018
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterEmailContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objSF_User_CreationPageClass.email, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objSF_User_CreationPageClass.email),
					"email contact does not exist in the UI");
			objSF_User_CreationPageClass.enterEmailContact(name);
			log.info("Entered email contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter email contact " + ex.getMessage());
		}
	}

	/**
	 *
	 * Method to navigate to 'Related' tab
	 *
	 * @Author sdhasadx
	 * @Since May 17, 2021
	 */
	public void navigateToRelatedTab() {
		try {
			objSF_User_CreationPageClass.clickOnAccountRelatedTab();
			log.info("Clicked on Related tab");
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Failed to navigate to 'Related' tab " + ex.getMessage());
		}
	}

	/**
	 * Method for click on new button To Create Contact
	 * 
	 * @Author sdhasadx
	 * @Since May 17, 2021
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickOnNewButtonForContact() throws TimeOutException {
		try {
			objSF_User_CreationPageClass.clickOnNewButtonForContact();
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on new button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on new button " + ex.getMessage());
		}
	}

	/**
	 * Method for click on next button To Create Contact
	 * 
	 * @Author sdhasadx
	 * @Since May 17, 2021
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickOnNextButtonForContact() throws TimeOutException {
		try {
			objSF_User_CreationPageClass.clickOnNextButtonForContact();
			sfcommonObj.waitTillAllXHRCallsComplete();
			log.info("Clicked on new button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on new button " + ex.getMessage());
		}
	}

	/**
	 * Method for click on Account More Actions Button
	 * 
	 * @Author sdhasadx
	 * @Since May 17, 2021
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickOnAccountMoreActionsButton() throws TimeOutException {
		try {
			objSF_User_CreationPageClass.clickOnAccountMoreActionsButton();
			log.info("Clicked on more actions button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on more actions button " + ex.getMessage());
		}
	}

	/**
	 * Method to MethodToEnablePartnerUserAccount
	 * 
	 * @Author sdhasadx
	 * @Since May 17, 2021
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void MethodToEnablePartnerUserAccount() {
		try {
			this.clickOnAccountMoreActionsButton();
			seleniumObj.waitForElement(objSF_User_CreationPageClass.EnablePartnerUser, 5, 10);
			objSF_User_CreationPageClass.clickOnEnablePartnerUserButton();
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception ex) {
			Assert.fail("Not able to Click On EnablePartnerUser Button " + ex.getMessage());
		}
	}

	/**
	 * Method for click on save Button
	 * 
	 * @Author njunghax
	 * @Since May 17, 2021
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickOnSaveButtonContact() throws TimeOutException {
		try {
			objSF_User_CreationPageClass.clickOnSaveButtonForContact();
			log.info("Clicked on save button");
		} catch (Exception ex) {
			Assert.fail("Not able to click on save button " + ex.getMessage());
		}
	}

	/**
	 * Method to create new Escalation
	 * 
	 * @Author sdhasadx
	 * @Since 3-May-2021
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void createNewContact(SF_User_CreationDetails objSF_User_CreationDetails ) throws TimeOutException {
		try {
			this.navigateToRelatedTab();
			this.clickOnNewButtonForContact();

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getContactRecordType())) {
				this.selectContactRecordType(objSF_User_CreationDetails.getContactRecordType());
			}
			this.clickOnNextButtonForContact();

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getSalutation())) {
				this.selectSalutationForContact(objSF_User_CreationDetails.getSalutation());
			}

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getFirstName())) {
				this.enterFirstNameContact(objSF_User_CreationDetails.getFirstName());
			}

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getLastName())) {
				this.enterLastNameContact(objSF_User_CreationDetails.getLastName());
			}

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getEmail())) {
				this.enterEmailContact(objSF_User_CreationDetails.getEmail());
			}

			this.clickOnSaveButtonContact();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" new contact is created");
		} catch (Exception ex) {
			Assert.fail("new contact is not created " + ex.getMessage());
		}
	}
	
	/**
	 * Method to create new user
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void Update_SFUserDetails_External(SF_User_CreationDetails objSF_User_CreationDetails) {

		try {
			seleniumObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			this.MethodToEnablePartnerUserAccount();
			sfcommonObj.switchToFrame(this.returnIframe());			

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getUserName())) {
				this.enterUsername(objSF_User_CreationDetails.getUserName());
			}

			

			if (!StringUtils.isNullOrBlank(objSF_User_CreationDetails.getProfile())) {
				this.selectUserProfile(objSF_User_CreationDetails.getProfile());
			}

			if (objSF_User_CreationDetails.checkbox.equalsIgnoreCase("FALSE")) {
				if (sfcommonObj.checkElementExists(objSF_User_CreationPageClass.passwordCheckbox)) {
					this.clickOnPasswordCheckboxInUserCreatePage();
				}
			}

			this.clickonSaveUserButton();
		} catch (Exception ex) {
			Assert.fail("Not able to update for creating new user External" + ex.getMessage());
		}
	}
	
	/**
	 * Method to addSFUserToPublicGroups
	 * 
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @throws IOException
	 *             Exception thrown when there has been an Input/Output (usually
	 *             when working with files) error.
	 */
	public void addSFUserToPublicGroupsExternal(SF_User_CreationDetails objSF_User_CreationDetails) {

		try {
			for (String publicGroup : objSF_User_CreationDetails.getPublicGroups()) {
				if(publicGroup.equals(null))
				{
					break;
				}
				this.searchAndSelectSetupObject(CommonEnum.SFSetupObjectNames.PUBLIC_GROUPS.getDescription());
				sfcommonObj.waitTillAllXHRCallsComplete();
				sfcommonObj.switchToFrame(this.returnIframe());
				this.selectPublicGroupView(objSF_User_CreationDetails.getPublicGroupView());
				this.navigateToPageUsingAlphabets(publicGroup.charAt(0));
				List<String> publicGroupsOnPage = new ArrayList<>();

				do {
					List<WebElement> records = objSF_User_CreationPageClass.getPublicGroupsNameOnPage();
					publicGroupsOnPage.clear();
					for (int i = 0; i < records.size(); i++) {
						WebElement ele = records.get(i);
						publicGroupsOnPage.add(ele.getText());
					}
					if (publicGroupsOnPage.contains(publicGroup)) {
						break;
					} else {
						this.clickNextPageBtn();
					}

				} while (!publicGroupsOnPage.contains(publicGroup));

				this.clickOnPublicGroupName(publicGroup);
				sfcommonObj.switchToFrame(this.returnIframe());
				this.clickEditOnPubicGroup();
				sfcommonObj.switchToFrame(this.returnIframe());
				this.SelectSearchSharingInPublicGroup(objSF_User_CreationDetails.getSearchSharingInPublicGroup());
				String searchUser = objSF_User_CreationDetails.getFirstName() + " "
						+ objSF_User_CreationDetails.getLastName();
				String selectUser = "Partner User: " + searchUser;
				this.searchValueAndFindInPublicGroup(searchUser);
				this.selectAvailableMembersInPublicGroup(selectUser);
				this.clickOnRightArrowAddBtn();
				this.clickOnSaveInPublicGroup();
			}

		} catch (Exception ex) {
			Assert.fail("Not able to add SFUserToPublicGroup " + ex.getMessage());
		}

	}

}
