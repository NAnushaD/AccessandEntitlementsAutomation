package com.intel.ebsqa.draco.enums;

public enum Button {
	New("New"),
	Next("Next"),
	
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
