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
public class InternalCustomerUIData {

	public Object _id;
	public String role;
	public String testCaseID;
//	public String view1;
//	public String view2;
//	public String view3;

	@Getter
	@Setter
	public class InternalCustomerUIDataDetails {
		public String environment;
		public ArrayList<Object> menu;
		public String campaign;
		public String program;
		public String budget;
		public String accountName;
	}
}
