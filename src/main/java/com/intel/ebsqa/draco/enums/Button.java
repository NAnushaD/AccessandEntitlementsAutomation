package com.intel.ebsqa.draco.enums;

public enum Button {
	New("New"),
	Next("Next"),
	CreateContact("Create Contact"),
	LoadEntitlements("Load Entitlements"),
	SaveEntitlements("Save Entitlements"),
	;
	private final String displayName;
	 
	Button(final String display)
    {
        this.displayName = display;
    }

    @Override public String toString()
    {
        return this.displayName;
    }

}
