package com.intel.ebsqa.draco.enums;

public enum Tabs {

	Contacts("Contacts"),
	Accounts("Accounts"),
	Home("Home"),
	Membership("Membership"),
	
	;
	private final String displayName;
	
	 Tabs(final String display)
   {
       this.displayName = display;
   }

   @Override public String toString()
   {
       return this.displayName;
   }

	
	
}


