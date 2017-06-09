package com.vclues.core.utils;

import java.util.UUID;

public class UUIDGenerator {
	public static String nextUUID() {
		return UUID.randomUUID().toString();
	}
}
