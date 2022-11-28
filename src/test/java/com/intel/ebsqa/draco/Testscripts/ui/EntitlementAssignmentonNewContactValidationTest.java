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
import com.intel.ebsqa.draco.enums.CommonEnum;
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
	 * Creation of contact and verify Entitlements of CCFadminUser
	 * @throws Exception 
	 * 
	 * @Author manish9x
	 * @Since 24-Nov-2022
	 */
	@Test(description = "Creation of contact and verify Entitlements of CCFadminUser", groups = { "Draco Smoke" })
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
		
		sfcommonObj.loginToApplicationAs(ApplicationType.Draco_INTERNAL_CUSTOMER.getDescription(),
				objAdminData.getRole());
		
		
		
		
		//objNavigation.logoutFromApplication();

	}
	
}
