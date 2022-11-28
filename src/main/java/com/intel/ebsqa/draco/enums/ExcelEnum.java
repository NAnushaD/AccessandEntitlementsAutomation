
/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common Enum in the application
 * 
 * @Author gmathavx
 * @Since 16-Nov-2018
 */

public class ExcelEnum {

	/**
	 * Enum for Global Navigator
	 * 
	 * @author gmathavx Since 16-Nov-2018
	 * @ModifiedAuthor mohseenx Since 26-01-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum GlobalNavigator {
		BUDGETS("Budgets"), CAMPAIGNS("Campaigns"), COMARKETING_ACTIVITIES("Co-Marketing Activities"), CLAIMS(
				"Claims"), CASES("Cases"), HOME("Home"), ACCOUNT_USERS("Account Users"), ACCOUNTS(
						"Accounts"), APPROVAL_REQUESTS(
								"Approval Requests"), ACCOUNT_TRANSFER("Account Transfer"), BANKS("Banks"), CHATTER(
										"Chatter"), DASHBOARDS("Dashboards"), FILES(
												"Files"), FORECASTS("Forecasts"), GROUPS("Groups"), NOTES("Notes"),

		PARTNER_FUND_ALLOCATIONS("Partner Fund Allocations"), TASKS("Tasks"), REPORTS("Reports"), CCF_CAMPAIGN_NAME(
				"CCF_Campain_Name_"), DCF_CAMPAIGN_NAME("DCF_Campain_Name_"), CCF("campaignCreationCCF"), DCF(
						"campaignCreationCCF"), CAMPAIGNNAME(
								"Test_Auto_Campaign_"), CLAIM_REVIEWS("Claim Reviews"), REJECT("Reject");

		private final String description;
	}

	/**
	 * @Description Enum for Excel Record type
	 * @author manish9x Since 23-Nov-2022
	 */
	@AllArgsConstructor
	@Getter
	public enum ExcelSheetNames {
		LOGIN_CREDENTIALS("LoginCredentials"),INTERNAL_USER_DATA("InternalUserData"), EXTERNAL_USER_DATA("ExternalUserData"),
		AdminUserData_QA("AdminUserDataQA"),AdminUserData_DEVINT("AdminUserDataDEVINT");

		private final String description;
	}

}
