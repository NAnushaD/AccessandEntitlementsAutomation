/*
 * Copyright (c) 2018 EBS Automation Team. All rights reserved.
 */

package com.intel.ebsqa.draco.helperclass;

import org.openqa.selenium.WebDriver;

import com.salesforce.lpop.framework.context.Application;
import com.salesforce.lpop.framework.context.AppContext;
import com.salesforce.lpop.framework.markers.ApplicationType;
import com.salesforce.lpop.framework.context.BrowserType;
import com.salesforce.lpop.integration.LightningPageObjects;
import com.salesforce.lpop.integration.LightningPageObjectsImpl;
import com.salesforce.lpop.integration.ApplicationUtilities;
import static org.mockito.Mockito.mock;

public class BaseTest {

	private LightningPageObjects lpop;
	protected ApplicationUtilities helpers;
	AppContext type;	
	protected WebDriver driver;
	
	/**
	 * Method to perform integration
	 * @Author: vveeranx
	 * @since 21-Mar-2019
	 * @return
	 */
	protected final LightningPageObjects getIntegration() {
		if (lpop == null) {
			throw new IllegalArgumentException("Integration object is null");
		}
		return lpop;
	}

	/**
	 * mock method that pretends to build WebDriver instance todo - for consumer to
	 * replace mock with real driver
	 *
	 * @return mock
	 */
	public WebDriver getWebDriverMock() {
		return mock(WebDriver.class);
	}

	/**
	 * setup with default browser and timeout
	 *
	 * @param driver
	 * @param type
	 * @see ApplicationType for type values
	 */
	public final void setup(WebDriver driver, ApplicationType type, String baseURL) {
		this.driver = driver;
		lpop = new LightningPageObjectsImpl();
		lpop.setDriver(driver);
		// set base url for navigation
		setIntegration(baseURL, type);
	}

	/**
	 * Method to perform integration
	 * 
	 * @Author mohseenx
	 * @Since 25-Jan-2019
	 * @param baseUrl Used Set base url for navigation Default Timeout for
	 *                integration set up is 10 seconds
	 */
	protected final void setIntegration(String baseUrl, ApplicationType type) {
		lpop.setTimeout(10);
		lpop.setBaseUrl(baseUrl);
		lpop.setApp(type);
		lpop.setBrowserType(BrowserType.GOOGLECHROME);

	}

	/**
	 * Method to get instance of given application
	 * 
	 * @Author mohseenx
	 * @Since 25-Jan-2019
	 * @return Application Getting Instance of Application
	 */
	public final Application getApp() {
		return lpop.getApp();
	}

	public final ApplicationUtilities utilities() {
		return getIntegration().getUtilities();
	}
}
