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

public class OperationPageClass extends TestBase {

	public OperationPageClass() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}

	
	
	@FindBy(xpath = "//label[text()='Salutation']/following::button[@name='salutation']")
	public WebElement salutationContact;

	@FindBy(xpath = "(//label[text()='First Name']/following::input[@name='firstName'])[1]")
	public WebElement firstNameContact;

	@FindBy(xpath = "(//label[text()='Last Name']/following::input[@name='lastName'])[1]")
	public WebElement lastNameContact;

	@FindBy(xpath = "(//label[text()='Email']/following::input[@name='Email'])[1]")
	public WebElement email;
	
	@FindBy(xpath = "//*[@data-key='setup']")
	public WebElement setup_icon;
	
	@FindBy(xpath = "//select[@name='name_salutationcon2']")
	public WebElement salutationContactclassic;

	@FindBy(xpath = "(//label[text()='First Name']/following::input[@name='name_firstcon2'])[1]")
	public WebElement firstNameContactclassic;

	@FindBy(xpath = "(//label[text()='Last Name']/following::input[@name='name_lastcon2'])[1]")
	public WebElement lastNameContactclassic;

	@FindBy(xpath = "//label[text()='Email']/following::input[@name='con15']")
	public WebElement emailclassic;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='CCP CCF USER'])[1]")
	public WebElement cCPCCFuser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='CCP External User Admin'])[1]")
   	public WebElement cCPExternalUser;
	
	@FindBy(xpath = "(//h3[text()='Permission Set Assignments']//following::th[text()='No records to display'])[1]")
	public WebElement nORecordToDisplay;
	
	//@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='PSG External - License User'])[1]")
   	@FindBy(xpath="(//*[text()='Permission Set Assignments']//following::a[text()='PSG External - License User'])[1]")
	public WebElement pSGExternalUser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='CPQ Managed License User'])[1]")
   	public WebElement CPQManagedLicenseUser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='PSG External - Disty'])[1]")
   	public WebElement PSGExternalDisty;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='Tender Partner Drafter Access'])[1]")
   	public WebElement TenderPartnerDrafterAccess;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='UCD ACM Foundation Access'])[1]")
   	public WebElement FoundationAccess;
	
	@FindBy(xpath = "(//*[text()='Permission Set Assignments']//following::a[text()='PMP External - Employee'])[1]")
	public WebElement Employee;
	
	
	@FindBy(xpath = "//input[contains(@placeholder,'Type here')]")
	public WebElement typeHereTextbox;
	
	@FindBy(xpath="//input[contains(@placeholder,'Enter Email Address...')]")
	public WebElement enteremailaddress;
	
	@FindBy(xpath = "(//label[text()='First Name']/following::input)[1]")
	public WebElement firstNameContactThroughPartnerCenter;

	@FindBy(xpath = "(//label[text()='Last Name']/following::input)[1]")
	public WebElement lastNameContactThroughPartnerCenter;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='SPCF USER'])[1]")
	public WebElement sPCFuser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='CCP External Activity Submitter'])[1]")
   	public WebElement cCPActivitySubmitter;
	
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
	
	JavascriptExecutor executor = (JavascriptExecutor) seleniumObj.getDriver();
	
	public void switchToTab(String tab){
			WebElement tabelement = seleniumObj.getDriver().findElement(By.xpath("//*[text()='"+tab+"']"));
			seleniumObj.waitForElement(tabelement, 4, 4);
			seleniumObj.scrollToElement(tabelement);
			executor.executeScript("arguments[0].click();", tabelement);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	
	public void switchToLightningExperience(){
		try
		{
		WebElement isLightning=seleniumObj.getDriver().findElement(By.xpath("//a[@class='switch-to-lightning']"));
		if(isLightning.isDisplayed())
		{
		WebElement element = seleniumObj.getDriver().findElement(By.xpath("//a[@class='switch-to-lightning']"));
		seleniumObj.waitForElement(element, 4, 4);
		seleniumObj.scrollToElement(element);
		executor.executeScript("arguments[0].click();", element);
		sfcommonObj.waitTillLightningPageLoadComplete();
		}
		else
		{
			sfcommonObj.waitTillLightningPageLoadComplete();
		}}
		catch(Exception e)
		{
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
	}
	public void switchToClassicExperience(){
		try
		{
		WebElement isClassic=seleniumObj.getDriver().findElement(By.xpath("(//span[@class='uiImage'])[1]"));
		if(isClassic.isDisplayed())
		{
		WebElement element = seleniumObj.getDriver().findElement(By.xpath("(//span[@class='uiImage'])[1]"));
		seleniumObj.waitForElement(element, 4, 4);
		seleniumObj.scrollToElement(element);
		executor.executeScript("arguments[0].click();", element);
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForSeconds(5);
		}
		else
		{
			sfcommonObj.waitTillLightningPageLoadComplete();
		}}
		catch(Exception e)
		{
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
	}
	public void clickonClassicExperience()
	{
		try{
			WebElement classic = seleniumObj.getDriver().findElement(By.xpath("//a[text()='Switch to Salesforce Classic']"));
			if(classic.isDisplayed())
			{
			WebElement element = seleniumObj.getDriver().findElement(By.xpath("//a[text()='Switch to Salesforce Classic']"));
			seleniumObj.waitForElement(element, 4, 4);
			seleniumObj.scrollToElement(element);
			executor.executeScript("arguments[0].click();", element);
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.waitForSeconds(5);
			}
			else
			{
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
		}
		catch(Exception e)
		{
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	}
	public void clickButton(String button){
		WebElement buttonelement = seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+button+"'])[1]"));
		seleniumObj.waitForElement(buttonelement, 4, 4);
		seleniumObj.scrollToElement(buttonelement);
		executor.executeScript("arguments[0].click();", buttonelement);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	public void clickContactsClassic(){
		WebElement contactsclassic = seleniumObj.getDriver().findElement(By.xpath("//a[@title='Contacts Tab - Selected']"));
		seleniumObj.waitForElement(contactsclassic, 4, 4);
		seleniumObj.scrollToElement(contactsclassic);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	public void clickContinueClassic(){
		WebElement Continueclassic = seleniumObj.getDriver().findElement(By.xpath("//input[@title='Continue']"));
		seleniumObj.waitForElement(Continueclassic, 4, 4);
		seleniumObj.scrollToElement(Continueclassic);
		Continueclassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	public void selectContactTypeAsIntelContact(){
		WebElement contactType = seleniumObj.getDriver().findElement(By.xpath("//span[text()='Intel Contact']/parent::*/parent::*/div/span[@class='slds-radio--faux']"));
		seleniumObj.waitForElement(contactType, 4, 4);
		seleniumObj.scrollToElement(contactType);
		contactType.click();
	}
	public void selectContactTypeAsIntelContactAndClickNextClassic(){
		WebElement contactTypeClassic=seleniumObj.getDriver().findElement(By.xpath("//option[text()='Intel Contact']"));
		seleniumObj.waitForElement(contactTypeClassic, 4, 4);
		seleniumObj.scrollToElement(contactTypeClassic);
		contactTypeClassic.click();
	}
	public void clickOnNewbuttonContactClassic(){
		WebElement NewbuttonClassic =seleniumObj.getDriver().findElement(By.xpath("//input[@title='New'][1]"));
		seleniumObj.waitForElement(NewbuttonClassic,4,4);
		seleniumObj.scrollToElement(NewbuttonClassic);
		NewbuttonClassic.click();
	}
	
	public void clickOnDropdownClassic(){
		WebElement dropdownClassic =seleniumObj.getDriver().findElement(By.xpath("//select[@id='p3']"));
		seleniumObj.waitForElement(dropdownClassic,4,4);
		seleniumObj.scrollToElement(dropdownClassic);
		dropdownClassic.click();
	}
	public void clickOnSalutation() {
		seleniumObj.waitForElement(salutationContactclassic, 4, 3);
		seleniumObj.scrollToElement(salutationContactclassic);
		seleniumObj.clickByJS(salutationContactclassic);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public void selectSalutationclassic(String salutation) {
		WebElement conatctSalutation = seleniumObj.getDriver().findElement(
				By.xpath("(//label[text()='Salutation']/following::div[contains(@class,'slds-listbox')]//*[@data-value='"
						+ salutation + "'])[1]"));
		seleniumObj.waitForElement(conatctSalutation, 4, 4);
		seleniumObj.scrollToElement(conatctSalutation);
		conatctSalutation.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();
	}

	public void selectSalutation(String salutation) {
		WebElement conatctSalutation = seleniumObj.getDriver().findElement(
				By.xpath("(//label[text()='Salutation']/following::div[contains(@class,'slds-listbox')]//*[@data-value='"
						+ salutation + "'])[1]"));
		seleniumObj.waitForElement(conatctSalutation, 4, 4);
		seleniumObj.scrollToElement(conatctSalutation);
		conatctSalutation.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();
	}	
	/**
	 * 
	 * @Description Method to enterFirstNameContact
	 * @Author manish9x
	 * @Since 29-Nov-2022
	 */
	public void enterFirstNameContactclassic(String searchText) {
		try {
			seleniumObj.waitForElement(firstNameContactclassic, 4, 4);
			seleniumObj.scrollToElement(firstNameContactclassic);
			firstNameContactclassic.click();
			firstNameContactclassic.clear();
			firstNameContactclassic.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in first name textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in first name text box");
			Assert.fail("Not able to enter text in first name text box");
		}

	}
	public void enterFirstNameContact(String searchText) {
		try {
			seleniumObj.waitForElement(firstNameContact, 4, 4);
			seleniumObj.scrollToElement(firstNameContact);
			firstNameContact.click();
			firstNameContact.clear();
			firstNameContact.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in first name textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in first name text box");
			Assert.fail("Not able to enter text in first name text box");
		}

	}
	public void enterfilter(String searchText) {
		try {
			seleniumObj.waitForElement(filterEmailaddress, 4, 4);
			seleniumObj.scrollToElement(filterEmailaddress);
			//filterEmailaddress.click();
			//filterEmailaddress.clear();
			filterEmailaddress.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in first name textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in filter text box");
			Assert.fail("Not able to enter text in filter text box");
		}

	}
	/**
	 * 
	 * @Description Method to enter Last name
	 * @Author manish9x
	 * @param searchText
	 * @Since 29-Nov-2022
	 */
	public void enterLastNameContactclassic(String searchText) {
		try {
			seleniumObj.waitForElement(lastNameContactclassic, 4, 4);
			seleniumObj.scrollToElement(lastNameContactclassic);
			lastNameContactclassic.click();
			lastNameContactclassic.clear();
			lastNameContactclassic.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in Last name textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in Last name text box");
			Assert.fail("Not able to enter text in Last name text box");
		}
	}
	public void enterLastNameContact(String searchText) {
		try {
			seleniumObj.waitForElement(lastNameContact, 4, 4);
			seleniumObj.scrollToElement(lastNameContact);
			lastNameContact.click();
			lastNameContact.clear();
			lastNameContact.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in Last name textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in Last name text box");
			Assert.fail("Not able to enter text in Last name text box");
		}
	}
	/**
	 * 
	 * @Description Method enter email
	 * @Author manish9x
	 * @param searchText
	 * @Since 29-Nov-2022
	 */
	public void enterEmailContactclassic(String searchText) {
		try {
			seleniumObj.waitForElement(emailclassic, 4, 4);
			seleniumObj.scrollToElement(emailclassic);
			emailclassic.click();
			emailclassic.clear();
			emailclassic.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in email textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in email text box");
			Assert.fail("Not able to enter text in email text box");
		}
	}
	public void enterEmailContact(String searchText) {
		try {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(2000);
			String Email=searchText+randomInt+"@mailinator.com";
			System.out.println(Email);
			seleniumObj.waitForElement(email, 4, 4);
			seleniumObj.scrollToElement(email);
			email.click();
			email.clear();
			email.sendKeys(Email);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in email textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in email text box");
			Assert.fail("Not able to enter text in email text box");
		}
	}
	@FindBy(xpath = "//label[text()='Account Name']/following::input[@id='con4']")
	public WebElement accountNameclassic;

	@FindBy(xpath="//img[@title='Account Name Lookup (New Window)']")
	public WebElement searchicon;
	
	public void enterAccountNameclassic(String account) {
		seleniumObj.waitForElement(accountNameclassic, 4, 4);
		seleniumObj.scrollToElement(accountNameclassic);
		accountNameclassic.clear();
		accountNameclassic.sendKeys(account);
		sfcommonObj.waitTillLightningPageLoadComplete();

	}
	

    @FindBy(xpath = "(//label[text()='Account Name']/following::input[@placeholder='Search Accounts...'])[1]")
	//@FindBy(xpath="(//label[text()='Account Name']/following::input[@name='con4'])[1]")
	public WebElement accountName;

    public void enterAccountName(String account) {
	seleniumObj.waitForElement(accountName, 4, 4);
	seleniumObj.scrollToElement(accountName);
	accountName.clear();
	accountName.sendKeys(account);
	sfcommonObj.waitTillLightningPageLoadComplete();

}
	@FindBy(xpath = "(//span[contains(@title,'Show more results for')])")
	public WebElement showAllResults;

	public void selectAccountName(String account) {
		seleniumObj.waitForElement(showAllResults, 4, 4);
		seleniumObj.scrollToElement(showAllResults);
		showAllResults.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
//		WebElement ele = seleniumObj.getDriver().findElement(
//				By.xpath("(//div[text()='Accounts']/following::a[text()='Account Name']/following::a[@title='"+ account + "'])[1]"));
		WebElement ele = seleniumObj.getDriver().findElement(
				By.xpath("(//span[text()='Account Name'])[last()]//following::lightning-base-formatted-text[text()='"+ account + "'][1]//preceding::span[@class='slds-radio_faux'][1]"));
		seleniumObj.waitForElement(ele, 5, 5);
		ele.click();
		WebElement selectBtn = seleniumObj.getDriver().findElement(By.xpath("//button[text()='Select']"));
		seleniumObj.waitForElement(selectBtn, 5, 5);
		selectBtn.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "(//button[text()='Save'])[1]")
	public WebElement save;

	public void clickSave() {
		seleniumObj.waitForElement(save, 4, 4);
		seleniumObj.scrollToElement(save);
		save.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "(//span[text()='ERPM Integration Status']/following::lightning-formatted-text)[1]")
	public WebElement eRPMIntegrationStatus;
	
	public String getERPMIntegrationStatus() {
		seleniumObj.waitForElement(eRPMIntegrationStatus, 4, 4);
		seleniumObj.scrollToElement(eRPMIntegrationStatus);
		return eRPMIntegrationStatus.getText();
	}
    @FindBy(xpath = "(//span[text()='AGS Integration Status']/following::lightning-formatted-text)[1]")
	public WebElement AGSIntegrationStatus;

    public String getAGSIntegrationStatus() {
		seleniumObj.waitForElement(AGSIntegrationStatus, 4, 4);
		seleniumObj.scrollToElement(AGSIntegrationStatus);
		return AGSIntegrationStatus.getText();
	}
    
	@FindBy(xpath = "//div[@id='00No000000Eg0V4_ileinner']")
	public WebElement eRPMIntegrationStatusclassic;

	public String getERPMIntegrationStatusclassic() {
		seleniumObj.waitForElement(eRPMIntegrationStatusclassic, 4, 4);
		seleniumObj.scrollToElement(eRPMIntegrationStatusclassic);
		return eRPMIntegrationStatusclassic.getText();
	}
	
	@FindBy(xpath = "//div[@id='00No000000Eg0V1_ileinner']")
	public WebElement AGSIntegrationStatusclassic;

	public String getAGSIntegrationStatusclassic() {
		seleniumObj.waitForElement(AGSIntegrationStatusclassic, 4, 4);
		seleniumObj.scrollToElement(AGSIntegrationStatusclassic);
		return AGSIntegrationStatusclassic.getText();
	}
	
	@FindBy(xpath = "(//input[@name='core_grant_access'])[1]")
	public WebElement grantAccessButtonclassic;

	public void clickOnGrantAccessButtonclassic() {
		seleniumObj.waitForElement(grantAccessButtonclassic, 4, 4);
		seleniumObj.scrollToElement(grantAccessButtonclassic);
		grantAccessButtonclassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "//button[text()='Grant Access']")
	public WebElement grantAccessButton;

	public void clickOnGrantAccessButton() {
		seleniumObj.waitForElement(grantAccessButton, 4, 4);
		seleniumObj.scrollToElement(grantAccessButton);
		grantAccessButton.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "(//span[text()='Grant Access'])[1]")
	public WebElement grantAccessButtonInShowMoreActions;

	public void clickOnGrantAccessInShowMoreActions() {
		seleniumObj.waitForElement(grantAccessButtonInShowMoreActions, 4, 4);
		seleniumObj.scrollToElement(grantAccessButtonInShowMoreActions);
		grantAccessButtonInShowMoreActions.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "//*[contains(text(),'Intel® Partner Investment Center')]/parent::div/div/div[@class='headerPlusMinusIcon']")
	public WebElement consolidatedPlatform;

	public void expandConsolidatedPlatform() {
		seleniumObj.waitForElement(consolidatedPlatform, 4, 4);
		seleniumObj.scrollToElement(consolidatedPlatform);
		consolidatedPlatform.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[contains(text(),'Consolidated Co-marketing Platform')]/parent::div/div/div[@class='headerPlusMinusIcon'][1]")
	public WebElement consolidatedPlatformclassic;
	
	public void expandConsolidatedPlatformclassic() {
		seleniumObj.waitForElement(consolidatedPlatformclassic, 4, 4);
		seleniumObj.scrollToElement(consolidatedPlatformclassic);
//		consolidatedPlatformclassic.click();
		seleniumObj.clickByJS(consolidatedPlatformclassic);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[text()='Channel MDF User Administrator']")
	public WebElement ChannelMDFUserAdministrator;

	public boolean verifyChannelMDFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(ChannelMDFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(ChannelMDFUserAdministrator);
		return ChannelMDFUserAdministrator.isDisplayed();
     }
	
	@FindBy(xpath = "(//*[text()='Channel MDF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement ChannelMDFUserAdministratorCheckbox;

	public void checkChannelMDFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(ChannelMDFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(ChannelMDFUserAdministratorCheckbox);
		ChannelMDFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[text()='RMF User Administrator']")
	public WebElement RMFUserAdministrator;

	public boolean verifyRMFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(RMFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(RMFUserAdministrator);
		return RMFUserAdministrator.isDisplayed();
     }
	
	@FindBy(xpath = "(//*[text()='RMF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement RMFUserAdministratorCheckbox;

	public void checkRMFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(RMFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(RMFUserAdministratorCheckbox);
		RMFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[text()='SPCF User Administrator']")
	public WebElement SPCFUserAdministrator;

	public boolean verifySPCFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(SPCFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(SPCFUserAdministrator);
		return SPCFUserAdministrator.isDisplayed();
     }
	
	@FindBy(xpath = "(//*[text()='SPCF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement SPCFUserAdministratorCheckbox;

	public void checkSPCFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(SPCFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(SPCFUserAdministratorCheckbox);
		SPCFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[text()='DCF User Administrator']")
	public WebElement DCFUserAdministrator;

	public boolean verifyDCFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(DCFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(DCFUserAdministrator);
		return DCFUserAdministrator.isDisplayed();
     }
	
	@FindBy(xpath = "(//*[text()='DCF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement DCFUserAdministratorCheckbox;

	public void checkDCFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(DCFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(DCFUserAdministratorCheckbox);
		DCFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "//*[text()='CCF Boost User Administrator']")
	public WebElement CCFBoostUserAdministrator;

	public boolean verifyCCFBoostUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(CCFBoostUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(CCFBoostUserAdministrator);
		return CCFBoostUserAdministrator.isDisplayed();
     }
	
	@FindBy(xpath = "(//*[text()='CCF Boost User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement CCFBoostUserAdministratorCheckbox;

	public void checkCCFBoostUserAdministratorCheckbox() {

		seleniumObj.waitForElement(CCFBoostUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(CCFBoostUserAdministratorCheckbox);
		CCFBoostUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	//@FindBy(xpath = "//*[text()='CCF User Administrator']")
	@FindBy(xpath ="//*[text()='Client User Administrator']")
	public WebElement CCFUserAdministrator;

	public boolean verifyCCFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(CCFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(CCFUserAdministrator);
		return CCFUserAdministrator.isDisplayed();
     }
	
	//@FindBy(xpath = "(//*[text()='CCF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	@FindBy(xpath = "(//*[text()='Client User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement CCFUserAdministratorCheckbox;

	public void checkCCFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(CCFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(CCFUserAdministratorCheckbox);
		CCFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath="//select[@id='portalUserLoginAsSelect']")
	public WebElement dropdownclassic;
	
	public void clickondropdowncommunityclassic() {

		seleniumObj.waitForElement(dropdownclassic, 4, 4);
		seleniumObj.scrollToElement(dropdownclassic);
		dropdownclassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath="//*[text()='Partnercenter Unified Community']")
	public WebElement community;
	
	public void clickonccpcommunityclassic() {

		seleniumObj.waitForElement(community, 4, 4);
		seleniumObj.scrollToElement(community);
		community.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath="//*[text()='CCF User Administrator']/following::input[@id='j_id0:j_id96:pbEngagements:j_id119:0:j_id125:14:isinstance']")
	public WebElement CCFUserAdministratorCheckboxclassic;
	
	public void checkCCFUserAdministratorCheckboxclassic() {

		seleniumObj.waitForElement(CCFUserAdministratorCheckboxclassic, 4, 4);
		seleniumObj.scrollToElement(CCFUserAdministratorCheckboxclassic);
		CCFUserAdministratorCheckboxclassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public WebElement getSelectedCheckboxOfContactEntitlementsAsFalse() {

		WebElement element = seleniumObj.getDriver().findElement(
				By.xpath("((//span[text()='Selected'])[2]/following::span/span/img)[2]"));
		return element;
	}
	
	@FindBy(xpath = "//input[@class='btn' and contains(@value,'Save ')]")
	public WebElement saveOnGrantAccessPage;

	public void clickOnSaveOnGrantAccessPage() {
		seleniumObj.waitForElement(saveOnGrantAccessPage, 4, 4);
		seleniumObj.scrollToElement(saveOnGrantAccessPage);
		saveOnGrantAccessPage.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "(//input[@value=' Save '])[2]")
	public WebElement saveOnGrantAccessPagelight;

	public void clickOnSaveOnGrantAccessPagelight() {
		seleniumObj.waitForElement(saveOnGrantAccessPagelight, 4, 4);
		seleniumObj.scrollToElement(saveOnGrantAccessPagelight);
		saveOnGrantAccessPagelight.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveOnManagePersonnel;
	
	public void clickOnSaveOnManagePersonnel() {
		seleniumObj.waitForElement(saveOnManagePersonnel, 4, 4);
		seleniumObj.scrollToElement(saveOnManagePersonnel);
		saveOnManagePersonnel.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	@FindBy(xpath = "(//span[text()='Contact Entitlements']/following::span[text()='View All'])[1]")
	public WebElement ViewAllOfContactEntitlements;

	public void clickOnViewAllOfContactEntitlements() {
		seleniumObj.waitForElement(ViewAllOfContactEntitlements, 4, 4);
		seleniumObj.scrollToElement(ViewAllOfContactEntitlements);
		executor.executeScript("arguments[0].click();", ViewAllOfContactEntitlements);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public void goBackToContactsPage(){
			WebElement ContactCreatedElement = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='Contacts']/following::a)[1]"));
			seleniumObj.waitForElement(ContactCreatedElement, 4, 4);
			seleniumObj.scrollToElement(ContactCreatedElement);
			executor.executeScript("arguments[0].click();", ContactCreatedElement);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	
	@FindBy(xpath = "(//button[text()='View Partner User'])[1]")
	public WebElement viewPartnerUserButton;
	
	@FindBy(xpath = "(//span[text()='View Partner User'])[1]")
	public WebElement viewPartnerUserButton1;

	public void clickOnViewPartnerUserButton() {
		try{
		seleniumObj.waitForElement(viewPartnerUserButton, 4, 4);
		seleniumObj.scrollToElement(viewPartnerUserButton);
		viewPartnerUserButton.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		}
		catch(Exception e){
			seleniumObj.waitForElement(viewPartnerUserButton1, 4, 4);
			seleniumObj.scrollToElement(viewPartnerUserButton1);
			viewPartnerUserButton1.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
	}
	
	@FindBy(xpath="//span[@id='workWithPortalLabel']")
	public WebElement ManageExternalUserClassic;
	
	public void clickOnManageExternalUserButtonclassic() {
		seleniumObj.waitForElement(ManageExternalUserClassic, 4, 4);
		seleniumObj.scrollToElement(ManageExternalUserClassic);
		ManageExternalUserClassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath="//a[text()='View Partner User'][1]")
	public WebElement ViewPartnerUser;
	
	public void clickOnViewPartnerUserButtonclassic() {
		seleniumObj.waitForElement(ViewPartnerUser, 4, 4);
		seleniumObj.scrollToElement(ViewPartnerUser);
		ViewPartnerUser.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public WebElement getActiveCheckboxOnUser() {

		WebElement element = seleniumObj.getDriver().findElement(
				By.xpath("(//*[text()='Active']/following::img)[1]"));
		return element;
	}
	
	@FindBy(xpath = "(//*[text()='Contact']/following::a)[1]")
	public WebElement contactFromPartnerUserPage;

	public void goToContactFromPartnerUserPage() {
		seleniumObj.waitForElement(contactFromPartnerUserPage, 4, 4);
		seleniumObj.scrollToElement(contactFromPartnerUserPage);
		executor.executeScript("arguments[0].click();", contactFromPartnerUserPage);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	 @FindBy(xpath = "(//button//span[text()='Show more actions'])[1]")
	 public WebElement showMoreActions; 
     public void ClickOnshowMoreActions() {
			executor.executeScript("arguments[0].click();", showMoreActions);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
     
     @FindBy(xpath = "//*[contains(text(),'Programmable Solutions Group')]/parent::div/div/div[@class='headerPlusMinusIcon']")
 	public WebElement programmableSolutionsGroup;

 	public void expandProgrammableSolutionsGroup() {
 		seleniumObj.waitForElement(programmableSolutionsGroup, 4, 4);
 		seleniumObj.scrollToElement(programmableSolutionsGroup);
 		programmableSolutionsGroup.click();
 		sfcommonObj.waitTillLightningPageLoadComplete();
 	}
     
 	@FindBy(xpath = "//*[text()='PSG Licensing User']")
	public WebElement PSGLicensingUser;

	public boolean verifyPSGLicensingUserEntitlementPresentOrNot() {

		seleniumObj.waitForElement(PSGLicensingUser, 4, 4);
		seleniumObj.scrollToElement(PSGLicensingUser);
		return PSGLicensingUser.isDisplayed();

	}
	
	@FindBy(xpath = "(//*[text()='PSG Licensing User']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement PSGLicensingUserCheckbox;

	public void checkPSGLicensingUserCheckbox() {

		seleniumObj.waitForElement(PSGLicensingUserCheckbox, 4, 4);
		seleniumObj.scrollToElement(PSGLicensingUserCheckbox);
		PSGLicensingUserCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();

	}
	
	@FindBy(xpath = "(//*[text()='PSG Licensing User']/following::label[@class='slds-checkbox1'])[1]")
	public WebElement PSGLicensingUserCheckboxclassic;

	public void checkPSGLicensingUserCheckboxclassic() {

		seleniumObj.waitForElement(PSGLicensingUserCheckboxclassic, 4, 4);
		seleniumObj.scrollToElement(PSGLicensingUserCheckboxclassic);
		PSGLicensingUserCheckboxclassic.click();
		sfcommonObj.waitTillLightningPageLoadComplete();

	}
	
	public void ClickOnEntitlementRecordElement(String elememnt) {
		
    	WebElement ele = seleniumObj.getDriver().findElement(By.xpath("(//span[text()='"+elememnt+"']//ancestor::tr//following::a)[1]"));
		executor.executeScript("arguments[0].click();", ele);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "(//span[text()='Status of Assignment/Removal']//following::lightning-formatted-text)[1]")
   	public WebElement statusOfAssignmentorRemoval;
	
	public void validateStatusOfAssignmentorRemoval()
	{
		try {

		String status=statusOfAssignmentorRemoval.getText();

		if (status.equalsIgnoreCase("Successful Assignment"))
		{
			log.info("statusOfAssignmentorRemoval  is Successful");

		}
	}
		catch (AssertionError e) {
			
			log.info(e.getMessage());
			Assert.fail("statusOfAssignmentorRemoval is not successful" + e.getMessage());
			}
	}
	
	public void validateStatusOfRemovalOfEntitlement()
	{
	
		try {

		String status=statusOfAssignmentorRemoval.getText();

		if (status.equalsIgnoreCase("Successful Removal"))
		{				
						System.out.println("statusOfAssignmentorRemoval is showing as Successful removal");
						log.info("statusOfAssignmentorRemoval  is Successful removal");

		}
	}
		catch (AssertionError e) {
			
			log.info(e.getMessage());
			Assert.fail("statusOfAssignmentorRemoval is not successful- " + e.getMessage());
			}
	}
	
	 @FindBy(xpath = "(//span[text()='Contact']/following::a)[1]")
	   	public WebElement contactNameInEntitlement;
	    
	    public void clickOncontactNameInEntitlement() 
	    {
	    	seleniumObj.waitForSeconds(10);
			executor.executeScript("arguments[0].click();", contactNameInEntitlement);
			sfcommonObj.waitTillLightningPageLoadComplete();
	    }
	    
	    @FindBy(xpath = "//*[contains(text(),'Contact is flagged with Global Export Compliance Block')]")
		public WebElement ErrorOnGrantAccessPage;

		public boolean verifyErrorOnGrantAccessPage() {

			seleniumObj.waitForElement(ErrorOnGrantAccessPage, 4, 4);
			seleniumObj.scrollToElement(ErrorOnGrantAccessPage);
			return ErrorOnGrantAccessPage.isDisplayed();

		}
		
		@FindBy(xpath = "//input[@class='btn' and contains(@value,'Cancel')]")
		public WebElement cancelOnGrantAccessPage;

		public void clickOnCancelOnGrantAccessPage() {
			seleniumObj.waitForElement(cancelOnGrantAccessPage, 4, 4);
			seleniumObj.scrollToElement(cancelOnGrantAccessPage);
			cancelOnGrantAccessPage.click();
		}
		
		@FindBy(xpath = "//*[text()='PSG Disti Quoting Access']")
		public WebElement PSGDistiQuotingAccess;

		public boolean verifyPSGDistiQuotingAccessEntitlementPresentOrNot() {

			seleniumObj.waitForElement(PSGDistiQuotingAccess, 4, 4);
			seleniumObj.scrollToElement(PSGDistiQuotingAccess);
			return PSGDistiQuotingAccess.isDisplayed();

		}
		
		@FindBy(xpath = "(//*[text()='PSG Disti Quoting Access']/following::span[@class='slds-checkbox--faux'])[1]")
		public WebElement PSGDistiQuotingAccessCheckbox;

		public void checkPSGDistiQuotingAccessCheckbox() {

			seleniumObj.waitForElement(PSGDistiQuotingAccessCheckbox, 4, 4);
			seleniumObj.scrollToElement(PSGDistiQuotingAccessCheckbox);
			PSGDistiQuotingAccessCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		@FindBy(xpath = "(//*[text()='PSG Disti Quoting Access']/following::label[@class='slds-checkbox1'])[1]")
		public WebElement PSGDistiQuotingAccessCheckboxclassic;

		public void checkPSGDistiQuotingAccessCheckboxclassic() {

			seleniumObj.waitForElement(PSGDistiQuotingAccessCheckboxclassic, 4, 4);
			seleniumObj.scrollToElement(PSGDistiQuotingAccessCheckboxclassic);
			PSGDistiQuotingAccessCheckboxclassic.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		@FindBy(xpath = "//*[contains(text(),'Intel Quote Request')]/parent::div/div/div[@class='headerPlusMinusIcon']")
	 	public WebElement IntelQuoteRequest;

	 	public void expandIntelQuoteRequest() {
	 		seleniumObj.waitForElement(IntelQuoteRequest, 4, 4);
	 		seleniumObj.scrollToElement(IntelQuoteRequest);
	 		IntelQuoteRequest.click();
	 		sfcommonObj.waitTillLightningPageLoadComplete();
	 	}
	 	
	 	@FindBy(xpath = "//*[text()='Customer Drafter Access']")
		public WebElement CustomerDrafterAccess;

		public boolean verifyCustomerDrafterAccessEntitlementPresentOrNot() {

			seleniumObj.waitForElement(CustomerDrafterAccess, 4, 4);
			seleniumObj.scrollToElement(CustomerDrafterAccess);
			return CustomerDrafterAccess.isDisplayed();

		}
		
		@FindBy(xpath = "(//*[text()='Customer Drafter Access']/following::span[@class='slds-checkbox--faux'])[1]")
		public WebElement CustomerDrafterAccessCheckbox;

		public void checkCustomerDrafterAccessCheckbox() {

			seleniumObj.waitForElement(CustomerDrafterAccessCheckbox, 4, 4);
			seleniumObj.scrollToElement(CustomerDrafterAccessCheckbox);
			CustomerDrafterAccessCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		@FindBy(xpath = "(//*[text()='Customer Drafter Access']/following::label[@class='slds-checkbox1'])[1]")
		public WebElement CustomerDrafterAccessCheckboxclassic;

		public void checkCustomerDrafterAccessCheckboxclassic() {

			seleniumObj.waitForElement(CustomerDrafterAccessCheckboxclassic, 4, 4);
			seleniumObj.scrollToElement(CustomerDrafterAccessCheckboxclassic);
			CustomerDrafterAccessCheckboxclassic.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		
		@FindBy(xpath = "(//*[text()='Employee']/following::label[@class='slds-checkbox1'])[1]")
		public WebElement EmployeeCheckboxclassic;

		public void checkEmployeeCheckboxclassic() {

			seleniumObj.waitForElement(EmployeeCheckboxclassic, 4, 4);
			seleniumObj.scrollToElement(EmployeeCheckboxclassic);
			EmployeeCheckboxclassic.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		@FindBy(xpath = "(//span[text()='Partner Contact Relationship']/following::span[text()='View All'])[1]")
		public WebElement ViewAllOfPartnerContactRelationship;

		public void clickOnViewAllOfPartnerContactRelationship() {
			seleniumObj.waitForElement(ViewAllOfPartnerContactRelationship, 4, 4);
			seleniumObj.scrollToElement(ViewAllOfPartnerContactRelationship);
			executor.executeScript("arguments[0].click();", ViewAllOfPartnerContactRelationship);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		 @FindBy(xpath = "(//*[contains(text(),'Intel Partner Alliance')]/parent::div/div/div[@class='headerPlusMinusIcon'])[1]")
		 public WebElement IntelPartnerAlliance;

		 public void expandIntelPartnerAlliance() {
		 		seleniumObj.waitForElement(IntelPartnerAlliance, 4, 4);
		 		seleniumObj.scrollToElement(IntelPartnerAlliance);
		 		executor.executeScript("window.scrollBy(0,250)", "");
		 		executor.executeScript("arguments[0].click();", IntelPartnerAlliance);
		 		sfcommonObj.waitTillLightningPageLoadComplete();
		 }
		
		@FindBy(xpath = "(//*[text()='Employee']/following::span[@class='slds-checkbox--faux'])[1]")
		public WebElement EmployeeCheckbox;

		public void checkEmployeeCheckbox() {

				seleniumObj.waitForElement(EmployeeCheckbox, 4, 4);
				seleniumObj.scrollToElement(EmployeeCheckbox);
				EmployeeCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();

		}
		
		@FindBy(xpath="(//*[text()='Partner Admin Delegate']/following::span[@class='slds-checkbox--faux'])[1]")
		public WebElement PartnerAdminDelegateCheckbox;
		
		public void checkPADCheckbox() {

			seleniumObj.waitForElement(PartnerAdminDelegateCheckbox, 4, 4);
			seleniumObj.scrollToElement(PartnerAdminDelegateCheckbox);
			PartnerAdminDelegateCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

	    }
		
		@FindBy(xpath="//slot[text()='Marketing Specialist']//following::span[@part='indicator']")
		public WebElement MarketingSpecialistCheckbox;
		
		public void checkMSCheckbox() {

			seleniumObj.waitForElement(MarketingSpecialistCheckbox, 4, 4);
			seleniumObj.scrollToElement(MarketingSpecialistCheckbox);
			MarketingSpecialistCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

	    }
		public void clickOnYesOnGrantAccessPage() {
				List<WebElement> ele = seleniumObj.getDriver().findElements(By.xpath("//commandbutton[@class='slds-button slds-button_neutral' and contains(text(),'Yes')]"));
				seleniumObj.waitForElement(ele.get(ele.size()-1), 4, 4);
				seleniumObj.scrollToElement(ele.get(ele.size()-1));
				executor.executeScript("arguments[0].click();", ele.get(ele.size()-1));
				sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		@FindBy(xpath="//*[text()='Ok']")
		public WebElement OkbuttonEmployee;
		
		public void clickOnOkOnGrantAccessPage() {

			seleniumObj.waitForElement(OkbuttonEmployee, 4, 4);
			seleniumObj.scrollToElement(OkbuttonEmployee);
			OkbuttonEmployee.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

	    }
		@FindBy(xpath="//button[text()='Yes']")
		public WebElement YesbuttonMP;
		
		public void clickOnYesOnManagePersonnelPage() {

			seleniumObj.waitForElement(YesbuttonMP, 4, 4);
			seleniumObj.scrollToElement(YesbuttonMP);
			YesbuttonMP.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

	    }
		public boolean verifyEmployeeNotPresent() {
			try {

				return seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Permission Set Label']//following::a[text()='PMP External - Employee'])[1]")).isDisplayed();

			} catch (Exception e) {
				return false;
			}
		}
		
		//@FindBy(xpath = "//button[text()='Log in to Experience as User']")
		@FindBy(xpath ="(//span[text()='Log in to Experience as User'])[1]")
		public WebElement LogintoExperienceasUserButton;

		public void clickOnLogintoExperienceasUserButton() {
			seleniumObj.waitForElement(LogintoExperienceasUserButton, 4, 4);
			seleniumObj.scrollToElement(LogintoExperienceasUserButton);
			LogintoExperienceasUserButton.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
		@FindBy(xpath = "(//*[text()='Partnercenter Unified Community'])[1]")
		public WebElement PartnercenterUnifiedCommunity;

		public void clickOnPartnercenterUnifiedCommunity() {
			seleniumObj.waitForElement(PartnercenterUnifiedCommunity, 4, 4);
			seleniumObj.scrollToElement(PartnercenterUnifiedCommunity);
			PartnercenterUnifiedCommunity.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		@FindBy(xpath = "(//*[text()='Intel Partner Alliance'])[1]")
		public WebElement IntelPartnerAllianceCommunity;

		public void clickOnIntelPartnerAllianceCommunity() {
			seleniumObj.waitForElement(IntelPartnerAllianceCommunity, 4, 4);
			seleniumObj.scrollToElement(IntelPartnerAllianceCommunity);
			IntelPartnerAllianceCommunity.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		@FindBy(xpath = "(//div[text()='Manage Personnel'])")
		public WebElement ManagePersonnel;
		
		public void clickOnManagePersonnel(){
			seleniumObj.waitForElement(ManagePersonnel, 4, 4);
			seleniumObj.scrollToElement(ManagePersonnel);
			ManagePersonnel.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
			}
		
		
		@FindBy(xpath="(//*[text()='Name'])[1]")
		public WebElement column_Name;
		
		@FindBy(xpath="(//*[text()='Email Address'])[1]")
		public WebElement column_EmailAddress;
		
		@FindBy(xpath="(//*[text()='Last Sign-in'])[1]")
		public WebElement column_LastSignIn;
		
		@FindBy(xpath="(//*[text()='IPA Membership'])[1]")
		public WebElement column_IPAMembership;
		
		@FindBy(xpath="(//*[text()='Country'])[1]")
		public WebElement column_Country;
		
		@FindBy(xpath="(//*[text()='Responsibilities Assigned'])[1]")
		public WebElement column_ResponsibilitiesAssigned;
		
		public void arecolumnsavailable(){
			seleniumObj.waitForElement(column_Name, 4, 4);
			seleniumObj.scrollToElement(column_Name);
			seleniumObj.waitForElement(column_EmailAddress, 4, 4);
			seleniumObj.scrollToElement(column_EmailAddress);
			//seleniumObj.waitForElement(column_LastSignIn, 4, 4);
			//seleniumObj.scrollToElement(column_LastSignIn);
			seleniumObj.waitForElement(column_IPAMembership, 4, 4);
			seleniumObj.scrollToElement(column_IPAMembership);
			seleniumObj.waitForElement(column_Country, 4, 4);
			seleniumObj.scrollToElement(column_Country);
			seleniumObj.waitForElement(column_ResponsibilitiesAssigned, 4, 4);
			seleniumObj.scrollToElement(column_ResponsibilitiesAssigned);
			System.out.println("All columns are visible.");
			
		}
		
		@FindBy(xpath="//*[@class='wrapDataClass slds-p-around_small slds-size_1-of-12' and @title='Inactive']")
		public WebElement InactiveIPAMembership;
		
		public void checkInactiveIPAMembership(){
			if(InactiveIPAMembership.isDisplayed())
			{
				seleniumObj.waitForElement(InactiveIPAMembership,4,4);
			    seleniumObj.scrollToElement(InactiveIPAMembership);
			    sfcommonObj.waitTillLightningPageLoadComplete();
			}
			else{
				System.out.print("No Inactive membership is present");
			}
		}
		@FindBy(xpath = "//button[text()='My Program']")
		public WebElement MyProgram;

		@FindBy(xpath=" //button[text()='Filter']")
		public WebElement managepersonnelFilter;
		
		@FindBy(xpath="//button[text()='Name']")
		public WebElement filterName;
		
		@FindBy(xpath="//button[text()='Email Address']")
		public WebElement filterEmail;
		
		public void clickOnfilterEmail(){
			seleniumObj.waitForElement(filterEmail,4,4);
			seleniumObj.scrollToElement(filterEmail);
			filterEmail.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		@FindBy(xpath="//input[@placeholder='Enter Email']")
		public WebElement filterEmailaddress;
		
		public void clickOnfilterEmailaddress(){
			seleniumObj.waitForElement(filterEmailaddress,4,4);
			seleniumObj.scrollToElement(filterEmailaddress);
			filterEmailaddress.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		@FindBy(xpath="//button[@class='slds-button slds-button_brand slds-m-right_xx-small prevNextButtonClass']//preceding::button[@class='slds-button slds-button_icon-container']")
		public WebElement rowsperpage;
		
		public void clickonrowsperpage(){
			seleniumObj.waitForElement(rowsperpage,4,4);
			seleniumObj.scrollToElement(rowsperpage);
			rowsperpage.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//span[text()='100']")
		public WebElement clickon100;
		
		public void clickon100rows(){
			seleniumObj.waitForElement(clickon100,4,4);
			seleniumObj.scrollToElement(clickon100);
			clickon100.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		@FindBy(xpath="//button[text()='IPA Membership']")
		public WebElement filterIPAMembership;
		
		@FindBy(xpath="//button[text()='Country']")
		public WebElement filterCountry;
		
		@FindBy(xpath="//button[text()='Responsibility Name']")
		public WebElement filterResponsibilityName;
		
		@FindBy(xpath="(//button[text()='Responsibility Name'])//following::button[2]")
		public WebElement filterApply;
		
		public void clickOnfilterApply(){
			seleniumObj.waitForElement(filterApply,4,4);
			seleniumObj.scrollToElement(filterApply);
			filterApply.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//button[@id='firstName-4']")
		public WebElement heading;
		
		public void clickelsewhere(){
			seleniumObj.waitForElement(heading,4,4);
			seleniumObj.scrollToElement(heading);
			heading.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		@FindBy(xpath="//button[text()='Delete']")
		public WebElement Deletemanagepersonnel;
		
		public void clickOnDeletemanagepersonnel(){
			seleniumObj.waitForElement(Deletemanagepersonnel,4,4);
			seleniumObj.scrollToElement(Deletemanagepersonnel);
			Deletemanagepersonnel.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//button[text()='Reinvite User']")
		public WebElement Reinvitemanagepersonnel;
		
		public void clickOnReinvitemanagepersonnel(){
			seleniumObj.waitForElement(Reinvitemanagepersonnel,4,4);
			seleniumObj.scrollToElement(Reinvitemanagepersonnel);
			Reinvitemanagepersonnel.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//button[text()='Invite a User']")
		public WebElement InviteAUserManagePersonnel;
		

		public void clickOnInviteAUserManagePersonnel(){
			seleniumObj.waitForElement(InviteAUserManagePersonnel,4,4);
			seleniumObj.scrollToElement(InviteAUserManagePersonnel);
			InviteAUserManagePersonnel.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		public String getfilteredvalue(String emailID) {
			return seleniumObj.getDriver()
					.findElements(By.xpath("//lightning-layout-item[@title='"+emailID.toLowerCase()+"']")).toString();
		}
		
		public boolean checkIfReinviteButtonDisplayed() throws TimeOutException {
			 return sfcommonObj.checkElementExists(Reinvitemanagepersonnel);
		}
		public boolean checkIfDeleteButtonDisplayed() throws TimeOutException {
			 return sfcommonObj.checkElementExists(Deletemanagepersonnel);
		}
		public boolean checkElementExists(WebElement element)
		{
			boolean visible =false;
			try{
			if(element!=null && element.isDisplayed()&& element.isEnabled())
			{
				visible =true;
			}
			}
			catch(Exception ex){
				visible=false;
				 log.info("Element is not visible on the screen!");
				 log.info(ex.getMessage());
			}
			return visible;
		}
		@FindBy(xpath="//input[@name='optradio']")
		public WebElement contacttoinvite;
		
		public void clickOnContacttoInvite(){
			seleniumObj.waitForElement(contacttoinvite,4,4);
			seleniumObj.scrollToElement(contacttoinvite);
			contacttoinvite.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//button[text()='Submit']")
		public WebElement SubmitInviteManagePersonnel;
		
		public void clickOnSubmitInviteManagePersonnel(){
			seleniumObj.waitForElement(SubmitInviteManagePersonnel,4,4);
			seleniumObj.scrollToElement(SubmitInviteManagePersonnel);
			SubmitInviteManagePersonnel.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		@FindBy(xpath="//span[text()='Invite the contact to register for the Intel� Partner Alliance Program']")
		public WebElement newcontactMessageManagePersonnel;
		
		//String str=newcontactMessageManagePersonnel.toString();
		public String validateNewContactMessageonManagePersonnel() throws TimeOutException {
			seleniumObj.waitForElement(newcontactMessageManagePersonnel,4,4);
			String str=newcontactMessageManagePersonnel.getText();
			return str;
		}
		
		
		@FindBy(xpath="//span[text()='The Contact has an Active Intel� Partner Alliance Membership. Need help?']")
		public WebElement acractivecontactMessageManagePersonnel;
		
		public String validateACRactiveMessageonManagePersonnel() throws TimeOutException {
			seleniumObj.waitForElement(acractivecontactMessageManagePersonnel,4,4); 
			String str=acractivecontactMessageManagePersonnel.getText();
			return str;
		}
		
		@FindBy(xpath="//span[text()='An invitation has been extended and registration is in progress. Need help?']")
		public WebElement invitedcontactMessageManagePersonnel;
		
		public String validateinvitedContactMessageonManagePersonnel() throws TimeOutException {
			seleniumObj.waitForElement(invitedcontactMessageManagePersonnel,4,4); 
			String str=invitedcontactMessageManagePersonnel.getText();
			return str;
		}
		
		@FindBy(xpath="//span[text()='The contact is not a part of your account. Need help?']")
		public WebElement diffOPIDcontactMessageManagePersonnel;
		
		public String validatediffOPIDContactMessageonManagePersonnel() throws TimeOutException {
			seleniumObj.waitForElement(diffOPIDcontactMessageManagePersonnel,4,4); 
			String str=diffOPIDcontactMessageManagePersonnel.getText();
			return str; 
		}
		
		@FindBy(xpath="//*[text()='Invitation cannot be extended to an Intel domain Email ID']")
		public WebElement InternalUserMessageManagePersonnel;
		
		public String validateInternalUserMessageonManagePersonnel() throws TimeOutException {
			seleniumObj.waitForElement(InternalUserMessageManagePersonnel,4,4); 
			String str=InternalUserMessageManagePersonnel.getText();
			return str; 
		}
		public void clickOnmanagepersonnelFilter(){
			seleniumObj.waitForElement(managepersonnelFilter, 4, 4);
			seleniumObj.scrollToElement(managepersonnelFilter);
			managepersonnelFilter.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		public void clickOnMyProgramDropDown() {
			seleniumObj.waitForElement(MyProgram, 4, 4);
			seleniumObj.scrollToElement(MyProgram);
			MyProgram.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		@FindBy(xpath = "//*[text()='Grant Access']")
		public WebElement GrantAccessFromMyProgram;

		public void clickOnGrantAccessFromMyProgramDropDown() {
			seleniumObj.waitForElement(GrantAccessFromMyProgram, 4, 4);
			seleniumObj.scrollToElement(GrantAccessFromMyProgram);
			GrantAccessFromMyProgram.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
		@FindBy(xpath = "//h1[text()='Contacts']")
		public WebElement CCPPage;

		public boolean verifyCCPPagePresent() {

			seleniumObj.waitForElement(CCPPage, 4, 4);
			seleniumObj.scrollToElement(CCPPage);
			return CCPPage.isDisplayed();

		}
		
		@FindBy(xpath = "//button[text()='Search Contact']")
		public WebElement SearchContactButton;

		public boolean verifySearchContactButtonPresent() {

			seleniumObj.waitForElement(SearchContactButton, 4, 4);
			seleniumObj.scrollToElement(SearchContactButton);
			return SearchContactButton.isDisplayed();

		}
		
		public void clickOnSearchContactButton() {
			seleniumObj.waitForElement(SearchContactButton, 4, 4);
			seleniumObj.scrollToElement(SearchContactButton);
			SearchContactButton.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		

		public boolean verifyContactIsVisibleOnclickingSearchContactButton(String email) {

			WebElement ele = seleniumObj.getDriver().findElement(
					By.xpath("//*[text()='"+email+"']"));
			seleniumObj.waitForElement(ele, 4, 4);
			seleniumObj.scrollToElement(ele);
			return ele.isDisplayed();

		}
		
		@FindBy(xpath = "(//button[text()='Load Entitlements'])[1]")
		public WebElement LoadEntitlements;

		public void clickOnLoadEntitlementsButton() {
			seleniumObj.waitForElement(LoadEntitlements, 4, 4);
			seleniumObj.scrollToElement(LoadEntitlements);
			LoadEntitlements.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
		@FindBy(xpath = "//*[contains(text(),'No Result Found')]")
		public WebElement NoResultFound;

		public boolean verifyNoResultFoundMessage() {

			seleniumObj.waitForElement(NoResultFound, 4, 4);
			seleniumObj.scrollToElement(NoResultFound);
			return NoResultFound.isDisplayed();

		}
		
		/**
		 * 
		 * @Description Method to enterFirstNameContact
		 * @Author manish9x
		 * @Since 20-Dec-2022
		 */
		public void enterFirstNameContactThroughPartnerCenter(String searchText) {
			try {
				seleniumObj.waitForElement(firstNameContactThroughPartnerCenter, 4, 4);
				seleniumObj.scrollToElement(firstNameContactThroughPartnerCenter);
				firstNameContactThroughPartnerCenter.click();
				firstNameContactThroughPartnerCenter.clear();
				firstNameContactThroughPartnerCenter.sendKeys(searchText);
				sfcommonObj.waitTillLightningPageLoadComplete();
				log.info("Entered text in first name textbox : " + searchText);
			} catch (Exception e) {
				log.error("Not able to enter text in first name text box");
				Assert.fail("Not able to enter text in first name text box");
			}

		}
		

		/**
		 * 
		 * @Description Method to enterLastNameContact
		 * @Author manish9x
		 * @Since 20-Dec-2022
		 */
		public void enterLastNameContactThroughPartnerCenter(String searchText) {
			try {
				seleniumObj.waitForElement(lastNameContactThroughPartnerCenter, 4, 4);
				seleniumObj.scrollToElement(lastNameContactThroughPartnerCenter);
				lastNameContactThroughPartnerCenter.click();
				lastNameContactThroughPartnerCenter.clear();
				lastNameContactThroughPartnerCenter.sendKeys(searchText);
				sfcommonObj.waitTillLightningPageLoadComplete();
				log.info("Entered text in last name textbox : " + searchText);
			} catch (Exception e) {
				log.error("Not able to enter text in last name text box");
				Assert.fail("Not able to enter text in last name text box");
			}

		}
		
		public void selectAccountType(String account) {
			
			WebElement ele = seleniumObj.getDriver().findElement(
					By.xpath("//input[@name='AccountType' and @value='"+account+"']"));
			seleniumObj.waitForElement(ele, 5, 5);
			seleniumObj.scrollToElement(ele);
			//ele.click();
			executor.executeScript("arguments[0].click();", ele);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		public void selectAccountNamefromDropDown(String account) {
			WebElement ele = seleniumObj.getDriver().findElement(
					By.xpath("//select[@name='myAccSelect']"));
			ele.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
			
			WebElement ele2 = seleniumObj.getDriver().findElement(
					By.xpath("//option[text()='"+account+"']"));
			seleniumObj.waitForElement(ele2, 5, 5);
			ele2.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		

		public void selectAnyOfTheEntitlements(String entitlement) {

			WebElement entitlementCheckbox = seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+entitlement+"']/following::span[@class='slds-checkbox_faux'])[1]"));
			seleniumObj.waitForElement(entitlementCheckbox, 4, 4);
			seleniumObj.scrollToElement(entitlementCheckbox);
			entitlementCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		  @FindBy(xpath = "//*[contains(text(),'You cannot create contact under this account')]")
			public WebElement ErrorOnContact;

			public boolean verifyErrorOnContactThroughPartnerCenter() {

				seleniumObj.waitForElement(ErrorOnContact, 4, 4);
				seleniumObj.scrollToElement(ErrorOnContact);
				return ErrorOnContact.isDisplayed();

			}
			
			@FindBy(xpath = "//*[text()='CCF Boost Activity Submitter']") ////*[text()='CCF Activity Submitter']
			public WebElement CCFActivitySubmitter;

			public boolean verifyCCFActivitySubmitterPresentOrNot() {

				seleniumObj.waitForElement(CCFActivitySubmitter, 4, 4);
				seleniumObj.scrollToElement(CCFActivitySubmitter);
				return CCFActivitySubmitter.isDisplayed();

			}
			
			@FindBy(xpath = "(//*[text()='CCF Boost Activity Submitter']/following::span[@class='slds-checkbox--faux'])[1]") //(//*[text()='CCF Activity Submitter']/following::span[@class='slds-checkbox--faux'])[1]
			public WebElement CCFActivitySubmitterCheckbox;

			public void checkCCFActivitySubmitterCheckbox() {

				seleniumObj.waitForElement(CCFActivitySubmitterCheckbox, 4, 4);
				seleniumObj.scrollToElement(CCFActivitySubmitterCheckbox);
				CCFActivitySubmitterCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			public boolean verifyGrantAccessNotPresentUnderMyProgram() {
				try {

					return seleniumObj.getDriver().findElement(By.xpath("//*[text()='Grant Access']")).isDisplayed();

				} catch (Exception e) {
					return false;
				}
			}
			public String verifyAccountsPresentInDropDown() {
				WebElement ele = seleniumObj.getDriver().findElement(
						By.xpath("//select[@name='myAccSelect']"));
				ele.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				
				List<WebElement> ele2 = seleniumObj.getDriver().findElements(
						By.xpath("//select[@name='myAccSelect']//option"));
				if(ele2.size()>2){
					
					Assert.fail("Accounts not matching with the accounts presents in PGM records");
				}
				else if(ele2.size()==2)
				{
					return ele2.get(ele2.size()-1).getText();
				}
				return ele2.get(ele2.size()-1).getText();
			}
			@FindBy(xpath = "//span[text()='Partner Admin']//preceding::a[1]")
			public WebElement clickOnNameID;

			public void clickOnNameID() {

				seleniumObj.waitForElement(clickOnNameID, 4, 4);
				seleniumObj.scrollToElement(clickOnNameID);
				sfcommonObj.pageRefresh();
			    seleniumObj.clickByJS(clickOnNameID);
				//clickOnNameID.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath="(//*[text()='Partner Admin']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PartnerAdminCheckbox;
			public void checkPartnerAdminCheckbox() {

				seleniumObj.waitForElement(PartnerAdminCheckbox, 4, 4);
				seleniumObj.scrollToElement(PartnerAdminCheckbox);
				PartnerAdminCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();

		    }
			@FindBy(xpath="//span[text()='Status of Assignment/Removal']//following::lightning-formatted-text[1]")
			public WebElement statusAssignmentOrRemoval;
			public String verifyStatusAssignmentOrRemoval() {
                seleniumObj.waitForElement(statusAssignmentOrRemoval, 3, 3);
                seleniumObj.scrollToElement(statusAssignmentOrRemoval);
				return statusAssignmentOrRemoval.getText();

		    }
			@FindBy(xpath="//span[text()='Update Reason']//following::lightning-formatted-text[1]")
			public WebElement updateReason;
			public String verifyUpdateReason() {
                seleniumObj.waitForElement(updateReason, 3, 3);
                seleniumObj.scrollToElement(updateReason);
				return updateReason.getText();

		    }
			public void goBackToContactPage(){
				WebElement ContactCreatedElement = seleniumObj.getDriver().findElement(By.xpath("(//span[text()='Contact']/following::a)[1]"));
				seleniumObj.waitForElement(ContactCreatedElement, 4, 4);
				seleniumObj.scrollToElement(ContactCreatedElement);
				executor.executeScript("arguments[0].click();", ContactCreatedElement);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath="//input[@placeholder='Quick Find']")
			public WebElement QuickFindElement;
			public void clickQuickFind(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				seleniumObj.waitForElement(QuickFindElement, 4, 4);
//				seleniumObj.clickByJS(QuickFindElement);
				
				//QuickFindElement.click();
				QuickFindElement.sendKeys("Public Groups");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupElement = seleniumObj.getDriver().findElement(By.xpath("//mark[text()='Public Groups']"));
				seleniumObj.waitForElement(PublicGroupElement, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupElement);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			
			@FindBy(xpath="//select[@id='fcf']")
			public WebElement selectView;
			@FindBy(xpath="//a[contains(text(),'Go to list')]")
			public WebElement selectList;
			@FindBy(xpath="//a[text()='AutomationMPdraco23 Useripa01']")
			public WebElement userName;
			public void verifyUser(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectView, 4, 4);
				selectView.sendKeys("PMP_New");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupName = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='PMP_0012i00000bms2fAAA'])[1]"));
				seleniumObj.waitForElement(PublicGroupName, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupName);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectList, 4, 4);
				seleniumObj.clickByJS(selectList);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(userName, 4, 4);
				seleniumObj.scrollToElement(userName);
				Assert.assertEquals("Successfully Found","AutomationMPdraco21 Useripa01", userName.getText());
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			public void verifyUserAsLeadsOfEmployees(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectView, 4, 4);
				selectView.sendKeys("PMP_New");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupName = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='PMP_0012i00000bms2fAAA_LeadsOfEmployees'])[1]"));
				seleniumObj.waitForElement(PublicGroupName, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupName);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectList, 4, 4);
				seleniumObj.clickByJS(selectList);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(userName, 4, 4);
				seleniumObj.scrollToElement(userName);
				Assert.assertEquals("Successfully Found","AutomationMPdraco23 Useripa01", userName.getText());
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath="(//*[text()='Partner Lead Development Rep']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PartnerLeadDevCheckbox;
			public void checkPartnerLeadDevCheckbox() {

				seleniumObj.waitForElement(PartnerLeadDevCheckbox, 4, 4);
				seleniumObj.scrollToElement(PartnerLeadDevCheckbox);
				PartnerLeadDevCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();

		    }
			@FindBy(xpath="//button[@title='Edit Contact Segment']")
			public WebElement EditContactBtn;
			@FindBy(xpath="//span[text()='Internet of Things and Embedded']")
			public WebElement selectIOT;
			@FindBy(xpath="(//button[@title='Move selection to Chosen'])[2]")
			public WebElement selectMovetoSelect;
			public void checkEditContactBtn() {

				seleniumObj.waitForElement(EditContactBtn, 4, 4);
				seleniumObj.scrollToElement(EditContactBtn);
				EditContactBtn.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				selectIOT.click();
				seleniumObj.waitForSeconds(10);
				selectMovetoSelect.click();
				seleniumObj.waitForSeconds(10);

		    }
			public void verifyUserAsInternetOfThings(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectView, 4, 4);
				selectView.sendKeys("PMP_New");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupName = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='PMP_0012i00000bms2fAAA_Internet_of_Thing'])[1]"));
				seleniumObj.waitForElement(PublicGroupName, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupName);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(userName, 4, 4);
				seleniumObj.scrollToElement(userName);
				Assert.assertEquals("Successfully Found","AutomationMPdraco23 Useripa01", userName.getText());
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			public void verifyUserAsLeadsOfEmployeesforInterNetOfThing(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectView, 4, 4);
				selectView.sendKeys("PMP_New");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupName = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='PMP_0012i00000bms2fAAA_LeadsOfEmployees'])[1]"));
				seleniumObj.waitForElement(PublicGroupName, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupName);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectList, 4, 4);
				seleniumObj.clickByJS(selectList);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(userName, 4, 4);
				if(!userName.isDisplayed()){
					System.out.println("Successfully Done");
				}
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath="//button[text()='Filter']")
			public WebElement FilterBtn;
			@FindBy(xpath="//button[text()='Email Address']")
			public WebElement EmailAddressBtn;
			@FindBy(xpath="//input[@placeholder='Enter Email']")
			public WebElement EnterEmailBtn;
			@FindBy(xpath="//button[text()='Responsibility Name']//following::button[2]")
			public WebElement ApplyBtn;
			public void verifyInvitedUser(String User){
				seleniumObj.waitForElement(FilterBtn, 4, 4);
				seleniumObj.clickByJS(FilterBtn);
				seleniumObj.waitForSeconds(10);
				seleniumObj.waitForElement(EmailAddressBtn, 4, 4);
				seleniumObj.clickByJS(EmailAddressBtn);
				seleniumObj.waitForSeconds(10);
				seleniumObj.waitForElement(EnterEmailBtn, 4, 4);
				seleniumObj.clickByJS(EnterEmailBtn);
				seleniumObj.waitForSeconds(10);
				EnterEmailBtn.sendKeys(User);
				seleniumObj.waitForSeconds(10);
				seleniumObj.waitForElement(ApplyBtn, 4, 4);
				seleniumObj.clickByJS(ApplyBtn);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath="//lightning-layout-item[@title='testevmail12@mailinator.com']")
			public WebElement EmailField;
			public void checkInvitedStatus(String User){
				WebElement EmailElement = seleniumObj.getDriver().findElement(By.xpath("//lightning-layout-item[@title='"+User+"']"));
				if(EmailElement.isDisplayed()){
					seleniumObj.waitForSeconds(10);
					WebElement InvitedElement = seleniumObj.getDriver().findElement(By.xpath("//lightning-layout-item[@title='Invited']"));
					if(InvitedElement.isDisplayed()){
						log.info("Successfully Invite the user");
					}
					else{
						Assert.fail("Unable to Send Invite");
					}
				}
				else{
					Assert.fail("Unable to Find the User");
				}
				
			}
			@FindBy(xpath="//span[text()='Pending Action'][1]")
			public WebElement StatusBtn;
			public void verifyEmployeeStatus(){
				seleniumObj.waitForElement(StatusBtn, 4, 4);
				if(StatusBtn.isDisplayed()){
					log.info("Successfuly verify Status");
				}
				else{
					Assert.fail("Unable to Verify Status");
				}
			}
			public void verifyUserAsLeads(){
				//WebElement QuickFindElement = seleniumObj.getDriver().findElement(By.xpath("//input[@placeholder='Quick Find']"));
				
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectView, 4, 4);
				selectView.sendKeys("PMP_New");
				seleniumObj.waitForSeconds(10);
				
				WebElement PublicGroupName = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='PMP_0012i00000bms2fAAA_Leads'])[1]"));
				seleniumObj.waitForElement(PublicGroupName, 4, 4);
				executor.executeScript("arguments[0].click();", PublicGroupName);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(selectList, 4, 4);
				seleniumObj.clickByJS(selectList);
				seleniumObj.getDriver().switchTo().frame(0);
				seleniumObj.waitForElement(userName, 4, 4);
				seleniumObj.scrollToElement(userName);
				Assert.assertEquals("Successfully Found","AutomationMPdraco4 Useripa03", userName.getText());
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "(//*[text()='IPA Activity Manager (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAActivityManagerCheckBox;

			public void checkIPAActivityManagerCheckBox() {

					seleniumObj.waitForElement(IPAActivityManagerCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAActivityManagerCheckBox);
					IPAActivityManagerCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//div[@class='slds-modal__content slds-p-around_medium benifitsClass']/li)[1]")
			public WebElement PopupMsg;

			public void checkPopupMsg() {

					seleniumObj.waitForElement(PopupMsg, 4, 4);
					seleniumObj.scrollToElement(PopupMsg);
					//PopupMsg.click();// Benifits are required
					System.out.println("Benifits are required for Mentioned Entitlement: "+PopupMsg.getText());
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			 
			@FindBy(xpath = "//button[@class='slds-button slds-button_brand']") ////button[@class='slds-button slds-button_brand']
			public WebElement OkBtn;

			public void ClickOkBtn() {

					seleniumObj.waitForElement(OkBtn, 4, 4);
					seleniumObj.scrollToElement(OkBtn);
					OkBtn.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			
			@FindBy(xpath = "//tbody/tr/td[2]/div")
			public WebElement Alertmsg;

			public void checkAlertmsg() {

					seleniumObj.waitForElement(Alertmsg, 4, 4);
					seleniumObj.scrollToElement(Alertmsg);
					System.out.println(Alertmsg.getText());
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA Claim Submitter (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAClaimSubmitterCheckBox;

			public void checkIPAClaimSubmitterCheckBox() {

					seleniumObj.waitForElement(IPAClaimSubmitterCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAClaimSubmitterCheckBox);
					IPAClaimSubmitterCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA Claim Submitter (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAClaimSubmitterProposalCheckBox;

			public void checkIPAClaimSubmitterProposalCheckBox() {

					seleniumObj.waitForElement(IPAClaimSubmitterProposalCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAClaimSubmitterProposalCheckBox);
					IPAClaimSubmitterProposalCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA External View Only (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPACExternalViewCheckBox;

			public void checkIPACExternalViewCheckBox() {

					seleniumObj.waitForElement(IPACExternalViewCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPACExternalViewCheckBox);
					IPACExternalViewCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA External View Only (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPACExternalViewProposalCheckBox;

			public void checkIPACExternalViewProposalCheckBox() {

					seleniumObj.waitForElement(IPACExternalViewProposalCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPACExternalViewProposalCheckBox);
					IPACExternalViewProposalCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA Proposal Manager (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAProposalManagerCheckBox;

			public void checkIPAProposalManagerCheckBox() {

					seleniumObj.waitForElement(IPAProposalManagerCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAProposalManagerCheckBox);
					IPAProposalManagerCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA Recovery Administrator (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPARecoveryAdministratorCheckBox;

			public void checkIPARecoveryAdministratorCheckBox() {

					seleniumObj.waitForElement(IPARecoveryAdministratorCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPARecoveryAdministratorCheckBox);
					IPARecoveryAdministratorCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='IPA Recovery Administrator (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPARecoveryAdministratorproposalCheckBox;

			public void checkIPARecoveryAdministratorProposalCheckBox() {

					seleniumObj.waitForElement(IPARecoveryAdministratorproposalCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPARecoveryAdministratorproposalCheckBox);
					IPARecoveryAdministratorproposalCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='MDF Claim Submitter']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MDFClaimSubmitterCheckBox;

			public void checkMDFClaimSubmitterCheckBox() {

					seleniumObj.waitForElement(MDFClaimSubmitterCheckBox, 4, 4);
					seleniumObj.scrollToElement(MDFClaimSubmitterCheckBox);
					MDFClaimSubmitterCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='MDF Proposal Manager']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MDFProposalCheckBox;

			public void checkMDFProposalCheckBox() {

					seleniumObj.waitForElement(MDFProposalCheckBox, 4, 4);
					seleniumObj.scrollToElement(MDFProposalCheckBox);
					MDFProposalCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='MDF Viewer']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MDFViewerCheckBox;

			public void checkMDFViewerCheckBox() {

					seleniumObj.waitForElement(MDFViewerCheckBox, 4, 4);
					seleniumObj.scrollToElement(MDFViewerCheckBox);
					MDFViewerCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='Platform Verification Program (PVP) access']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PlatformVerificationCheckbox;

			public void checkPlatformVerificationCheckbox() {

					seleniumObj.waitForElement(PlatformVerificationCheckbox, 4, 4);
					seleniumObj.scrollToElement(PlatformVerificationCheckbox);
					seleniumObj.clickByJS(PlatformVerificationCheckbox);
					//PlatformVerificationCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='Points Manager']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PointManagerCheckbox;

			public void checkPointManagerCheckBox() {

					seleniumObj.waitForElement(PointManagerCheckbox, 4, 4);
					seleniumObj.scrollToElement(PointManagerCheckbox);
					PointManagerCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='Points View']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PointViewCheckbox;

			public void checkPointViewCheckBox() {

					seleniumObj.waitForElement(PointViewCheckbox, 4, 4);
					seleniumObj.scrollToElement(PointViewCheckbox);
					PointViewCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='Warranty Requestor']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement WarrantyRequestorCheckbox;

			public void checkWarrantyRequestor() {

					seleniumObj.waitForElement(WarrantyRequestorCheckbox, 4, 4);
					seleniumObj.scrollToElement(WarrantyRequestorCheckbox);
					WarrantyRequestorCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//*[text()='Technology Sandbox']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement TechnologySandboxCheckbox;

			public void checkTechnologySandboxCheckbox() {

					seleniumObj.waitForElement(TechnologySandboxCheckbox, 4, 4);
					seleniumObj.scrollToElement(TechnologySandboxCheckbox);
					TechnologySandboxCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "(//div/span[contains(text(),'Republish OBM')]//following::button)[2]")
			public WebElement republishobmEdit;
			@FindBy(xpath = "(//label/span[contains(text(),'Republish OBM')]//following::span/input)[1]")
			public WebElement republishobmCheckBox;
			@FindBy(xpath = "//button[@name='SaveEdit']")
			public WebElement savebtn;
			
			public void checkRepublishObm() {

					seleniumObj.waitForElement(republishobmEdit, 4, 4);
					seleniumObj.scrollToElement(republishobmEdit);
					republishobmEdit.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
					seleniumObj.waitForElement(republishobmCheckBox, 4, 4);
					seleniumObj.scrollToElement(republishobmCheckBox);
					republishobmCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
					seleniumObj.waitForElement(savebtn, 4, 4);
					seleniumObj.scrollToElement(savebtn);
					savebtn.click();
					sfcommonObj.waitTillLightningPageLoadComplete();

			}
			@FindBy(xpath = "//*[contains(text(),'Order Management')]/parent::div/div/div[@class='headerPlusMinusIcon']")
			public WebElement ordermanagement;

			public void expandOrderManagement() {
				seleniumObj.waitForElement(ordermanagement, 4, 4);
				seleniumObj.scrollToElement(ordermanagement);
				ordermanagement.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "(//*[text()='Intel On Demand Purchase Agent']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IntelOnDemandPurchaseCheckbox;

			public void checkIntelOnDemandPurchaseCheckbox() {

				seleniumObj.waitForElement(IntelOnDemandPurchaseCheckbox, 4, 4);
				seleniumObj.scrollToElement(IntelOnDemandPurchaseCheckbox);
				IntelOnDemandPurchaseCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "((//span[text()='Internal Entitlement Status']//following::span[@class='test-id__field-label'])[1]/following::div/span/slot/lightning-formatted-text)[1]")
			public WebElement internalEntitlementStatus;

			public void internalEntitlementStatus() {
                try{
                	
                		seleniumObj.waitForElement(internalEntitlementStatus, 4, 4);
        				seleniumObj.scrollToElement(internalEntitlementStatus);
        				System.out.println("Internal Enetitlement Status:"+internalEntitlementStatus.getAttribute("value"));
        				
                	
                }
                catch(Exception Ex){
                	Assert.fail("Internal Entitlement Status is not Displayed");
                }
				
			}
			@FindBy(xpath = "//*[contains(text(),'Technical Content & Tools (Intel Developer Zone & Others)')]/parent::div/div/div[@class='headerPlusMinusIcon']")
			public WebElement TechnicalContentAndTools;
			public void expandTechnicalContentAndTools() {
				// TODO Auto-generated method stub
						seleniumObj.waitForElement(TechnicalContentAndTools, 4, 4);
						seleniumObj.scrollToElement(TechnicalContentAndTools);
						TechnicalContentAndTools.click();
						sfcommonObj.waitTillLightningPageLoadComplete();
			}
		/*	@FindBy(xpath = "(//*[text()='Developer Zone Premier(CNDA required)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement DeveloperZonePremierCheckbox;
 
			public void checkDeveloperZonePremierCheckbox() {
					// TODO Auto-generated method stub
					seleniumObj.waitForElement(DeveloperZonePremierCheckbox, 4, 4);
					seleniumObj.scrollToElement(DeveloperZonePremierCheckbox);
					DeveloperZonePremierCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
				}*/
			/*@FindBy(xpath = "(//*[text()='IPA Activity Manager (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAActivityManagerCheckBox;
 
			public void checkIPAActivityManagerCheckBox() {
					seleniumObj.waitForElement(IPAActivityManagerCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAActivityManagerCheckBox);
					IPAActivityManagerCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}*/
			/*@FindBy(xpath = "(//div[@class='slds-modal__content slds-p-around_medium benifitsClass']/li)[1]")
			public WebElement PopupMsg;*/
			
			@FindBy(xpath = "(//div[@class='slds-modal__content slds-p-around_medium benifitsClass']/li)[2]")
			public WebElement PopupMsg1;
			
			@FindBy(xpath = "(//div[@class='slds-modal__content slds-p-around_medium benifitsClass']/li)[3]")
			public WebElement PopupMsg2;
 
			/*public void checkPopupMsg() 
			{
					seleniumObj.waitForElement(PopupMsg, 4, 4);
					seleniumObj.scrollToElement(PopupMsg);
					PopupMsg.click();
					System.out.println("Below mentioned benifits are required : ");
					System.out.println(PopupMsg.getText());
					sfcommonObj.waitTillLightningPageLoadComplete();
			}*/
			public void check3PopupMsg() 
			{
					seleniumObj.waitForElement(PopupMsg, 4, 4);
					seleniumObj.scrollToElement(PopupMsg);
					PopupMsg.click();
					System.out.println("Below mentioned benifits are required : ");
					System.out.println(PopupMsg.getText());
					System.out.println(PopupMsg1.getText());
					System.out.println(PopupMsg2.getText());
					sfcommonObj.waitTillLightningPageLoadComplete();
			}
			public void check2PopupMsg() 
			{
					seleniumObj.waitForElement(PopupMsg, 4, 4);
					seleniumObj.scrollToElement(PopupMsg);
					PopupMsg.click();
					System.out.println("Below mentioned benifits are required : ");
					System.out.println(PopupMsg.getText());
					System.out.println(PopupMsg1.getText());
					sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "(//*[text()='IPA Claim Submitter (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAClaimSubmitterAccountPlanCheckBox;
 
			public void checkIPAClaimSubmitterAccountPlanCheckBox() {
					seleniumObj.waitForElement(IPAClaimSubmitterAccountPlanCheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAClaimSubmitterAccountPlanCheckBox);
					IPAClaimSubmitterAccountPlanCheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA Claim Submitter (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAClaimSubmitterProposalMDFcheckBox;
 
			public void checkIPAClaimSubmitterProposalMDFcheckBox() {
					seleniumObj.waitForElement(IPAClaimSubmitterProposalMDFcheckBox, 4, 4);
					seleniumObj.scrollToElement(IPAClaimSubmitterProposalMDFcheckBox);
					IPAClaimSubmitterProposalMDFcheckBox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA External View Only (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAExternalViewAccountPlanCheckbox;
 
			public void checkIPAExternalViewAccountPlanCheckbox() {
					seleniumObj.waitForElement(IPAExternalViewAccountPlanCheckbox, 4, 4);
					seleniumObj.scrollToElement(IPAExternalViewAccountPlanCheckbox);
					IPAExternalViewAccountPlanCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA External View Only (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAExternalViewProposalMDFCheckbox;
 
			public void checkIPAExternalViewProposalMDFCheckbox() {
					seleniumObj.waitForElement(IPAExternalViewProposalMDFCheckbox, 4, 4);
					seleniumObj.scrollToElement(IPAExternalViewProposalMDFCheckbox);
					IPAExternalViewProposalMDFCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA Proposal Manager (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPAProposalManagerCheckbox;
 
			public void checkIPAProposalManagerCheckbox() {
					seleniumObj.waitForElement(IPAProposalManagerCheckbox, 4, 4);
					seleniumObj.scrollToElement(IPAProposalManagerCheckbox);
					IPAProposalManagerCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA Recovery Administrator (Account Plan MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPARecoveryAdministratorAccountPlanCheckbox;
 
			public void checkIPARecoveryAdministratorAccountPlanCheckbox() {
					seleniumObj.waitForElement(IPARecoveryAdministratorAccountPlanCheckbox, 4, 4);
					seleniumObj.scrollToElement(IPARecoveryAdministratorAccountPlanCheckbox);
					IPARecoveryAdministratorAccountPlanCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='IPA Recovery Administrator (Proposal MDF)']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement IPARecoveryAdministratorProposalMDFCheckbox;
 
			public void checkIPARecoveryAdministratorProposalMDFCheckbox() {
					seleniumObj.waitForElement(IPARecoveryAdministratorProposalMDFCheckbox, 4, 4);
					seleniumObj.scrollToElement(IPARecoveryAdministratorProposalMDFCheckbox);
					IPARecoveryAdministratorProposalMDFCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='MDF Claim Submitter']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MDFClaimSubmitterCheckbox;
 
			public void checkMDFClaimSubmitterCheckbox() {
					seleniumObj.waitForElement(MDFClaimSubmitterCheckbox, 4, 4);
					seleniumObj.scrollToElement(MDFClaimSubmitterCheckbox);
					MDFClaimSubmitterCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='MDF Proposal Manager']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MDFProposalManagerCheckbox;
 
			public void checkMDFProposalManagerCheckbox() {
					seleniumObj.waitForElement(MDFProposalManagerCheckbox, 4, 4);
					seleniumObj.scrollToElement(MDFProposalManagerCheckbox);
					MDFProposalManagerCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='Platform Verification Program (PVP) access']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PlatformVerificationProgramCheckbox;
 
			public void checkPlatformVerificationProgramCheckbox() {
					seleniumObj.waitForElement(PlatformVerificationProgramCheckbox, 4, 4);
					seleniumObj.scrollToElement(PlatformVerificationProgramCheckbox);
					PlatformVerificationProgramCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='Points Manager']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PointsManagerCheckbox;
 
			public void checkPointsManagerCheckbox() {
					seleniumObj.waitForElement(PointsManagerCheckbox, 4, 4);
					seleniumObj.scrollToElement(PointsManagerCheckbox);
					PointsManagerCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			@FindBy(xpath = "(//*[text()='Points View']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement PointsViewCheckbox;
 
			public void checkPointsViewCheckbox() {
					seleniumObj.waitForElement(PointsViewCheckbox, 4, 4);
					seleniumObj.scrollToElement(PointsViewCheckbox);
					PointsViewCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}
			/*@FindBy(xpath = "(//*[text()='Warranty Requestor']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement WarrantyRequestorCheckbox;
 
			public void checkWarrantyRequestorCheckbox() {
					seleniumObj.waitForElement(WarrantyRequestorCheckbox, 4, 4);
					seleniumObj.scrollToElement(WarrantyRequestorCheckbox);
					WarrantyRequestorCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
 
			}*/
			/*@FindBy(xpath = "//*[contains(text(),'Intel® Partner Investment Center')]/parent::div/div/div[@class='headerPlusMinusIcon']")
			public WebElement IntelPartnerInvestmentCenter;
	 
			public void expandIntelPartnerInvestmentCenter() {
				seleniumObj.waitForElement(IntelPartnerInvestmentCenter, 4, 4);
				seleniumObj.scrollToElement(IntelPartnerInvestmentCenter);
				IntelPartnerInvestmentCenter.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}*/
			@FindBy(xpath = "//div[@class='messageText']")
			public WebElement ErrormsgAfterSAVEOnGrantAccessPage;
	 
			public boolean verifyErrormsgAfterSAVEOnGrantAccessPage() {
				seleniumObj.waitForElement(ErrormsgAfterSAVEOnGrantAccessPage, 4, 4);
				seleniumObj.scrollToElement(ErrormsgAfterSAVEOnGrantAccessPage);
				System.out.println(ErrormsgAfterSAVEOnGrantAccessPage.getText());
				return ErrormsgAfterSAVEOnGrantAccessPage.isDisplayed();
			}
			 @FindBy(xpath = "(//span[text()='Entitlement Assignment Status']/following::lightning-formatted-text)[1]")
				public WebElement InternalEntitlementStatus;

			    public String getInternalEntitlementStatus() {
					seleniumObj.waitForElement(InternalEntitlementStatus, 4, 4);
					seleniumObj.scrollToElement(InternalEntitlementStatus);
					return InternalEntitlementStatus.getText();
				}
			    @FindBy(xpath = "//*[contains(text(),'Data Center Blocks Configurator')]/parent::div/div/div[@class='headerPlusMinusIcon']")
				public WebElement DataCenterBlocksConfigurator;
				
				public void expandDataCenterBlocksConfigurator() {
					// TODO Auto-generated method stub
						seleniumObj.waitForElement(DataCenterBlocksConfigurator, 4, 4);
						seleniumObj.scrollToElement(DataCenterBlocksConfigurator);
						DataCenterBlocksConfigurator.click();
						sfcommonObj.waitTillLightningPageLoadComplete();
				}

				@FindBy(xpath = "//*[text()='Distributor']")
				public WebElement Distributor;
				
				public boolean verifyDistributorPresentOrNot() {
					// TODO Auto-generated method stub

						seleniumObj.waitForElement(Distributor, 4, 4);
						seleniumObj.scrollToElement(Distributor);
						return Distributor.isDisplayed();
				
				}
				
				@FindBy(xpath = "(//*[text()='Distributor']/following::span[@class='slds-checkbox--faux'])[1]")
				public WebElement DistributorCheckbox;

				public void checkDistributorCheckbox() {
					// TODO Auto-generated method stub
						seleniumObj.waitForElement(DistributorCheckbox, 4, 4);
						seleniumObj.scrollToElement(DistributorCheckbox);
						DistributorCheckbox.click();
						sfcommonObj.waitTillLightningPageLoadComplete();
				}
				
				@FindBy(xpath = "//*[text()='Reseller']")
				public WebElement Reseller;
				
				public boolean verifyResellerPresentOrNot() {
					// TODO Auto-generated method stub
					seleniumObj.waitForElement(Reseller, 4, 4);
					seleniumObj.scrollToElement(Reseller);
					return Reseller.isDisplayed();
				}
				
				@FindBy(xpath = "(//*[text()='Reseller']/following::span[@class='slds-checkbox--faux'])[1]")
				public WebElement ResellerCheckbox;
				

				public void checkResellerCheckbox() {
					// TODO Auto-generated method stub
					seleniumObj.waitForElement(ResellerCheckbox, 4, 4);
					seleniumObj.scrollToElement(ResellerCheckbox);
					ResellerCheckbox.click();
					sfcommonObj.waitTillLightningPageLoadComplete();
				}
				 @FindBy(xpath = "//td/div")
					public WebElement ErrorAfterSAVEOnGrantAccessPage;
				 
				public boolean verifyErrorAfterSAVEOnGrantAccessPage() {
					// TODO Auto-generated method stub
					seleniumObj.waitForElement(ErrorAfterSAVEOnGrantAccessPage, 4, 4);
					seleniumObj.scrollToElement(ErrorAfterSAVEOnGrantAccessPage);
					System.out.println(ErrorAfterSAVEOnGrantAccessPage.getText());
					return ErrorAfterSAVEOnGrantAccessPage.isDisplayed();
				}
				
				@FindBy(xpath = "//*[text()='Developer Zone Premier(CNDA required)']")
				public WebElement DeveloperZonePremier;
				
				public boolean VerifyDeveloperZonePremierPresentOrNot() {
					// TODO Auto-generated method stub
					seleniumObj.waitForElement(DeveloperZonePremier, 4, 4);
					seleniumObj.scrollToElement(DeveloperZonePremier);
					return DeveloperZonePremier.isDisplayed();
				}
				
				@FindBy(xpath = "(//*[text()='Developer Zone Premier(CNDA required)']/following::span[@class='slds-checkbox--faux'])[1]")
				public WebElement DeveloperZonePremierCheckbox;

				public void checkDeveloperZonePremierCheckbox() {
						// TODO Auto-generated method stub
						seleniumObj.waitForElement(DeveloperZonePremierCheckbox, 4, 4);
						seleniumObj.scrollToElement(DeveloperZonePremierCheckbox);
						DeveloperZonePremierCheckbox.click();
						sfcommonObj.waitTillLightningPageLoadComplete();
					}
				/*@FindBy(xpath = "//*[contains(text(),'Technical Content & Tools (Intel Developer Zone & Others)')]/parent::div/div/div[@class='headerPlusMinusIcon']")
				public WebElement TechnicalContentAndTools;
				
				public void expandTechnicalContentAndTools() {
					// TODO Auto-generated method stub
							seleniumObj.waitForElement(TechnicalContentAndTools, 4, 4);
							seleniumObj.scrollToElement(TechnicalContentAndTools);
							TechnicalContentAndTools.click();
							sfcommonObj.waitTillLightningPageLoadComplete();
				}*/
				@FindBy(xpath = "//td/span/ul/li[2]")
				public WebElement ERRORmsgAfterSAVEOnGrantAccessPage;
			 
			public boolean verifyERRORmsgAfterSAVEOnGrantAccessPage() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(ERRORmsgAfterSAVEOnGrantAccessPage, 4, 4);
				seleniumObj.scrollToElement(ERRORmsgAfterSAVEOnGrantAccessPage);
				System.out.println(ERRORmsgAfterSAVEOnGrantAccessPage.getText());
				return ERRORmsgAfterSAVEOnGrantAccessPage.isDisplayed();
			}
			
			@FindBy(xpath = "//*[contains(text(),'Intel® Partner Investment Center')]/parent::div/div/div[@class='headerPlusMinusIcon']")
			public WebElement IntelPartnerInvestmentCenter;

			public void expandIntelPartnerInvestmentCenter() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(IntelPartnerInvestmentCenter, 4, 4);
				seleniumObj.scrollToElement(IntelPartnerInvestmentCenter);
				IntelPartnerInvestmentCenter.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
	}
			
			@FindBy(xpath = "//*[text()='Partner Admin']")
			public WebElement PartnerAdmin;
			
			public boolean verifyPartnerAdminPresentOrNot() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(PartnerAdmin, 4, 4);
				seleniumObj.scrollToElement(PartnerAdmin);
				PartnerAdmin.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				return PartnerAdmin.isDisplayed();
			}
			@FindBy(xpath = "//*[text()='Partner Admin Delegate']")
			public WebElement PartnerAdminDelegate;
			
			public boolean verifyPartnerAdminDelegatePresentOrNot() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(PartnerAdminDelegate, 4, 4); 
				seleniumObj.scrollToElement(PartnerAdminDelegate);
				PartnerAdminDelegate.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				return PartnerAdminDelegate.isDisplayed();
			}
			@FindBy(xpath = "//*[text()='Marketing Specialist']")
			public WebElement MarketingSpecialist;
			
			public boolean verifyMarketingSpecialistPresentOrNot() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(MarketingSpecialist, 4, 4); 
				seleniumObj.scrollToElement(MarketingSpecialist);
				MarketingSpecialist.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				return MarketingSpecialist.isDisplayed();
			}
			
			@FindBy(xpath = "(//*[text()='Marketing Specialist']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement MarketingSpecialistCkbox;
			
			public void checkMarketingSpecialistCheckbox() {
				// TODO Auto-generated method stub
						seleniumObj.waitForElement(MarketingSpecialistCkbox, 4, 4);
						seleniumObj.scrollToElement(MarketingSpecialistCkbox);
						MarketingSpecialistCkbox.click();
						sfcommonObj.waitTillLightningPageLoadComplete();
				
			}
			@FindBy(xpath = "//td/span/ul")
			public WebElement ErrorMessageAfterSAVEOnGrantAccessPage;
			
			public boolean VerifyErrorMessageAfterSAVEOnGrantAccessPage() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(ErrorMessageAfterSAVEOnGrantAccessPage, 4, 4);
				seleniumObj.scrollToElement(ErrorMessageAfterSAVEOnGrantAccessPage);
				System.out.println(ErrorMessageAfterSAVEOnGrantAccessPage.getText());
				return ErrorMessageAfterSAVEOnGrantAccessPage.isDisplayed();
			}
			@FindBy(xpath = "(//*[text()='Employee']/following::span[@class='slds-checkbox--faux'])[1]")
			public WebElement UnEmployeeCheckbox;
			
			public void UncheckEmployeeCheckBox() {
				// TODO Auto-generated method stud

			seleniumObj.waitForElement(UnEmployeeCheckbox, 4, 4);
			seleniumObj.scrollToElement(UnEmployeeCheckbox);
			UnEmployeeCheckbox.click();
			sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "//span[text()='Contact Entitlements']")
			public WebElement clickOnContactEntitlements;
			
			public void clickOnContactEntitlements() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(clickOnContactEntitlements, 4, 4);
				seleniumObj.scrollToElement(clickOnContactEntitlements);
				executor.executeScript("arguments[0].click();", clickOnContactEntitlements);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "//span[@class='countSortedByFilteredBy']")
			public WebElement DispalyCERecord;
			
			public void VerifyNoCERecordCreated() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(DispalyCERecord, 4, 4);
				System.out.println(DispalyCERecord.getText());
				System.out.println("CE Record Not Created");
			}
			
			@FindBy(xpath = "//button[text()='Edit']")
			public WebElement clickonEditOnContactPage;
			
			public void clickonEditOnContactPage() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(clickonEditOnContactPage, 4, 4);
				seleniumObj.scrollToElement(clickonEditOnContactPage);
				executor.executeScript("arguments[0].click();", clickonEditOnContactPage);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			@FindBy(xpath = "((//span[@class='slds-form-element__label'])[11]//following::input)[1]")
			public WebElement CheckRepublishOBMCheckbox;
			
			public void CheckRepublishOBMCheckbox() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(CheckRepublishOBMCheckbox, 4, 4);
				seleniumObj.scrollToElement(CheckRepublishOBMCheckbox);
				CheckRepublishOBMCheckbox.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
				
			}
			@FindBy(xpath = "//button[text()='Save']")
			public WebElement ClickOneditSave;
			
			public void ClickOneditSave() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(ClickOneditSave, 4, 4);
				seleniumObj.scrollToElement(ClickOneditSave);
				executor.executeScript("arguments[0].click();", ClickOneditSave);
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
			
			@FindBy(xpath = "(//span[text()='AGS Integration Status']/following::lightning-formatted-text)[1]")
			public WebElement AGSIntegrationStatusNotShowPending;
			
			public void AGSIntegrationStatusNotShowPendingStatus() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(AGSIntegrationStatusNotShowPending, 4, 4);
				seleniumObj.scrollToElement(AGSIntegrationStatusNotShowPending);
				System.out.println(AGSIntegrationStatusNotShowPending.getText());
				System.out.println("AGS Integration Status Not Show Pending");
				
			}
			@FindBy(xpath = "(//label[text()='Mailing Country/Region']//following::div/input)[1]")
			public WebElement mailingCountry;
			@FindBy(xpath = "//lightning-base-combobox-item[@data-value='ALB']")
			public WebElement SelectmailingCountry;
		    public void enterAndSelectMailingCountry(String Country) {
			seleniumObj.waitForElement(mailingCountry, 4, 4);
			seleniumObj.scrollToElement(mailingCountry);
			mailingCountry.click();
			seleniumObj.waitForElement(SelectmailingCountry, 4, 4);
			seleniumObj.scrollToElement(SelectmailingCountry);
			SelectmailingCountry.click();
			sfcommonObj.waitTillLightningPageLoadComplete();

		}
		    @FindBy(xpath = "//*[contains(text(),'Intel On Demand')]/parent::div/div/div[@class='headerPlusMinusIcon']")
			public WebElement IntelOnDemand;
			public void expandIntelOnDemand() {
				// TODO Auto-generated method stub
				seleniumObj.waitForElement(IntelOnDemand, 4, 4);
				seleniumObj.scrollToElement(IntelOnDemand);
				IntelOnDemand.click();
				sfcommonObj.waitTillLightningPageLoadComplete();
			}
		
}
