package com.intel.ebsqa.draco.Testscripts.ui;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intel.ebsqa.draco.BusinessFunction.ui.AdminFunctions;
import com.intel.ebsqa.draco.BusinessFunction.ui.ExternalApplicationNavigation;
import com.intel.ebsqa.draco.BusinessFunction.ui.Navigation;
import com.intel.ebsqa.draco.BusinessFunction.ui.SF_User_Creation;
import com.intel.ebsqa.draco.DataClass.AdminData;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData;
import com.intel.ebsqa.draco.DataClass.AdminData.AdminDataDetails;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData.ExternalCustomerUIDataDetails;
import com.intel.ebsqa.draco.enums.Button;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.Tabs;
import com.intel.ebsqa.draco.enums.CommonEnum.ApplicationType;
import com.intel.ebsqa.draco.enums.CommonEnum.BooleanValues;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalSearchResultDescription_Plural;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.draco.utility.MongoDBRepository;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ManagePersonalTest extends TestBase {
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

	public ManagePersonalTest() {

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
	 * Verify that user is able to send a invite to a new contact from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 07-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to a new contact from manage personnel", groups = { "Draco Smoke" })
	public void TC0041_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		objNavigation.logoutFromApplication();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteNewUser(objAdminDataDetails.getFirstName(),objAdminDataDetails.getLastName(),objAdminDataDetails.getCountry());
     	seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
	}
	/**
	 * Verify that user is able to send a invite to an existing  contact with Inactive ACR of same Accountfrom manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 07-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing  contact with Inactive ACR of same Accountfrom manage personnel", groups = { "Draco Smoke" })
	public void TC0042_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		sfcommonObj.waitTillLightningPageLoadComplete();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		objNavigation.logoutFromApplication();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
	}
	/**
	 * Verify that user is able to send a invite to an existing soft contact with same Account as that of Logged in user from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 08-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing soft contact with same Account as that of Logged in user from manage personnel", groups = { "Draco Smoke" })
	public void TC0043_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		//objAdminFunctions.switchToLightningExperience();
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
        
	}
	
	/**
	 * Verify that user is able to send a invite to an existing non-ipa user with same Account as that of Logged in user from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 08-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing non-ipa user with same Account as that of Logged in user from manage personnel", groups = { "Draco Smoke" })
	public void TC0044_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
        
        
	}
	/**
	 * Verify that user is able to send a invite to an existing  contact with Inactive ACR of different Account same OPID and same Partner Role as that of logged in user from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 08-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing  contact with Inactive ACR of different Account same OPID and same Partner Role as that of logged in user from manage personnel", groups = { "Draco Smoke" })
	public void TC0045_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		//objAdminFunctions.switchToLightningExperience();
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
        
	}
	/**
	 * Verify that user is able to send a invite to an existing soft contact with different Account same OPID and same Partner role  as that of Logged in user from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 08-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing soft contact with different Account same OPID and same Partner role  as that of Logged in user from manage personnel", groups = { "Draco Smoke" })
	public void TC0046_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		//objAdminFunctions.switchToLightningExperience();
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		seleniumObj.waitTillPageLoadIsComplete();
		seleniumObj.waitTillPageLoadIsComplete();
		
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
        
	}
	/**
	 *Verify that user is able to send a invite to an existing non-ipa user with same Account as that of Logged in user from manage personnel
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 08-Feb-2023
	 */
	@Test(description = "Verify that user is able to send a invite to an existing non-ipa user with same Account as that of Logged in user from manage personnel", groups = { "Draco Smoke" })
	public void TC0047_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		sfcommonObj.waitTillLightningPageLoadComplete();
		//objAdminFunctions.switchToLightningExperience();
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail());
		objNavigation.logintoIntelPartnerAlliance();
		objNavigation.switchToOverviewPage();
		objNavigation.AddSecondaryUserEmail(objAdminDataDetails.getEmail2());
		objNavigation.InviteUserAsExistingACR();
		seleniumObj.waitForSeconds(20);
		seleniumObj.waitForSeconds(20);
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyInvitedUser(objAdminDataDetails.getEmail2());
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
//		String contactName = objAdminDataDetails.getFirstName2() + " "
//				+ objAdminDataDetails.getLastName2();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail2(), contactName);
		objNavigation.getSalesforceSearchIdContact(objAdminDataDetails.getEmail2());
		//write code for invite mail acceptance
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.verifyEmployeeStatus();
		objNavigation.logoutFromApplication();
        
	}
	
	/**
	 *Verify that a user gets added to Public Group named as PMP_OnePartnerID, if it gets assigned with Employee entitlement successfully
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 09-Feb-2023
	 */
	@Test(description = "Verify that a user gets added to Public Group named as PMP_OnePartnerID, if it gets assigned with Employee entitlement successfully", groups = { "Draco Smoke" })
	public void TC0048_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
 	    objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
 	   // objAdminFunctions.ClickOnshowMoreActions();
 	    objAdminFunctions.clickOnGrantAccessButton();
 	    //objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandIntelPartnerAlliance();
		//objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkPartnerAdminCheckbox();
		
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		//sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		seleniumObj.pageRefresh();
		objAdminFunctions.clickQuickFind();
		objAdminFunctions.verifyUser();
		seleniumObj.waitTillPageLoadIsComplete();
	}
	/**
	 *Verify that a user gets added to Public Group named as PMP_OnePartnerID_Leads, if it gets assigned with Partner Admin/Partner Admin Delegate entitlement successfully
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 09-Feb-2023
	 */
	@Test(description = "Verify that a user gets added to Public Group named as PMP_OnePartnerID_Leads, if it gets assigned with Partner Admin/Partner Admin Delegate entitlement successfully", groups = { "Draco Smoke" })
	public void TC0049_DRACO() throws Exception {
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
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
 	    objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
 	   // objAdminFunctions.ClickOnshowMoreActions();
 	    objAdminFunctions.clickOnGrantAccessButton();
 	    //objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandIntelPartnerAlliance();
		//objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkPartnerAdminCheckbox();
		
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		//sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		seleniumObj.pageRefresh();
		objAdminFunctions.clickQuickFind();
		objAdminFunctions.verifyUserAsLead();
		seleniumObj.waitTillPageLoadIsComplete();
	}
	/**
	 *Verify that a user gets added to Public Group named as PMP_OnePartnerID_LeadsOfEmployees, if it gets assigned with Marketing Specialist/Offering Editor/Partner Lead Development Rep entitlement successfully
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 24-Feb-2023
	 */
	@Test(description = "Verify that a user gets added to Public Group named as PMP_OnePartnerID_LeadsOfEmployees, if it gets assigned with Marketing Specialist/Offering Editor/Partner Lead Development Rep entitlement successfully", groups = { "Draco Smoke" })
	public void TC0050_DRACO() throws Exception {
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
		Entitlement_Name.add("Partner Lead Development Rep");
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		sfcommonObj.waitTillLightningPageLoadComplete();
		String contactName = objAdminDataDetails.getFirstName() + " "
				+ objAdminDataDetails.getLastName();
//		objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//				GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//				objAdminDataDetails.getEmail(), contactName);
		//objAdminFunctions.switchToLightningExperience();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
 	    objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
 	   // objAdminFunctions.ClickOnshowMoreActions();
 	    objAdminFunctions.clickOnGrantAccessButton();
 	    //objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		objAdminFunctions.expandIntelPartnerAlliance();
		//objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkPartnerLeadDevCheckbox();
		
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		//sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		seleniumObj.pageRefresh();
		objAdminFunctions.clickQuickFind();
		objAdminFunctions.verifyUserAsLeadOfEmployee();
		seleniumObj.waitTillPageLoadIsComplete();
	}
	/**
	 *Verify that a user gets added to Public Group named as PMP_OnePartnerID_LeadsOfEmployees, if it gets assigned with Marketing Specialist/Offering Editor/Partner Lead Development Rep entitlement successfully
	 * @throws Exception 
	 * 
	 * @Author Amartyax
	 * @Since 24-Feb-2023
	 */
	@Test(description = "Verify that a user gets added to Public Group named as PMP_OnePartnerID_Internet_of_Thing, if  Internet of Things is added as a Contact Segment on Contact provided Partner Lead Development Rep entitlement has been assigned successfully on the Contact", groups = { "Draco Smoke" })
	public void TC0051_DRACO() throws Exception {
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
		Entitlement_Name.add("Partner Lead Development Rep");
//		Entitlement_Name.add("Partner Portal");
//		Entitlement_Name.add("CCF User Administrator");
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		sfcommonObj.waitTillLightningPageLoadComplete();
		//objAdminFunctions.switchToLightningExperience();
		objAdminFunctions.switchToTab(Tabs.Contacts.toString());
		objAdminFunctions.clickButton(Button.New.toString());
		objAdminFunctions.selectContactTypeAsIntelContactAndClickNext();
		objAdminFunctions.createNewContact(objAdminDataDetails);
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
 	    objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
// 	    objAdminFunctions.checkContactSegment();
 	   // objAdminFunctions.ClickOnshowMoreActions();
//		 String contactName = objAdminDataDetails.getFirstName() + " "
//					+ objAdminDataDetails.getLastName();
//			objNavigation.globalUISearchContactAndSelect(objAdminDataDetails.getAccountName(),
//					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
//					objAdminDataDetails.getEmail(), contactName);
 	    objAdminFunctions.clickOnGrantAccessButton();
		objAdminFunctions.expandIntelPartnerAlliance();
		//objAdminFunctions.verifyCCFUserAdministratorPresentOrNot();
		objAdminFunctions.checkPartnerLeadDevCheckbox();
		objAdminFunctions.clickOnYesOnGrantAccessPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
	    objAdminFunctions.verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),Entitlement_Name);
		objAdminFunctions.goBackToContactsPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
		sfcommonObj.pageRefresh();
		sfcommonObj.pageRefresh();
		objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfContactEntitlements();
		objAdminFunctions.verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),Entitlement_Name);
		objAdminFunctions.clickOnContactEntitlements();
		objAdminFunctions.verifyStatusofAssignmentOrRemoval();
		objAdminFunctions.verifyUpdateReason();
		objAdminFunctions.goBackToContactPage();
		sfcommonObj.pageRefresh();
		objAdminFunctions.switchToTab(Tabs.Membership.toString());
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.clickOnViewAllOfPartnerContactRelationship();
		seleniumObj.waitForSeconds(10);
		objAdminFunctions.goBackToContactsPage();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.clickOnViewPartnerUserButton();
		seleniumObj.waitTillPageLoadIsComplete();
		objAdminFunctions.verifyActiveCheckboxOnUserIsCheckedOrNot();
		seleniumObj.waitTillPageLoadIsComplete();
		seleniumObj.pageRefresh();
		objAdminFunctions.clickQuickFind();
		objAdminFunctions.verifyUserAsInternetOfThings();
		seleniumObj.pageRefresh();
		objAdminFunctions.clickQuickFind();
		objAdminFunctions.verifyUserAsLeadOfEmployee();
		seleniumObj.waitTillPageLoadIsComplete();
	}
}
