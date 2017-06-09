package com.vclues.core.enums;

import com.vclues.core.utils.BitOperations;

public enum Features {
	NONE(0), ALL_DISPUTES(1), ADD_DISPUTES(2), READ_DISPUTES(4), ALL_DESCRIPTOR(
			8), ADD_DESCRIPTOR(16), READ_DESCRIPTOR(32), ALL_ACCOUNT(64), ADD_ACCOUNT(
			128), READ_ACCOUNT(256), ALL_PERMISSION(512), SEND_REFUND(1024), REJECT_REFUND(
			2048);

	// READ_PERMISSION(4096), ADD_PERMISSION(8192), ADD_GROUP(16384),
	// EDIT_GROUP(
	// 32768), DELETE_GROUP(65536), READ_GROUP(131072), READ_PARENT_ACCOUNT(
	// 262144), EDIT_PARENT_ACCOUNT(524288), DELETE_PARENT_ACCOUNT(1048576),
	// SEND_REFUND(
	// 2097152), REJECT_REFUND(4194304);

	private Integer bit;

	private Features(Integer bit) {
		this.bit = bit;
	}

	public Integer getBit() {
		return bit;
	}

	public static Integer enableAllFeatures() {
		Integer permissions = 0;
		for (Features feature : Features.values()) {
			permissions = BitOperations.enable(permissions, feature.getBit());
		}
		return permissions;
	}

	public boolean isEnabled(Integer permissions) {
		return BitOperations.isTrue(permissions, bit);
	}

	public boolean isDisabled(Integer permissions) {
		return !BitOperations.isTrue(permissions, bit);
	}

	public Integer enabled(Integer permissions) {
		return BitOperations.enable(permissions, bit);
	}

	public Integer disabled(Integer permissions) {
		return BitOperations.disable(permissions, bit);
	}
}
