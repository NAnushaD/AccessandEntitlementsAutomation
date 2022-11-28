package com.intel.ebsqa.draco.PageClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

import junit.framework.Assert;

public class SF_User_CreationPageClass extends TestBase {

	public SF_User_CreationPageClass() {
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

	@FindBy(xpath = "//input[@placeholder='Quick Find']")
	public WebElement quickFind;

	public void searchInQuickFindBox(String obj) {
		seleniumObj.waitForElement(quickFind, 4, 4);
		seleniumObj.scrollToElement(quickFind);
		quickFind.click();
		quickFind.clear();
		seleniumObj.waitForSeconds(3);
		quickFind.sendKeys(obj);
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	public void selectSearchValue(String obj) {
		WebElement ele = seleniumObj.getDriver().findElement(
				By.xpath("//*[@data-placement='leaf' and @aria-label='" + obj + "']//*[text()='" + obj + "']"));
		seleniumObj.waitForElement(ele, 3, 5);
		ele.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	public WebElement returnObjHeader(String obj) {
		// sfcommonObj.switchToFrame(objectIFrame);
		sfcommonObj.waitTillLightningPageLoadComplete();
		WebElement ele = seleniumObj.getDriver().findElement(By.xpath("//h1[text()='" + obj + "']"));
		seleniumObj.waitForElement(ele, 3, 5);
		return ele;

	}

	@FindBy(xpath = "//iframe[contains(@title,'Salesforce - Unlimited Edition')]")
	public WebElement objectIFrame;

	public void verifyPublicGroupName(String grpName) {
		WebElement ele = seleniumObj.getDriver().findElement(By.xpath("//a[text()='" + grpName + "']"));
		seleniumObj.waitForElement(ele, 3, 5);
		seleniumObj.scrollToElement(ele);
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	@FindBy(xpath = "//input[@title='New User']")
	public WebElement newUser;

	/**
	 * 
	 * @Description Method to click on new user
	 * @Author njunghax
	 * @Since May 4, 2021
	 */
	public void clickOnNewUser() {
		seleniumObj.waitForElement(newUser, 4, 5);
		seleniumObj.clickByJS(newUser);
		sfcommonObj.waitTillLightningPageLoadComplete();

	}

	/**
	 * 
	 * @Description Method to enter first name
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param claimName
	 */
	public void enterUserFirstName(String firstName) throws TimeOutException {
		seleniumObj.waitForElement(firstNameUser, 5, 5);
		seleniumObj.scrollToElement(firstNameUser);
		firstNameUser.click();
		firstNameUser.clear();
		firstNameUser.sendKeys(firstName);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//label[text()='First Name']/following::input[@name='name_firstName']")
	public WebElement firstNameUser;

	/**
	 * 
	 * @Description Method to enter first name
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param claimName
	 */
	public void enterUserLastName(String lastName) throws TimeOutException {
		seleniumObj.waitForElement(lastNameUser, 5, 5);
		seleniumObj.scrollToElement(lastNameUser);
		lastNameUser.click();
		lastNameUser.clear();
		lastNameUser.sendKeys(lastName);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//label[text()='Last Name']/following::input[@name='name_lastName']")
	public WebElement lastNameUser;

	/**
	 * 
	 * @Description Method to select User Profile
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectUserProfile(String profile) throws TimeOutException {
		seleniumObj.waitForElement(profileUser, 5, 4);
		seleniumObj.scrollToElement(profileUser);
		Select select = new Select(profileUser);
		select.selectByVisibleText(profile);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//label[text()='Profile']/following::select[@name='Profile']")
	public WebElement profileUser;

	/**
	 * 
	 * @Description Method to select User license
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectUserLicense(String license) throws TimeOutException {
		seleniumObj.waitForElement(userLicense, 5, 4);
		seleniumObj.scrollToElement(userLicense);
		Select select = new Select(userLicense);
		select.selectByVisibleText(license);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();

	}

	@FindBy(xpath = "//label[text()='User License']/following::select[@name='user_license_id']")
	public WebElement userLicense;

	/**
	 * 
	 * @Description Method to select User license
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectUserRole(String role) throws TimeOutException {
		seleniumObj.waitForElement(roleUser, 5, 4);
		seleniumObj.scrollToElement(roleUser);
		Select select = new Select(roleUser);
		select.selectByVisibleText(role);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//select[@id='role']")
	public WebElement roleUser;

	@FindBy(xpath = "//input[@name='new_password' and @checked='checked']")
	public WebElement passwordCheckbox;

	/**
	 * 
	 * @Description Method to click on checkbox
	 * @Author njunghax
	 * @Since May 4, 2021
	 * @Param activity
	 */
	public void clickOnPasswordCheckboxInUserCreatePage() throws TimeOutException {
		seleniumObj.waitForElement(passwordCheckbox, 5, 4);
		seleniumObj.scrollToElement(passwordCheckbox);
		seleniumObj.clickByJS(passwordCheckbox);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//label[text()='Alias']/following::input[@name='Alias'])[1]")
	public WebElement aliasTextBox;

	@FindBy(xpath = "(//label[text()='Email']/following::input[@name='Email'])[1]")
	public WebElement emailTextBox;

	@FindBy(xpath = "(//label[text()='Username']/following::input[@name='Username'])[1]")
	public WebElement usernameTextBox;

	@FindBy(xpath = "(//label[text()='Nickname']/following::input[@name='CommunityNickname'])[1]")
	public WebElement nicknameTextBox;

	@FindBy(xpath = "(//input[@title='Save'])[1]")
	public WebElement saveUserBtn;

	/**
	 *
	 * @Description Method to enter Alias name]
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Alias Name
	 */
	public void enterAliasName(String aliasname) throws TimeOutException {
		seleniumObj.waitForElement(aliasTextBox, 5, 5);
		aliasTextBox.click();
		aliasTextBox.clear();
		aliasTextBox.sendKeys(aliasname);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to enter Email Address
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Email
	 */
	public void enterEmailAddress(String email) throws TimeOutException {
		seleniumObj.waitForElement(emailTextBox, 5, 5);
		emailTextBox.click();
		emailTextBox.clear();
		emailTextBox.sendKeys(email);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to enter Username
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Username
	 */
	public void enterUsername(String username) throws TimeOutException {
		seleniumObj.waitForElement(usernameTextBox, 5, 5);
		usernameTextBox.click();
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to enter Nickname
	 * @Author sdhasadx
	 * @Since May 14 2021
	 * @Param Nickname
	 */
	public void enterNickname(String nickname) throws TimeOutException {
		seleniumObj.waitForElement(nicknameTextBox, 5, 5);
		nicknameTextBox.click();
		nicknameTextBox.clear();
		nicknameTextBox.sendKeys(nickname);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to click on save button
	 * @Author sdhasadx
	 * @Since May 14 2021
	 */
	public void clickOnSaveUserButton() {
		seleniumObj.waitForElement(saveUserBtn, 4, 3);
		seleniumObj.scrollToElement(saveUserBtn);
		saveUserBtn.click();
		sfcommonObj.acceptPopUpsAndAlerts();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "((//div[@class='listRelatedObject setupBlock'])[1]//h3[text()='Permission Set Assignments']/following::input[@name='editPermSetAssignments'])[1]")
	public WebElement editPermissionSetsBtn;

	/**
	 *
	 * @Description Method to click on edit Permission Sets button
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnEditPermissionSetsBtn() {
		seleniumObj.waitForElement(editPermissionSetsBtn, 4, 3);
		seleniumObj.scrollToElement(editPermissionSetsBtn);
		editPermissionSetsBtn.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//select[@title='View:']")
	public WebElement selectUsersView;

	/**
	 * 
	 * @Description Method to select User license
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectUsersView(String view) throws TimeOutException {
		seleniumObj.waitForElement(selectUsersView, 4, 5);
		seleniumObj.scrollToElement(selectUsersView);
		Select select = new Select(selectUsersView);
		select.selectByVisibleText(view);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//label[text()='Available Permission Sets']/following::select[@class='dueling ' and contains(@name,'permSetAssignSection')])[1]")
	public WebElement selectAvailablePermissionSets;

	/**
	 * 
	 * @Description Method to select User license
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectAvailablePermissionSet(String permissionSet) throws TimeOutException {
		seleniumObj.waitForElement(selectAvailablePermissionSets, 4, 5);
		seleniumObj.scrollToElement(selectAvailablePermissionSets);
		Select select = new Select(selectAvailablePermissionSets);
		select.selectByVisibleText(permissionSet);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//*[@class='rightArrowIcon'])[1]")
	public WebElement rightArrowAddBtn;

	/**
	 *
	 * @Description Method to click on right Arrow Add button
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnRightArrowAddBtn() {
		seleniumObj.waitForElement(rightArrowAddBtn, 4, 3);
		seleniumObj.scrollToElement(rightArrowAddBtn);
		rightArrowAddBtn.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//input[@value='Save' and @type='submit'])[1]")
	public WebElement savePermissionSets;

	/**
	 *
	 * @Description Method to click on save Permission Sets button
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnsavePermissionSetsBtn() {
		seleniumObj.waitForElement(savePermissionSets, 4, 3);
		seleniumObj.scrollToElement(savePermissionSets);
		savePermissionSets.click();
		sfcommonObj.acceptPopUpsAndAlerts();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//select[@title='View:']")
	public WebElement selectPublicGroupView;

	/**
	 * 
	 * @Description Method to select PublicGroupView
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectPublicGroupView(String view) throws TimeOutException {
		seleniumObj.waitForElement(selectPublicGroupView, 4, 5);
		seleniumObj.scrollToElement(selectPublicGroupView);
		Select select = new Select(selectPublicGroupView);
		select.selectByVisibleText(view);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to click navigateToPageUsingAlphabets
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void navigateToPageUsingAlphabets(char alp) {
		WebElement ele = seleniumObj.getDriver()
				.findElement(By.xpath("(//span[@class='listItemPad' and text()='" + alp + "'])[1]"));
		seleniumObj.waitForElement(ele, 2, 3);
		seleniumObj.scrollToElement(ele);
		ele.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//*[text()='Next Page>'])[1]")
	public WebElement nextPageLink;

	/**
	 *
	 * @Description Method to click on click NextPage Btn
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickNextPageBtn() {
		seleniumObj.waitForElement(nextPageLink, 4, 3);
		seleniumObj.scrollToElement(nextPageLink);
		nextPageLink.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 *
	 * @Description Method to returnPublicGroupNameElement
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public WebElement returnPublicGroupNameElement(String publicGrp) {
		WebElement ele = seleniumObj.getDriver().findElement(By.xpath("//a[text()='" + publicGrp + "']"));
		seleniumObj.waitForElement(ele, 4, 3);
		seleniumObj.scrollToElement(ele);
		return ele;
	}

	/**
	 *
	 * @Description Method to click on clickOnPublicGroupName
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickOnPublicGroupName(String publicGrp) {
		seleniumObj.waitForElement(returnPublicGroupNameElement(publicGrp), 4, 3);
		seleniumObj.scrollToElement(returnPublicGroupNameElement(publicGrp));
		returnPublicGroupNameElement(publicGrp).click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//input[@title='Edit'])[1]")
	public WebElement editPublicGroup;

	/**
	 *
	 * @Description Method to clickEditOnPubicGroup
	 * @Author kumark8x
	 * @Since May 14 2021
	 */
	public void clickEditOnPubicGroup() {
		seleniumObj.waitForElement(editPublicGroup, 4, 3);
		seleniumObj.scrollToElement(editPublicGroup);
		editPublicGroup.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//select[@id='sharing_search']")
	public WebElement selectSearchSharingInPublicGroup;

	/**
	 * 
	 * @Description Method to select PublicGroupView
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void SelectSearchSharingInPublicGroup(String search) throws TimeOutException {
		seleniumObj.waitForElement(selectSearchSharingInPublicGroup, 4, 5);
		seleniumObj.scrollToElement(selectSearchSharingInPublicGroup);
		Select select = new Select(selectSearchSharingInPublicGroup);
		select.selectByVisibleText(search);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "//input[@id='searchValue_sharing_search']")
	public WebElement searchValueInFindInPublicGroup;

	public void searchValueInFindInPublicGroup(String value) {
		seleniumObj.waitForElement(searchValueInFindInPublicGroup, 4, 5);
		seleniumObj.scrollToElement(searchValueInFindInPublicGroup);
		searchValueInFindInPublicGroup.clear();
		searchValueInFindInPublicGroup.sendKeys(value);
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	@FindBy(xpath = "(//input[@title='Find'])[1]")
	public WebElement findBtn;

	public void clickOnFindInPublicGroup() {
		seleniumObj.waitForElement(findBtn, 4, 4);
		seleniumObj.scrollToElement(findBtn);
		seleniumObj.clickByJS(findBtn);
		// findBtn.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	@FindBy(xpath = "(//label[text()='Available Members']/following::select[contains(@id,'duel_select')])[1]")
	public WebElement selectAvailableMembersInPublicGroup;

	/**
	 * 
	 * @Description Method to select PublicGroupView
	 * @Author kumark8x
	 * @Since Nov 27, 2021
	 * @Param recordType
	 */
	public void selectAvailableMembersInPublicGroup(String availableMembers) throws TimeOutException {
		seleniumObj.waitForElement(selectAvailableMembersInPublicGroup, 4, 4);
		seleniumObj.scrollToElement(selectAvailableMembersInPublicGroup);
		Select select = new Select(selectAvailableMembersInPublicGroup);
		select.selectByVisibleText(availableMembers);
		sfcommonObj.waitTillAllXHRCallsComplete();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	@FindBy(xpath = "(//input[@title='Save'])[1]")
	public WebElement saveInPublicGroup;

	public void clickOnSaveInPublicGroup() {
		seleniumObj.waitForElement(saveInPublicGroup, 4, 4);
		seleniumObj.scrollToElement(saveInPublicGroup);
		saveInPublicGroup.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	public List<WebElement> getPublicGroupsNameOnPage() {
		String xpath = "(//a[text()='Label']/following::th[@scope='row']/a)";
		return seleniumObj.getDriver().findElements(By.xpath(xpath));
	}

	@FindBy(xpath = "(//label[text()='Salutation']/following::input[@name='salutation'])[1]")
	public WebElement salutationContact;

	@FindBy(xpath = "(//label[text()='First Name']/following::input[@name='firstName'])[1]")
	public WebElement firstNameContact;

	@FindBy(xpath = "(//label[text()='Last Name']/following::input[@name='lastName'])[1]")
	public WebElement lastNameContact;

	@FindBy(xpath = "(//label[text()='Email']/following::input[@name='Email'])[1]")
	public WebElement email;

	@FindBy(xpath = "(//span[text()='Enable Partner User'])[1]")
	public WebElement EnablePartnerUser;

	@FindBy(xpath = "(//span[@title='Contacts']/following::div[@title='New'])[1]")
	public WebElement newButtonForContact;

	@FindBy(xpath = "(//h2[text()='New Contact']/following::span[text()='Next'])[1]")
	public WebElement nextButtonForContact;

	@FindBy(xpath = "(//button[text()='Save' and @name='SaveEdit'])[1]")
	public WebElement saveButtonForCotact;

	@FindBy(xpath = "(//span[@title='Contacts'])[1]")
	public WebElement contactSectionInAccount;

	@FindBy(xpath = "(//*[@title='Related']//*[text()='Related'])[1]")
	public WebElement accountRelatedTab;

	/**
	 * Method to click on related tab
	 * 
	 * @author njunghax
	 * @Since May 17, 2021
	 */
	public void clickOnAccountRelatedTab() {
		seleniumObj.waitForElement(accountRelatedTab, 4, 3);
		seleniumObj.scrollToElement(accountRelatedTab);
		seleniumObj.clickByJS(accountRelatedTab);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * Method to click on salutation
	 * 
	 * @author njunghax
	 * @Since May 17, 2021
	 */
	public void clickOnSalutation() {
		seleniumObj.waitForElement(salutationContact, 4, 3);
		seleniumObj.scrollToElement(salutationContact);
		seleniumObj.clickByJS(salutationContact);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * 
	 * @Description Method to select record type
	 * @Author njunghax
	 * @Since May 17, 2021
	 */
	public void selectRecordTypeContact(String type) throws TimeOutException, InterruptedException {
		WebElement selectType = seleniumObj.getDriver()
				.findElement(By.xpath("//span[text()='" + type + "']/preceding::span[@class='slds-radio--faux'][1]"));
		seleniumObj.waitForElement(selectType, 4, 4);
		seleniumObj.scrollToElement(selectType);
		selectType.click();
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

	@FindBy(xpath = "(//span[contains(text(),'more actions')]/parent::button[contains(@class,'slds-button')])[1]")
	public WebElement moreActions;
	
	/**
	 * 
	 * @Description Method enter first name
	 * @Author ubijux
	 * @param searchText
	 * @Since Dec 09, 2018
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
	 * @Description Method enter Last name
	 * @Author ubijux
	 * @param searchText
	 * @Since Dec 09, 2018
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
	 * @Author ubijux
	 * @param searchText
	 * @Since Dec 09, 2018
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

	public void clickOnNewButtonForContact() {
		seleniumObj.scrollByDown();
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.scrollToElement(contactSectionInAccount);
		seleniumObj.waitForElement(newButtonForContact, 4, 5);
		seleniumObj.scrollToElement(newButtonForContact);
		seleniumObj.clickByJS(newButtonForContact);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickOnNextButtonForContact() {
		seleniumObj.waitForElement(nextButtonForContact, 4, 5);
		seleniumObj.scrollToElement(nextButtonForContact);
		seleniumObj.clickByJS(nextButtonForContact);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickOnSaveButtonForContact() {
		seleniumObj.waitForElement(saveButtonForCotact, 4, 5);
		seleniumObj.scrollToElement(saveButtonForCotact);
		seleniumObj.doubleClick(saveButtonForCotact);
		//seleniumObj.clickByJS(saveButtonForCotact);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickOnEnablePartnerUserButton() {
		seleniumObj.waitForElement(EnablePartnerUser, 4, 5);
		seleniumObj.scrollToElement(EnablePartnerUser);
		seleniumObj.clickByJS(EnablePartnerUser);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickOnAccountMoreActionsButton() {
		seleniumObj.waitForElement(moreActions, 4, 4);
		seleniumObj.scrollToElement(moreActions);
		seleniumObj.clickByJS(moreActions);
		sfcommonObj.waitTillLightningPageLoadComplete();
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
}
