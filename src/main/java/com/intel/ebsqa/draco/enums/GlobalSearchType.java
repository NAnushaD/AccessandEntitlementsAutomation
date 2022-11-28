/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author ubijux
 * @Since 18-Dec-2018
 */

public class GlobalSearchType {

	/**
	 * @Description Enum for Global search types
	 * @author ubijux Since 18-Dec-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum GlobalSearchTypes {
	PARTNERFUNDCLAIM("Claim"), ACCOUNT("Account"), COMARKETINGACTIVITY("Co-Marketing Activity"),PARTNERFUNDBUDGET("Budget");

		private final String description;
	}
}
