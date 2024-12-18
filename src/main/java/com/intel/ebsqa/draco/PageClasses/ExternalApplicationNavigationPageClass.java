/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

/**
 * @Description
 * @Author gmathavx
 * @Since 14-Sep-2018
 */

public class ExternalApplicationNavigationPageClass extends TestBase {

	public ExternalApplicationNavigationPageClass() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}

	@FindBy(xpath = "//div[contains(@data-region-name,'navBar')]")
	public WebElement profileMenu;

	@FindBy(xpath = "//button[contains(.,'My Program')]")
	public WebElement myProgramMenu;

	public WebElement subMenu(String subMenu) {
		String xpath = "//a[@title='" + subMenu + "']";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 1);
	}

	@FindBy(xpath = "//a[@title='New']")
	public WebElement newButton;

	@FindBy(xpath = "//button[@title='New']")
	public WebElement actvtynewButton;

	@FindBy(xpath = "//a[contains(@class,'trigger-link')]")
	public WebElement userMenuLink;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//label[contains(.,'Account Name')]/following-sibling::input")
	public WebElement accountName;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//input[@title='Search Channel Programs']")
	public WebElement channelProgram;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//input[@title='Search Accounts']")
	public WebElement parentFundAccount;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//span[text()='Parent Account']/parent::div/following-sibling::div")
	public WebElement parentAccount;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//div[contains(@class,'recordTypeName')]")
	public WebElement accountRecordType;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//button[@title='Cancel']")
	public WebElement cancelButton;

	@FindBy(xpath = "//div[contains(@class,'modal-container')]//button[@title='Save']")
	public WebElement saveButton;

	@FindBy(xpath = "//div[contains(@class,'dedupeToast')]/div")
	public WebElement duplicateErrorMessage;

	@FindBy(xpath = "//div[contains(@class,'dedupeToast')]//div//a[text()='View Duplicates']")
	public WebElement viewDuplicateLink;

	@FindBy(xpath = "//ul[@class='errorsList']/li")
	public WebElement newCoMarketingWindowHeaderErrorMessage;

	@FindBy(xpath = "//span[text()='Related']")
	public WebElement relatedTab;

	@FindBy(xpath = "//input[@title='Search My Program data...']")
	public WebElement globalSearchText;

	@FindBy(xpath = "//a[contains(@class,'SEARCH_OPTION')]")
	public WebElement globalSearchResultSearchIcon;

	public WebElement searchResult(String SearchText, String SearchType) {
		return seleniumObj.getDriver()
				.findElement(By.xpath("//div[contains(@class,'mruDescription')]//div[text()='" + SearchType
						+ "']/ancestor::a//div//span[contains(@class,'mruName') and @title='" + SearchText + "']"));
	}

	public WebElement dynamicPopupDropdown(String value) {
		String xpath = "//div[contains(@class,'modal-container')]//div[contains(@class,'lookup__menu') and not(contains(@class,'invisible'))]//ul[@class='lookup__list  visible']//a[contains(.,'"
				+ value + "')]";
		return seleniumObj.waitForElement(By.xpath(xpath), 30, 1);
	}

	public WebElement dynamicDropDownValueLink(String menu) {
		return seleniumObj.waitForElement(By.xpath(
				"//div[contains(@class,'uiPopupTarget') and contains(@class,'visible')]//descendant::li//a[contains(text(), '"
						+ menu + "')]"),
				10, 1);
	}

	public boolean WaitTillHomePageVisibleFroExternalApplication()
			throws TimeOutException, com.intel.ebsqa.exceptions.TimeOutException {
		seleniumObj.waitForElement(profileMenu, 15, 5);
		return seleniumObj.waitForWebElementVisible(profileMenu, 15);
	}

	public WebElement menuName(String menuTabName) {
		String xpath = "//button[contains(.,'" + menuTabName + "')]";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 5);
	}

	public WebElement viewOption() {
		String xpath = "//a[@title='Select List View']";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 5);
	}

	public WebElement budgetNameInPartnerFundAllocationView(String budgetName) {
		String xpath = "//span[text()='Partner Fund Allocation Name']/following :: a[@title='" + budgetName + "'][1]";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 5);
	}

	public List<WebElement> coMarketingAccouctColumnHeader() {
		String xpath = "//div[contains(@class,'forceListViewManagerGrid')]//table/thead//th//a/span[@class='slds-truncate']";
		return seleniumObj.getDriver().findElements(By.xpath(xpath));

	}

	public List<WebElement> coMarketingAccouctViewRows() {
		String xpath = "//div[contains(@class,'forceListViewManagerGrid')]//table/tbody//tr";
		return seleniumObj.getDriver().findElements(By.xpath(xpath));

	}

	public WebElement coMarketingAccouctViewRows(int rowIndex, int columnindex) {
		String xpath = "//div[contains(@class,'forceListViewManagerGrid')]//table/tbody//tr[" + rowIndex + "]/td["
				+ columnindex + "]//a";
		return seleniumObj.getDriver().findElement(By.xpath(xpath));

	}

	public WebElement searchFieldInView() {
		String xpath = "//input[contains(@name, 'search-input')]";
		return seleniumObj.waitForElement(By.xpath(xpath), 10, 3);

	}

	public WebElement selectBudget(String budgetName) {
		String xpath = "//span[text()='Budget Name']/following :: a[text()='" + budgetName + "']";
		return seleniumObj.waitForElement(By.xpath(xpath), 10, 3);

	}

	public WebElement selectColumnHeader() {
		String xpath = "//table/thead//th[contains(@class,' selectionColumnHeader')]";
		return seleniumObj.waitForElement(By.xpath(xpath), 10, 3);

	}

	public WebElement programText() {
		String xpath = "//span[text()='Program']//ancestor::div[@role='listitem']//a";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 1);
	}

	public WebElement headerElement(String accountName) {
		String headerIDXPath = "//a[text()='" + accountName + "']/ancestor::tr[contains(@class,'dataRow')]/th";
		return seleniumObj.waitForElement(By.xpath(headerIDXPath), 5, 1);
	}

	public List<WebElement> userList(String headerID) {
		String userListXpath = "//td[contains(@headers, '" + headerID + "')]";
		return seleniumObj.getDriver().findElements(By.xpath(userListXpath));
	}

	public WebElement accountName(String accountName) {
		String accountNameXPath = "//a[text()='" + accountName + "']";
		return seleniumObj.waitForElement(By.xpath(accountNameXPath), 5, 1);
	}

	public WebElement accountUserLink() {
		String accountUsersLink = "//h2//span[text()='Account Users']";
		return seleniumObj.waitForElement(By.xpath(accountUsersLink), 5, 1);
	}

	public List<WebElement> accountList() {
		String accountListXpath = "//table[@data-aura-class='uiVirtualDataTable']/tbody//tr/td[2]/span/a";
		return seleniumObj.getDriver().findElements(By.xpath(accountListXpath));
	}

	public WebElement fullName() {
		String xpathforFullName = "//table/tbody/tr[@class='headerRow']/th[2]/a";
		return seleniumObj.waitForElement(By.xpath(xpathforFullName), 5, 1);
	}

	public WebElement accountName() {
		String xpathForAccount = "//table/tbody/tr[@class='headerRow']/th[1]/div";
		return seleniumObj.waitForElement(By.xpath(xpathForAccount), 5, 1);
	}

	public void clickOnProgramMenu() {
		myProgramMenu.click();
	}

	public void clickOnSubMenu(String menu) {
		// subMenu(menu).click();
		seleniumObj.clickByJS(subMenu(menu));
	//	sfcommonObj.clickOnElement(subMenu(menu));
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public boolean isNewButtonVisible(String menu) {
		if (menu.equals("Co-Marketing Activities") || menu.equals("Claims")) {
			return seleniumObj.isElementExists(actvtynewButton);
		} else {
			return seleniumObj.isElementExists(newButton);
		}
	}


	public void clickOnUserMenuLink() {
		seleniumObj.click(userMenuLink);
	}

	public void selectDynamicDropDownValueLink(String menu) {
		seleniumObj.click(dynamicDropDownValueLink(menu));
	}

	public void clickOnNewButton() {
		seleniumObj.click(newButton);
	}

	public WebElement budgetNameInPartnermarketingFundView(String budgetName) {
		String xpath = "//span[text()='Budget Name']/following::a[@title='" + budgetName + "']";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 1);
	}

	public WebElement budgetAmountInPartnermarketingFundView(String budgetName, String budgetAmount) {
		String xpath = "//span[text()='Budget Name']/following ::tbody/tr/th/span/a[@title='" + budgetName
				+ "']/following::td/following::span[contains(text(), '" + budgetAmount + "')]";
		return seleniumObj.waitForElement(By.xpath(xpath), 5, 1);
	}

	@FindBy(xpath = "//span[text()='Details']")
	public WebElement campaignDetails;

	@FindBy(xpath = "//div/span[@title='Status']/following-sibling::div//span")
	public WebElement campaignStatus;

	/**
	 * 
	 * @Description Method to click on a particular view
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @param field
	 * @return
	 */
	public void clickView() throws TimeOutException {
		seleniumObj.waitForElement(viewOption(), 10,5);
		viewOption().click();
		log.info("Clicked on view option");
	}

	/**
	 * 
	 * @Description Method to verify budget amount displayed in the partner fund
	 *              allocation view
	 * @Author ubijux
	 * @Since Sep 26, 2018
	 * @param field
	 * @return
	 */
	public Boolean isBudgetAmountDisplayedInPartnerFundAllocation(String budgetName, String budgetAmount) {
		seleniumObj.waitForElement(budgetAmountInPartnermarketingFundView(budgetName, budgetAmount), 10, 3);
		return seleniumObj.isElementExists(budgetAmountInPartnermarketingFundView(budgetName, budgetAmount));

	}

	/**
	 * 
	 * @Description Method to verify budget displayed or not under the partner fund
	 *              allocation view
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @param field
	 * @return
	 */
	public Boolean isBudgetNameDisplayedInPartnerFundAllocation(String budgetName) {
		seleniumObj.waitForElement(budgetNameInPartnerFundAllocationView(budgetName), 10, 3);
		return seleniumObj.isElementExists(budgetNameInPartnerFundAllocationView(budgetName));

	}

	/**
	 * 
	 * @Description Method to verify budget displayed or not under the partner
	 *              marketing fund view
	 * @Author ubijux
	 * @Since Sep 24, 2018
	 * @param field
	 * @return
	 */
	public Boolean isBudgetNameDisplayedInPartnerMarketingBudget(String budgetName) {
		seleniumObj.waitForElement(budgetNameInPartnermarketingFundView(budgetName), 10, 3);
		return seleniumObj.isElementExists(budgetNameInPartnermarketingFundView(budgetName));

	}

	public int getRowCountInAllCampaignTable() {
		return seleniumObj.getDriver()
				.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody//tr")).size();
	}

	@FindBy(xpath = "//span[text()='Co-Marketing Campaigns']")
	public WebElement allCampaigns_Link;

	@FindBy(xpath = "//input[contains(@placeholder,'Search this list...')]")
	public WebElement allCampaigns_Search_TextBox;

	@FindBy(xpath = "//a[@title='Select List View']")
	public WebElement allCampaigns_SelectView_Link;

	@FindBy(xpath = "//input[contains(@name, 'search-input')]")
	public WebElement searchTextBox;

	public void enterTextInSearchTextBoxInAllCampaignsTab(String text) {
		allCampaigns_Search_TextBox.sendKeys(text);
	}

	public void clearTextInSearchTextBoxInAllCampaignsTab() {
		allCampaigns_Search_TextBox.clear();
	}

	public void sendEnterKeyToSearchTextBoxInAllCampaignsTab() {
		allCampaigns_Search_TextBox.sendKeys(Keys.ENTER);
		allCampaigns_Search_TextBox.sendKeys(Keys.RETURN);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickAllCampaignsLink() {
		if (seleniumObj.isElementExists(allCampaigns_Link))
			allCampaigns_Link.click();
	}

	public void clickSelectViewLink() {
		allCampaigns_SelectView_Link.click();
	}

	public void selectView(String view) throws TimeOutException, InterruptedException {
		try {
			WebElement selectView = seleniumObj.getDriver().findElement(By.xpath("//span[text()='" + view + "']"));
			seleniumObj.waitForWebElementVisible(selectView, 10);
			selectView.click();
			seleniumObj.waitForSeconds(5);
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception e) {
			log.error("selectView method got the error", e);
		}
	}

	public void setAccountName(String accountNameValue) {
		seleniumObj.sendKeys(accountName, accountNameValue);
	}

	public void selectDynamicPopupDropdown(String dynamicDropdownValue) {
		seleniumObj.click(dynamicPopupDropdown(dynamicDropdownValue));
	}

	public void setChannelProgram(String channelProgramValue) {
		seleniumObj.click(channelProgram);
		JavascriptExecutor executor = (JavascriptExecutor)seleniumObj.getDriver();
		executor.executeScript("arguments[0].setAttribute('value', arguments[1])", channelProgram, channelProgramValue);						
		seleniumObj.waitForSeconds(5);
		seleniumObj.sendKeys(channelProgram, Keys.SPACE);		
		seleniumObj.waitForSeconds(2);
		seleniumObj.sendKeys(channelProgram, Keys.BACK_SPACE);
	}

	public void setParentFundAccountValue(String parentFundAccountValue) {
		seleniumObj.click(parentFundAccount);
		JavascriptExecutor executor = (JavascriptExecutor)seleniumObj.getDriver();
		executor.executeScript("arguments[0].setAttribute('value', arguments[1])", parentFundAccount, parentFundAccountValue);		
		seleniumObj.waitForSeconds(5);
		seleniumObj.sendKeys(parentFundAccount, Keys.SPACE);		
		seleniumObj.waitForSeconds(2);
		seleniumObj.sendKeys(parentFundAccount, Keys.BACK_SPACE);			
	}

	public void clickOnSaveButton() {
		seleniumObj.click(saveButton);
	}

	public void clickOnCancelButton() {
		cancelButton.click();
	}

	public String getHeaderErrorMessageInCoMarketingWindow() {
		return duplicateErrorMessage.getText();
	}

	public void clickOnViewDuplicateLink() {
		viewDuplicateLink.click();
	}

	/**
	 * 
	 * @Description Method to check if red asterisk mark is visible for new Co
	 *              Marketing Fields
	 * @Author sharmata
	 * @Since Sep 7, 2018
	 * @param field
	 * @return
	 */
	public Boolean isRedAsteriskMarkVisibleForNewCampaignField(String field) {
		Boolean status = false;
		try {
			WebElement element = seleniumObj.getDriver()
					.findElement(By.xpath("//*[text()='" + field + "']/following-sibling::span[text()='*']"));
			status = seleniumObj.isElementExists(element);
		} catch (Exception ex) {
			status = false;
		}
		return status;
	}

	public String getMandatoryFieldErrorMessageInCoMarketingWindow() {
		return newCoMarketingWindowHeaderErrorMessage.getText();
	}

	/**
	 * 
	 * 
	 * @Description Click On Campaign Name
	 * @Author sharmata
	 * @Since Oct 8, 2018
	 * @param campaignName
	 * @throws TimeOutException
	 * @throws InterruptedException
	 */
	public void clickOnCampaignName(String campaignName) throws TimeOutException, InterruptedException {
		try {
			WebElement campaign = seleniumObj.getDriver().findElement(
					By.xpath("//span[text()='Campaign Name']/following :: a[text()='" + campaignName + "']"));
			Assert.assertTrue(sfcommonObj.checkElementExists(campaign), "Unable to search the campaign");
			campaign.click();

			log.info("Click on capmaign name");
			seleniumObj.waitForWebElementVisible(campaignDetails, 30);
		} catch (Exception e) {
			log.error("Unable to click on Campaign Name", e);
			Assert.fail("Did not click on Campaign name due to exception");
		}

	}

	/**
	 * 
	 * @Description Method to verify campaign status
	 * @Author sharmata
	 * @Since Oct 7, 2018
	 */
	public String getCampaignStatus() {
		return campaignStatus.getText();

	}

	/**
	 * @Description Method to get the column index of a particular column
	 * @Author ubijux
	 * @Since Oct 19, 2018
	 */
	public int getColumnIndex(String columnName) {
		int index = 0;
		List<WebElement> columnHeader = coMarketingAccouctColumnHeader();
		int headerCount = columnHeader.size();
		if (headerCount > 0) {
			for (int i = 0; i < headerCount; i++) {
				if ((columnHeader.get(i).getAttribute("title")).equals(columnName)) {
					index = i + 1;
					if (seleniumObj.isElementExists(selectColumnHeader()))
						index++;
					break;
				}
			}
		} else {
			log.error("No columns displayed");
		}
		return index;
	}

	/**
	 * 
	 * @Description Method to verify the Channel Program value displayed for all
	 *              rows in the Co-Marketing Account view
	 * @Author ubijux
	 * @Since Oct 19, 2018
	 * @param columnIndex index of Channel Program column
	 * @param columnValue to verify
	 */
	public boolean verifyChannelProgramValue(int columnIndex, String columnValue) {
		boolean isDiplayed = false;
		List<WebElement> rows = coMarketingAccouctViewRows();
		int rowCount = rows.size();
		if (rowCount > 0) {
			for (int i = 1; i <= rowCount; i++) {
				String actualValue = coMarketingAccouctViewRows(i, columnIndex).getAttribute("title").trim();
				if (actualValue.equals(columnValue)) {
					isDiplayed = true;
					log.info("verified column value : " + columnValue + " in row : " + i);
				} else {
					isDiplayed = false;
					log.error("Failed to verify column value : " + columnValue + " in row : " + i);
					break;
				}
			}
		} else {
			log.error("No rows displayed");
		}
		return isDiplayed;
	}

	/**
	 * @Description Click On Co-Marketing activity Name
	 * @Author vveeranx
	 * @Since Oct 22, 2018
	 * @param activityName
	 * @throws TimeOutException
	 * @throws InterruptedException
	 */
	public void clickOnCoMarketingActivityName(String activityName) throws TimeOutException, InterruptedException {
		try {
			WebElement activity = seleniumObj.getDriver().findElement(
					By.xpath("//span[text()='Activity Name']/following :: a[text()='" + activityName + "']"));
			activity.click();
			log.info("Clicked on Co-Marketing activity name");
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();
		} catch (Exception e) {
			log.error("Unable to click on Co-Marketing activity Name", e);
			Assert.fail("Did not click on Co-Marketing activity name due to exception");
		}
	}

	/**
	 * 
	 * @Description Method to get the value in the program field
	 * @Author ubijux
	 * @Since Oct 23, 2018
	 */
	public String getChannelProgram() {
		seleniumObj.waitTillPageLoadIsComplete();
		return programText().getText();
	}

	/**
	 * 
	 * @Description Method to search budget name
	 * @Author ubijux
	 * @Since Oct 23, 2018
	 * @param budgetName
	 */
	public void SearchBudget(String budgetName) {

		seleniumObj.waitTillPageLoadIsComplete();
		searchFieldInView().clear();
		searchFieldInView().sendKeys(budgetName);
		searchFieldInView().sendKeys(Keys.RETURN);
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	/**
	 * 
	 * @Description Method to select budget name
	 * @Author ubijux
	 * @Since Oct 23, 2018
	 * @param budgetName
	 */
	public void SeleclBudget(String budgetName) {

		seleniumObj.scrollToElement(selectBudget(budgetName));
		selectBudget(budgetName).click();
		seleniumObj.waitTillPageLoadIsComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();

	}

	public WebElement reportSubMenu(String subMenuName) {
		String xpath = "//a[@title='" + subMenuName + "']";
		return seleniumObj.getDriver().findElement(By.xpath(xpath));

	}

	/**
	 * @Description Method to click on Report Sub Menu in Report Side bar
	 * @Author mohseenx
	 * @Since Nov 15, 2018
	 * @param subMenu
	 */
	public void clickOnReportSubMenu(String subMenu) {
		seleniumObj.waitForElement(reportSubMenu(subMenu), 10, 3);
		reportSubMenu(subMenu).click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Description Method to search for a text
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @param text
	 */
	public void enterTextInSearchTextBox(String text) {
		searchTextBox.sendKeys(text);
		searchTextBox.sendKeys(Keys.ENTER);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Description Method to click on search result
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @param text
	 */
	public void clickSearchResult(String text) {
		String xpath = "//button[@title='" + text + "']";
		WebElement element = seleniumObj.getDriver().findElement(By.xpath(xpath));
		element.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Description Method to get budget name of first record in 'Account Balances'
	 *              Report
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @return String
	 */
	public String getFirstBudgetInAccountBalancesReport() {
		String firstRecordXPath = "//table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/th/a";
		WebElement element = seleniumObj.getDriver().findElement(By.xpath(firstRecordXPath));
		seleniumObj.scrollToElement(element);
		String budgetName = element.getText();
		return budgetName;
	}

	/**
	 * @Description Method to get account balance sum field corresponding to each
	 *              account in 'Account Balances' Report
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @param budgetName
	 * @return String[][]
	 */
	public String[] getAccountAndCorrespondingAccountBalanceSumInAccountBalancesReport(String budgetName) {
		String headerIDXPath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/th";
		WebElement headerIDElement = seleniumObj.getDriver().findElement(By.xpath(headerIDXPath));
		seleniumObj.scrollToElement(headerIDElement);
		String headerID = headerIDElement.getAttribute("id") + " ";

		String acountListXpath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/td[contains(@headers, '"
				+ headerID + "')]/a";
		List<WebElement> elements = seleniumObj.getDriver().findElements(By.xpath(acountListXpath));
		String[] accountAndAmountList = new String[elements.size() * 2];
		log.info("Account List: \n");
		int i = 0;
		for (int j = 0; j < elements.size(); j++) {
			String text = elements.get(j).getText();
			accountAndAmountList[i] = text;
			i++;
			log.info(text + "\n");
		}

		String acountBalanceSumListXpath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/td[contains(@headers, '"
				+ headerID + "')][2]";
		elements = seleniumObj.getDriver().findElements(By.xpath(acountBalanceSumListXpath));
		log.info("Account Balance Sum List: \n");
		for (int j = 0; j < elements.size(); j++) {
			String text = elements.get(j).getText();
			accountAndAmountList[i] = text;
			i++;
			log.info(text + "\n");
		}

		return accountAndAmountList;
	}

	/**
	 * @Description Method to click on budget name in 'Account Balances' Report
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 * @param budgetName
	 */
	public void clickOnBudgetNameInAccountBalanceReport(String budgetName) {
		String xpath = "//a[text()='" + budgetName + "']";
		WebElement element = seleniumObj.getDriver().findElement(By.xpath(xpath));
		element.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Description Method to click on related tab
	 * @Author vveeranx
	 * @Since Nov 19, 2018
	 */
	public void clickOnRelatedTab() {
		relatedTab.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Description Method to get budget name corresponding to parent account in
	 *              'Account Balance' report
	 * @Author vveeranx
	 * @Since Nov 21, 2018
	 * @param budgetName
	 * @param account
	 * @return Boolean
	 */
	public Boolean checkIfBudgetNameCorrespondingToAccountDisplayingInAccountBalanceReport(String budgetName,
			String account) {
		String headerIDXPath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/th";
		WebElement headerIDElement = seleniumObj.getDriver().findElement(By.xpath(headerIDXPath));
		seleniumObj.scrollToElement(headerIDElement);
		String headerID = headerIDElement.getAttribute("id") + " ";

		Boolean isDisplay = false;
		String budgetXpath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/td[contains(@headers, '"
				+ headerID + "')]/a[text()='" + account + "']";
		WebElement element = seleniumObj.getDriver().findElement(By.xpath(budgetXpath));
		isDisplay = seleniumObj.isElementExists(element);
		return isDisplay;
	}

	/**
	 * @Description Method to get account field in 'Account Balances' Report
	 * @Author csingamx
	 * @Since Nov 26, 2018
	 * @param budgetName
	 * @return String[][]
	 */
	public String[] getAccountInAccountBalancesReport(String budgetName) {
		String headerIDXPath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/th";
		WebElement headerIDElement = seleniumObj.getDriver().findElement(By.xpath(headerIDXPath));
		seleniumObj.scrollToElement(headerIDElement);
		String headerID = headerIDElement.getAttribute("id") + " ";

		String acountListXpath = "//a[text()='" + budgetName
				+ "']/ancestor::table[contains(@class, 'reportTable')]//tr[contains(@class, 'dataRow')][1]/td[contains(@headers, '"
				+ headerID + "')]/a";
		List<WebElement> elements = seleniumObj.getDriver().findElements(By.xpath(acountListXpath));
		String[] accountList = new String[elements.size()];
		log.info("Account List: \n");
		int i = 0;
		for (int j = 0; j < elements.size(); j++) {
			String text = elements.get(j).getText();
			accountList[i] = text;
			i++;
			log.info(text + "\n");
		}
		return accountList;
	}

	/**
	 * @Author csingamx
	 * @Since Dec 17, 2018
	 */
	public Boolean checkElementEnabled(WebElement element) {
		return sfcommonObj.checkElementExists(element);
	}

	/**
	 * @Author csingamx
	 * @Since Dec 17, 2018
	 */
	public void clickOnNewButton_ext() {
		seleniumObj.click(actvtynewButton);
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	/**
	 * @Author csingamx
	 * @Since Dec 18, 2018
	 */
	public void setValueForGlobalSearchTextbox(String searchText) {
		try {
			globalSearchText.click();
			globalSearchText.clear();
			globalSearchText.sendKeys(searchText);
			log.info("Entered text in global search textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in global search text box");
			Assert.fail("Not able to enter text in global search text box");
		}

	}

	/**
	 * @Author csingamx
	 * @Since Dec 18, 2018
	 */
	public void clickOnSearchResultSearchIcon() {

		try {
			globalSearchResultSearchIcon.click();
			log.info("Clicked on search icon in global search");
		} catch (Exception e) {
			log.error("Not able to click on search icon in global search");
			Assert.fail("Not able to click on search icon in global search");
		}
	}

	/**
	 * 
	 * @Author csingamx
	 * @Since Dec 18, 2018
	 */
	public void clickOnSearchResult(String searchResultText, String SearchType) {
		try {
			searchResult(searchResultText, SearchType).click();
			log.info("Clicked on search text : " + searchResultText + " with search type : " + SearchType
					+ " in auto-complete list view");
		} catch (Exception e) {
			log.error("Not able click on search result with search type in auto-complete list view");
			Assert.fail("Not able click on search result with search type in auto-complete list view");
		}

	}
}
