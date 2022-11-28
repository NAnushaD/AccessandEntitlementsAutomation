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
import com.intel.ebsqa.draco.BusinessFunction.ui.Navigation;
import com.intel.ebsqa.draco.BusinessFunction.ui.SF_User_Creation;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData;
import com.intel.ebsqa.draco.DataClass.ExternalCustomerUIData.ExternalCustomerUIDataDetails;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.ExcelEnum;
import com.intel.ebsqa.draco.enums.CommonEnum.ApplicationType;
import com.intel.ebsqa.draco.enums.CommonEnum.BooleanValues;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalNavigator;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalSearchResultDescription;
import com.intel.ebsqa.draco.enums.CommonEnum.GlobalSearchResultDescription_Plural;
import com.intel.ebsqa.draco.enums.ExternalUserEnum.ExternalUserMenu;
import com.intel.ebsqa.draco.enums.ExternalUserEnum.ExternalUserSubMenu;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.draco.library.common.ExcelCommon;
import com.intel.ebsqa.draco.utility.MongoDBRepository;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @Description
 * @Author kumark8x
 * @Since 18-May-2021
 */

public class SF_User_CreationTest extends TestBase {
	MongoDBRepository<SF_User_CreationTest> objMongoDBContext;
	DBCollection objSF_User_CreationCollData;
	SF_User_CreationData objSF_User_CreationData;
	SF_User_CreationDetails objSF_User_CreationDetails;
	Navigation objNavigation;
	SF_User_Creation objSF_User_Creation;
	ExternalApplicationNavigation objExternalApplicationNavigation;
	ExternalCustomerUIData objExternalCustomerUIData;
	ExternalCustomerUIDataDetails objExternalCustomerUIDetails;

	@BeforeTest(alwaysRun = true)
	public void BeforeTestcase() {

		objMongoDBContext = new MongoDBRepository<SF_User_CreationTest>("SF_users_creation");
		objSF_User_CreationCollData = objMongoDBContext._collection;
		objSF_User_CreationData = new SF_User_CreationData();
		objSF_User_CreationDetails = objSF_User_CreationData.new SF_User_CreationDetails();
		objExternalCustomerUIData = new ExternalCustomerUIData();
		objExternalCustomerUIDetails = objExternalCustomerUIData.new ExternalCustomerUIDataDetails();
	}

	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod() {
		sfcommonObj.setUp(com.salesforce.lpop.framework.markers.ApplicationType.SALES_LEX);
		objNavigation = new Navigation();
		objSF_User_Creation = new SF_User_Creation();
		objExternalApplicationNavigation = new ExternalApplicationNavigation();

		objExternalApplicationNavigation = new ExternalApplicationNavigation();
	}

	public SF_User_CreationTest() {

		objMongoDBContext = new MongoDBRepository<SF_User_CreationTest>("SF_User_Creation");
		objSF_User_CreationCollData = objMongoDBContext._collection;
		objSF_User_CreationData = new SF_User_CreationData();
		objSF_User_CreationDetails = objSF_User_CreationData.new SF_User_CreationDetails();
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
			objSF_User_CreationData = gson.fromJson(mongoObject.toString(), SF_User_CreationData.class);

			BasicDBList PMB = (BasicDBList) mongoObject.get("SF_User_CreationDetails");
			for (int i = 0; i < PMB.size(); i++) {
				BasicDBObject PMBobj = (BasicDBObject) PMB.get(i);
				if (PMBobj.getString("Environment").equals(configObj.getEnvironment())) {
					objSF_User_CreationDetails = gson.fromJson(PMBobj.toString(), SF_User_CreationDetails.class);
				}
			}
		}
	}

	/**
	 * SF User Creation 1
	 * 
	 * @Author kumark8x
	 * @Since 06-June-2021
	 * @throws InterruptedException
	 * @throws TimeOutException
	 * @throws IOException
	 */
	@Test(description = "SF Internal User Creation 1", groups = { "Salesforce UserCreation" })
	public void SF_Internal_User_Creation_1() throws InterruptedException, TimeOutException, IOException {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String filepath = System.getProperty("user.dir") + configObj.getFilexlsxPath();
		int rowCount = sfcommonObj.getRowCount(filepath, "InternalUserData");
		for (int i = 1; i <= rowCount; i++) {
			objSF_User_CreationData.setRole(sfcommonObj.GetCellValue(filepath, "InternalUserData", "User", i + 1));
			objSF_User_CreationDetails
					.setFirstName(sfcommonObj.GetCellValue(filepath, "InternalUserData", "FirstName", i + 1));
			objSF_User_CreationDetails
					.setLastName(sfcommonObj.GetCellValue(filepath, "InternalUserData", "LastName", i + 1));
			objSF_User_CreationDetails.setEmail(sfcommonObj.GetCellValue(filepath, "InternalUserData", "Email", i + 1));
			objSF_User_CreationDetails
					.setUserName(sfcommonObj.GetCellValue(filepath, "InternalUserData", "UserName", i + 1));
			objSF_User_CreationDetails
			.setNickName(sfcommonObj.GetCellValue(filepath, "InternalUserData", "NickName", i + 1));

			objSF_User_CreationDetails
					.setUserRole(sfcommonObj.GetCellValue(filepath, "InternalUserData", "UserRole", i + 1));
			objSF_User_CreationDetails
					.setProfile(sfcommonObj.GetCellValue(filepath, "InternalUserData", "Profile", i + 1));
			objSF_User_CreationDetails
					.setUserLicense(sfcommonObj.GetCellValue(filepath, "InternalUserData", "UserLicense", i + 1));
			objSF_User_CreationDetails.setPublicGroupView(
					sfcommonObj.GetCellValue(filepath, "InternalUserData", "Public_Group_View", i + 1));
			objSF_User_CreationDetails.setSearchSharingInPublicGroup(
					sfcommonObj.GetCellValue(filepath, "InternalUserData", "Search_Sharing_In_PublicGroup", i + 1));
			objSF_User_CreationDetails
					.setUsersView(sfcommonObj.GetCellValue(filepath, "InternalUserData", "UsersView", i + 1));
			objSF_User_CreationDetails
					.setCheckbox(sfcommonObj.GetCellValue(filepath, "InternalUserData", "Checkbox", i + 1));
			String publicGrps = sfcommonObj.GetCellValue(filepath, "InternalUserData", "Public_Groups", i + 1);
			String[] pBGrps = publicGrps.split("[,]", 0);
			List<String> pBGrpsVals = new ArrayList<String>();
			for (String val : pBGrps) {
				pBGrpsVals.add(val);
			}
			objSF_User_CreationDetails.setPublicGroups(pBGrpsVals);
			String permSets = sfcommonObj.GetCellValue(filepath, "InternalUserData", "Permission_Sets", i + 1);
			String[] prmSets = permSets.split("[,]", 0);
			List<String> prmSetsVals = new ArrayList<String>();
			for (String val : prmSets) {
				prmSetsVals.add(val);
			}
			objSF_User_CreationDetails.setPermissionSets(prmSetsVals);
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),objSF_User_CreationData.getRole());
			objSF_User_Creation.clickOnSetup();
			objSF_User_Creation.clickOnSetupTool();
			objSF_User_Creation.createSFUser(objSF_User_CreationDetails);
			objSF_User_Creation.addPermissionSetsTo_SFUser(objSF_User_CreationDetails);
			objSF_User_Creation.addSFUserToPublicGroups(objSF_User_CreationDetails);
			String user = objSF_User_CreationDetails.getFirstName()+" "+objSF_User_CreationDetails.getLastName();
			log.info(user + ":Internal User is created");
			System.out.println(user + ":Internal User is created");
			objNavigation.logoutFromApplication();
		}

	}

	/**
	 * External User Creation 1
	 * 
	 * @throws Exception
	 * 
	 * @Author kumark8x
	 * @Since 06-June-2021
	 */
	@Test(description = "External User Creation 1", groups = { "Salesforce UserCreation" })
	public void External_User_Creation_1() throws Exception {
		String MethodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String filepath = System.getProperty("user.dir") + configObj.getFilexlsxPath();
		int rowCount = sfcommonObj.getRowCount(filepath, "ExternalUserData");
		for (int i = 1; i <= rowCount; i++) {
			objSF_User_CreationData.setRole(sfcommonObj.GetCellValue(filepath, ExcelEnum.ExcelSheetNames.EXTERNAL_USER_DATA.getDescription(), "User", i + 1));
			objSF_User_CreationDetails
					.setSalutation(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Salutation", i + 1));
			objSF_User_CreationDetails
					.setFirstName(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "FirstName", i + 1));
			objSF_User_CreationDetails
					.setLastName(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "LastName", i + 1));
			objSF_User_CreationDetails.setEmail(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Email", i + 1));
			objSF_User_CreationDetails
					.setUserName(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "UserName", i + 1));
			objSF_User_CreationDetails
					.setAccountName(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "AccountName", i + 1));
			objSF_User_CreationDetails
					.setAccountCIMID(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "AccountCIMID", i + 1));
			objSF_User_CreationDetails
					.setAccountID(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "AccountID", i + 1));
			objSF_User_CreationDetails.setContactRecordType(
					sfcommonObj.GetCellValue(filepath, "ExternalUserData", "ContactRecordType", i + 1));
			objSF_User_CreationDetails
					.setProfile(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Profile", i + 1));
			objSF_User_CreationDetails.setSearchSharingInPublicGroup(
					sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Search_Sharing_In_PublicGroup", i + 1));
			objSF_User_CreationDetails.setPublicGroupView(
					sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Public_Group_View", i + 1));
			objSF_User_CreationDetails
					.setCheckbox(sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Checkbox", i + 1));

			String publicGrps = sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Public_Groups", i + 1);
			String[] pBGrps = publicGrps.split("[,]", 0);
			List<String> pBGrpsVals = new ArrayList<String>();
			for (String val : pBGrps) {
				pBGrpsVals.add(val);
			}
			objSF_User_CreationDetails.setPublicGroups(pBGrpsVals);
			String permSets = sfcommonObj.GetCellValue(filepath, "ExternalUserData", "Permission_Sets", i + 1);
			String[] prmSets = permSets.split("[,]", 0);
			List<String> prmSetsVals = new ArrayList<String>();
			for (String val : prmSets) {
				prmSetsVals.add(val);
			}
			objSF_User_CreationDetails.setPermissionSets(prmSetsVals);
			sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),objSF_User_CreationData.getRole());
			Navigation navigationObj = new Navigation();
			navigationObj.globalUISearchAccountAndSelect(objSF_User_CreationDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.ACCOUNTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objSF_User_CreationDetails.getAccountCIMID(), objSF_User_CreationDetails.getAccountID());
			objSF_User_Creation.createNewContact(objSF_User_CreationDetails);
			String contactName = objSF_User_CreationDetails.getFirstName() + " "
					+ objSF_User_CreationDetails.getLastName();
			navigationObj.globalUISearchContactAndSelect(objSF_User_CreationDetails.getAccountName(),
					GlobalSearchResultDescription_Plural.CONTACTS.getDescription(), BooleanValues.TRUE.getDescription(),
					objSF_User_CreationDetails.getEmail(), contactName);
			objSF_User_Creation.Update_SFUserDetails_External(objSF_User_CreationDetails);
			objSF_User_Creation.addPermissionSetsTo_SFUser(objSF_User_CreationDetails);
			objSF_User_Creation.addSFUserToPublicGroupsExternal(objSF_User_CreationDetails);
			log.info(contactName + " : External User is created");
			System.out.println(contactName + " : External User is created");
			objNavigation.logoutFromApplication();

		}

	}

}
