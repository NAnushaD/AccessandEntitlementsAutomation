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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.intel.ebsqa.draco.DataClass.AdminData;
import com.intel.ebsqa.draco.DataClass.AdminData.AdminDataDetails;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.PageClasses.OperationPageClass;
import com.intel.ebsqa.draco.PageClasses.SF_User_CreationPageClass;
import com.intel.ebsqa.draco.enums.Button;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.ExcelEnum;
import com.intel.ebsqa.draco.enums.FieldName;
import com.intel.ebsqa.draco.enums.Tabs;
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
			
			//objAdminDataDetails.setSalutationclassic(excelData.get(2));
			objAdminDataDetails.setFirstName(excelData.get(3));
			objAdminDataDetails.setLastName(excelData.get(4));
			objAdminDataDetails.setEmail(excelData.get(5));
			objAdminDataDetails.setAccountName(excelData.get(6));
			objAdminDataDetails.setFilter(excelData.get(15));
			objAdminDataDetails.setNonExistingEmail(excelData.get(7));
			objAdminDataDetails.setFirstName2(excelData.get(8));
			objAdminDataDetails.setLastName2(excelData.get(9));
			objAdminDataDetails.setAccountType(excelData.get(10));
			objAdminDataDetails.setAccountName2(excelData.get(11));
			objAdminDataDetails.setAccountType2(excelData.get(12));
			objAdminDataDetails.setAccountName3(excelData.get(13));
			objAdminDataDetails.setExistingEmail(excelData.get(14));
			
			
		
			objAdminDataDetails.setFirstNameclassic(excelData.get(18));
			objAdminDataDetails.setLastNameclassic(excelData.get(19));
			objAdminDataDetails.setEmailclassic(excelData.get(20));
            objAdminDataDetails.setEmail2(excelData.get(16));
			objAdminDataDetails.setAccountNameclassic(excelData.get(21));
			objAdminDataDetails.setNonExistingEmailclassic(excelData.get(22));
			objAdminDataDetails.setFirstName2classic(excelData.get(23));
			objAdminDataDetails.setLastName2classic(excelData.get(24));
			objAdminDataDetails.setAccountTypeclassic(excelData.get(25));
			objAdminDataDetails.setAccountName2classic(excelData.get(26));
			objAdminDataDetails.setAccountType2classic(excelData.get(27));
			objAdminDataDetails.setAccountName3classic(excelData.get(28));
			objAdminDataDetails.setExistingEmailclassic(excelData.get(29));
			objAdminDataDetails.setFilterclassic(excelData.get(30));
			
		
			//System.out.println("Email " + objAdminDataDetails.getExistingEmail());
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" set setAdminDataDetails created");
		} catch (Exception ex) {
			Assert.fail("set setAdminDataDetails not created " + ex.getMessage());
		}
	}
	
	
	/**
	 * @Description Method to switchToLightningExperience
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 */
	public void switchToLightningExperience() throws TimeOutException {
		try {
            
			objOperationPageClass.switchToLightningExperience();
            }
            //sfcommonObj.waitTillLightningPageLoadComplete();
			//log.info("Succussfully switchToLightningExperience");
		//}

		catch (Exception ex) {
			Assert.fail("Not able to switchToLightningExperience. " + ex.getMessage());
		}
	}
	public void switchToClassicExperience() throws TimeOutException {
		try {
            
			objOperationPageClass.switchToClassicExperience();
            }
            //sfcommonObj.waitTillLightningPageLoadComplete();
			//log.info("Succussfully switchToClassicExperience");
		//}

		catch (Exception ex) {
			Assert.fail("Not able to switchToClassicExperience. " + ex.getMessage());
		}
	}
	public void clickonClassicExperience() throws TimeOutException {
		try {
            
			objOperationPageClass.clickonClassicExperience();
            }
            //sfcommonObj.waitTillLightningPageLoadComplete();
			//log.info("Succussfully clickonClassicExperience");
		//}

		catch (Exception ex) {
			Assert.fail("Not able to clickonClassicExperience. " + ex.getMessage());
		}
	}
	/**
	 * @Description Method to switchToTab
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 */
	public void switchToTab(String tab) throws TimeOutException {
		try {

			objOperationPageClass.switchToTab(tab);
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully switchToTab");
		}

		catch (Exception ex) {
			Assert.fail("Not able to switchToTab. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickButton
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickButton(String button) throws TimeOutException, InterruptedException {
		try {

			objOperationPageClass.clickButton(button);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickButton :" +button);
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on :" +button);
		}
		
	}
	public void clickContactsClassic() throws TimeOutException, InterruptedException {
		try {

			objOperationPageClass.clickContactsClassic();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickButton :");
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on :");
		}
		
	}
	/**
	 * @Description Method to selectContactTypeAsIntelContactAndClickNext
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 */
	public void selectContactTypeAsIntelContactAndClickNext() throws TimeOutException, InterruptedException {
		try {

			objOperationPageClass.selectContactTypeAsIntelContact();
			objOperationPageClass.clickButton(Button.Next.toString());
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully selectContactTypeAsIntelContactAndClickNext");
		}

		catch (Exception ex) {
			Assert.fail("Not able to selectContactTypeAsIntelContactAndClickNext. " + ex.getMessage());
		}
	}
		public void selectContactTypeAsIntelContactAndClickNextClassic() throws TimeOutException, InterruptedException {
			try {

				objOperationPageClass.selectContactTypeAsIntelContactAndClickNextClassic();
				//objOperationPageClass.clickButton(Button.Continue.toString());
				sfcommonObj.waitTillLightningPageLoadComplete();
				log.info("Succussfully selectContactTypeAsIntelContactAndClickNextClassic");
			}

		catch (Exception ex) {
			Assert.fail("Not able to selectContactTypeAsIntelContactAndClickNextClassic. " + ex.getMessage());
		}
		
		}
		public void clickContinueClassic() throws TimeOutException, InterruptedException {
			try {

				objOperationPageClass.clickContinueClassic();
				//objOperationPageClass.clickButton(Button.Continue.toString());
				sfcommonObj.waitTillLightningPageLoadComplete();
				log.info("Succussfully clickContinueClassic");
			}

		catch (Exception ex) {
			Assert.fail("Not able to clickContinueClassic. " + ex.getMessage());
		}
		
		}
	/**
	 * Method to clickonContactSaluatation
	 * 
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void clickonContactSalutation() throws TimeOutException {
		try {
			objOperationPageClass.clickOnSalutation();

			log.info("Clicked on contact Salutation");

		} catch (Exception ex) {
			Assert.fail("Not able to click on contact Salutation" + ex.getMessage());
		}
	}
	
	public void clickOnNewbuttonContactClassic() throws TimeOutException {
		try {
			objOperationPageClass.clickOnNewbuttonContactClassic();

			log.info("Clicked on New button for contact creation");

		} catch (Exception ex) {
			Assert.fail("Not able to click on contact New button" + ex.getMessage());
		}
	}
	
	public void clickOnDropdownClassic() throws TimeOutException {
		try {
			objOperationPageClass.clickOnDropdownClassic();

			log.info("Clicked on dropdown for contact creation");

		} catch (Exception ex) {
			Assert.fail("Not able to click on dropdown for contact creation" + ex.getMessage());
		}
	}
	
	/**
	 * Method to select salutation for contact
	 * 
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void selectSalutationForContactclassic(String salutation) throws TimeOutException {
		try {
			this.clickonContactSalutation();
			objOperationPageClass.selectSalutationclassic(salutation);
			log.info("salutation for contact is selected: " + salutation);
		} catch (Exception ex) {
			Assert.fail("Not able to select salutation for contact " + ex.getMessage());
		}
	}
	public void selectSalutationForContact(String salutation) throws TimeOutException {
		try {
			this.clickonContactSalutation();
			objOperationPageClass.selectSalutation(salutation);
			log.info("salutation for contact is selected: " + salutation);
		} catch (Exception ex) {
			Assert.fail("Not able to select salutation for contact " + ex.getMessage());
		}
	}
	/**
	 * Method to enter first name contact
	 * 
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterFirstNameContactclassic(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.firstNameContactclassic, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.firstNameContactclassic),
					"first name contact does not exist in the UI");
			objOperationPageClass.enterFirstNameContactclassic(name);
			log.info("Entered first name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter first name contact " + ex.getMessage());
		}
	}
	public void enterFirstNameContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.firstNameContact, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.firstNameContact),
					"first name contact does not exist in the UI");
			objOperationPageClass.enterFirstNameContact(name);
			log.info("Entered first name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter first name contact " + ex.getMessage());
		}
	}
	/**
	 * Method to enter last name contact
	 * 
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterLastNameContactclassic(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.lastNameContactclassic, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.lastNameContactclassic),
					"last name contact does not exist in the UI");
			objOperationPageClass.enterLastNameContactclassic(name);
			log.info("Entered last name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter last name contact " + ex.getMessage());
		}
	}
	public void enterLastNameContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.lastNameContact, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.lastNameContact),
					"last name contact does not exist in the UI");
			objOperationPageClass.enterLastNameContact(name);
			log.info("Entered last name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter last name contact " + ex.getMessage());
		}
	}
	/**
	 * Method to enter email contact
	 * 
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterEmailContactclassic(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.emailclassic, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.emailclassic),
					"email contact does not exist in the UI");
			objOperationPageClass.enterEmailContactclassic(name);
			log.info("Entered email contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter email contact " + ex.getMessage());
		}
	}
	public void enterEmailContact(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.email, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.email),
					"email contact does not exist in the UI");
			objOperationPageClass.enterEmailContact(name);
			log.info("Entered email contact value : " + name);
		 } catch (Exception ex) {
			Assert.fail("Not able to enter email contact " + ex.getMessage());
		 }
	}
	public void enterfilter(String Filter) throws TimeOutException {
		try {
			
			//Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.filterEmail),
			//		"email contact does not exist in the UI");
			objOperationPageClass.enterfilter(Filter);
			log.info("Entered email contact value : " + Filter);
		 } catch (Exception ex) {
			Assert.fail("Not able to enter email contact " + ex.getMessage());
		 }
	}
	
	/**
	 * @Description Method to enterAccountName
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void enterAccountName(String account) throws TimeOutException {
		try {

			objOperationPageClass.enterAccountName(account);
			log.info("Succussfully enterAccountName");
		}

		catch (Exception ex) {
			Assert.fail("Not able to enterAccountName. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to selectAccountName
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void selectAccountName(String account) throws TimeOutException {
		try {

			objOperationPageClass.selectAccountName(account);
			log.info("Succussfully selectAccountName");
		}

		catch (Exception ex) {
			Assert.fail("Not able to selectAccountName. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to enterAndselectAccountName
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void enterAndselectAccountName(String account) throws TimeOutException {
		try {
			this.enterAccountName(account);
			this.selectAccountName(account);
			log.info("Succussfully enterAndselectAccountName");
		}

		catch (Exception ex) {
			Assert.fail("Not able to enterAndselectAccountName. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickSave
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickSave() throws TimeOutException {
		try {
			objOperationPageClass.clickSave();
			log.info("Succussfully clickSave");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickSave. " + ex.getMessage());

		}

	}
	public void clickelsewhere() throws TimeOutException {
		try {
			objOperationPageClass.clickelsewhere();
			log.info("Succussfully clickelsewhere");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickelsewhere. " + ex.getMessage());

		}

	}
	/**
	 * Method to createNewContact
	 * 
	 * @Author manish9x
	 * @Since 28-Nov-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
/*	public void createNewContact(AdminDataDetails objAdminDataDetails) throws TimeOutException {
		try {

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getSalutation())) {
				this.selectSalutationForContact(objAdminDataDetails.getSalutation());
			}
            
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFirstName())) {
				this.enterFirstNameContact(objAdminDataDetails.getFirstName());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getLastName())) {
				this.enterLastNameContact(objAdminDataDetails.getLastName());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getEmail())) {
				this.enterEmailContact(objAdminDataDetails.getEmail());
			}
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getAccountName())) {
				this.enterAndselectAccountName(objAdminDataDetails.getAccountName());
			}

			this.clickSave();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" createNewContact Successfully Done");
		} catch (Exception ex) {
			Assert.fail("createNewContact not created " + ex.getMessage());
		}
	}
	
	*/
	public void createNewContactClassic(AdminDataDetails objAdminDataDetails) throws TimeOutException {
		try {

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getSalutationclassic())) {
				this.selectSalutationForContactclassic(objAdminDataDetails.getSalutationclassic());
			}
            
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFirstNameclassic())) {
				this.enterFirstNameContactclassic(objAdminDataDetails.getFirstNameclassic());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getLastNameclassic())) {
				this.enterLastNameContactclassic(objAdminDataDetails.getLastNameclassic());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getEmailclassic())) {
				this.enterEmailContactclassic(objAdminDataDetails.getEmailclassic());
			}
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getAccountNameclassic())) {
				this.enterAndselectAccountName(objAdminDataDetails.getAccountNameclassic());
			}

			this.clickSave();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" createNewContact Successfully Done");
		} catch (Exception ex) {
			Assert.fail("createNewContact not created " + ex.getMessage());
		}
	}
	public void createNewContact(AdminDataDetails objAdminDataDetails) throws TimeOutException {
		try {

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getSalutation())) {
				this.selectSalutationForContact(objAdminDataDetails.getSalutation());
			}
            
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFirstName())) {
				this.enterFirstNameContact(objAdminDataDetails.getFirstName());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getLastName())) {
				this.enterLastNameContact(objAdminDataDetails.getLastName());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getEmail())) {
				this.enterEmailContact(objAdminDataDetails.getEmail());
			}
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getAccountName())) {
				this.enterAndselectAccountName(objAdminDataDetails.getAccountName());
			}
            
			this.clickSave();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" createNewContact Successfully Done");
		} catch (Exception ex) {
			Assert.fail("createNewContact not created " + ex.getMessage());
		}
	}
	public void addfilterclassic(AdminDataDetails objAdminDataDetails) throws TimeOutException{
		try{
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFilter())) {
				this.selectSalutationForContactclassic(objAdminDataDetails.getFilterclassic());
		      }
		}
			catch (Exception ex) {
				Assert.fail("Filter not entered " + ex.getMessage());
			}
			
	}
	public void addfilter(AdminDataDetails objAdminDataDetails) throws TimeOutException{
		try{
			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFilter())) {
				this.enterfilter(objAdminDataDetails.getFilter());
		      }
		}
			catch (Exception ex) {
				Assert.fail("Filter not entered " + ex.getMessage());
			}
			
	}
	public void validatefilterapply(AdminDataDetails objAdminDataDetails) throws TimeOutException{
		try{
			String email=objOperationPageClass.getfilteredvalue(objAdminDataDetails.getFilter());
			System.out.println("Email Filter is working correctly."+email);
			if(email==objAdminDataDetails.getFilter())
			{
			System.out.println("Email Filter is working correctly."+email);
			log.info("Succussfully verified emailfilter");
			}
		      
		}
			catch (Exception ex) {
				Assert.fail("Filter not working " + ex.getMessage());
			}
			
	}
	/**
	 * @Description Method to verifyERPMIntegrationStatus
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void verifyERPMIntegrationStatus(String expectedStatus) throws TimeOutException {
		try {
			String actualStatus = objOperationPageClass.getERPMIntegrationStatus();
			Assert.assertEquals(actualStatus, expectedStatus, "Integration status Mis-Match");
			log.info("Succussfully verifyERPMIntegrationStatus");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyERPMIntegrationStatus. " + ex.getMessage());

		}
	}
	public void verifyERPMIntegrationStatusClassic(String expectedStatus) throws TimeOutException {
		try {
			String actualStatus = objOperationPageClass.getERPMIntegrationStatusclassic();
			Assert.assertEquals(actualStatus, expectedStatus, "Integration status Mis-Match");
			log.info("Succussfully verifyERPMIntegrationStatus");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyERPMIntegrationStatus. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to verifyAGSIntegrationStatus
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void verifyAGSIntegrationStatus(String expectedStatus) throws TimeOutException {
		try {
			String actualStatus = objOperationPageClass.getAGSIntegrationStatus();
			Assert.assertEquals(actualStatus, expectedStatus, "Integration status Mis-Match");
			log.info("Succussfully verifyAGSIntegrationStatus");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyAGSIntegrationStatus. " + ex.getMessage());

		}
	}
	public void verifyAGSIntegrationStatusClassic(String expectedStatus) throws TimeOutException {
		try {
			String actualStatus = objOperationPageClass.getAGSIntegrationStatusclassic();
			Assert.assertEquals(actualStatus, expectedStatus, "Integration status Mis-Match");
			log.info("Succussfully verifyAGSIntegrationStatus");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyAGSIntegrationStatus. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnGrantAccessButton
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickOnGrantAccessButtonclassic() throws TimeOutException {
		try {

			objOperationPageClass.clickOnGrantAccessButtonclassic();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnGrantAccessButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnGrantAccessButton. " + ex.getMessage());

		}
	}
	public void clickOnGrantAccessButton() throws TimeOutException {
		try {

			objOperationPageClass.clickOnGrantAccessButton();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnGrantAccessButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnGrantAccessButton. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to clickOnGrantAccessButton
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickOnGrantAccessInShowMoreActions() throws TimeOutException {
		try {

			objOperationPageClass.clickOnGrantAccessInShowMoreActions();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnGrantAccessButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnGrantAccessButton. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to expandConsolidatedPlatform
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void expandConsolidatedPlatform() throws TimeOutException {
		try {
			objOperationPageClass.expandConsolidatedPlatform();
			log.info("Succussfully expandConsolidatedPlatform");
		}

		catch (Exception ex) {
			Assert.fail("Not able to expandConsolidatedPlatform. " + ex.getMessage());

		}
	}
	public void expandConsolidatedPlatformclassic() throws TimeOutException {
		try {
			objOperationPageClass.expandConsolidatedPlatformclassic();
			log.info("Succussfully expandConsolidatedPlatform");
		}

		catch (Exception ex) {
			Assert.fail("Not able to expandConsolidatedPlatform. " + ex.getMessage());

		}
	}
	
	public void clickondropdowncommunityclassic() throws TimeOutException {
		try {
			objOperationPageClass.clickondropdowncommunityclassic();
			log.info("Succussfully clickondropdowncommunityclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickondropdowncommunityclassic. " + ex.getMessage());

		}
	}
	public void clickonccpcommunityclassic() throws TimeOutException {
		try {
			objOperationPageClass.clickonccpcommunityclassic();
			log.info("Succussfully clickonccpcommunityclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickonccpcommunityclassic. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to verifyCCFUserAdministratorPresentOrNot
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void verifyCCFUserAdministratorPresentOrNot() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyCCFUserAdministratorPresentOrNot(), "CCF User Adm is not present");
			log.info("Succussfully verifyCCFUserAdministratorPresentOrNot");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyCCFUserAdministratorPresentOrNot. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyCCFUserAdministratorPresentOrNot
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void checkCCFUserAdministratorCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkCCFUserAdministratorCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkCCFUserAdministratorCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkCCFUserAdministratorCheckbox. " + ex.getMessage());

		}
	}
	
	public void checkCCFUserAdministratorCheckboxclassic() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkCCFUserAdministratorCheckboxclassic();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkCCFUserAdministratorCheckboxclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkCCFUserAdministratorCheckboxclassic. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifySelectedCheckboxOfContactEntitlementsAsFalse
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifySelectedCheckboxOfContactEntitlementsAsFalse() throws TimeOutException {
		
		try {
			String expextedValue="False";
			WebElement changeflag =  objOperationPageClass.getSelectedCheckboxOfContactEntitlementsAsFalse();
			String actualValue=changeflag.getAttribute("alt");
			
			Assert.assertEquals(actualValue, expextedValue,"CheckBox are not False");
		
		log.info("Succussfully verifySelectedCheckboxOfContactEntitlementsAsFalse");
		}
		catch (Exception ex) {
			Assert.fail("Not able to verifySelectedCheckboxOfContactEntitlementsAsFalse. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnSaveOnGrantAccessPage
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickOnSaveOnGrantAccessPage() throws TimeOutException {
		try {
			objOperationPageClass.clickOnSaveOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnSaveOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnSaveOnGrantAccessPage. " + ex.getMessage());

		}

	}
	public void clickOnSaveOnGrantAccessPagelight() throws TimeOutException {
		try {
			objOperationPageClass.clickOnSaveOnGrantAccessPagelight();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnSaveOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnSaveOnGrantAccessPage. " + ex.getMessage());

		}

	}
    public void clickOnSaveOnManagePersonnel() throws TimeOutException {
    	try{
    		objOperationPageClass.clickOnSaveOnManagePersonnel();
    		sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnSaveOnGrantAccessPage");
		}
    	catch (Exception ex) {
			Assert.fail("Not able to clickOnSaveOnGrantAccessPage. " + ex.getMessage());

		}
    	}
     
	/**
	 * @Description Method to clickOnViewAllOfContactEntitlements
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 * @throws TimeOutException
	 */
	public void clickOnViewAllOfContactEntitlements() throws TimeOutException {
		try {
			objOperationPageClass.clickOnViewAllOfContactEntitlements();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnViewAllOfContactEntitlements");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnViewAllOfContactEntitlements. " + ex.getMessage());

		}

	}
	
	/**
	 * @Description Method to verifyStatusAndSelectedCheckboxOfContactEntitlements
	 * @Author manish9x
	 * @Since 01-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlements(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='"+Entitlement_Name.get(i)+"']//following::span[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Pending"))
	    		System.out.println("Entitlements status is Pending");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not Pending" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not Pending");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkebox is not selected for the entitlement");
			}		
		}
	}
	public void verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsclassic(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//*[text()='"+Entitlement_Name.get(i)+"']//following::*[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Pending"))
	    		System.out.println("Entitlements status is Pending");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not Pending" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not Pending");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='Checked'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkebox is not selected for the entitlement");
			}		
		}
	}
	/**
	 * @Description Method to verifyStatusAndSelectedCheckboxOfContactEntitlements
	 * @Author manish9x
	 * @Since 01-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyStatusAsCompleteAndSelectedCheckboxOfContactEntitlements(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='"+Entitlement_Name.get(i)+"']//following::span[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Complete"))
	    		System.out.println("Entitlements status is complete");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not complete");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkebox is not selected for the entitlement");
			}		
		}
	}
	
	/**
	 * @Description Method to validateContactEntitlementsAssignment
	 * @Author manish9x
	 * @Since 02-dec-2022
	 * @throws TimeOutException
	 */
	public void validateContactEntitlementsAssignment(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='"+Entitlement_Name.get(i)+"']//following::span[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Complete"))
	    		System.out.println("Entitlements status is complete");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not complete");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkebox is not selected for the entitlement");
			}		
		}
		
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String entitlement=Entitlement_Name.get(i);
			objOperationPageClass.ClickOnEntitlementRecordElement(entitlement);
			objOperationPageClass.validateStatusOfAssignmentorRemoval();
			objOperationPageClass.clickOncontactNameInEntitlement();
			this.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(20);
			this.clickOnViewAllOfContactEntitlements();
		}
		this.goBackToContactsPage();
		
	}
	public void validateContactEntitlementsAssignmentclassic(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//*[text()='"+Entitlement_Name.get(i)+"']//following::*[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Complete"))
	    		System.out.println("Entitlements status is complete");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not complete");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='Checked'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkebox is not selected for the entitlement");
			}		
		}
		
	/*	for(int i=0;i<Entitlement_Name.size();i++)
		{
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String entitlement=Entitlement_Name.get(i);
			objOperationPageClass.ClickOnEntitlementRecordElement(entitlement);
			objOperationPageClass.validateStatusOfAssignmentorRemoval();
			objOperationPageClass.clickOncontactNameInEntitlement();
			this.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(20);
			this.clickOnViewAllOfContactEntitlements();
		}
		this.goBackToContactsPage();*/
		
	}
	/**
	 * @Description Method to validateContactEntitlementsAssignment
	 * @Author manish9x
	 * @Since 02-dec-2022
	 * @throws TimeOutException
	 */
	public void validateContactEntitlementsRemoval(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='"+Entitlement_Name.get(i)+"']//following::span[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Complete"))
	    		System.out.println("Entitlements status is complete");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not complete");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='False'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is Not selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkbox is selected for the entitlement");
			}		
		}
		
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			String entitlement=Entitlement_Name.get(i);
			objOperationPageClass.ClickOnEntitlementRecordElement(entitlement);
			objOperationPageClass.validateStatusOfRemovalOfEntitlement();
			objOperationPageClass.clickOncontactNameInEntitlement();
			this.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(20);
			this.clickOnViewAllOfContactEntitlements();
		}
		this.goBackToContactsPage();
		
	}
	public void validateContactEntitlementsRemovalclassic(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//*[text()='"+Entitlement_Name.get(i)+"']//following::*[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Complete"))
	    		System.out.println("Entitlements status is complete");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not complete");
			}
		
		}
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='Unchecked'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is Not selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkbox is selected for the entitlement");
			}		
		}
		
	}
	/**
	 * @Description Method to goBackToContactsPage
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void goBackToContactsPage() throws TimeOutException {
		try {

			objOperationPageClass.goBackToContactsPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactsPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactsPage. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnViewPartnerUserButton
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnViewPartnerUserButton() throws TimeOutException {
		try {

			objOperationPageClass.clickOnViewPartnerUserButton();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnViewPartnerUserButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnViewPartnerUserButton. " + ex.getMessage());

		}
	}
	
	
	public void clickOnManageExternalUserButtonclassic() throws TimeOutException {
		try {

			objOperationPageClass.clickOnManageExternalUserButtonclassic();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnManageExternalUserButtonclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnManageExternalUserButtonclassic. " + ex.getMessage());

		}
	}
	public void clickOnViewPartnerUserButtonclassic() throws TimeOutException {
		try {

			objOperationPageClass.clickOnViewPartnerUserButtonclassic();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//seleniumObj.getDriver().switchTo().frame(0);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnViewPartnerUserButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnViewPartnerUserButton. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifySelectedCheckboxOfContactEntitlements
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyActiveCheckboxOnUserIsCheckedOrNot() throws TimeOutException {
		
		try {
			String expectedValue="Checked";
			WebElement changeflag =  objOperationPageClass.getActiveCheckboxOnUser();
		    
			String actualValue=changeflag.getAttribute("alt");
			Assert.assertEquals(actualValue, expectedValue,"CheckBox are not Checked");
		
		log.info("Succussfully verifyActiveCheckboxOnUserIsCheckedOrNot");
		}
		catch (Exception ex) {
			Assert.fail("Not able to verifyActiveCheckboxOnUserIsCheckedOrNot. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyActiveCheckboxOnUserIsUnChecked
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyActiveCheckboxOnUserIsUnChecked() throws TimeOutException {
		
		try {
			String expectedValue="Not Checked";
			WebElement changeflag =  objOperationPageClass.getActiveCheckboxOnUser();
		    
			String actualValue=changeflag.getAttribute("alt");
			Assert.assertEquals(actualValue, expectedValue,"CheckBox are showing as Checked");
		
		log.info("Succussfully verifyActiveCheckboxOnUserIsUnChecked");
		}
		catch (Exception ex) {
			Assert.fail("Not able to verifyActiveCheckboxOnUserIsUnChecked. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifySelectedCheckboxOfContactEntitlements
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignments()
	{
	
		String CCF=objOperationPageClass.cCPCCFuser.getText();
		String CCPExternal=objOperationPageClass.cCPExternalUser.getText();
	    try
	    {
	    if(CCF.equalsIgnoreCase("CCP CCF USER") && CCPExternal.equalsIgnoreCase("CCP External User Admin"))
	    {
	    	log.info("Succussfully verifyPermissionSetAssignments");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignments. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyPermissionSetRemovals
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetRemovals()
	{
	
		String NoCCF=objOperationPageClass.nORecordToDisplay.getText();
		
	    try
	    {
	    	if(NoCCF.equalsIgnoreCase("No records to display"))
		    {
	    		log.info("Succussfully verifyPermissionSetRemovals");
	        }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetRemovals. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to goToContactFromPartnerUserPage
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void goToContactFromPartnerUserPage() throws TimeOutException {
		try {
			objOperationPageClass.goToContactFromPartnerUserPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goToContactFromPartnerUserPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goToContactFromPartnerUserPage. " + ex.getMessage());
            
		}

	}
	
	/**
	 * @Description Method to ClickOnshowMoreActions
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void ClickOnshowMoreActions() throws TimeOutException {
		try {

			objOperationPageClass.ClickOnshowMoreActions();
			log.info("Succussfully ClickOnshowMoreActions");
		}

		catch (Exception ex) {
			Assert.fail("Not able to ClickOnshowMoreActions. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyStatusAndSelectedCheckboxOfContactEntitlementsAsFalse
	 * @Author manish9x
	 * @Since 01-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalse(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='"+Entitlement_Name.get(i)+"']//following::span[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Pending"))
	    		System.out.println("Entitlements status is Pending");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not Pending" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not Pending");
			}
		
		}
		
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='False'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is Not selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is Not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkbox is selected for the entitlement");
			}		
		}
	}
	
	public void verifyStatusAsPendingAndSelectedCheckboxOfContactEntitlementsAsFalseclassic(String expectedStatus,List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//*[text()='"+Entitlement_Name.get(i)+"']//following::*[text()='"+expectedStatus+"']"));
			String CCF=ele.getText();
	  
	    	if(CCF.equalsIgnoreCase("Pending"))
	    		System.out.println("Entitlements status is Pending");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not Pending" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not Pending");
			}
		
		}
		
		seleniumObj.waitForSeconds(5);
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+Entitlement_Name.get(i)+"']//following::img[@alt='Unchecked'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("checkbox is Not selected for the entitlement");
			}
			catch (Exception e) {
				log.info("checkbox is Not selected for the entitlement" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "checkbox is selected for the entitlement");
			}		
		}
	}
	
	/**
	 * @Description Method to expandProgrammableSolutionsGroup
	 * @Author manish9x
	 * @Since 02-Dec-2022
	 * @throws TimeOutException
	 */
	public void expandProgrammableSolutionsGroup() throws TimeOutException {
		try {
			objOperationPageClass.expandProgrammableSolutionsGroup();
			log.info("Succussfully expandProgrammableSolutionsGroup");
		}

		catch (Exception ex) {
			Assert.fail("Not able to expandProgrammableSolutionsGroup. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyPSGLicensingUserEntitlementPresentOrNot
	 * @Author manish9x
	 * @Since 02-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPSGLicensingUserEntitlementPresentOrNot() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyPSGLicensingUserEntitlementPresentOrNot(), "PSG Licensing User is not present");
			log.info("Succussfully verifyPSGLicensingUserEntitlementPresentOrNot");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyPSGLicensingUserEntitlementPresentOrNot. " + ex.getMessage());

		}
	}
	public void checkPSGLicensingUserCheckboxclassic() throws TimeOutException {
		try {
			
			objOperationPageClass.checkPSGLicensingUserCheckboxclassic();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkPSGLicensingUserCheckboxclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkPSGLicensingUserCheckboxclassic. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to checkPSGLicensingUserCheckbox
	 * @Author manish9x
	 * @Since 02-Dec-2022
	 * @throws TimeOutException
	 */
	public void checkPSGLicensingUserCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPSGLicensingUserCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkPSGLicensingUserCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkPSGLicensingUserCheckbox. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyPermissionSetAssignmentsForPSGLicensingUser
	 * @Author manish9x
	 * @Since 06-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignmentsForPSGLicensingUser()
	{
	
		String PSGLicensingUser=objOperationPageClass.pSGExternalUser.getText();
	    try
	    {
	    if(PSGLicensingUser.equalsIgnoreCase("PSG External - License User") )
	    {
	    	log.info("Succussfully verifyPermissionSetAssignmentsForPSGLicensingUser");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignmentsForPSGLicensingUser. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyErrorOnGrantAccessPage
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyErrorOnGrantAccessPage() throws TimeOutException {
		try {
			 sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertTrue(objOperationPageClass.verifyErrorOnGrantAccessPage(), "Error on Grant Access Page is not present");
			log.info("Succussfully verifyErrorOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyErrorOnGrantAccessPage. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnCancelOnGrantAccessPage
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnCancelOnGrantAccessPage() throws TimeOutException {
		try {
			objOperationPageClass.clickOnCancelOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnCancelOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnCancelOnGrantAccessPage. " + ex.getMessage());

		}

	}
	
	/**
	 * @Description Method to verifyPSGDistiQuotingAccessEntitlementPresentOrNot
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPSGDistiQuotingAccessEntitlementPresentOrNot() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyPSGDistiQuotingAccessEntitlementPresentOrNot(), "PSG Disti Quoting Access is not present");
			log.info("Succussfully verifyPSGDistiQuotingAccessEntitlementPresentOrNot");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyPSGDistiQuotingAccessEntitlementPresentOrNot. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to checkPSGDistiQuotingAccessCheckbox
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void checkPSGDistiQuotingAccessCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPSGDistiQuotingAccessCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkPSGDistiQuotingAccessCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkPSGDistiQuotingAccessCheckbox. " + ex.getMessage());

		}
	}
	public void checkPSGDistiQuotingAccessCheckboxclassic() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPSGDistiQuotingAccessCheckboxclassic();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkPSGDistiQuotingAccessCheckboxclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkPSGDistiQuotingAccessCheckboxclassic. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to expandIntelQuoteRequest
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void expandIntelQuoteRequest() throws TimeOutException {
		try {
			objOperationPageClass.expandIntelQuoteRequest();
			log.info("Succussfully expandIntelQuoteRequest");
		}

		catch (Exception ex) {
			Assert.fail("Not able to expandIntelQuoteRequest. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyCustomerDrafterAccessEntitlementPresentOrNot
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyCustomerDrafterAccessEntitlementPresentOrNot() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyCustomerDrafterAccessEntitlementPresentOrNot(), "PSG Disti Quoting Access is not present");
			log.info("Succussfully verifyCustomerDrafterAccessEntitlementPresentOrNot");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyCustomerDrafterAccessEntitlementPresentOrNot. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to checkCustomerDrafterAccessCheckbox
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void checkCustomerDrafterAccessCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkCustomerDrafterAccessCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkCustomerDrafterAccessCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkCustomerDrafterAccessCheckbox. " + ex.getMessage());

		}
	}
	public void checkCustomerDrafterAccessCheckboxclassic() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkCustomerDrafterAccessCheckboxclassic();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkCustomerDrafterAccessCheckboxclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkCustomerDrafterAccessCheckboxclassic. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess
	 * @Author manish9x
	 * @Since 12-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess()
	{
	
		String CPQManagedLicenseUser=objOperationPageClass.CPQManagedLicenseUser.getText();
		String PSGExternalDisty=objOperationPageClass.PSGExternalDisty.getText();
		String TenderPartnerDrafterAccess=objOperationPageClass.TenderPartnerDrafterAccess.getText();
		String FoundationAccess=objOperationPageClass.FoundationAccess.getText();
	    try
	    {
	    if(CPQManagedLicenseUser.equalsIgnoreCase("CPQ Managed License User") && PSGExternalDisty.equalsIgnoreCase("PSG External - Disty") && TenderPartnerDrafterAccess.equalsIgnoreCase("Tender Partner Drafter Access") && FoundationAccess.equalsIgnoreCase("UCD ACM Foundation Access"))
	    {
	    	log.info("Succussfully verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignmentsForDistiQuotingAndDrafterAccess. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnViewAllOfPartnerContactRelationship
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnViewAllOfPartnerContactRelationship() throws TimeOutException {
		try {
			objOperationPageClass.clickOnViewAllOfPartnerContactRelationship();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnViewAllOfPartnerContactRelationship");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnViewAllOfPartnerContactRelationship. " + ex.getMessage());

		}

	}
	
	/**
	 * @Description Method to verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalse
	 * @Author manish9x
	 * @Since 13-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalseclassic(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//input[@title='New Account Contact Relationship']//following::a[text()='Employee']//following::*[text()='"+expectedStatus+"'])[1]"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='False'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is Not selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox checkbox is selected");
			}		
	}
		public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalse(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='Employee']//following::span[text()='"+expectedStatus+"']"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='False'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is Not selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox checkbox is selected");
			}		
	}
	public void verifyEmployeeRelationshipStatusAsPendingAndInactiveIndicatorAsFalse(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//input[@title='New Account Contact Relationship']//following::a[text()='Employee']//following::*[text()='"+expectedStatus+"'])[1]"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Pending"))
	    		System.out.println("Employee Relationship status is pending");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not pending" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not pending");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='False'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is Not selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox checkbox is selected");
			}		
	}
	
	/**
	 * @Description Method to verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue
	 * @Author manish9x
	 * @Since 13-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='Employee']//following::span[text()='"+expectedStatus+"']"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is not selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox is not selected");
			}		
	}
	public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsFalseClassic(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//input[@title='New Account Contact Relationship']//following::a[text()='Employee']//following::*[text()='"+expectedStatus+"'])[1]"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='Not Checked'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is Not selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox checkbox is selected");
			}		
	}
	
	/**
	 * @Description Method to verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrue
	 * @Author manish9x
	 * @Since 13-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrueClassic(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Employee'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='Employee']//following::span[text()='"+expectedStatus+"']"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is not selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox is not selected");
			}		
	}
	public void verifyEmployeeRelationshipStatusAsCompleteAndInactiveIndicatorAsTrueclassic(String expectedStatus) throws TimeOutException {
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//*[@id='0032i00001Jf8OL_00N3b000006juA6_title']//following::a[text()='Employee']"));
			if(ele.isDisplayed())
				System.out.println("Employee Relationship is displayed");
			}
			catch (Exception e) {
				log.info("Employee Relationship is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship is not displayed");
			}
		
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("//a[text()='Employee']//following::span[text()='"+expectedStatus+"']"));
			String actualStatus=ele.getText();
	  
	    	if(actualStatus.equalsIgnoreCase("Complete"))
	    		System.out.println("Employee Relationship status is complete");
			}
	    	catch (Exception e) {
				log.info("Employee Relationship status is not complete" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Employee Relationship status is not complete");
			}
	
		try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Employee']//following::img[@alt='True'])[1]"));
			if(!(ele.isSelected()))
				System.out.println("Inactive Indicator checkbox is selected");
			}
			catch (Exception e) {
				log.info("Inactive Indicator checkbox is not selected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Inactive Indicator checkbox is not selected");
			}		
	}
	
	/**
	 * @Description Method to verifyPermissionSetAssignmentsForEmployee
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignmentsForEmployee()
	{
	
		String EmployeeUser=objOperationPageClass.Employee.getText();
	    try
	    {
	    if(EmployeeUser.equalsIgnoreCase("PMP External - Employee") )
	    {
	    	log.info("Succussfully verifyPermissionSetAssignmentsForEmployee");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignmentsForEmployee. " + ex.getMessage());

		}
	}
	
	
	/**
	 * @Description Method to verifyPermissionSetRemovalForEmployee
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetRemovalForEmployee()
	{
	    try
	    {
	    	Assert.assertFalse(objOperationPageClass.verifyEmployeeNotPresent(), "Employee is present in permisiion set");
	    	log.info("Succussfully verifyPermissionSetRemovalForEmployee");
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetRemovalForEmployee. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to expandIntelPartnerAlliance
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void expandIntelPartnerAlliance() throws TimeOutException {
		try {
			objOperationPageClass.expandIntelPartnerAlliance();
			log.info("Succussfully expandIntelPartnerAlliance");
		}

		catch (Exception ex) {
			Assert.fail("Not able to expandIntelPartnerAlliance. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to checkEmployeeCheckbox
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void checkEmployeeCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkEmployeeCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkEmployeeCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkEmployeeCheckbox. " + ex.getMessage());

		}
	}
	public void checkEmployeeCheckboxclassic() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkEmployeeCheckboxclassic();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkEmployeeCheckboxclassic");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkEmployeeCheckboxclassic. " + ex.getMessage());

		}
	}
	public void checkPADCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPADCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkPADCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkPADCheckbox. " + ex.getMessage());

		}
	}
	public void checkMSCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkMSCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkMSCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkMSCheckbox. " + ex.getMessage());

		}
	}
	public void checkInactiveIPAMembership() throws TimeOutException {
		try {
			objOperationPageClass.checkInactiveIPAMembership();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkInactiveIPAMembership");
		}
		catch(Exception ex){
			Assert.fail("Not able to checkInactiveIPAMembership."+ex.getMessage());
		}
	}
	public void clickonrowsperpage() throws TimeOutException {
		try {
			objOperationPageClass.clickonrowsperpage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickonrowsperpage");
		}
		catch(Exception ex){
			Assert.fail("Not able to clickonrowsperpage."+ex.getMessage());
		}
	}
	public void clickon100rows() throws TimeOutException {
		try {
			objOperationPageClass.clickon100rows();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickon100rows");
		}
		catch(Exception ex){
			Assert.fail("Not able to clickon100rows."+ex.getMessage());
		}
	}
	/**
	 * @Description Method to clickOnYesOnGrantAccessPage
	 * @Author manish9x
	 * @Since 13-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnYesOnGrantAccessPage() throws TimeOutException {
		try {
			objOperationPageClass.clickOnYesOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnYesOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnYesOnGrantAccessPage. " + ex.getMessage());

		}

	}
	public void clickOnYesOnManagePersonnelPage() throws TimeOutException {
		try {
			objOperationPageClass.clickOnYesOnManagePersonnelPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnYesOnManagePersonnelPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnYesOnManagePersonnelPage. " + ex.getMessage());

		}

	}
	public void clickOnOkOnGrantAccessPage() throws TimeOutException {
		try {
			objOperationPageClass.clickOnOkOnGrantAccessPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnOkOnGrantAccessPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnOkOnGrantAccessPage. " + ex.getMessage());

		}

	}
	/**
	 * @Description Method to clickOnLogintoExperienceasUserButton
	 * @Author manish9x
	 * @Since 15-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnLogintoExperienceasUserButton() throws TimeOutException {
		try {

			objOperationPageClass.clickOnLogintoExperienceasUserButton();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnLogintoExperienceasUserButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnLogintoExperienceasUserButton. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnPartnercenterUnifiedCommunity
	 * @Author manish9x
	 * @Since 15-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnPartnercenterUnifiedCommunity() throws TimeOutException {
		try {

			objOperationPageClass.clickOnPartnercenterUnifiedCommunity();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnPartnercenterUnifiedCommunity");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnPartnercenterUnifiedCommunity. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to clickOnIntelPartnerAllianceCommunity
	 * @Author asuhassx
	 * @Since 24-Jan-2023
	 * @throws TimeOutException
	 */
	public void clickOnIntelPartnerAllianceCommunity() throws TimeOutException {
		try {

			objOperationPageClass.clickOnIntelPartnerAllianceCommunity();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnIntelPartnerAllianceCommunity");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnIntelPartnerAllianceCommunity. " + ex.getMessage());

		}
	}
	
	public void clickOnManagePersonnel() throws TimeOutException {
		try {

			objOperationPageClass.clickOnManagePersonnel();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnManagePersonnel");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnManagePersonnel. " + ex.getMessage());

		}
	}
	public void arecolumnsavailable() throws TimeOutException{
		try{
			objOperationPageClass.arecolumnsavailable();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("All columns are available");
		}
		catch (Exception ex){
			Assert.fail("All columns are not available " + ex.getMessage());
		}
	}
	public void clickOnfilterEmail() throws TimeOutException{
		try{
			objOperationPageClass.clickOnfilterEmail();
			log.info("Successfully clickOnfilterEmail");
		}
		catch(Exception ex){
			Assert.fail("Not able to clickOnfilterEmail. "+ex.getMessage());
		}
	}
	public void clickOnfilterEmailaddress() throws TimeOutException{
		try{
			objOperationPageClass.clickOnfilterEmailaddress();
			log.info("Successfully clickOnfilterEmail");
		}
		catch(Exception ex){
			Assert.fail("Not able to clickOnfilterEmail. "+ex.getMessage());
		}
	}
	public void clickOnReinvitemanagepersonnel() throws TimeOutException{
		try{
			objOperationPageClass.clickOnReinvitemanagepersonnel();
			log.info("Successfully clickOnReinvitemanagepersonnel");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to clickOnReinvitemanagepersonnel"+ex.getMessage());
		}
	}
	/**
	 * @Description Method to click on Invite a User button on Manage Personnel
	 * @Author asuhassx
	 * @Since 02-Feb-2023
	 * @throws TimeOutException
	 */
	public void clickOnInviteAUserManagePersonnel() throws TimeOutException{
		try{
			objOperationPageClass.clickOnInviteAUserManagePersonnel();
			log.info("Successfully clickOnInviteAUserManagePersonnel");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to clickOnInviteAUserManagePersonnel"+ex.getMessage());
		}
	}
	public void clickOnSubmitInviteManagePersonnel() throws TimeOutException{
		try{
			objOperationPageClass.clickOnSubmitInviteManagePersonnel();
			log.info("Successfully clickOnSubmitInviteManagePersonnel");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to clickOnSubmitInviteManagePersonnel"+ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to check if Reinvite and Delete button exist or not
	 *@Author asuhassx
	 *@Since 25-Jan-2023
	 *@throws TimeOutException
	 */
	public void verifyIfReinviteButtonExists() throws TimeOutException {
	try{
		sfcommonObj.waitTillLightningPageLoadComplete();
		Assert.assertFalse(objOperationPageClass.checkIfReinviteButtonDisplayed(),"'Reinvite Button' is not Displayed");
		log.info("'Reinvite Button' is Displayed");
	}
	catch(Exception ex)
	{
		Assert.fail("Not able to verify if 'Reinvite Button' is Displayed " + ex.getMessage());	
	}
	}	
	public void verifyIfDeleteButtonExists() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertFalse(objOperationPageClass.checkIfDeleteButtonDisplayed(),"'Delete Button' is not Displayed");
			log.info("'Delete Button' is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if 'Delete Button' is Displayed " + ex.getMessage());	
		}
		}	
	/**
	 * @Description Method to check if messages coming on Manage Personnel invite are correct or not
	 *@Author asuhassx
	 *@Since 06-Feb-2023
	 *@throws TimeOutException
	 */
	public void validateNewContactMessageonManagePersonnel() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertEquals(objOperationPageClass.validateNewContactMessageonManagePersonnel(),"Invite the contact to register for the Intel® Partner Alliance Program");
			log.info("Message is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if Message is Displayed " + ex.getMessage());	
		}
		}	
	
	public void validateACRactiveMessageonManagePersonnel() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertEquals(objOperationPageClass.validateACRactiveMessageonManagePersonnel(),"The Contact has an Active Intel® Partner Alliance Membership. Need help?");
			log.info("Message is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if Message is Displayed " + ex.getMessage());	
		}
		}	
	public void validateinvitedContactMessageonManagePersonnel() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertEquals(objOperationPageClass.validateinvitedContactMessageonManagePersonnel(),"An invitation has been extended and registration is in progress. Need help?");
			log.info("Message is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if Message is Displayed " + ex.getMessage());	
		}
		}
	public void validatediffOPIDContactMessageonManagePersonnel() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertEquals(objOperationPageClass.validatediffOPIDContactMessageonManagePersonnel(),"The contact is not a part of your account. Need help?");
			log.info("Message is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if Message is Displayed " + ex.getMessage());	
		}
		}
	public void validateInternalUserMessageonManagePersonnel() throws TimeOutException {
		try{
			sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertEquals(objOperationPageClass.validateInternalUserMessageonManagePersonnel(),"Invitation cannot be extended to an Intel domain Email ID");
			log.info("Message is Displayed");
		}
		catch(Exception ex)
		{
			Assert.fail("Not able to verify if Message is Displayed " + ex.getMessage());	
		}
		}
	/**
	 * @Description Method to clickOnMyProgramDropDown
	 * @Author manish9x
	 * @Since 15-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnMyProgramDropDown() throws TimeOutException {
		try {

			objOperationPageClass.clickOnMyProgramDropDown();
	
			log.info("Succussfully clickOnMyProgramDropDown");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnMyProgramDropDown. " + ex.getMessage());

		}
	}
	public void clickOnContacttoInvite() throws TimeOutException{
		try{
			objOperationPageClass.clickOnContacttoInvite();
			log.info("Successfully clickOnContacttoInvite");
		}
		catch(Exception ex){
			Assert.fail("Not able to clickOnContacttoInvite. "+ex.getMessage());
		}
	}
	public void clickOnmanagepersonnelFilter() throws TimeOutException {
		try {

			objOperationPageClass.clickOnmanagepersonnelFilter();
	
			log.info("Succussfully clickOnmanagepersonnelFilter");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnmanagepersonnelFilter. " + ex.getMessage());

		}
	}
	public void clickOnDeletemanagepersonnel() throws TimeOutException {
		try {

			objOperationPageClass.clickOnDeletemanagepersonnel();
	
			log.info("Succussfully clickOnDeletemanagepersonnel");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnDeletemanagepersonnel. " + ex.getMessage());

		}
	}
	public void clickOnfilterApply() throws TimeOutException {
		try {

			objOperationPageClass.clickOnfilterApply();
	
			log.info("Succussfully clickOnfilterApply");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnfilterApply. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to clickOnGrantAccessFromMyProgramDropDown
	 * @Author manish9x
	 * @Since 15-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnGrantAccessFromMyProgramDropDown() throws TimeOutException {
		try {

			objOperationPageClass.clickOnGrantAccessFromMyProgramDropDown();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnGrantAccessFromMyProgramDropDown");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnGrantAccessFromMyProgramDropDown. " + ex.getMessage());

		}
	}

	/**
	 * @Description Method to verifyCCPPageWithSearchContactButtonPresent
	 * @Author manish9x
	 * @Since 15-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyCCPPageWithSearchContactButtonPresent() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyCCPPagePresent(), "CCP Page is not present");
			Assert.assertTrue(objOperationPageClass.verifySearchContactButtonPresent(), "Search Contact Button is not present");
			log.info("Succussfully verifyCCPPageWithSearchContactButtonPresent");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyCCPPageWithSearchContactButtonPresent. " + ex.getMessage());

		}
	}
	
	/**
	 * 
	 * @Description Method to SearchContactWithEmailAndClickOnSearchContactButton
	 * @Author manish9x
	 * @Since 16-Dec-2022
	 */
	public void SearchContactWithEmailAndClickOnSearchContactButton(String searchText) {
		try {
			
			seleniumObj.waitForElement(objOperationPageClass.typeHereTextbox, 5, 5);
			objOperationPageClass.typeHereTextbox.click();
			objOperationPageClass.typeHereTextbox.clear();
			seleniumObj.waitForSeconds(2);
			objOperationPageClass.typeHereTextbox.sendKeys(searchText);
			seleniumObj.waitForSeconds(2);
			objOperationPageClass.clickOnSearchContactButton();
			
			log.info("Successfully SearchContactWithEmailAndClickOnSearchContactButton");
		} catch (Exception e) {
			log.error("Not able to SearchContactWithEmailAndClickOnSearchContactButton");
			Assert.fail("Not able to SearchContactWithEmailAndClickOnSearchContactButton");
		}

	}
	public void SearchContactWithEmailAndClickOnSearchContactButtonMP(String searchText) {
		try {
			
			seleniumObj.waitForElement(objOperationPageClass.enteremailaddress, 5, 5);
			objOperationPageClass.enteremailaddress.click();
			objOperationPageClass.enteremailaddress.clear();
			seleniumObj.waitForSeconds(2);
			objOperationPageClass.enteremailaddress.sendKeys(searchText);
			seleniumObj.waitForSeconds(2);
			//objOperationPageClass.clickOnSearchContactButton();
			
			log.info("Successfully SearchContactWithEmailAndClickOnSearchContactButtonMP");
		} catch (Exception e) {
			log.error("Not able to SearchContactWithEmailAndClickOnSearchContactButtonMP");
			Assert.fail("Not able to SearchContactWithEmailAndClickOnSearchContactButtonMP");
		}

	}
	/**
	 * @Description Method to verifyContactIsVisibleOnclickingSearchContactButton
	 * @Author manish9x
	 * @Since 16-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyContactIsVisibleOnclickingSearchContactButton(String email) throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyContactIsVisibleOnclickingSearchContactButton(email), "Contact is not visible on clicking Search Contact Button!");
			log.info("Succussfully verifyContactIsVisibleOnclickingSearchContactButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyContactIsVisibleOnclickingSearchContactButton. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to clickOnLoadEntitlementsButton
	 * @Author manish9x
	 * @Since 16-Dec-2022
	 * @throws TimeOutException
	 */
	public void clickOnLoadEntitlementsButton() throws TimeOutException {
		try {

			objOperationPageClass.clickOnLoadEntitlementsButton();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnLoadEntitlementsButton");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnLoadEntitlementsButton. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyNoResultFoundMessage
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyNoResultFoundMessage() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyNoResultFoundMessage(), "No Result Found is not present");
			log.info("Succussfully verifyNoResultFoundMessage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyNoResultFoundMessage. " + ex.getMessage());

		}
	}
	
	/**
	 * Method to createContactThroughPartnerCenter
	 * 
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void createContactThroughPartnerCenter(AdminDataDetails objAdminDataDetails) throws TimeOutException {
		try {


			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getFirstName2())) {
				this.enterFirstNameContactThroughPartnerCenter(objAdminDataDetails.getFirstName2());
			}

			if (!StringUtils.isNullOrBlank(objAdminDataDetails.getLastName2())) {
				this.enterLastNameContactThroughPartnerCenter(objAdminDataDetails.getLastName2());
			}

		    this.selectAccountType(objAdminDataDetails.getAccountType());
		    this.selectAccountNamefromDropDown(objAdminDataDetails.getAccountName2());
		    
			this.clickSave();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info(" createContactThroughPartnerCenter Successfully Done");
		} catch (Exception ex) {
			Assert.fail("createContactThroughPartnerCenter not created " + ex.getMessage());
		}
	}
	
	
	
	
	/**
	 * Method to enter first name contact
	 * 
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterFirstNameContactThroughPartnerCenter(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.firstNameContactThroughPartnerCenter, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.firstNameContactThroughPartnerCenter),
					"first name contact does not exist in the UI");
			objOperationPageClass.enterFirstNameContactThroughPartnerCenter(name);
			log.info("Entered first name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter first name contact " + ex.getMessage());
		}
	}
	
	/**
	 * Method to enter last name contact
	 * 
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void enterLastNameContactThroughPartnerCenter(String name) throws TimeOutException {
		try {
			seleniumObj.waitForElement(objOperationPageClass.lastNameContactThroughPartnerCenter, 30, 10);
			Assert.assertTrue(seleniumObj.isElementExists(objOperationPageClass.lastNameContactThroughPartnerCenter),
					"last name contact does not exist in the UI");
			objOperationPageClass.enterLastNameContactThroughPartnerCenter(name);
			log.info("Entered last name contact value : " + name);
		} catch (Exception ex) {
			Assert.fail("Not able to enter last name contact " + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to selectAccountType
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 */
	public void selectAccountType(String account) throws TimeOutException {
		try {

			objOperationPageClass.selectAccountType(account);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully selectAccountType");
		}

		catch (Exception ex) {
			Assert.fail("Not able to selectAccountType. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to selectAccountNamefromDropDown
	 * @Author manish9x
	 * @Since 20-Dec-2022
	 * @throws TimeOutException
	 */
	public void selectAccountNamefromDropDown(String account) throws TimeOutException {
		try {

			objOperationPageClass.selectAccountNamefromDropDown(account);
			log.info("Succussfully selectAccountNamefromDropDown");
		}

		catch (Exception ex) {
			Assert.fail("Not able to selectAccountNamefromDropDown. " + ex.getMessage());

		}
	}
	
	
	/**
	 * @Description Method to selectAnyOfTheEntitlements
	 * @Author manish9x
	 * @Since 23-Dec-2022
	 * @throws TimeOutException
	 */
	public void selectAnyOfTheEntitlements(String entitlement) throws TimeOutException {
		try {
			
	        objOperationPageClass.selectAnyOfTheEntitlements(entitlement);
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully selectAnyOfTheEntitlements");
		}

		catch (Exception ex) {
			Assert.fail("Not able to selectAnyOfTheEntitlements. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to validateContactEntitlementsAssignment
	 * @Author manish9x
	 * @Since 02-dec-2022
	 * @throws TimeOutException
	 */
	public void VerifyStatusOfEntitlementAsProcessing(String entitlement) throws TimeOutException {
		
		{
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+entitlement+"']/following::div[@class='slds-show'])[1]"));
			String ActualStatus=ele.getText();
	  
	    	if(ActualStatus.equalsIgnoreCase("Processing"))
	    		System.out.println("Entitlement status is Processing");
			}
	    	catch (Exception e) {
				log.info("Entitlements status is not Processing" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not Processing");
			}
		
		}
	}
	
	/**
	 * @Description Method to verifyErrorOnContactThroughPartnerCenter
	 * @Author manish9x
	 * @Since 27-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyErrorOnContactThroughPartnerCenter() throws TimeOutException {
		try {
			 sfcommonObj.waitTillLightningPageLoadComplete();
			Assert.assertTrue(objOperationPageClass.verifyErrorOnContactThroughPartnerCenter(), "Error is not present");
			log.info("Succussfully verifyErrorOnContactThroughPartnerCenter");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyErrorOnContactThroughPartnerCenter. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyPermissionSetAssignmentsForSPCFUserAdministrator
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignmentsForSPCFUserAdministrator()
	{
	
		String SPCF=objOperationPageClass.sPCFuser.getText();
		String CCPExternal=objOperationPageClass.cCPExternalUser.getText();
	    try
	    {
	    if(SPCF.equalsIgnoreCase("SPCF USER") && CCPExternal.equalsIgnoreCase("CCP External User Admin"))
	    {
	    	log.info("Succussfully verifyPermissionSetAssignmentsForSPCFUserAdministrator");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignmentsForSPCFUserAdministrator. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyCCFActivitySubmitterPresentOrNot
	 * @Author manish9x
	 * @Since 28-dec-2022
	 * @throws TimeOutException
	 */
	public void verifyCCFActivitySubmitterPresentOrNot() throws TimeOutException {
		try {
			
			Assert.assertTrue(objOperationPageClass.verifyCCFActivitySubmitterPresentOrNot(), "CCF Activity Submitter is not present");
			log.info("Succussfully verifyCCFActivitySubmitterPresentOrNot");
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyCCFActivitySubmitterPresentOrNot. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to checkCCFActivitySubmitterCheckbox
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 * @throws TimeOutException
	 */
	public void checkCCFActivitySubmitterCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkCCFActivitySubmitterCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully checkCCFActivitySubmitterCheckbox");
		}

		catch (Exception ex) {
			Assert.fail("Not able to checkCCFActivitySubmitterCheckbox. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyPermissionSetAssignmentsForCCFActivitySubmitter
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignmentsForCCFActivitySubmitter()
	{
	
		String CCF=objOperationPageClass.cCPCCFuser.getText();
		String CCPActivitySubmitter=objOperationPageClass.cCPActivitySubmitter.getText();
	    try
	    {
	    if(CCF.equalsIgnoreCase("CCP CCF USER") && CCPActivitySubmitter.equalsIgnoreCase("CCP External Activity Submitter"))
	    {
	    	log.info("Succussfully verifyPermissionSetAssignmentsForCCFActivitySubmitter");
	    }
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyPermissionSetAssignmentsForCCFActivitySubmitter. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyGrantAccessNotPresentUnderMyProgram
	 * @Author manish9x
	 * @Since 28-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyGrantAccessNotPresentUnderMyProgram()
	{
	    try
	    {
	    	Assert.assertFalse(objOperationPageClass.verifyGrantAccessNotPresentUnderMyProgram(), "Grant Access is present in My Program Dropdown");
	    	log.info("Succussfully verifyGrantAccessNotPresentUnderMyProgram");
	    }
	    catch (Exception ex) {
			Assert.fail("Not able to verifyGrantAccessNotPresentUnderMyProgram. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to validateContactEntitlementsPresent
	 * @Author manish9x
	 * @Since 28-dec-2022
	 * @throws TimeOutException
	 */
	public void validateContactEntitlementsPresent(List<String> Entitlement_Name) throws TimeOutException {
		for(int i=0;i<Entitlement_Name.size();i++)
		{
			Entitlement_Name.get(i);
			try {
			WebElement ele=seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+ Entitlement_Name.get(i) +"'])[1]"));
			if(ele.isDisplayed())
				System.out.println("Entitlements is displayed");
			}
			catch (Exception e) {
				log.info("Entitlements is not displayed" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements is not displayed");
			}
		}
	}
	
	/**
	 * Method to verifyAccountsPresentWhichWereInPGMRecords
	 * 
	 * @Author manish9x
	 * @Since 29-Dec-2022
	 * @throws TimeOutException
	 *             Exception thrown when a blocking operation times
	 */
	public void verifyAccountsPresentWhichWereInPGMRecords(AdminDataDetails objAdminDataDetails) throws TimeOutException {

		try {
		    this.selectAccountType(objAdminDataDetails.getAccountType());
		    this.verifyAccountsPresentInDropDown(objAdminDataDetails.getAccountName2());
		    
		    this.selectAccountType(objAdminDataDetails.getAccountType2());
		    this.verifyAccountsPresentInDropDown(objAdminDataDetails.getAccountName3());
		    
			log.info(" verifyAccountsPresentWhichWereInPGMRecords Successfully Done");
		} catch (Exception ex) {
			Assert.fail("verifyAccountsPresentWhichWereInPGMRecords not done " + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to verifyAccountsPresentInDropDown
	 * @Author manish9x
	 * @Since 30-Dec-2022
	 * @throws TimeOutException
	 */
	public void verifyAccountsPresentInDropDown(String expectedAccountName) throws TimeOutException {
		try {

			String actualAccountName=objOperationPageClass.verifyAccountsPresentInDropDown();
			Assert.assertEquals(actualAccountName,expectedAccountName, "Accounts Mismatch");

			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully verifyAccountsPresentInDropDown");
			
		}

		catch (Exception ex) {
			Assert.fail("Not able to verifyAccountsPresentInDropDown. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to clickOnViewAllOfContactEntitlements
	 * @Author AmartyaX
	 * @Since 3-Feb-2023
	 * @throws TimeOutException
	 */
	public void clickOnContactEntitlements() throws TimeOutException {
		try {
			objOperationPageClass.clickOnNameID();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully clickOnViewAllOfContactEntitlements");
		}

		catch (Exception ex) {
			Assert.fail("Not able to clickOnViewAllOfContactEntitlements. " + ex.getMessage());

		}

	}
	public void checkPartnerAdminCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPartnerAdminCheckbox();;
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Successfully check Partner Admin Check Box");
		}

		catch (Exception ex) {
			Assert.fail("Not able check Partner Admin Check Box. " + ex.getMessage());

		}
	}
	public void verifyStatusofAssignmentOrRemoval() throws TimeOutException {
		try {
			
	        String actualmsg=objOperationPageClass.verifyStatusAssignmentOrRemoval();
	        String exceptedmsg="Successful Assignment";
	        Assert.assertEquals(actualmsg, exceptedmsg, "Status Mismatch");
		}

		catch (Exception ex) {
			Assert.fail("Not able verify Status of Assignment Or Removal. " + ex.getMessage());

		}
	}
	public void verifyUpdateReason() throws TimeOutException {
		try {
			
	        String actualmsg=objOperationPageClass.verifyUpdateReason();
	        if(actualmsg==null){
	        	log.info("Successfully verified");
	        }
		}

		catch (Exception ex) {
			Assert.fail("Not able verify Status of Assignment Or Removal. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to goBackToContactsPage
	 * @Author manish9x
	 * @Since 01-Dec-2022
	 * @throws TimeOutException
	 */
	public void goBackToContactPage() throws TimeOutException {
		try {

			objOperationPageClass.goBackToContactPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Click Quick Find
	 * @Author Amartyax
	 * @Since 16-Feb-2022
	 * @throws TimeOutException
	 */
	public void clickQuickFind() throws TimeOutException {
		try {

			objOperationPageClass.clickQuickFind();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	public void selectOptionUnderMoreActions() throws TimeOutException {
		try {

			objOperationPageClass.ClickOnshowMoreActions();
			log.info("Succussfully clicked on selectOptionUnderMoreActions");
		}

		catch (Exception ex) {
			Assert.fail("Not able to click on selectOptionUnderMoreActions. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify User
	 * @Author Amartyax
	 * @Since 23-Feb-2023
	 * @throws TimeOutException
	 */
	public void verifyUser() throws TimeOutException {
		try {

			objOperationPageClass.verifyUser();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify User
	 * @Author Amartyax
	 * @Since 23-Feb-2023
	 * @throws TimeOutException
	 */
	public void verifyUserAsLeadOfEmployee() throws TimeOutException {
		try {

			objOperationPageClass.verifyUserAsLeadsOfEmployees();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify User 
	 * @Author Amartyax
	 * @Since 23-Feb-2023
	 * @throws TimeOutException
	 */
	public void verifyUserAsLead() throws TimeOutException {
		try {

			objOperationPageClass.verifyUserAsLeads();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	public void checkPartnerLeadDevCheckbox() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkPartnerLeadDevCheckbox();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Successfully check Partner Lead Dev Check Box");
		}

		catch (Exception ex) {
			Assert.fail("Not able check Partner Lead Dev Check Box. " + ex.getMessage());

		}
	}
	public void checkContactSegment() throws TimeOutException {
		try {
			
	        objOperationPageClass.checkEditContactBtn();
	        this.clickSave();
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Successfully check Partner Lead Dev Check Box");
		}

		catch (Exception ex) {
			Assert.fail("Not able check Partner Lead Dev Check Box. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify User
	 * @Author Amartyax
	 * @Since 23-Feb-2023
	 * @throws TimeOutException
	 */
	public void verifyUserAsInternetOfThings() throws TimeOutException {
		try {

			objOperationPageClass.verifyUserAsInternetOfThings();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify User 
	 * @Author Amartyax
	 * @Since 23-Feb-2023
	 * @throws TimeOutException
	 */
	public void verifyUserAsLeadsOfEmployeesforInterNetOfThing() throws TimeOutException {
		try {

			objOperationPageClass.verifyUserAsLeadsOfEmployeesforInterNetOfThing();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify Invited User 
	 * @Author Amartyax
	 * @Since 04-Apr-2023
	 * @throws TimeOutException
	 */
	public void verifyInvitedUser(String User) throws TimeOutException {
		try {

			objOperationPageClass.verifyInvitedUser(User);
			sfcommonObj.waitTillLightningPageLoadComplete();
			objOperationPageClass.checkInvitedStatus(User);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
	/**
	 * @Description Method to Verify Invited User 
	 * @Author Amartyax
	 * @Since 04-Apr-2023
	 * @throws TimeOutException
	 */
	public void verifyEmployeeStatus() throws TimeOutException {
		try {

			objOperationPageClass.verifyEmployeeStatus();
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully goBackToContactPage");
		}

		catch (Exception ex) {
			Assert.fail("Not able to goBackToContactPage. " + ex.getMessage());

		}
	}
}
