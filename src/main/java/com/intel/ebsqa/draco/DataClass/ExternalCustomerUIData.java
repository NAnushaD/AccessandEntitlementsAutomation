/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.DataClass;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author gmathavx
 * @Since 14-Sep-2018
 */
@Getter
@Setter
public class ExternalCustomerUIData {

	public Object _id;
	public String role;
	public String testCaseID;

	@Getter
	@Setter
	public class ExternalCustomerUIDataDetails {
		public String environment;
		public String menu;
		public ArrayList<Object> subMenu;
		public String account;
		public String users;
		public String errorMessage;
		public String accountName;
		public String channelProgram;
		public String parentFundAccount;
		public String parentAccount;
		public String accountRecordType;
		public String accountOwner;
		public String genericErrorMessage;
		public String genericListErrorMessage;
		public String mandatoryErrorMessage;
		public String budgetName;
	}
}
