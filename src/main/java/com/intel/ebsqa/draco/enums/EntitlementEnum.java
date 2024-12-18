package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Account Page Enum in the application
 * 
 * @author mohseenx
 * @Since 11-Jan-2019
 */
public class EntitlementEnum {
	
	/**
	 * @Description Enum for Entitlement category
	 * @author nmurugan
	 * @Since Sep 19, 2024
	 */
	@AllArgsConstructor
	@Getter
	public enum EntitlementCategory {
		SUPPORT("Support"),
		PARTNER_PORTAL("Partner Portal"),
		INTEL_PARTNER_INVESTMENT_CENTER("Intel® Partner Investment Center"),
		INTEL_QUOTE_REQUEST("Intel Quote Request"),
		PROGRAMMABLE_SOLUTIONS_GROUP("Programmable Solutions Group");
		private final String description;
	}
	
	/**
	 * @Description Enum for Entitlement name
	 * @author nmurugan
	 * @Since Sep 19, 2024
	 */
	@AllArgsConstructor
	@Getter
	public enum EntitlementName {
		INTEL_PREMIER_SUPPORT("Intel Premier Support"),
		PARTNER_PORTAL_DISTI_DESIGN_REGISTRATION("Partner Portal - Disti Design Registration"),
		CCF_USER_ADMINISTRATOR("CCF User Administrator"),
		CUSTOMER_DRAFTER_ACCESS("Customer Drafter Access"),
		QUOTE_REQUEST_CUSTOMER_DRAFTER_ACCESS("Quote Request Customer Drafter Access"),
		PARTNER_PORTAL("Partner Portal"),
		CCF_USER("CCF User"),
		SALES_SOFT("Sales Soft"),
		PSG_LICENSING_USER("PSG Licensing User");
		private final String description;
	}

	/**
	 * @Description Enum for Entitlement name
	 * @author nmurugan
	 * @Since Sep 19, 2024
	 */
	@AllArgsConstructor
	@Getter
	public enum EntitlementStatus {
		PENDING("Pending"),COMPLETE("Complete");
		private final String description;
	}
	/**
	 * @Description Enum for Permission set 
	 * @author priti
	 * @Since Sep 20, 2024
	 */
	@AllArgsConstructor
	@Getter
	public enum PermissionSet {
		CCP_CCF_USER("CCP CCF USER"),
		IPS_PERMISSION_SET("IPS Permission Set"),
		PSG_External_License_User("PSG External - License User"),
		DDR_Permission_Set("DDR Permission Set");
		
		private final String description;
	}
}