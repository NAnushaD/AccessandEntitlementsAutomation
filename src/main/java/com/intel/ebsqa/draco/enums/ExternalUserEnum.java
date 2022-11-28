
/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common Enum in the application
 * 
 * @Author ubijux
 * @Since 25-Jan-2019
 */

public class ExternalUserEnum {
	/**
	 * Enum for storing sub menu names in external portal
	 * 
	 * @author ubijux Since 11-Jan-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ExternalUserSubMenu {
	ACCOUNT_LIST("Account List"), COMARKETING_ACTIVITIES("Co-Marketing Activities"), BUDGET("Budget"), PARTNER_FUND_ALLOCATION("Partner Fund Allocation"), ALL_BUDGETS("All Budgets"), CAMPAIGNS("Campaigns"), CLAIMS("Claims"), CLAIM_REVIEWS("Claim Reviews"), REPORTS("Reports");

		private final String description;
	}

	/**
	 * Enum for storing sub menu names in external portal
	 * 
	 * @author ubijux Since 24-Jan-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ExternalUserMenu {
		MY_PROGRAM("My Program"), HOME("Home"), BRANDING("Branding"), TRAINING("Training"),
		PRODUCT_SUPPORT("Product Support");

		private final String description;
	}
}
