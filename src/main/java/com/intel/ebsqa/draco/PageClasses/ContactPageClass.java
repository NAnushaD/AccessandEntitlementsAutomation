package com.intel.ebsqa.draco.PageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

import junit.framework.Assert;

public class ContactPageClass extends TestBase {

	public ContactPageClass() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}
	JavascriptExecutor executor = (JavascriptExecutor) seleniumObj.getDriver();
	
	/**
	 * @Description Method to expandEntitlementCategory 
	 * @Author nmurugan
	 * @Since Sep 19, 2024
	 * @throws TimeOutException
	 * @throws InterruptedException 
	 */
	 public void expandEntitlementCategory(String entitlementCategory) {
			WebElement entitlementCategoryPath=seleniumObj.getDriver().findElement(By.xpath("(//*[contains(text(),'" + entitlementCategory + "')]/parent::div/div/div[@class='headerPlusMinusIcon'])[1]"));
	 		seleniumObj.waitForElement(entitlementCategoryPath, 4, 4);
	 		seleniumObj.scrollToElement(entitlementCategoryPath);
	 		executor.executeScript("window.scrollBy(0,250)", "");
	 		executor.executeScript("arguments[0].click();", entitlementCategoryPath);
	 		sfcommonObj.waitTillLightningPageLoadComplete();
	 	}
	 /**
	  * @Description Method to verifyEntitilementPresentOrNot 
	  * @Author nmurugan
	  * @Since Sep 19, 2024
	  * @throws TimeOutException
	  * @throws InterruptedException 
	  */
	 public boolean verifyPartnerAdminPresentOrNot(String entitlement) {
		 	WebElement entitlementPath=seleniumObj.getDriver().findElement(By.xpath("//*[text()='" + entitlement + "']"));
	 		seleniumObj.waitForElement(entitlementPath, 4, 4);
			seleniumObj.scrollToElement(entitlementPath);
			entitlementPath.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
			return entitlementPath.isDisplayed();
	 }
	 
	 /**
	  * @Description Method to checkEntitlementCheckBox 
	  * @Author nmurugan
	  * @Since Sep 19, 2024
	  * @throws TimeOutException
	  * @throws InterruptedException 
	  */
	 public void checkEntitlementCheckBox(String entitlement) {
		 	WebElement entitlementCheckBoxPath=seleniumObj.getDriver().findElement(By.xpath("(//*[text()='" + entitlement + "']/following::span[@class='slds-checkbox--faux'])[1]"));
	 		seleniumObj.waitForElement(entitlementCheckBoxPath, 4, 4);
			seleniumObj.scrollToElement(entitlementCheckBoxPath);
			entitlementCheckBoxPath.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
	 }
	 
	 
	 
	 
	 
	 @FindBy(xpath="")
		public WebElement PartnerAdminCheckbox;
		public void checkPartnerAdminCheckbox() {

			seleniumObj.waitForElement(PartnerAdminCheckbox, 4, 4);
			seleniumObj.scrollToElement(PartnerAdminCheckbox);
			PartnerAdminCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

	    }
		
	
	 
	 
}