package com.vclues.core.enums;


public enum Frequency {
	HOURLY(10, "HOURLY"), DAILY(5, "DAILY"), WEEKLY(7, "WEEKLY"), MONTHLY(
			2, "MONTHLY"), YEARLY(1, "YEARLY");

	/*
	 * Calendar static values
	 */
	private Integer bit;

	private final String displayName;

	Frequency(Integer bit, String displayName) {
        this.displayName = displayName;
        this.bit = bit;
    }

    public String getDisplayName() {
        return displayName;
    }

	public Integer getBit() {
		return bit;
	}

}
