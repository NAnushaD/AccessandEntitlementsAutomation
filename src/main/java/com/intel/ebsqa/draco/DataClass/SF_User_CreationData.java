package com.intel.ebsqa.draco.DataClass;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Reports Test Data
 * @Author csingamx
 * @Since Nov 6, 2018
 */
@Getter
@Setter
public class SF_User_CreationData {

	public Object _id;
	public String role;
	public String role2;
	public String testCaseID;
	public String internalRole;

	@Getter
	@Setter
	public class SF_User_CreationDetails {

		public String environment;
		public String sfUserName;
		public String sfPassWord;
		public Map<String, String> partnerBudgetNameAndAmout;
		public String viewOption;
		public String usersView;
		public List<String> publicGroups;
		public List<String> permissionSets;
		public String firstName;
		public String lastName;
		public String profile;
		public String userLicense;
		public String email;
		public String aliasName;
		public String userName;
		public String nickName;
		public String checkbox;
		public String userRole;
		public String publicGroupView;
		public String searchSharingInPublicGroup;
		
		public String accountName;
		public String accountCIMID;
		public String accountID;
		public String salutation;
		public String contactRecordType;
	}
}
