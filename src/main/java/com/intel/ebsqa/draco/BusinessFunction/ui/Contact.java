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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.intel.ebsqa.draco.DataClass.AdminData;
import com.intel.ebsqa.draco.DataClass.AdminData.AdminDataDetails;
import com.intel.ebsqa.draco.DataClass.SF_User_CreationData.SF_User_CreationDetails;
import com.intel.ebsqa.draco.PageClasses.ContactPageClass;
import com.intel.ebsqa.draco.PageClasses.OperationPageClass;
import com.intel.ebsqa.draco.PageClasses.SF_User_CreationPageClass;
import com.intel.ebsqa.draco.enums.Button;
import com.intel.ebsqa.draco.enums.CommonEnum;
import com.intel.ebsqa.draco.enums.ExcelEnum;
import com.intel.ebsqa.draco.enums.FieldName;
import com.intel.ebsqa.draco.enums.Tabs;
import com.intel.ebsqa.draco.enums.CommonEnum.ApplicationType;
import com.intel.ebsqa.draco.enums.CommonEnum.CustomDateFormat;
import com.intel.ebsqa.draco.enums.EntitlementEnum;
import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;
import com.intel.ebsqa.helperclass.StringUtils;

/**
 * @Description
 * @Author manish9x
 * @Since 23-Nov-2022
 */

public class Contact extends TestBase {
	OperationPageClass objOperationPageClass = null;
	ContactPageClass objContactPageClass = null;
	AdminFunctions objAdminFunctions = null;

	public Contact() {
		objOperationPageClass = new OperationPageClass();
		objContactPageClass = new ContactPageClass();
		objAdminFunctions = new AdminFunctions();
	}

	/**
	 * @Description Method to create contact
	 * @Author nmurugan
	 * @Since Sep 18, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void createContact(AdminDataDetails objAdminDataDetails) throws TimeOutException, InterruptedException {
		AdminFunctions objAdminFunctions = new AdminFunctions();
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
	}
	
	/**
	 * @Description Method to create contact
	 * @Author nmurugan
	 * @Since Sep 18, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void grandAccessToEntitilement(List<String> entitlementCategory) throws TimeOutException, InterruptedException {
		AdminFunctions objAdminFunctions = new AdminFunctions();
		objAdminFunctions.ClickOnshowMoreActions();
		objAdminFunctions.clickOnGrantAccessInShowMoreActions();
		
	}
		
	/**
	 * @Description Method to validate whether Contact Entitlements checkbox is selected or not 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
    public boolean isContactEntitlementsCheckboxSelected(String entitlementName) {
    	WebElement checkBox =seleniumObj.getDriver().findElement(By.xpath("((//a[@title='" + entitlementName + "'])[last()]/following::lightning-primitive-input-checkbox)[1]"));
        String checkedAttribute = checkBox.getAttribute("checked");
        boolean isChecked = "true".equalsIgnoreCase(checkedAttribute);
        return isChecked;
    }
  
    /**
	 * @Description Method to expandEntitlementCategory 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void expandEntitlementCategory(String entitlemetCategory) throws TimeOutException {
		try {
			objContactPageClass.expandEntitlementCategory(entitlemetCategory);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully expanded the Entitlement Category");
		}
		catch (Exception ex) {
			Assert.fail("Not able to expandIntelPartnerAlliance. " + ex.getMessage());

		}
	}
	
	/**
	 * @Description Method to verifyEntitilementPresentOrNot 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void verifyEntitilementPresentOrNot(String entitlement) throws TimeOutException {
		try {
			Assert.assertTrue(objContactPageClass.verifyPartnerAdminPresentOrNot(entitlement), "Given entitlement is not present");
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Succussfully verified EntitilementPresentOrNot");			
		}
		catch (Exception ex) {
			Assert.fail("Not able to verifyEntitilementPresentOrNot" + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to verifyEntitilementPresentOrNot 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void checkEntitlementCheckBox(String entitlement) throws TimeOutException {
		try {
			
			objContactPageClass.checkEntitlementCheckBox(entitlement);;
	        sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Successfully check Partner Admin Check Box");
		}		
		catch (Exception ex) {
			Assert.fail("Not able check checkEntitlementCheckBox" + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to grandAccessToEntitlement 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void grandOrRemoveAccessForEntitlement(String entitlementCategory, String entitlement) throws TimeOutException {
		try {
			objAdminFunctions.ClickOnshowMoreActions();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.clickOnGrantAccessInShowMoreActions();
			this.expandEntitlementCategory(entitlementCategory);

			this.verifyEntitilementPresentOrNot(entitlement);
			this.checkEntitlementCheckBox(entitlement);
			objAdminFunctions.clickOnSaveOnGrantAccessPage();
		}		
		catch (Exception ex) {
			Assert.fail("Not able to grandAccessToEntitlement" + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to grandAccessToEntitlement 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void validationsAfterGrandOrRemoveAccessToEntitlement(List<String> entitlement_Name, boolean isCheckedExpected) throws TimeOutException {
		try {
			if(isCheckedExpected == true)
			{
				objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
				objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.Pending.getDescription());
			} else{
				objAdminFunctions.verifyERPMIntegrationStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
				objAdminFunctions.verifyAGSIntegrationStatus(CommonEnum.IntegrationStatus.IN_PROGRESS.getDescription());
			}
//			objAdminFunctions.switchToTab(Tabs.Membership.toString());
//			//seleniumObj.waitForSeconds(5);
//			objAdminFunctions.clickOnViewAllOfContactEntitlements();
//			this.verifyEntitlementStatusAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Pending.getDescription(),entitlement_Name,isCheckedExpected);
//			objAdminFunctions.goBackToContactsPage();
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
			sfcommonObj.pageRefresh();
			sfcommonObj.waitTillLightningPageLoadComplete();
			objAdminFunctions.verifyInternalEntitlementStatus(CommonEnum.IntegrationStatus.Successful.getDescription());
			objAdminFunctions.switchToTab(Tabs.Membership.toString());
			seleniumObj.waitForSeconds(5);
 
			objAdminFunctions.clickOnViewAllOfContactEntitlements();
			sfcommonObj.pageRefresh();
			seleniumObj.waitForSeconds(10);
			this.verifyEntitlementStatusAndSelectedCheckboxOfContactEntitlements(CommonEnum.IntegrationStatus.Complete.getDescription(),entitlement_Name, isCheckedExpected);
			objAdminFunctions.goBackToContactsPage();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}		
		catch (Exception ex) {
			Assert.fail("validationsAfterGrandAccessToEntitlement failed" + ex.getMessage());
		}
	}
	
	/**
	 * @Description Method to validate whether Contact Entitlement status is complete or not
	 * @Author priti
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	public void verifyEntitlementStatusAndSelectedCheckboxOfContactEntitlements(String expectedStatus, List<String> entitlement_Name,boolean isCheckedExpected) throws TimeOutException {
		for(int i=0;i<entitlement_Name.size();i++)
		{
			try {
				WebElement entitltementActualStatusPath = seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+entitlement_Name.get(i)+"']//following::*[text()='"+expectedStatus+"'])[1]"));
				String entitltementActualStatus = entitltementActualStatusPath.getText();
				if(entitltementActualStatus.equalsIgnoreCase(expectedStatus))
					System.out.println("Entitlements status is as expected");
				boolean isCheckedActual = this.isContactEntitlementsCheckboxSelected(entitlement_Name.get(i));
				Assert.assertEquals(isCheckedActual, isCheckedExpected);
			}
			catch (Exception e) {
				log.info("Entitlements status is not as expected" + e.getMessage());
				Assert.fail(e.getClass().getSimpleName() + " : " + "Entitlements status is not as expected");
			}
		}
//		objAdminFunctions.goBackToContactsPage();
	}
	/**
	 * @Description Method to verifyPermission set value
	 * @Author Priti
	 * @Since 20-Sept-2024
	 * @throws TimeOutException
	 */
	public void verifyPermissionSetAssignment(String expectedValueOfPermissionSet)
	{
		objOperationPageClass.clickOnViewPartnerUserButton();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.getDriver().switchTo().frame(0);
		sfcommonObj.waitTillLightningPageLoadComplete();
		WebElement permissionSetXpath = seleniumObj.getDriver().findElement(By.xpath("//a/span[text()='Permission Set Assignments']"));  	
		permissionSetXpath.click();		
		WebElement actualPermissionSetValueXpath = seleniumObj.getDriver().findElement(By.xpath( "(//*[text()='Permission Set Label']//following::a[text()='"+expectedValueOfPermissionSet+"'])[1]"));  	
		String actualPermissionSetValue = actualPermissionSetValueXpath.getText();
		try
		{
			if(actualPermissionSetValue.equalsIgnoreCase(expectedValueOfPermissionSet) )
			{
				log.info("Succussfully Verified Permission set value");
			}
		}
		catch (Exception ex) {
			Assert.fail("Not able to verify permission set value" + ex.getMessage());

		}
	}

	public void verifyUpdateReasonInContactEntitlement(String updateReason, List<String> entitlement_Name) {
		for(int i=0;i<entitlement_Name.size();i++)
		{try{
				//	(//*[text()='PSG Licensing User']//following::*[text()='User record Inactive'])[last()]
				WebElement updateReasonXpath = seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+entitlement_Name.get(i)+"']//following::*[text()='"+updateReason+"'])[last()]"));
				String updateReasonXpathValue = updateReasonXpath.getText();
				if(updateReasonXpathValue.equalsIgnoreCase(updateReason))
				System.out.println("update reason value is as expected");
			}
			catch (Exception ex) {
				Assert.fail("Not able to verify update reason in contact entitlement" + ex.getMessage());

			}
		}
	}
}