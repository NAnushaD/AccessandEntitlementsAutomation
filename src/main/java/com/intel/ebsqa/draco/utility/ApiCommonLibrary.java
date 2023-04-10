/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.utility;

import com.intel.ebsqa.draco.helperclass.TestBaseApi;

/**
 * @Description
 * @Author subhranx
 * @Since Sep 12, 2018
 */
public class ApiCommonLibrary extends TestBaseApi {

	/**
	 * @Description
	 * @Author subhranx
	 * @Since Sep 12, 2018
	 * @param apiStatus
	 * @param expectedStatusCode
	 * @return
	 */
	public static boolean validateStatusCode(String apiStatus, String expectedStatusCode) {
		boolean isResult = false;

		try {
			if (apiStatus.contains(expectedStatusCode)) {
				log.info("Pass: Status Code matching.");
				isResult = true;
			} else {
				log.info("Fail: Status Code not matching.");
				log.info("Fail: Api Status Code= " + apiStatus + " and expected Status code= " + expectedStatusCode);
			}
		} catch (Exception e) {
			log.error("Error at ApiCommonLibrary.validateStatusCode, Exception=" + e);
		}
		return isResult;
	}

	/**
	 * @Description
	 * @Author subhranx
	 * @Since Sep 12, 2018
	 * @param apiMessage
	 * @param expectedMessage
	 * @return
	 */
	public static boolean verifyMessage(String apiMessage, String expectedMessage) {
		boolean isResult = false;
		try {
			if (apiMessage.replaceAll("\\s+", "").equals(expectedMessage.replaceAll("\\s+", ""))) {
				log.info("Pass: Response data matching.");
				isResult = true;
			} else {
				log.info("Fail: Response data not matching.");
				log.info("Fail: Api Response message= " + apiMessage + " and expected Response message= "
						+ expectedMessage);
			}
		} catch (Exception e) {
			log.error("Error at ApiCommonLibrary.validateStatusCode, Exception=" + e);
		}
		return isResult;
	}

	/**
	 * 
	 * @Description verifyPartialMessage- function checks apiMessage Contains
	 *              Expected Message
	 * @Author rshikkal
	 * @Since Sep 25, 2018
	 * @param String apiMessage
	 * @param String expectedMessage
	 * @return boolean isResult
	 */
	public static boolean verifyPartialMessage(String apiMessage, String expectedMessage) {
		boolean isResult = false;
		try {
			if (apiMessage.replaceAll("\\s+", "").contains(expectedMessage.replaceAll("\\s+", ""))) {
				log.info("Pass: Response data matching.");
				isResult = true;
			} else {
				log.info("Fail: Response data not matching.");
				log.info("Fail: Api Response message= " + apiMessage + " and expected Response message= "
						+ expectedMessage);
			}
		} catch (Exception e) {
			log.error("Error at ApiCommonLibrary.validateStatusCode, Exception=" + e);
		}
		return isResult;
	}
}
