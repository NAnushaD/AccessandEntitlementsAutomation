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
	
	@FindBy(xpath = "//label[text()='Salutation']/following::button[@name='salutation']")
	public WebElement salutationContact;

	@FindBy(xpath = "(//label[text()='First Name']/following::input[@name='firstName'])[1]")
	public WebElement firstNameContact;

	@FindBy(xpath = "(//label[text()='Last Name']/following::input[@name='lastName'])[1]")
	public WebElement lastNameContact;

	@FindBy(xpath = "(//label[text()='Email']/following::input[@name='Email'])[1]")
	public WebElement email;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='CCP CCF USER'])[1]")
	public WebElement cCPCCFuser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='CCP External User Admin'])[1]")
   	public WebElement cCPExternalUser;
	
	@FindBy(xpath = "(//h3[text()='Permission Set Assignments']//following::th[text()='No records to display'])[1]")
	public WebElement nORecordToDisplay;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='PSG External - License User'])[1]")
   	public WebElement pSGExternalUser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='CPQ Managed License User'])[1]")
   	public WebElement CPQManagedLicenseUser;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='PSG External - Disty'])[1]")
   	public WebElement PSGExternalDisty;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='Tender Partner Drafter Access'])[1]")
   	public WebElement TenderPartnerDrafterAccess;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='UCD ACM Foundation Access'])[1]")
   	public WebElement FoundationAccess;
	
	@FindBy(xpath = "(//*[text()='Permission Set Label']//following::a[text()='PMP External - Employee'])[1]")
	public WebElement Employee;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Type here')]")
	public WebElement typeHereTextbox;
	
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
		WebElement element = seleniumObj.getDriver().findElement(By.xpath("//a[@class='switch-to-lightning']"));
		seleniumObj.waitForElement(element, 4, 4);
		seleniumObj.scrollToElement(element);
		executor.executeScript("arguments[0].click();", element);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public void clickButton(String button){
		WebElement buttonelement = seleniumObj.getDriver().findElement(By.xpath("(//*[text()='"+button+"'])[1]"));
		seleniumObj.waitForElement(buttonelement, 4, 4);
		seleniumObj.scrollToElement(buttonelement);
		executor.executeScript("arguments[0].click();", buttonelement);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public void selectContactTypeAsIntelContact(){
		WebElement contactType = seleniumObj.getDriver().findElement(By.xpath("//span[text()='Intel Contact']/parent::*/parent::*/div/span[@class='slds-radio--faux']"));
		seleniumObj.waitForElement(contactType, 4, 4);
		seleniumObj.scrollToElement(contactType);
		contactType.click();
	}
	
	public void clickOnSalutation() {
		seleniumObj.waitForElement(salutationContact, 4, 3);
		seleniumObj.scrollToElement(salutationContact);
		seleniumObj.clickByJS(salutationContact);
		sfcommonObj.waitTillLightningPageLoadComplete();
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
	
	/**
	 * 
	 * @Description Method to enter Last name
	 * @Author manish9x
	 * @param searchText
	 * @Since 29-Nov-2022
	 */
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
	public void enterEmailContact(String searchText) {
		try {
			seleniumObj.waitForElement(email, 4, 4);
			seleniumObj.scrollToElement(email);
			email.click();
			email.clear();
			email.sendKeys(searchText);
			sfcommonObj.waitTillLightningPageLoadComplete();
			log.info("Entered text in email textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in email text box");
			Assert.fail("Not able to enter text in email text box");
		}
	}
	
	@FindBy(xpath = "(//label[text()='Account Name']/following::input[@placeholder='Search Accounts...'])[1]")
	public WebElement accountName;

	public void enterAccountName(String account) {
		seleniumObj.waitForElement(accountName, 4, 4);
		seleniumObj.scrollToElement(accountName);
		accountName.clear();
		accountName.sendKeys(account);
		sfcommonObj.waitTillLightningPageLoadComplete();

	}

	@FindBy(xpath = "(//span[contains(@title,'Show All Results for ')])[1]")
	public WebElement showAllResults;

	public void selectAccountName(String account) {
		seleniumObj.waitForElement(showAllResults, 4, 4);
		seleniumObj.scrollToElement(showAllResults);
		showAllResults.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
		WebElement ele = seleniumObj.getDriver().findElement(
				By.xpath("(//div[text()='Accounts']/following::a[text()='Account Name']/following::a[@title='"
						+ account + "'])[1]"));
		seleniumObj.waitForElement(ele, 5, 5);
		ele.click();
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
	
	@FindBy(xpath = "(//button[text()='Grant Access'])[1]")
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
	
	@FindBy(xpath = "//*[contains(text(),'Consolidated Co-marketing Platform')]/parent::div/div/div[@class='headerPlusMinusIcon']")
	public WebElement consolidatedPlatform;

	public void expandConsolidatedPlatform() {
		seleniumObj.waitForElement(consolidatedPlatform, 4, 4);
		seleniumObj.scrollToElement(consolidatedPlatform);
		consolidatedPlatform.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	@FindBy(xpath = "//*[text()='CCF User Administrator']")
	public WebElement CCFUserAdministrator;

	public boolean verifyCCFUserAdministratorPresentOrNot() {

		seleniumObj.waitForElement(CCFUserAdministrator, 4, 4);
		seleniumObj.scrollToElement(CCFUserAdministrator);
		return CCFUserAdministrator.isDisplayed();

	}
	
	@FindBy(xpath = "(//*[text()='CCF User Administrator']/following::span[@class='slds-checkbox--faux'])[1]")
	public WebElement CCFUserAdministratorCheckbox;

	public void checkCCFUserAdministratorCheckbox() {

		seleniumObj.waitForElement(CCFUserAdministratorCheckbox, 4, 4);
		seleniumObj.scrollToElement(CCFUserAdministratorCheckbox);
		CCFUserAdministratorCheckbox.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}
	
	public WebElement getSelectedCheckboxOfContactEntitlementsAsFalse() {

		WebElement element = seleniumObj.getDriver().findElement(
				By.xpath("((//span[text()='Selected'])[2]/following::span/span/img)[2]"));
		return element;
	}
	
	@FindBy(xpath = "//input[@class='btn' and contains(@value,'Save')]")
	public WebElement saveOnGrantAccessPage;

	public void clickOnSaveOnGrantAccessPage() {
		seleniumObj.waitForElement(saveOnGrantAccessPage, 4, 4);
		seleniumObj.scrollToElement(saveOnGrantAccessPage);
		saveOnGrantAccessPage.click();
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

	public void clickOnViewPartnerUserButton() {
		seleniumObj.waitForElement(viewPartnerUserButton, 4, 4);
		seleniumObj.scrollToElement(viewPartnerUserButton);
		viewPartnerUserButton.click();
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
	
	public void ClickOnEntitlementRecordElement(String elememnt) {
		
    	WebElement ele = seleniumObj.getDriver().findElement(By.xpath("(//a[text()='"+elememnt+"']//ancestor::tr//following::a)[1]"));
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
		
		@FindBy(xpath = "(//span[text()='Partner Contact Relationship']/following::span[text()='View All'])[1]")
		public WebElement ViewAllOfPartnerContactRelationship;

		public void clickOnViewAllOfPartnerContactRelationship() {
			seleniumObj.waitForElement(ViewAllOfPartnerContactRelationship, 4, 4);
			seleniumObj.scrollToElement(ViewAllOfPartnerContactRelationship);
			executor.executeScript("arguments[0].click();", ViewAllOfPartnerContactRelationship);
			sfcommonObj.waitTillLightningPageLoadComplete();
		}
		
		 @FindBy(xpath = "//*[contains(text(),'Intel Partner Alliance')]/parent::div/div/div[@class='headerPlusMinusIcon']")
		 public WebElement IntelPartnerAlliance;

		 public void expandIntelPartnerAlliance() {
		 		seleniumObj.waitForElement(IntelPartnerAlliance, 4, 4);
		 		seleniumObj.scrollToElement(IntelPartnerAlliance);
		 		IntelPartnerAlliance.click();
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
			
		public void clickOnYesOnGrantAccessPage() {
				List<WebElement> ele = seleniumObj.getDriver().findElements(By.xpath("//commandbutton[@class='slds-button slds-button_neutral' and contains(text(),'Yes')]"));
				seleniumObj.waitForElement(ele.get(ele.size()-1), 4, 4);
				seleniumObj.scrollToElement(ele.get(ele.size()-1));
				executor.executeScript("arguments[0].click();", ele.get(ele.size()-1));
				sfcommonObj.waitTillLightningPageLoadComplete();
		}	
		
		public boolean verifyEmployeeNotPresent() {
			try {

				return seleniumObj.getDriver().findElement(By.xpath("(//*[text()='Permission Set Label']//following::a[text()='PMP External - Employee'])[1]")).isDisplayed();

			} catch (Exception e) {
				return false;
			}
		}
		
		@FindBy(xpath = "(//button[text()='Log in to Experience as User'])[1]")
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
		
		@FindBy(xpath = "//button[text()='My Program']")
		public WebElement MyProgram;

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
			
			@FindBy(xpath = "//*[text()='CCF Activity Submitter']")
			public WebElement CCFActivitySubmitter;

			public boolean verifyCCFActivitySubmitterPresentOrNot() {

				seleniumObj.waitForElement(CCFActivitySubmitter, 4, 4);
				seleniumObj.scrollToElement(CCFActivitySubmitter);
				return CCFActivitySubmitter.isDisplayed();

			}
			
			@FindBy(xpath = "(//*[text()='CCF Activity Submitter']/following::span[@class='slds-checkbox--faux'])[1]")
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
}
