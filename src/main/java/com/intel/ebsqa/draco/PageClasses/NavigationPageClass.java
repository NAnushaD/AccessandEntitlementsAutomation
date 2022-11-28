package com.intel.ebsqa.draco.PageClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.intel.ebsqa.draco.helperclass.TestBase;
import com.intel.ebsqa.exceptions.TimeOutException;

public class NavigationPageClass extends TestBase {

	public NavigationPageClass() {
		PageFactory.initElements(seleniumObj.getDriver(), this);
	}

	@FindBy(xpath = "//a[span[text()='Home']]")
	public WebElement homePage;

	@FindBy(xpath = "//one-app-launcher-header/button")
	public WebElement appLauncher;

	@FindBy(xpath = "//input[@placeholder='Search apps or items...']")
	public WebElement findAPPTextBox;

	@FindBy(xpath = "//button[contains(@class,'branding-userProfile-button')]")
	public WebElement userImage;

	@FindBy(xpath = "//div[contains(@class,'oneUserProfileCard')]//a[text()='Log Out']")
	public WebElement logoutButton;

	@FindBy(id = "username")
	public WebElement userName;

	public WebElement GetAppName(String appNameToNavigate) {
		WebElement appName = seleniumObj.getDriver()
				.findElement(By.xpath("//mark[text()='" + appNameToNavigate + "']"));
		return appName;
	}

	@FindBy(xpath = "(//div[@class='forceSearchAssistantDialog']//input[contains(@placeholder,'Search')])[1]")
	public WebElement globalSearchText;
	
	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement globalSearchTextButton;

	@FindBy(xpath = "//a[contains(@class,'SEARCH_OPTION')]")
	public WebElement globalSearchResultSearchIcon;
	
	@FindBy(xpath = "(//span[contains(text(),'Show more results for')])[1]")
	public WebElement globalSearchShowMoreResults;

	public WebElement searchResult(String SearchText, String SearchType) {
		return seleniumObj.getDriver()
				.findElement(By.xpath("//div[contains(@class,'mruDescription')]//div[text()='" + SearchType
						+ "']/ancestor::a//div//span[contains(@class,'mruName') and @title='" + SearchText + "']"));
	}
	
	public WebElement searchType( String SearchType) {
		return seleniumObj.getDriver()
				.findElement(By.xpath("(//*[contains(@class,'scopesListSection')]//a[@title='"+SearchType+"']//span[text()='"+SearchType+"'])[1]"));
	}
	
	public WebElement selectSearchResult(String SearchText, String SearchType) {
		return seleniumObj.getDriver()
				.findElement(By.xpath("(//h2/*[text()='"+SearchType+"']/following::th[@scope='row']//a[text()='"+SearchText+"'])[1]"));
	}
	
	@FindBy(xpath = "//input[contains(@title,'Search.')]")
	public WebElement globalSearchTextExternal;
	// div[contains(@class,'mruDescription')]//div[text()='Co-Marketing
	// Activitiy']/ancestor::a//div//span[contains(@class,'mruName') and
	// @title='Automation Team CCF Activity (Not to be touched)']

	@FindBy(xpath = "//span[text()='Related']")
	public WebElement relatedTabInCampaignView;

	@FindBy(xpath = "//span[text()='Upload Files']")
	public WebElement uploadFilesButtonInCampaignView;

	@FindBy(xpath = "//button[text()='View All']")
	public WebElement ViewAllButtonInLaunchView;

	@FindBy(xpath = "//div[contains(@class,'windowViewMode-normal')]//div[contains(@class,'forceListViewManager')]//span[contains(@class,'forceBreadCrumbItem')]")
	public WebElement pageHeaderTitle;

	@FindBy(xpath = "//div[contains(@class,'windowViewMode-normal')]//div[contains(@class,'forceListViewManagerGrid')]")
	public WebElement gridViewTable;

	@FindBy(xpath = "//div[contains(@class,'windowViewMode-normal')]//a[@title='New']")
	public WebElement newBtn;

	public boolean WaitTillHomePageVisible() throws TimeOutException, com.intel.ebsqa.exceptions.TimeOutException {
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForElement(homePage, 5, 1);
		return seleniumObj.waitForWebElementVisible(homePage, 10);
	}

	@FindBy(xpath = "//span[@class='triggerDownArrow down-arrow']")
	public WebElement userMenu;

	public WebElement logoutButtonInExternalCustomer() {
		String xpath = "//li[@class='logOut uiMenuItem']/a[@title='Logout']";
		return seleniumObj.waitForElement(By.xpath(xpath), 1, 1);
	}

	public void ClickOnAppLauncher() throws TimeOutException, InterruptedException {
		try {
			seleniumObj.waitForElement(appLauncher, 4, 3);
			//appLauncher.click();
			seleniumObj.clickByJS(appLauncher);
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Reporter.log("Click On AppLauncher  method completed");
		}
	}

	public void EnterAppNameToNavigate(String appName) throws TimeOutException, InterruptedException {
		try {
			seleniumObj.waitForElement(findAPPTextBox, 4, 3);
			seleniumObj.waitForSeconds(3);
			findAPPTextBox.sendKeys(appName);
			seleniumObj.waitForElement(GetAppName(appName), 4, 3);
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Reporter.log("Enter AppName To Navigate method completed");
		}
	}

	public void ClickAppLink(String appNameToNavigate) throws InterruptedException {
		try {
			GetAppName(appNameToNavigate).click();
			seleniumObj.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Reporter.log("Click App Link method completed");
		}
	}

	public void ClickOnUserIcon() throws TimeOutException {
		try {
			seleniumObj.waitForElement(userImage, 2, 3);
			seleniumObj.click(userImage);
			sfcommonObj.waitTillAllXHRCallsComplete();
			Reporter.log("Click on user icon");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Reporter.log("Click On User Icon Method completed");
		}
	}

	public void ClickOnLogOut() throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(logoutButton, 2);
			seleniumObj.click(logoutButton);
			seleniumObj.waitForSeconds(1);
			sfcommonObj.waitTillAllXHRCallsComplete();
			Reporter.log("Click on logout button");
			// return sfcommonObj.CheckElementExists(userName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Reporter.log("Click On LogOut method completed");
		}
	}

	/**
	 * 
	 * @Description Method enter text in Global search text box
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 */
	public void setValueForGlobalSearchTextbox(String searchText) {
		try {
			seleniumObj.waitForElement(globalSearchTextButton, 5, 5);
			globalSearchTextButton.click();
			seleniumObj.waitForElement(globalSearchText, 5, 5);
			globalSearchText.click();
			globalSearchText.clear();
			seleniumObj.waitForSeconds(2);
			globalSearchText.sendKeys(searchText);
			globalSearchText.sendKeys(Keys.ENTER);
			seleniumObj.waitForSeconds(4);
			log.info("Entered text in global search textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in global search text box");
			Assert.fail("Not able to enter text in global search text box");
		}

	}
	
	/**
	 * 
	 * @Description Method enter text in Global search text box
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 */
	public void setValueForGlobalSearchTextboxExternal(String searchText) {
		try {
			seleniumObj.waitForElement(globalSearchTextExternal, 5, 5);
			globalSearchTextExternal.click();
			globalSearchTextExternal.clear();
			seleniumObj.waitForSeconds(2);
			globalSearchTextExternal.sendKeys(searchText);
			log.info("Entered text in global search textbox : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in global search text box External");
			Assert.fail("Not able to enter text in global search text box External");
		}

	}

	/**
	 * 
	 * @Description Method click on search icon near the global search text box
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 */
	public void clickOnSearchResultSearchIcon() {

		try {
			seleniumObj.waitForElement(globalSearchShowMoreResults, 5, 5);
			seleniumObj.waitForSeconds(3);
			seleniumObj.clickByJS(globalSearchShowMoreResults);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//globalSearchResultSearchIcon.click();
			log.info("Clicked on search icon in global search");
		} catch (Exception e) {
			log.error("Not able to click on search icon in global search");
			Assert.fail("Not able to click on search icon in global search");
		}
	}

	/**
	 * 
	 * @Description Method click on search text with search type in auto-complete
	 *              list view
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 */
	public void clickOnSearchResult(String searchResultText, String SearchType) {
		try {
			//this.clickOnSearchResultSearchIcon();
			//seleniumObj.waitForElement(selectSearchResult(searchResultText,SearchType), 5, 5);
			//seleniumObj.fluentWaitForElement(selectSearchResult(searchResultText,SearchType), 10);
			//seleniumObj.clickByJS(selectSearchResult(searchResultText,SearchType));
						
	        seleniumObj.waitForElement(searchResult(searchResultText, SearchType), 5, 5);
		    seleniumObj.waitForSeconds(3);
		    seleniumObj.clickByJS(searchResult(searchResultText, SearchType));
			//searchResult(searchResultText, SearchType).click();
			
			
			log.info("Clicked on search text : " + searchResultText + " with search type : " + SearchType
					+ " in auto-complete list view");
		} catch (Exception e) {
			log.error("Not able click on search result with search type in auto-complete list view");
			Assert.fail("Not able click on search result with search type in auto-complete list view");
		}

	}
	
	/**
	 * 
	 * @Description Method click on search text with search type in auto-complete
	 *              list view
	 * @Author kumark8x
	 * @Since Sep 13, 2018
	 */
	public void clickOnSearchResultAndSelect(String searchResultText, String SearchType) {
		try {
			this.clickOnSearchResultSearchIcon();
			seleniumObj.clickByJS(searchType(SearchType));
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.waitTillPageLoadIsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			seleniumObj.waitForElement(selectSearchResult(searchResultText,SearchType), 5, 5);
			seleniumObj.scrollToElement(selectSearchResult(searchResultText,SearchType));
			seleniumObj.fluentWaitForElement(selectSearchResult(searchResultText,SearchType), 10);
			seleniumObj.scrollToElement(selectSearchResult(searchResultText,SearchType));
			seleniumObj.clickByJS(selectSearchResult(searchResultText,SearchType));
						
	       // seleniumObj.waitForElement(searchResult(searchResultText, SearchType), 5, 5);
		   // seleniumObj.waitForSeconds(3);
		   // seleniumObj.clickByJS(searchResult(searchResultText, SearchType));
				//searchResult(searchResultText, SearchType).click();
			
			
			log.info("Clicked on search text : " + searchResultText + " with search type : " + SearchType
					+ " in auto-complete list view");
		} catch (Exception e) {
			log.error("Not able click on search result with search type in auto-complete list view");
			Assert.fail("Not able click on search result with search type in auto-complete list view");
		}

	}

	public void clickRelatedTabInCampaignDetailsPage() throws TimeOutException {
		relatedTabInCampaignView.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		seleniumObj.waitForWebElementVisible(relatedTabInCampaignView, 20);
	}

	public String getPageHeaderTitle() {
		return pageHeaderTitle.getText();
	}

	/**
	 * @Description method to click on the user profile menu
	 * @author ubijux
	 * @since sep 24 , 2018
	 * @throws TimeOutException
	 */
	public void ClickOnUserMenu() throws TimeOutException {

		seleniumObj.waitForWebElementVisible(userMenu, 10);
		userMenu.click();
		log.info("Click on user profile menu");

	}

	/**
	 * @Description method to click on logout link from the user profile menu
	 * @author ubijux
	 * @since sep 24 , 2018
	 * @throws TimeOutException
	 */
	public void ClickOnLogOutInExternalCustomerUI() throws TimeOutException {
		seleniumObj.waitForWebElementVisible(logoutButtonInExternalCustomer(), 30);
		logoutButtonInExternalCustomer().click();
		sfcommonObj.waitTillLightningPageLoadComplete();
		log.info("Click on logout button");

	}

	/**
	 * 
	 * @Description Method to click on related tab
	 * @Author vveeranx
	 * @Since Sep 21, 2018
	 * @throws TimeOutException
	 */
	public void clickRelatedTab() throws TimeOutException {
		try {
			relatedTabInCampaignView.click();
			log.info("Clicked on 'Related' tab");
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception e) {
			log.error("Not able click on related tab");
			Assert.fail("Not able click on related tab");
		}
	}

	@FindBy(xpath = "(//a[@title='Details'])")
	public WebElement pmbDetails;

	public void clickOnDetailsTab() throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(pmbDetails, 20);
			pmbDetails.click();
		} catch (Exception e) {
			log.error("Exception in click on pmb details method", e);
			Assert.fail("clickOnPmbDetails failed due to exception");
		}
		log.info("Click on Pmb details method completed");
	}

	@FindBy(xpath = "//a[@title='Select List View']")
	public WebElement allCampaigns_SelectView_Link;

	public void selectView(String view) throws TimeOutException, InterruptedException {
		try {
			WebElement selectView = seleniumObj.getDriver().findElement(By.xpath("//span[text()='" + view + "']"));
			seleniumObj.waitForWebElementVisible(selectView, 20);
			//selectView.click();
			seleniumObj.scrollToElement(selectView);
			seleniumObj.clickByJS(selectView);
			seleniumObj.waitForSeconds(5);
			sfcommonObj.waitTillLightningPageLoadComplete();
		} catch (Exception e) {
			log.error("selectView method got the error", e);
		}
	}

	public void clickSelectViewLink() {
		seleniumObj.waitForElement(allCampaigns_SelectView_Link, 5, 5);
		seleniumObj.clickByJS(allCampaigns_SelectView_Link);
		sfcommonObj.waitTillLightningPageLoadComplete();
		//allCampaigns_SelectView_Link.click();
	}
	
	@FindBy(xpath = "//img[contains(@src, '/img/search/no-results.png')]")
	public WebElement noResultFoundImageAfterGlobalSearch;

	@FindBy(xpath = "//a[text()='Switch to Lightning Experience'and@class='switch-to-lightning']")
	public WebElement lighteningUI;

	public void clickLighteningUILink() {
		seleniumObj.waitForElement(lighteningUI, 4, 5);
		seleniumObj.scrollToElement(lighteningUI);
		lighteningUI.click();
		sfcommonObj.waitTillLightningPageLoadComplete();
	}

	public void clickViewAllinLaunchUI() {
		//ViewAllButtonInLaunchView.click();
		seleniumObj.waitForElement(ViewAllButtonInLaunchView, 5, 5);
		seleniumObj.clickByJS(ViewAllButtonInLaunchView);
	}

	@FindBy(xpath = "(//a[@title='New'])")
	public WebElement newButton;

	public void clickOnNewButton() throws TimeOutException {
		try {
			seleniumObj.waitForWebElementVisible(newButton, 2);
			newButton.click();
		} catch (Exception e) {
			log.error("Exception in click on pmb details method", e);
			Assert.fail("clickOnPmbDetails failed due to exception");
		}
		log.info("Click on Pmb details method completed");
	}
	
	public List<WebElement> getselectSearchedAccounts(String accName, String SearchType, String cimID, String accID) {
		return seleniumObj.getDriver()
				.findElements(By.xpath("(//h2/*[text()='"+SearchType+"']/following::span[@title='"+cimID+"']/following::span[text()='"+accID+"']/preceding::th[@scope='row']//a[text()='"+accName+"'])"));
	}
	
	public List<WebElement> getselectSearchedContact(String accName, String SearchType, String emailID, String contactName) {
		return seleniumObj.getDriver()
				.findElements(By.xpath("(//h2/*[text()='"+SearchType+"']/following::a[text()='"+emailID.toLowerCase()+"']/preceding::a[@title='"+accName+"']/preceding::th[@scope='row']//a[@title='"+contactName+"'])"));
	}
	
	
	@FindBy(xpath = "(//input[@id='phSearchInput' and @title='Search...'])[1]")
	public WebElement globalSearchTextIPS_External;
	
	/**
	 * 
	 * @Description Method enter text in Global search text box
	 * @Author kumark8x
	 * @Since Sep 13, 2021
	 */
	public void setValueForGlobalSearchTextboxIPS_External(String searchText) {
		try {
			seleniumObj.waitForElement(globalSearchTextIPS_External, 5, 5);
			globalSearchTextIPS_External.click();
			globalSearchTextIPS_External.clear();
			seleniumObj.waitForSeconds(2);
			globalSearchTextIPS_External.sendKeys(searchText);
			log.info("Entered text in global search textbox IPS External : " + searchText);
		} catch (Exception e) {
			log.error("Not able to enter text in global search text box IPS External");
			Assert.fail("Not able to enter text in global search text box IPS External");
		}

	}
	
	@FindBy(xpath = "(//input[@id='phSearchButton'])[1]")
	public WebElement globalSearchResultSearchIconIPS_External;
	
	/**
	 * 
	 * @Description Method click on search icon near the global search text box IPS_External
	 * @Author mohseenx
	 * @Since Sep 13, 2018
	 */
	public void clickOnSearchResultSearchIconIPS_External() {

		try {
			seleniumObj.waitForElement(globalSearchResultSearchIconIPS_External, 5, 5);
			seleniumObj.waitForSeconds(3);
			seleniumObj.clickByJS(globalSearchResultSearchIconIPS_External);
			sfcommonObj.waitTillAllXHRCallsComplete();
			sfcommonObj.waitTillLightningPageLoadComplete();
			//globalSearchResultSearchIcon.click();
			log.info("Clicked on search icon in global search IPS_External");
		} catch (Exception e) {
			log.error("Not able to click on search icon in global search IPS_External");
			Assert.fail("Not able to click on search icon in global search IPS_External");
		}
	}
	
	public WebElement selectSearchResultIPS_External(String SearchText, String SearchType) {
		return seleniumObj.getDriver()
				.findElement(By.xpath("(//h3/*[contains(text(),'"+SearchType+"')]/following::th[@scope='row']//a[text()='"+SearchText+"'])[1]"));
	}
	
	/**
	 * 
	 * @Description Method click on search text with search type in auto-complete
	 *              list view
	 * @Author kumark8x
	 * @Since Sep 13, 2018
	 */
	public void clickOnSearchResultAndSelectIPS_External(String searchResultText, String SearchType) {
		try {
			this.clickOnSearchResultSearchIconIPS_External();
			seleniumObj.waitForElement(selectSearchResultIPS_External(searchResultText,SearchType), 5, 5);
			seleniumObj.scrollToElement(selectSearchResultIPS_External(searchResultText,SearchType));
			seleniumObj.fluentWaitForElement(selectSearchResultIPS_External(searchResultText,SearchType), 10);
			seleniumObj.scrollToElement(selectSearchResultIPS_External(searchResultText,SearchType));
			seleniumObj.clickByJS(selectSearchResultIPS_External(searchResultText,SearchType));
						
	       // seleniumObj.waitForElement(searchResult(searchResultText, SearchType), 5, 5);
		   // seleniumObj.waitForSeconds(3);
		   // seleniumObj.clickByJS(searchResult(searchResultText, SearchType));
				//searchResult(searchResultText, SearchType).click();
			
			
			log.info("Clicked on search text : " + searchResultText + " with search type : " + SearchType
					+ " in auto-complete IPS_External");
		} catch (Exception e) {
			log.error("Not able click on search result with search type in auto-complete IPS_External");
			Assert.fail("Not able click on search result with search type in auto-complete IPS_External");
		}

	}
	
	@FindBy(xpath = "(//table/tbody/tr[1]/th[1]/span/a)[1]")
	public WebElement quoteNotable;
	
	@FindBy(xpath = "(//table/tbody/tr[1]/th[1]/span/a)[1]")
	public List<WebElement> quoteNotablePresence;
	
	public void clickQuoteSearched()
	{
		seleniumObj.waitForSeconds(8);
		seleniumObj.getDriver().navigate().refresh();
		sfcommonObj.waitTillLightningPageLoadComplete();
		sfcommonObj.waitTillAllXHRCallsComplete();
		seleniumObj.waitForSeconds(5);
		for(int i=1;i<=10;i++)
		{
			if(quoteNotablePresence.size()==1)
			{
				seleniumObj.waitForElement(quoteNotable, 5, 10);
				quoteNotable.click();
				break;
			}
			else
			{
				seleniumObj.getDriver().navigate().refresh();
				sfcommonObj.waitTillLightningPageLoadComplete();
				sfcommonObj.waitTillAllXHRCallsComplete();
				continue;
			}
		}
		
	}
	
	@FindBy(xpath = "//button[text()='Log In']")
	public WebElement loginButton;
	
	@FindBy(xpath = "//button[text()='Log In']")
	public List<WebElement> loginButtonPresence;
	
	public void clickLoginBtn()
	{
		if(loginButtonPresence.size()==1)
		{
			seleniumObj.waitForElement(loginButton, 5, 5);
			loginButton.click();
			log.info("Clicked on login button");
			System.out.println("Clicked on login button");
		}
		else
		{
			log.info("Successfully logged in");
		}
	}

}