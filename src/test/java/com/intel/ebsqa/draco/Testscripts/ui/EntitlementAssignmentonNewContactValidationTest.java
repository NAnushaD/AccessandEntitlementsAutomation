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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public List<String> IPA_membership=new ArrayList<String>();
    public List<String> Entitlement_Name1 = new ArrayList<String>();

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
		seleniumObj.waitForSeconds(5);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		objAdminFunctions.switchToLightningExperience();
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
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		objAdminFunctions.switchToLightningExperience();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		seleniumObj.waitForSeconds(60);
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
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		objAdminFunctions.switchToLightningExperience();
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
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		objAdminFunctions.switchToLightningExperience();
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
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		objAdminFunctions.switchToLightningExperience();
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
		objAdminFunctions.switchToLightningExperience();
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
				objAdminDataDetails.getEmail(), contactName);
		
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		sfcommonObj.waitTillLightningPageLoadComplete();
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
		objAdminFunctions.switchToLightningExperience();
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
		seleniumObj.waitForSeconds(60);
		seleniumObj.waitForSeconds(60);
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
		
		objAdminFunctions.switchToLightningExperience();
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
	/**
	 * Verify that User can assign CCP entitlements to an existing non-ccp user of same Corporate Account  
	 * @throws Exception 
	 * 
	 * @Author asuhassx
	 * @Since 02-Jan-2023
	 */
	
		@Test(description="Verify that User can assign CCP entitlements to an existing non-ccp user of same Corporate Account",groups={"Draco Smoke"})
		public void TC0019_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
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
			
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getExistingEmail());
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
					objAdminDataDetails.getExistingEmail(), contactName2);
			
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
		
		
		@Test(description="Verify that User can assign CCP entitlements to an existing non-ccp user of same Agency Account",groups={"Draco Smoke"})
		public void TC0020_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
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
			
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButton(objAdminDataDetails.getExistingEmail());
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
					objAdminDataDetails.getExistingEmail(), contactName2);
			
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
		@Test(description="Verify that after opening Manage Personnel page, User should be able to see columns of Contacts list as Select, Name, Email Address, Last Sign-in, IPA Membership, Country,Responibilities Assigned",groups={"Draco Smoke"})
		public void TC0021_DRACO() throws Exception
		{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			seleniumObj.waitForSeconds(100);
			objAdminFunctions.arecolumnsavailable();
			seleniumObj.waitForSeconds(100);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();		
		}
		
		@Test(description="Verify that  Contacts with IPA Membership Active(ACR-Complete) and Invited(ACR-Pending) are displayed and ACR-Inactive contacts are not displayed under Contacts list of Manage Personnel page",groups={"Draco Smoke"})
		public void TC0022_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			seleniumObj.waitForSeconds(5);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.arecolumnsavailable();
			objAdminFunctions.clickonrowsperpage();
			objAdminFunctions.clickon100rows();
			//seleniumObj.waitForSeconds(10);
			objAdminFunctions.checkInactiveIPAMembership();
			//objAdminFunctions.clickelsewhere();
			
			seleniumObj.waitForSeconds(10);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify Manage Personnel filters work properly",groups={"Draco Smoke"})
		public void TC0023_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			seleniumObj.waitForSeconds(5);
			sfcommonObj.waitTillLightningPageLoadComplete();
			//seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.clickOnfilterEmailaddress();
			objAdminFunctions.addfilter(objAdminDataDetails);
			//objAdminFunctions.clickelsewhere();
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.validatefilterapply(objAdminDataDetails);
			seleniumObj.waitForSeconds(10);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		
		@Test(description="Verify that a User can remove the Invited/Active Contact with Same Partner Role and Same Account from IPA by clicking on Delete button",groups={"Draco Smoke"})
		public void TC0024_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.addfilter(objAdminDataDetails);
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.clickOnContacttoInvite();
			objAdminFunctions.clickOnDeletemanagepersonnel();
			objAdminFunctions.clickOnYesOnManagePersonnelPage();
			seleniumObj.waitForSeconds(10);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			String contactName2 = objAdminDataDetails.getFirstName2() + " "
					+ objAdminDataDetails.getLastName2();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getExistingEmail(), contactName2);
			
			seleniumObj.waitForSeconds(10);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToTab(Tabs.Membership.toString());
			objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
			objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue(CommonEnum.IntegrationStatus.Complete.getDescription());
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that a User can re-invite the Invited Contact with Same Partner Role and Same Account from IPA by clicking on Re-invite button",groups={"Draco Smoke"})
		public void TC0025_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.addfilter(objAdminDataDetails);
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.clickOnContacttoInvite();
			objAdminFunctions.clickOnReinvitemanagepersonnel();
			seleniumObj.waitForSeconds(100);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			String contactName2 = objAdminDataDetails.getFirstName2() + " "
					+ objAdminDataDetails.getLastName2();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getExistingEmail(), contactName2);
			
			seleniumObj.waitForSeconds(100);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
			objAdminFunctions.verifyEmployeeRelationshipStatusAsPendingAndInactiveIndicatorAsFalse(CommonEnum.IntegrationStatus.Pending.getDescription());
			objNavigation.logoutFromApplication();	
		}
		@Test(description="Verify that a User can't perform Re-invite or Delete Quick Actions on the Same Account Different Partner Role contacts and Contacts with different OPID associated through MPI",groups={"Draco Smoke"})
		public void TC0026_DRACO() throws Exception{
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
				Entitlement_Name.add("Partner Admin");
				Entitlement_Name.add("Partner Portal");
				Entitlement_Name.add("Employee");
				sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
						objAdminData.getRole());
				
				objAdminFunctions.switchToLightningExperience();
				sfcommonObj.waitTillLightningPageLoadComplete();

				String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
				
				String contactName = objAdminDataDetails.getFirstName() + " "
						+ objAdminDataDetails.getLastName();
				objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
						GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
						objAdminDataDetails.getEmail(), contactName);
				sfcommonObj.waitTillLightningPageLoadComplete();
				objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
				objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
				objAdminFunctions.clickOnLogintoExperienceasUserButton();
				objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
				objAdminFunctions.clickOnManagePersonnel();
				objAdminFunctions.clickOnmanagepersonnelFilter();
				objAdminFunctions.clickOnfilterEmail();
				objAdminFunctions.addfilter(objAdminDataDetails);
				objAdminFunctions.clickOnfilterApply();
				objAdminFunctions.clickOnContacttoInvite();
				objAdminFunctions.verifyIfReinviteButtonExists();
				objAdminFunctions.verifyIfDeleteButtonExists();
				seleniumObj.waitForSeconds(100);
				seleniumObj.getDriver().get(currentContactsURL);
				sfcommonObj.waitTillLightningPageLoadComplete();
				objNavigation.logoutFromApplication();
				
		}
		@Test(description="Verify that Delete and Reinvite buttons are not shown for the PA contact",groups={"Draco Smoke"})
		public void TC0027_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
		    objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.addfilter(objAdminDataDetails);
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.clickOnContacttoInvite();
			objAdminFunctions.verifyIfReinviteButtonExists();
			objAdminFunctions.verifyIfDeleteButtonExists();
			seleniumObj.waitForSeconds(100);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
		}
		@Test(description="Verify that user is able to assign and remove any of the entitlements below for a contact having same partner role same Account as that of logged in user based on the benefits assigned on the Account: Employee, Partner Admin,Partner Admin Delegate, Intel Solutions Library Partner NDA,IPA Distributor User,IPA Titanium User,MDF Claim Submitter,MDF Proposal Manager,MDF User ,MDF Viewer,Platform Verification Program (PVP) access,Points Manager,Points View ,Warranty Requestor,Web Ticketing ",groups={"Draco Smoke"})
		public void TC0028_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.addfilter(objAdminDataDetails);
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.clickOnContacttoInvite();
			objAdminFunctions.checkPADCheckbox();
			seleniumObj.waitForSeconds(100);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName2 = objAdminDataDetails.getFirstName2() + " "
					+ objAdminDataDetails.getLastName2();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getExistingEmail(), contactName2);
			
			seleniumObj.waitForSeconds(100);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			Entitlement_Name.add("Partner Admin Delegate");
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
		@Test(description="Verify that a User can assign/remove Marketplace entitlement(Marketing Specialist/Offering Editor/Partner Lead Development Rep) based on benefit on Account/OPID to the Contact of Same Account with different Partner Role ",groups={"Draco Smoke"})
		public void TC0029_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.clickOnmanagepersonnelFilter();
			objAdminFunctions.clickOnfilterEmail();
			objAdminFunctions.clickOnfilterEmailaddress();
			objAdminFunctions.addfilter(objAdminDataDetails);
			objAdminFunctions.clickOnfilterApply();
			objAdminFunctions.clickOnContacttoInvite();
			objAdminFunctions.checkMSCheckbox();
			objAdminFunctions.clickOnSaveOnManagePersonnel();
			seleniumObj.waitForSeconds(10);
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName2 = objAdminDataDetails.getFirstName2() + " "
					+ objAdminDataDetails.getLastName2();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getExistingEmail(), contactName2);
			
			seleniumObj.waitForSeconds(10);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			Entitlement_Name.add("Marketing Specialist");
			Entitlement_Name.remove("Partner Admin");
			seleniumObj.waitForSeconds(10);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignments();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that the message is displayed as 'Invite the contact to register for the Intel® Partner Alliance Program. (Invite User)' On Manage Personnel when user searches for a new contact ",groups={"Draco Smoke"})
		public void TC0030_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateNewContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
			
			
		}
		
		@Test(description="Verify that the message is displayed as 'Invite the contact to register for the Intel® Partner Alliance Program. (Send Invite)' On Manage Personnel when user searches for a Non-IPA contact of same Account ",groups={"Draco Smoke"})
		public void TC0031_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateNewContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
					
		}
		@Test(description="Verify that the message is displayed as 'The Contact already has an Active Intel® Partner Alliance Membership. Need help? Chat with Intel' On Manage Personnel when user searches for a Existing IPA contact",groups={"Draco Smoke"})
		public void TC0032_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateACRactiveMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that the message is displayed as 'Invite the contact to register for the Intel® Partner Alliance Program. (Send Invite)' On Manage Personnel when user searches for a Soft/Deleted contact ",groups={"Draco Smoke"})
		public void TC0033_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateNewContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
			
			
		}
		@Test(description="Verify that the message is displayed as 'Invite the contact to register for the Intel® Partner Alliance Program. (Send Invite)' On Manage Personnel when user searches for an ACR inactive contact ",groups={"Draco Smoke"})
		public void TC0034_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateNewContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that the message is displayed as 'An invitation has been extended and registration is in progress. Need help? Chat with Intel' On Manage Personnel when user searches for an Invited contact",groups={"Draco Smoke"})
		public void TC0035_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateinvitedContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that the message is displayed as 'An invitation has been extended and registration is in progress. Need help? Chat with Intel' On Manage Personnel when user searches for a Registration Pending contact",groups={"Draco Smoke"})
		public void TC0036_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateinvitedContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that the message is displayed as 'The contact is associated with a different partner role or Account membership of requested role is not yet approved. Need help adding a account? Chat with Intel' On Manage Personnel when user searches for a contact which is of same OPID but Different Partner Role",groups={"Draco Smoke"})
		public void TC0037_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validatediffOPIDContactMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description="Verify that when a user invites any internal user then message is displayed as'Invitation cannot be extended to an Intel domain Email ID'",groups={"Draco Smoke"})
		public void TC0038_DRACO() throws Exception{
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
			Entitlement_Name.add("Partner Admin");
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Employee");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			
			
			objAdminFunctions.clickOnLogintoExperienceasUserButton();
			objAdminFunctions.clickOnIntelPartnerAllianceCommunity();
			
			//js.executeScript("window.scrollBy(0,1000)");
			objAdminFunctions.clickOnManagePersonnel();
			objAdminFunctions.SearchContactWithEmailAndClickOnSearchContactButtonMP(objAdminDataDetails.getExistingEmail());
			objAdminFunctions.clickOnSubmitInviteManagePersonnel();
			objAdminFunctions.validateInternalUserMessageonManagePersonnel();
			//objAdminFunctions.clickOnInviteAUserManagePersonnel();
			seleniumObj.getDriver().get(currentContactsURL);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();
			
		}
		@Test(description = "Creation of contact and verify Entitlements of CCFadminUser through Classic UI", groups = {"Draco Smoke"})
		public void TC0052_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickOnNewbuttonContactClassic();
			objAdminFunctions.clickOnDropdownClassic();
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNextClassic();
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.clickContinueClassic();
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.createNewContactClassic(objAdminDataDetails);
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandConsolidatedPlatformclassic();
			objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
			objAdminFunctions.checkCCFUserAdministratorCheckboxclassic();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Pending.getDescription());
			
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(30);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.waitForSeconds(30);
			sfcommonObj.pageRefresh();
			seleniumObj.waitForSeconds(30);
			sfcommonObj.pageRefresh();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			//objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButtonclassic();
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignments();
			objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that 'CCF User Administrator' Entitlement gets successfully removed from the existing Contact in 1.0 flow through Classic UI", groups = { "Draco Smoke" })
		public void TC0053_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelectclassic(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			//objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignments();
			
			objAdminFunctions.goToContactFromPartnerUserPage();
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandConsolidatedPlatform();
			objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
			objAdminFunctions.checkCCFUserAdministratorCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Pending.getDescription());
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalseclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.validateContactEntitlementsRemovalclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsUnChecked();
			objAdminFunctions.verifyPermissionSetRemovals();
			//objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that 'PSG Licensing User' Entitlement gets successfully removed from the existing Contact in 2.0 flow in classic UI", groups = { "Draco Smoke" })
		public void TC0054_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelectclassic(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignmentsForPSGLicensingUser();
			
			objAdminFunctions.goToContactFromPartnerUserPage();
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			seleniumObj.waitForSeconds(2);
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandProgrammableSolutionsGroup();
			objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
			objAdminFunctions.checkPSGLicensingUserCheckboxclassic();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalseclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			objAdminFunctions.validateContactEntitlementsRemovalclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButtonclassic();
			objAdminFunctions.verifyActiveCheckboxOnUserIsUnChecked();
			objAdminFunctions.verifyPermissionSetRemovals();
			objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that 'PSG Licensing User' entitlement gets successfully assigned on the New Contact in 2.0 flow through Classic UI", groups = { "Draco Smoke" })
		public void TC0055_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			objAdminFunctions.clickOnNewbuttonContactClassic();
			objAdminFunctions.clickOnDropdownClassic();
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNextClassic();
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.clickContinueClassic();
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.createNewContactClassic(objAdminDataDetails);
			seleniumObj.waitForSeconds(2);
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandProgrammableSolutionsGroup();
			objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
			objAdminFunctions.checkPSGLicensingUserCheckboxclassic();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignmentsForPSGLicensingUser();
			
			objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that 'PSG Disti Quoting Access' and 'Customer Drafter Access' entitlements get successfully assigned on an existing contact through classic UI", groups = { "Draco Smoke" })
		public void TC0056_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelectclassic(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			 objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
	        objAdminFunctions.clickOnGrantAccessButtonclassic();
	        sfcommonObj.pageRefresh();
			objAdminFunctions.expandProgrammableSolutionsGroup();
			objAdminFunctions.verifyPSGDistiQuotingAccessEntitlementPresentOrNot();
			objAdminFunctions.checkPSGDistiQuotingAccessCheckboxclassic();
			
			objAdminFunctions.expandIntelQuoteRequest();
			objAdminFunctions.verifyCustomerDrafterAccessEntitlementPresentOrNot();
			objAdminFunctions.checkCustomerDrafterAccessCheckboxclassic();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Pending.getDescription());
			
			Entitlement_Name.remove("PSG Licensing User");
			Entitlement_Name.remove("Partner Portal");
			Entitlement_Name.add("Customer Drafter Access");
			Entitlement_Name.add("PSG Disti Quoting Access");
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButtonclassic();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess();
			
			objNavigation.logoutFromApplication();
			
		}
		@Test(description = "Verify that User can't assign any entitlement on Existing contact if 'Export Compliance Block' is checked in Classic UI", groups = { "Draco Smoke" })
		public void TC0057_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelectclassic(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.verifyErrorOnGrantAccessPage();
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
			
		}
		
		@Test(description = "Verify that 'Employee' entitlement gets successfully removed from an existing contact", groups = { "Draco Smoke" })
		public void TC0058_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			seleniumObj.waitForSeconds(5);
			
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalseclassic(CommonEnum.IntegrationStatus.Complete.getDescription());
			
			objAdminFunctions.clickOnViewPartnerUserButton();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignmentsForEmployee();
			objAdminFunctions.goToContactFromPartnerUserPage();
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkEmployeeCheckboxclassic();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			seleniumObj.waitForSeconds(20);
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalseclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			
			seleniumObj.waitForSeconds(30);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			seleniumObj.waitForSeconds(20);
			objAdminFunctions.validateContactEntitlementsRemovalclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(20);
			objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue(CommonEnum.IntegrationStatus.Complete.getDescription());
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButtonclassic();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetRemovalForEmployee();
			objNavigation.logoutFromApplication();

		}
		
		@Test(description = "Verify that PCR gets created and completed upon successful  completion of  'Employee/Partner Admin/Partner Admin Delegate' entitlement assignment on an existing contact", groups = { "Draco Smoke" })
		public void TC0059_DRACO() throws Exception {
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
			objAdminFunctions.switchToClassicExperience();
			objAdminFunctions.clickonClassicExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstNameclassic() + " "
					+ objAdminDataDetails.getLastNameclassic();
			objNavigation.globalUISearchContactAndSelectclassic(objAdminDataDetails.getAccountNameclassic(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmailclassic(), contactName);
			
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnGrantAccessButtonclassic();
			sfcommonObj.pageRefresh();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkEmployeeCheckboxclassic();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.verifyERPMIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			
			seleniumObj.waitForSeconds(30);
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsclassic(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			
			seleniumObj.waitForSeconds(30);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyAGSIntegrationStatusClassic(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objAdminFunctions.validateContactEntitlementsAssignmentclassic(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(30);
			
			objAdminFunctions.verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalseClassic(CommonEnum.IntegrationStatus.Complete.getDescription());
			objAdminFunctions.clickOnManageExternalUserButtonclassic();
			objAdminFunctions.clickOnViewPartnerUserButtonclassic();
			objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
			objAdminFunctions.verifyPermissionSetAssignmentsForEmployee();
			objNavigation.logoutFromApplication();
			
		}
		/*------------------------------------ new testCase ------------------------------------------*/
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Activity Manager (Account Plan MDF)' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA Activity Manager (Account Plan MDF)'",groups={"Draco Smoke"})
		public void TC0086_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAActivityManager();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Claim Submitter (Account Plan MDF)' as below -  'Account or OPID doesn't have required benefit for these entitlements : IPA Claim Submitter (Account Plan MDF)'",groups={"Draco Smoke"})
		public void TC0087_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAClaimSubmitter();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Claim Submitter (Proposal MDF) ' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA Claim Submitter (Proposal MDF)'",groups={"Draco Smoke"})
		public void TC0088_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAClaimSubmitterProposal();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA External View Only (Account Plan MDF)' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA External View Only (Account Plan MDF)'",groups={"Draco Smoke"})
		public void TC0089_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAExternalViewOnlycheckbox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA External View Only (Proposal MDF) ' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA External View Only (Proposal MDF)'",groups={"Draco Smoke"})
		public void TC0090_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAExternalViewOnlyProposalcheckbox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Proposal Manager (Proposal MDF)  'as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA Proposal Manager (Proposal MDF)'",groups={"Draco Smoke"})
		public void TC0091_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAProposalManagerCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Recovery Administrator (Proposal MDF)' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA Recovery Administrator (Proposal MDF)",groups={"Draco Smoke"})
		public void TC0092_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPARecoveryAdministratorCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'IPA Recovery Administrator (Proposal MDF)' as below - 'Account or OPID doesn't have required benefit for these entitlements : IPA Recovery Administrator (Proposal MDF)'",groups={"Draco Smoke"})
		public void TC0093_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPARecoveryAdministratorProposalCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'MDF Claim Submitter' as below - 'Account or OPID doesn't have required benefit for these entitlements : MDF Claim Submitter'",groups={"Draco Smoke"})
		public void TC0094_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFClaimSubmitterCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'MDF Proposal Manager' as below - 'Account or OPID doesn't have required benefit for these entitlements : MDF Proposal Manager'",groups={"Draco Smoke"})
		public void TC0095_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFProposalCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'MDF Viewer' as below - 'Account or OPID doesn't have required benefit for these entitlements : MDF Viewer",groups={"Draco Smoke"})
		public void TC0096_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFViewerCheckBox();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'Platform Verification Program (PVP) access' as below - 'Account or OPID doesn't have required benefit for these entitlements : Platform Verification Program (PVP) access'",groups={"Draco Smoke"})
		public void TC0097_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPlatformVerification();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			
			
		
	}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'Points Manager' as below - 'Account or OPID doesn't have required benefit for these entitlements : Points Manager'",groups={"Draco Smoke"})
		public void TC0098_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPointManager();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			}
		
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of Points View as below - 'Account or OPID doesn't have required benefit for these entitlements : Points View'",groups={"Draco Smoke"})
		public void TC0099_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPointView();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			}
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'Warranty Requestor' as below - 'Account or OPID doesn't have required benefit for these entitlements : Warranty Requestor",groups={"Draco Smoke"})
		public void TC0100_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkWarrantyRequestor();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			}
		
		@Test(description="Verify that popup message is displayed on clicking on Save button on GA page after checking the checkbox in front of 'Technology Sandbox' as below - Account or OPID doesn't have required benefit for these entitlements : Technology Sandbox'",groups={"Draco Smoke"})
		public void TC0101_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkTechnologySandbox();
			//objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.checkMsg();
			}
		@Test(description="Verify that if we republish a contact with no CE records does not change the AGS Integration Status to Pending",groups={"Draco Smoke"})
		public void TC0102_DRACO() throws Exception{
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
			
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);
			seleniumObj.waitForSeconds(20);
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
			objAdminFunctions.checkRepublishObm();
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
			
			}
		@Test(description = "Verify that user is able to successfully assign 1.0  external entitlement on a new contact", groups = { "Draco Smoke" })
		public void TC0105_DRACO() throws Exception {
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
			Entitlement_Name.add("Intel On Demand Purchase Agent");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToLightningExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickButton(Button.New.toString());
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
			objAdminFunctions.createNewContact(objAdminDataDetails);
			
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			
			/*String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);*/
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandOrderManagement();
			
			objAdminFunctions.checkIntelOnDemand();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
			objAdminFunctions.switchToTab(Tabs.Related.toString());
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			seleniumObj.waitForSeconds(60);
			 do{
					sfcommonObj.pageRefresh();
					sfcommonObj.waitTillLightningPageLoadComplete();
				   }
			 while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			
			objAdminFunctions.internalEntitlementStatus();
			objAdminFunctions.switchToTab(Tabs.Related.toString());
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			//objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			
			objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that user is able to successfully assign 2.0  external entitlements on a new contact", groups = { "Draco Smoke" })
		public void TC0106_DRACO() throws Exception {
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
			Entitlement_Name.add("Developer Zone Premier(CNDA required)");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToLightningExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickButton(Button.New.toString());
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
			objAdminFunctions.createNewContact(objAdminDataDetails);
			
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			
			/*String contactName2 = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName2);*/
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandTechnicalContentAndTools();
			
			objAdminFunctions.checkDeveloperZonePremierCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			seleniumObj.waitForSeconds(60);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			seleniumObj.waitForSeconds(60);
		   do{
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
		   }
		   while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.internalEntitlementStatus();
			objAdminFunctions.switchToTab(Tabs.Related.toString());
			seleniumObj.waitForSeconds(20);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			//objAdminFunctions.validateContactEntitlementsAssignment(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			
			objNavigation.logoutFromApplication();

		}
		
		/*------------------------------------------------------------------------------Priti---------------------------------------------------------------------------------------------------------------------------------------------------*/
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Activity Manager (Account Plan MDF) as below - "
				+ "IPA Activity Manager (Account Plan MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)--> Market Development Funds (Account MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0071_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAActivityManager();
			objAdminFunctions.checkPopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Claim Submitter (Account Plan MDF) as below - "
				+ "IPA Claim Submitter (Account Plan MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Account MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0072_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAClaimSubmitterAccountPlan();
			objAdminFunctions.checkPopUpMsg();
		
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();

		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Claim Submitter (Proposal MDF)  as below -"
				+ " IPA Claim Submitter (Proposal MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Proposal MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0073_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAClaimSubmitterProposalMDF();
			objAdminFunctions.checkPopUpMsg();
			//objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();

		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA External View Only (Account Plan MDF) as below - "
				+ "IPA External View Only (Account Plan MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Account MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0074_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAExternalViewAccountPlan();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA External View Only (Proposal MDF) as below -"
				+ " IPA External View Only (Proposal MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Proposal MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0075_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAExternalViewProposalMDF();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();

		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Proposal Manager (Proposal MDF) as below -"
				+ "IPA Proposal Manager (Proposal MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Proposal MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0076_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPAProposalManager();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();

		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Recovery Administrator (Account Plan MDF) as below -"
				+ "IPA Recovery Administrator (Account Plan MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Account MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0077_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPARecoveryAdministratorAccountPlan();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();

		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of IPA Recovery Administrator (Proposal MDF) as below -"
				+ "IPA Recovery Administrator (Proposal MDF) Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Market Development Funds (Proposal MDF)-CCP",
				groups={"Draco Smoke"})
		public void TC0078_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkIPARecoveryAdministratorProposalMDF();
			objAdminFunctions.checkPopUpMsg();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of MDF Claim Submitter as below - "
				+ "MDF Claim Submitter Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-"
				+ "->Market Development Funds (MDF)-CCP"
				+ "->Market Development Funds (MDF)-CCPPilot"
				+ "->Market Development Funds (MDF)-IMX",	groups={"Draco Smoke"})
		public void TC0079_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFClaimSubmitter();
			objAdminFunctions.check3PopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of MDF Proposal Manager as below - "
				+ "MDF Proposal Manager Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-"
				+ "->Market Development Funds (MDF)-CCP"
				+ "->Market Development Funds (MDF)-CCPPilot"
				+ "->Market Development Funds (MDF)-IMX",	groups={"Draco Smoke"})
		public void TC0080_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFProposalManager();
			objAdminFunctions.check3PopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of MDF Viewer as below - "
				+ "MDF Viewer Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-"
				+ "->Market Development Funds (MDF)-CCP"
				+ "->Market Development Funds (MDF)-CCPPilot"
				+ "->Market Development Funds (MDF)-IMX",	groups={"Draco Smoke"})
		public void TC0081_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkMDFProposalManager();
			objAdminFunctions.check3PopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of Platform Verification Program (PVP) access as below - "
				+ "PVP Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Platform Verification Program (PVP)",	groups={"Draco Smoke"})
		public void TC0082_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPlatformVerificationProgram();
			objAdminFunctions.checkPopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}

		@Test(description="Verify that popup message is displayed on checking the checkbox in front of Points Manager as below - "
				+ "Points Manager Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Points Redemption",	groups={"Draco Smoke"})
		public void TC0083_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPointsManager();
			objAdminFunctions.checkPopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of Points View as below - "
				+ "Points View Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-->Points Accruals View",	groups={"Draco Smoke"})
		public void TC0084_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkPointsView();
			objAdminFunctions.checkPopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}
		@Test(description="Verify that popup message is displayed on checking the checkbox in front of Warranty Requestor as below - "
				+ "Warranty Requestor Entitlement can only be granted if ACCOUNT/OPID has any one of the following benefit(s)-"
				+ "->Advanced Warranty Replacement"
				+ "->Standard Warranty Replacement",	groups={"Draco Smoke"})
		public void TC0085_DRACO() throws Exception{
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
			String contactName = objAdminDataDetails.getFirstName() + " "+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			String currentContactsURL  = seleniumObj.getDriver().getCurrentUrl();
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkWarrantyRequestor();
			objAdminFunctions.check2PopupMsg();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objNavigation.logoutFromApplication();	
		}
		/**
		 *Verify that user is able to successfully assign Intel® Partner Investment Center entitlements  on a new contact
		 * @throws Exception 
		 * 
		 * @Author Priti
		 * @Since 19-June-2024
		 */
		@Test(description = "Verify that user is able to successfully assign Intel® Partner Investment Center entitlements  on a new contact", groups = { "Draco Smoke" })
		public void TC0103_DRACO() throws Exception {
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
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerInvestmentCenter();
			objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
			objAdminFunctions.checkCCFUserAdministratorCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription()));
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.verifyInternalEntitlementStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			sfcommonObj.pageRefresh();
			seleniumObj.waitForSeconds(60);
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objNavigation.logoutFromApplication();

		}

		/**
		 * Verify that user is able to successfully assign IQR internal entitlements on a new contact 
		 * @throws Exception 
		 * 
		 * @Author Priti
		 * @Since 19-June-2024
		 */
		@Test(description = "Verify that user is able to successfully assign IQR internal entitlements on a new contact", groups = { "Draco Smoke" })
		public void TC0104_DRACO() throws Exception {
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
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("Customer Drafter Access");
			Entitlement_Name.add("Customer User");
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());

			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToLightningExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickButton(Button.New.toString());
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
			objAdminFunctions.createNewContact(objAdminDataDetails);
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelQuoteRequest();
			objAdminFunctions.verifyCustomerDrafterAccessEntitlementPresentOrNot();
			objAdminFunctions.checkCustomerDrafterAccessCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription()));
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.verifyInternalEntitlementStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			//objAdminFunctions.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			sfcommonObj.pageRefresh();
			seleniumObj.waitForSeconds(60);
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objNavigation.logoutFromApplication();

		}
		/**
		 * Verify that user is able to successfully assign internal entitlement on an existing contact 
		 * @throws Exception 
		 * 
		 * @Author Priti
		 * @Since 19-June-2024
		 */
		@Test(description = " Verify that user is able to successfully assign internal entitlement on an existing contact ", groups = { "Draco Smoke" })
		public void TC0107_DRACO() throws Exception {
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
			Entitlement_Name.add("Partner Portal");
			Entitlement_Name.add("PSG Licensing User");

			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());

			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToLightningExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickButton(Button.New.toString());
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
			objAdminFunctions.createNewContact(objAdminDataDetails);
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandProgrammableSolutionsGroup();
			objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
			objAdminFunctions.checkPSGLicensingUserCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription()));
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));
			do{
				sfcommonObj.pageRefresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				}
			while(!objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription()));	
			objAdminFunctions.verifyInternalEntitlementStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			seleniumObj.waitForSeconds(10);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			sfcommonObj.pageRefresh();
			seleniumObj.waitForSeconds(60);
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
			objNavigation.logoutFromApplication();

		}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects Distributor and Reseller entitlements together", groups = { "Draco Smoke" })
		public void TC0060_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandDataCenterBlocksConfigurator();
			
			objAdminFunctions.verifyDistributorPresentOrNot();
			objAdminFunctions.checkDistributorCheckbox();
			
			objAdminFunctions.verifyResellerPresentOrNot();
			objAdminFunctions.checkResellerCheckbox();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
		
			objAdminFunctions.verifyErrorAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects any of the DZS entitlements if an Account associated with that contact doesn't have a CNDA number", groups = { "Draco Smoke" })
		public void TC0061_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandTechnicalContentAndTools();
			
			objAdminFunctions.VerifyDeveloperZonePremierPresentOrNot();
			objAdminFunctions.checkDeveloperZonePremierCheckbox();
			
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			
			objAdminFunctions.verifyERRORAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects any of the IntelÂ® Partner Investment Center entitlements if an Account associated with that contact doesn't have a CIM ID number", groups = { "Draco Smoke" })
		public void TC0062_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerInvestmentCenter();
			
			objAdminFunctions.verifyCCFActivitySubmitterPresentOrNot();
			objAdminFunctions.checkCCFActivitySubmitterCheckbox();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.VerifyErrorMsgAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects any of the PSG B2B entitlements if an Account associated with that contact doesn't have a CIM ID number", groups = { "Draco Smoke" })
		public void TC0063_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandProgrammableSolutionsGroup();
			
			objAdminFunctions.verifyPSGLicensingUserEntitlementPresentOrNot();
			objAdminFunctions.checkPSGLicensingUserCheckbox();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.VerifyErrorMsgAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects any of the IQR entitlements if an Account associated with that contact doesn't have a CIM ID number", groups = { "Draco Smoke" })
		public void TC0064_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelQuoteRequest();
			
			objAdminFunctions.verifyCustomerDrafterAccessEntitlementPresentOrNot();
			objAdminFunctions.checkCustomerDrafterAccessCheckbox();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.VerifyErrorMsgAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects Partner Admin and Partner Admin Delegate entitlements together", groups = { "Draco Smoke" })
		public void TC0065_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			
			objAdminFunctions.verifyPartnerAdminPresentOrNot();
			objAdminFunctions.checkPartnerAdminCheckbox();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			
			objAdminFunctions.verifyPartnerAdminDelegatePresentOrNot();
			objAdminFunctions.checkPADCheckbox();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.VerifyErrorMsgAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that a validation error is thrown on Grant Access if a user selects Partner Admin Partner Admin Delegate  and any of the Marketing Specialist Offering Editor Partner Lead Development Rep entitlements together", groups = { "Draco Smoke" })
		public void TC0066_DRACO() throws Exception {
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
			//Entitlement_Name.add("Data Center Blocks Configurator");
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			
			objAdminFunctions.verifyPartnerAdminPresentOrNot();
			objAdminFunctions.checkPartnerAdminCheckbox();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			
			objAdminFunctions.verifyMarketingSpecialistPresentOrNot();
			objAdminFunctions.checkMarketingSpecialistCheckbox();
			
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.VerifyErrorMessageAfterSAVEOnGrantAccessPage();
			
			objAdminFunctions.clickOnCancelOnGrantAccessPage();
			objNavigation.logoutFromApplication();
}
		@Test(description = "Verify that DZP entitlement is getting removed upon removing an Employee entitlement if the Account associated to that Contact does not have CNDA number on it", groups = { "Draco Smoke" })
		public void TC0067_DRACO() throws Exception {
			String MethodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//contact with cnd no. is null (emp,Dzp both are assigned)
			
			objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
			objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
			String[] filePaths = new String[2];
			filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
			filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
					+ objAdminDataDetails.getFileName();
			objAdminDataDetails.setFilePaths(filePaths);
			
			objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
			Entitlement_Name.add("Employee");
			Entitlement_Name1.add("Employee");
			Entitlement_Name1.add("Developer Zone Premier(CNDA required)"); 
			
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.UncheckEmployeeCheckBox();
			objAdminFunctions.clickOnYesOnGrantAccessPage();
			//objAdminFunctions.clickOnSaveOnGrantAccessPage();
			
			//objAdminFunctions.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
		   objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name1);
			objNavigation.logoutFromApplication();
}
		@Test(description = "Assignment of DZP upon assigning employee entitlement Non CNDA", groups = { "Draco Smoke" })
		public void TC0068_DRACO() throws Exception {
			String MethodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			 //contact with cnd no. is null 
			
			
			objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
			objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
			String[] filePaths = new String[2];
			filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
			filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
					+ objAdminDataDetails.getFileName();
			objAdminDataDetails.setFilePaths(filePaths);
			
			objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
			Entitlement_Name.add("Employee");
			Entitlement_Name1.add("Employee");
			Entitlement_Name1.add("Developer Zone Premier(CNDA required)"); 
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.checkEmployeeCheckbox();
			objAdminFunctions.clickOnOkOnGrantAccessPage();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			seleniumObj.waitForSeconds(05);
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandTechnicalContentAndTools();
			objAdminFunctions.VerifyDeveloperZonePremierPresentOrNot();
			objAdminFunctions.checkDeveloperZonePremierCheckbox();
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name1);
			objNavigation.logoutFromApplication();
			
}
		@Test(description = "CNDA Account's contact Removal of Employee entitltment should not remove DZP entitlement", groups = { "Draco Smoke" })
		public void TC0069_DRACO() throws Exception {
			String MethodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//contact with cnd no. (emp,Dzp both are assigned)
			
			objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
			objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
			String[] filePaths = new String[2];
			filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
			filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
					+ objAdminDataDetails.getFileName();
			objAdminDataDetails.setFilePaths(filePaths);
			
			objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
			Entitlement_Name.add("Employee");
			Entitlement_Name1.add("Employee");
			Entitlement_Name1.add("Developer Zone Premier(CNDA required)"); 
			
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			objAdminFunctions.ClickOnshowMoreActions();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			objAdminFunctions.expandIntelPartnerAlliance();
			objAdminFunctions.UncheckEmployeeCheckBox();
			objAdminFunctions.clickOnYesOnGrantAccessPage();
			//objAdminFunctions.clickOnSaveOnGrantAccessPage();
			
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
		   objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
			objAdminFunctions.goBackToContactsPage();
			seleniumObj.waitForSeconds(5);
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.validateContactEntitlementsRemoval(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name1);
			objNavigation.logoutFromApplication();
}
		@Test(description = "CNDA Account's contact Removal of Employee entitltment should not remove DZP entitlement", groups = { "Draco Smoke" })
		public void TC0070_DRACO() throws Exception {
			String MethodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//contact with cnd no. (emp,Dzp both are assigned)
			
			objAdminDataDetails.setFilePath(CommonEnum.UploadFile.FILEPATH.getDescription());
			objAdminDataDetails.setFileName(CommonEnum.UploadFile.FILENAME.getDescription());
			String[] filePaths = new String[2];
			filePaths[0] = System.getProperty("user.dir") + configObj.getAutoITFileUploadExePath();
			filePaths[1] = System.getProperty("user.dir") + objAdminDataDetails.getFilePath()
					+ objAdminDataDetails.getFileName();
			objAdminDataDetails.setFilePaths(filePaths);
			
			objAdminFunctions.setAdminDataDetails(objAdminDataDetails, objAdminData, MethodName);
			Entitlement_Name.add("Employee");
			Entitlement_Name1.add("Employee");
			Entitlement_Name1.add("Developer Zone Premier(CNDA required)"); 
			
			
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
					objAdminData.getRole());
			objAdminFunctions.switchToLightningExperience();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String contactName = objAdminDataDetails.getFirstName() + " "
					+ objAdminDataDetails.getLastName();
			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objAdminDataDetails.getEmail(), contactName);
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.switchToLightningExperience();
			objAdminFunctions.switchToTab(Tabs.Contacts.toString());
			objAdminFunctions.clickButton(Button.New.toString());
			objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
			objAdminFunctions.createNewContact(objAdminDataDetails);
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			seleniumObj.waitForSeconds(60);
			seleniumObj.waitForSeconds(60);
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			seleniumObj.waitForSeconds(05);
			sfcommonObj.pageRefresh();
			objAdminFunctions.clickOnContactEntitlementsAndVerifyNoCERecordCreated();
			objAdminFunctions.clickonEditOnContactPage();
			objAdminFunctions.CheckRepublishOBMCheckbox();
			objAdminFunctions.ClickOneditSave();
			objAdminFunctions.AGSIntegrationStatusShouldNotShowPendingStatus();
			objNavigation.logoutFromApplication();
}

}
