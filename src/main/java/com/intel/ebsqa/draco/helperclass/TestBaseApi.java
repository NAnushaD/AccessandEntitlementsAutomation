package com.intel.ebsqa.draco.helperclass;

import java.util.HashMap;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.intel.ebsqa.databaseutils.MySqlWrapper;
import com.intel.ebsqa.draco.library.common.SFCommon;
import com.intel.ebsqa.wrappers.LoggerWrapper;
import com.mongodb.DBCollection;

//@Listeners(customEventListener.class)
public class TestBaseApi {
	final String sConfigFilePath = "configuration/Config_QA.cfg";
	final String sLoggerConfigFilePath = "configuration/log4j.properties";

	public static ConfigHelper configObj = null;
	public static LoggerWrapper log = null;
	public static SFCommon sfcommonObj = null;
	public static HashMap<String, Object> userCredentials = new HashMap<String, Object>();
	public static DBCollection objCampaignAPIDataCollData = null;
	public static MySqlWrapper mySQLObj = null;

	@BeforeSuite
	@Parameters({ "configpath" })
	public void initialize(@Optional(sConfigFilePath) String sCfgPath) {
		PropertyConfigurator.configure(sLoggerConfigFilePath);
		log = new LoggerWrapper(TestBase.class);
		configObj = new ConfigHelper(sCfgPath);
		sfcommonObj = new SFCommon();

	}

	public String sFilePath() {

		return sConfigFilePath;
	}

}
