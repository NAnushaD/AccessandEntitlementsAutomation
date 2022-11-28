package com.intel.ebsqa.draco.PageClasses;

import java.util.ArrayList;
import java.util.List;
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

public class OperationPageClass extends TestBase {

	public OperationPageClass() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}

	@FindBy(xpath = "//*[@data-key='setup']")
	public WebElement setup_icon;

	public void clickOnSetup() {
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForElement(setup_icon, 4, 4);
		seleniumObj.scrollToElement(setup_icon);
		setup_icon.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	@FindBy(xpath = "//*[@id='related_setup_app_home']")
	public WebElement setup_Tool;

	public void clickOnSetupTool() {
		seleniumObj.waitForElement(setup_Tool, 4, 4);
		seleniumObj.scrollToElement(setup_Tool);
		setup_Tool.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	public List<WebElement> getEnabledPermissionSets() {
		String xpath = "(//label[text()='Enabled Permission Sets']/following::select[@class='dueling ' and contains(@name,'permSetAssignSection')]/option)";
		return seleniumObj.getDriver().findElements(By.xpath(xpath));
	}

	@FindBy(xpath = "(//*[@title='Home']/span[text()='Home'])[1]")
	public WebElement home_Tab;

	public void clickOnHomeTab() {
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForElement(home_Tab, 4, 4);
		seleniumObj.scrollToElement(home_Tab);
		seleniumObj.clickByJS(home_Tab);
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	@FindBy(xpath = "//*[text()='Create PSG Quote']")
	public WebElement createPSG_QuoteButton;

	public void clickCreatePSGQuote() {
		seleniumObj.waitForElement(createPSG_QuoteButton, 4, 4);
		seleniumObj.scrollToElement(createPSG_QuoteButton);
		seleniumObj.clickByJS(createPSG_QuoteButton);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

}
