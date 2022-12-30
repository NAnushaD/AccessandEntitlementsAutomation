/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.Testscripts.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intel.ebsqa.draco.BusinessFunction.ui.ExternalApplicationNavigation;
import com.intel.ebsqa.draco.BusinessFunction.ui.AdminFunctions;
import com.intel.ebsqa.draco.BusinessFunction.ui.Navigation;
import com.intel.ebsqa.draco.BusinessFunction.ui.SF_User_Creation;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData;
import com.intel.ebsqa.draco.DataClass.AdminData;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData.ExternalCustomerUIDataDetails;
import com.intel.ebsqa.draco.DataClass.AdminData.AdminDataDetails;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.enums.Button;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.CommonEnum.ApplicationType;
import com.intel.ebsqa.draco.enums.CommonEnum.BooleanValues;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalNavigator;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalSearchResultDescription;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalSearchResultDescription_Plural;
import com.intel.ebsqa.draco.enums.ExternalUserEnum.ExternalUserMenu;
import com.intel.ebsqa.draco.enums.ExternalUserEnum.ExternalUserSubMenu;
import com.intel.ebsqa.draco.enums.Tabs;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.draco.library.common.ExcelCommon;
import com.intel.ebsqa.draco.utility.MongoDBRepository;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.intel.ebsqa.helperclass.StringUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @Description
 * @Author manish9x
 * @Since 23-Nov-2022
 */

public class EntitlementAssignmentonNewContactValidationTest extends TestBase {
	MongoDBRepository<EntitlementAssignmentonNewContactValidationTest> objMongoDBContext;
	DBCollection objSF_User_CreationCollData;
	AdminData objAdminData;
	AdminDataDetails objAdminDataDetails;
	Navigation objNavigation;
	SF_User_Creation objSF_User_Creation;
	AdminFunctions objAdminFunctions;
	ExternalApplicationNavigation objExternalApplicationNavigation;
	ExternalCustomerUIData objExternalCustomerUIData;
	ExternalCustomerUIDataDetails objExternalCustomerUIDetails;
	public List<String> Entitlement_Name = new ArrayList<String>();

	
	@BeforeTest(alwaysRun = true)
	public void BeforeTestcase() {

		objMongoDBContext = new MongoDBRepository<EntitlementAssignmentonNewContactValidationTest>("SF_users_creation");
		objSF_User_CreationCollData = objMongoDBContext._collection;
		objAdminData = new AdminData();
		objAdminFunctions = new AdminFunctions();
		objAdminDataDetails = objAdminData.new AdminDataDetails();
		objExternalCustomerUIData = new ExternalCustomerUIData();
		objExternalCustomerUIDetails = objExternalCustomerUIData.new ExternalCustomerUIDataDetails();
	}

	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod() {
		sfcommonObj.setUp(com.salesforce.lpop.framework.markers.ApplicationType.SALES_LEX);
		objNavigation = new Navigation();
		objSF_User_Creation = new SF_User_Creation();
		objAdminFunctions = new AdminFunctions();
		objExternalApplicationNavigation = new ExternalApplicationNavigation();

		objExternalApplicationNavigation = new ExternalApplicationNavigation();
	}

	public EntitlementAssignmentonNewContactValidationTest() {

		objMongoDBContext = new MongoDBRepository<EntitlementAssignmentonNewContactValidationTest>("SF_User_Creation");
		objSF_User_CreationCollData = objMongoDBContext._collection;
		objAdminData = new AdminData();
		objAdminDataDetails = objAdminData.new AdminDataDetails();
	}

	/**
	 * Read data from mongo DB collection
	 * 
	 * @Author csingamx
	 * @Since 06-Nov-2018
	 * @param testCaseID
	 */
	public void ReadDataFromMongoCollection(String testCaseID) {

		BasicDBObject search = new BasicDBObject();
		search.put("TestCaseID", testCaseID);
		DBCursor dbc = objSF_User_CreationCollData.find(search);
		while (dbc.hasNext()) {
			DBObject mongoObject = dbc.next();
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
			Gson gson = gsonBuilder.create();
			objAdminData = gson.fromJson(mongoObject.toString(), AdminData.class);

			BasicDBList PMB = (BasicDBList) mongoObject.get("SF_User_CreationDetails");
			for (int i = 0; i < PMB.size(); i++) {
				BasicDBObject PMBobj = (BasicDBObject) PMB.get(i);
				if (PMBobj.getString("Environment").equals(configObj.getEnvironment())) {
					objAdminDataDetails = gson.fromJson(PMBobj.toString(), AdminDataDetails.class);
				}
			}
		}
	}


	/**
	 * Creation of contact and verify Entitlements of CCFadminUser through lightning UI
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 24-Nov-2022
	 */
	@Test(description = "Creation of contact and verify Entitlements of CCFadminUser through lightning UI", groups = { "Draco Smoke" })
	public void TC0001_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToLightningExperience();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(60);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		//objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		objNavigation.logoutFromApplication();

	}
	

	/**
	 * Verify that "CCF User Administrator" Entitlement gets successfully removed from the existing Contact in 1.0 flow
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 */
	@Test(description = "Verify that 'CCF User Administrator' Entitlement gets successfully removed from the existing Contact in 1.0 flow", groups = { "Draco Smoke" })
	public void TC0002_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User Administrator");
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		
		objAdminFunctions.goToContactFromPartnerUserPage();
		objAdminFunctions.ClickOnshowMoreActions();
		objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalse(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(30);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsUnChecked();
		objAdminFunctions.verifyPermissionSetRemovals();
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that 'PSG Licensing User' entitlement gets successfully assigned on the New Contact in 2.0 flow through lightning UI
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 02-Dec-2022
	 */
	@Test(description = "Verify that 'PSG Licensing User' entitlement gets successfully assigned on the New Contact in 2.0 flow through lightning UI", groups = { "Draco Smoke" })
	public void TC0004_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("PSG Licensing User");
		Entitlement_Name.add("Partner Portal");
	
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(60);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandProgrammableSolutionsGroup();
		objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
		objAdminFunctions.checkPSGLicensingUserCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForPSGLicensingUser();
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that 'PSG Licensing User' Entitlement gets successfully removed from the existing Contact in 2.0 flow in lightning UI
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 05-Dec-2022
	 */
	@Test(description = "Verify that 'PSG Licensing User' Entitlement gets successfully removed from the existing Contact in 2.0 flow in lightning UI", groups = { "Draco Smoke" })
	public void TC0003_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("PSG Licensing User");
		Entitlement_Name.add("Partner Portal");
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.goBackToContactsPage();
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForPSGLicensingUser();
		
		objAdminFunctions.goToContactFromPartnerUserPage();
		objAdminFunctions.ClickOnshowMoreActions();
		objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		
		objAdminFunctions.expandProgrammableSolutionsGroup();
		objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
		objAdminFunctions.checkPSGLicensingUserCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalse(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(30);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsUnChecked();
		objAdminFunctions.verifyPermissionSetRemovals();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that 'PSG Disti Quoting Access' and 'Customer Drafter Access' entitlements get successfully assigned on an existing contact through Lightning UI
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 08-Dec-2022
	 */
	@Test(description = "Verify that 'PSG Disti Quoting Access' and 'Customer Drafter Access' entitlements get successfully assigned on an existing contact through Lightning UI", groups = { "Draco Smoke" })
	public void TC0005_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("PSG Licensing User");
		Entitlement_Name.add("Partner Portal");
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		
        objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.goBackToContactsPage();
		objAdminFunctions.ClickOnshowMoreActions();
		objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandProgrammableSolutionsGroup();
		objAdminFunctions.verifyPSGDistiQuotingAccessEntitlementPresentOrNot();
		objAdminFunctions.checkPSGDistiQuotingAccessCheckbox();
		
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.expandIntelQuoteRequest();
		objAdminFunctions.verifyCustomerDrafterAccessEntitlementPresentOrNot();
		objAdminFunctions.checkCustomerDrafterAccessCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		
		Entitlement_Name.remove("PSG Licensing User");
		Entitlement_Name.remove("Partner Portal");
		Entitlement_Name.add("Customer Drafter Access");
		Entitlement_Name.add("PSG Disti Quoting Access");
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(30);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess();
		
		objNavigation.logoutFromApplication();
		
	}
	
	/**
	 * Verify that User can't assign any entitlement on Existing contact if 'Export Compliance Block' is checked in Lightning UI
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 08-Dec-2022
	 */
	@Test(description = "Verify that User can't assign any entitlement on Existing contact if 'Export Compliance Block' is checked in Lightning UI", groups = { "Draco Smoke" })
	public void TC0006_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.verifyErrorOnGrantAccessPage();
		objAdminFunctions.clickOnCancelOnGrantAccessPage();
		objNavigation.logoutFromApplication();
		
	}
	
	/**
	 * Verify that 'Employee' entitlement gets successfully removed from an existing contact
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 */
	@Test(description = "Verify that 'Employee' entitlement gets successfully removed from an existing contact", groups = { "Draco Smoke" })
	public void TC0007_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("Employee");
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(20);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalse(CommonEnum.IntegrationStatus.Complete.getDescription());
		objAdminFunctions.goBackToContactsPage();
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForEmployee();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.ClickOnshowMoreActions();
		objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandIntelPartnerAlliance();
		objAdminFunctions.checkEmployeeCheckbox();
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(20);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalse(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(30);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(20);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(20);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue(CommonEnum.IntegrationStatus.Complete.getDescription());
		objAdminFunctions.goBackToContactsPage();
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetRemovalForEmployee();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that PCR gets created and completed upon successful  completion of  'Employee/Partner Admin/Partner Admin Delegate' entitlement assignment on an existing contact
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 */
	@Test(description = "Verify that PCR gets created and completed upon successful  completion of  'Employee/Partner Admin/Partner Admin Delegate' entitlement assignment on an existing contact", groups = { "Draco Smoke" })
	public void TC0008_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("Employee");
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(30);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandIntelPartnerAlliance();
		objAdminFunctions.checkEmployeeCheckbox();
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(30);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(30);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(30);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(30);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalse(CommonEnum.IntegrationStatus.Complete.getDescription());
		objAdminFunctions.goBackToContactsPage();
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForEmployee();
		objNavigation.logoutFromApplication();
		
	}
	
	/**
	 * Verify that a user with 'CCF User Administrator' entitlement can login to the 'Partnercenter Unified Community' and can see Grant Access option under Program
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 14-Dec-2022
	 */
	@Test(description = "Verify that a user with 'CCF User Administrator' entitlement can login to the 'Partnercenter Unified Community' and can see Grant Access option under Program", groups = { "Draco Smoke" })
	public void TC0009_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(60);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		seleniumObj.getDriver().navigate().back();
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.getDriver().navigate().back();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that User can search the contacts in his account through Grant Access
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 16-Dec-2022
	 */
	@Test(description = "Verify that User can search the contacts in his account through Grant Access", groups = { "Draco Smoke" })
	public void TC0010_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(60);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getEmail());
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		
		seleniumObj.getDriver().navigate().back();
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.getDriver().navigate().back();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that User can create a User in Corporate/Agency account with acceptable email id domain
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 19-Dec-2022
	 */
	@Test(description = "Verify that User can create a User in Corporate/Agency account with acceptable email id domain", groups = { "Draco Smoke" })
	public void TC0011_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		String contactName2 = objAdminDataDetails.getFirstName2() + " "
				+ objAdminDataDetails.getLastName2();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getNonExistingEmail(), contactName2);
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that User can Assign entitlements for the Contacts in their Corporate Accounts
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 22-Dec-2022
	 */
	@Test(description = "Verify that User can Assign entitlements for the Contacts in their Corporate Accounts", groups = { "Draco Smoke" })
	public void TC0012_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		objAdminFunctions.selectAnyOfTheEntitlements("CCF User Administrator");
		objAdminFunctions.clickButton(Button.SaveEntitlements.toString());
		objAdminFunctions.VerifyStatusOfEntitlementAsProcessing("CCF User Administrator");
	
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		String contactName2 = objAdminDataDetails.getFirstName2() + " "
				+ objAdminDataDetails.getLastName2();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getNonExistingEmail(), contactName2);
		
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that User can Remove entitlements from the Contacts in their Corporate Accounts
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 26-Dec-2022
	 */
	@Test(description = "Verify that User can Remove entitlements from the Contacts in their Corporate Accounts", groups = { "Draco Smoke" })
	public void TC0013_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		objAdminFunctions.selectAnyOfTheEntitlements("CCF User Administrator");
		objAdminFunctions.clickButton(Button.SaveEntitlements.toString());
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		String contactName2 = objAdminDataDetails.getFirstName2() + " "
				+ objAdminDataDetails.getLastName2();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getNonExistingEmail(), contactName2);
		
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		objAdminFunctions.selectAnyOfTheEntitlements("CCF User");
		objAdminFunctions.clickButton(Button.SaveEntitlements.toString());
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getNonExistingEmail(), contactName2);
		
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsUnChecked();
		objAdminFunctions.verifyPermissionSetRemovals();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that User can Assign entitlement for the Contacts in their Agency Accounts
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 27-Dec-2022
	 */
	@Test(description = "Verify that User can Assign entitlement for the Contacts in their Agency Accounts", groups = { "Draco Smoke" })
	public void TC0014_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkCCFUserAdministratorCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		objAdminFunctions.selectAnyOfTheEntitlements("CCF User Administrator");
		objAdminFunctions.clickButton(Button.SaveEntitlements.toString());
		objAdminFunctions.VerifyStatusOfEntitlementAsProcessing("CCF User Administrator");
	
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		String contactName2 = objAdminDataDetails.getFirstName2() + " "
				+ objAdminDataDetails.getLastName2();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getNonExistingEmail(), contactName2);
		
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objNavigation.logoutFromApplication();

	}
	

	/**
	 * Verify that contact can't be created if email id of new contact is not with Acceptable Domains in 'Partner Group Domain Mapping' object
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 27-Dec-2022
	 */
	@Test(description = "Verify that contact can't be created if email id of new contact is not with Acceptable Domains in 'Partner Group Domain Mapping' object", groups = { "Draco Smoke" })
	public void TC0015_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyErrorOnContactThroughPartnerCenter();
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that a user can create a contact only into the  Corporate/Agency Accounts which are mapped under 'Partner Group Mapping' object
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 */
	@Test(description = "Verify that a user can create a contact only into the  Corporate/Agency Accounts which are mapped under 'Partner Group Mapping' object", groups = { "Draco Smoke" })
	public void TC0016_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignments();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		objAdminFunctions.verifyAccountsPresentWhichWereInPGMRecords(objAdminDataDetails);
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that user can see all the entitlements of sub-communities of CCP for which admin entitlement is assigned to the logged in User(eg. User with "SPCF User Administrator" entitlement will be able to see all SPCF entitlements on Grant Access) when he clicks on "Load Entitlements" button for any contact
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 */
	@Test(description = "Verify that user can see all the entitlements of sub-communities of CCP for which admin entitlement is assigned to the logged in User when he clicks on 'Load Entitlements' button for any contact", groups = { "Draco Smoke" })
	public void TC0017_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("SPCF USER");
		Entitlement_Name.add("SPCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForSPCFUserAdministrator();
		objAdminFunctions.goToContactFromPartnerUserPage();
		
		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.clickOnGrantAccessFromMyProgramDropDown();
		objAdminFunctions.verifyCCPPageWithSearchContactButtonPresent();
		objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.verifyNoResultFoundMessage();
		objAdminFunctions.clickButton(Button.CreateContact.toString());
		objAdminFunctions.createContactThroughPartnerCenter(objAdminDataDetails);
		objAdminFunctions.verifyContactIsVisibleOnclickingSearchContactButton(objAdminDataDetails.getNonExistingEmail());
		objAdminFunctions.clickOnLoadEntitlementsButton();
		Entitlement_Name.add("SPCF Account Budget Administrator");
		Entitlement_Name.add("SPCF Activity Submitter");
		Entitlement_Name.add("SPCF Claim Submitter");
		
		objAdminFunctions.validateContactEntitlementsPresent(Entitlement_Name);
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		objNavigation.logoutFromApplication();

	}
	
	/**
	 * Verify that a user with 'CCF Activity Submitter' entitlement can login to the 'Partnercenter Unified Community' and cannot see Grant Access option under Program
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 */
	@Test(description = "Verify that a user with 'CCF Activity Submitter' entitlement can login to the 'Partnercenter Unified Community' and cannot see Grant Access option under Program", groups = { "Draco Smoke" })
	public void TC0018_DRACO() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
		objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
		String[] filePaths = new String[2];
		filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
		filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
				+ objAdminDataDetails.getFileName();
		objAdminDataDetails.setFilePaths(filePaths);
		
		objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
		Entitlement_Name.add("CCF Activity Submitter");
		Entitlement_Name.add("CCF User");
		Entitlement_Name.add("Partner Portal");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		//objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		
		String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
		
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandConsolidatedPlatform();
		objAdminFunctions.verifyCCFActivitySubmitterPresentOrNot();
		objAdminFunctions.checkCCFActivitySubmitterCheckbox();
		objAdminFunctions.clickOnSaveOnGrantAccessPage();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(100);
		sfcommonObj.pageRefresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		
		objAdminFunctions.clickOnViewPartnerUserButton();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		objAdminFunctions.verifyPermissionSetAssignmentsForCCFActivitySubmitter();
		objAdminFunctions.goToContactFromPartnerUserPage();

		objAdminFunctions.clickOnLogintoExperienceasUserButton();
		objAdminFunctions.clickOnPartnercenterUnifiedCommunity();
		objAdminFunctions.clickOnMyProgramDropDown();
		objAdminFunctions.verifyGrantAccessNotPresentUnderMyProgram();
		
		seleniumObj.getDriver().get(currentContactsURL);
		sfcommonObj.waitTillLightningPageLoadComplete();
		
		objNavigation.logoutFromApplication();

	}
}
