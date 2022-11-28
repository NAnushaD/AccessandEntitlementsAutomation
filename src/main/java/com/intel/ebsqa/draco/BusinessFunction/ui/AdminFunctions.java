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

import com.intel.ebsqa.draco.DataClass.AdminData;
import com.intel.ebsqa.draco.DataClass.AdminData.AdminDataDetails;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.PageClasses.OperationPageClass;
import com.intel.ebsqa.draco.PageClasses.SF_User_CreationPageClass;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.ExcelEnum;
import com.intel.ebsqa.draco.enums.FieldName;
import com.intel.ebsqa.draco.enums.CommonEnum.CustomDateFormat;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.intel.ebsqa.helperclass.StringUtils;

/**
 * @Description
 * @Author manish9x
 * @Since 23-Nov-2022
 */

public class AdminFunctions extends TestBase {
	OperationPageClass objOperationPageClass = null;

	public AdminFunctions() {
		objOperationPageClass = new OperationPageClass();
	}

	/**
	 * Method to create new setAdminDataDetails
	 * 
	 * @Author manish9x
	 * @Since 23-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void setAdminDataDetails(AdminDataDetails objAdminDataDetails,
			AdminData objAdminData, String method) throws TimeOutException {
		try {

			String filepath = System.getProperty("user.dir") + configObj.getFilexlsxPath();
			String sheetName = "";
			ArrayList<String> excelData = new ArrayList<String>();
			if (configObj.getEnvironment().equals("DEVINT")) {
				sheetName = ExcelEnum.ExcelSheetNames.AdminUserData_DEVINT.getDescription();
			}
			if (configObj.getEnvironment().equals("QA")) {
				sheetName = ExcelEnum.ExcelSheetNames.AdminUserData_QA.getDescription();
			}
			excelData = sfcommonObj.GetRowFromExcelSheet(filepath, sheetName, method, "Test Case ID");
			objAdminData.setRole(excelData.get(1));
			
			
			objAdminDataDetails.setEndCustomerAccount(excelData.get(2));
			objAdminDataDetails.setMSVPL1(excelData.get(3));
			objAdminDataDetails.setQuoteOwner(excelData.get(4));
			
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" set setAdminDataDetails created");
		} catch (Exception ex) {
			Assert.fail("set setAdminDataDetails not created " + ex.getMessage());
		}
	}
}
