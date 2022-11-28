package com.intel.ebsqa.draco.enums;



public enum FieldName {
	
	EndCustomer("End Customer"),
	Distributor("Distributor"),
	PriceBook("Price Book"),
	PriceStartDate("Price Start Date"),
	PriceEligibilityDate("Price Eligibility Date"),
	PriceEndDate("Price End Date"),
	ReviewDate("Review Date"),
	
	;
	private final String displayName;
	
	FieldName(final String display)
  {
      this.displayName = display;
  }

  @Override public String toString()
  {
      return this.displayName;
  }
}
