package com.vclues.core.enums;

import org.junit.Assert;
import org.junit.Test;

import com.vclues.core.enums.UserType;
import com.vclues.core.utils.BitOperations;

public class FeaturesTest {

	@Test
	public void testEnableAll() {
		Integer permission = 0;

		Assert.assertTrue(BitOperations.isTrue(permission, UserType.NONE.getBit()));

		permission = UserType.enableAllFeatures();
		
		for(UserType feature : UserType.values()) {
			Assert.assertTrue(BitOperations.isTrue(permission, feature.getBit()));
		}
		
		System.out.println(permission);
	}
	
	@Test
	public void testEnableOne() {
		Integer permission = 0;
		
		Assert.assertFalse(BitOperations.isTrue(permission, UserType.ADD_ACCOUNT.getBit()));
		
		permission = BitOperations.enable(permission, UserType.ADD_ACCOUNT.getBit());
		System.out.println("permission " + permission);

		Assert.assertTrue(BitOperations.isTrue(permission, UserType.ADD_ACCOUNT.getBit()));
		
		permission = 0;
		permission = UserType.READ_ACCOUNT.enabled(0);
		permission = UserType.READ_DISPUTES.enabled(permission);
		permission = UserType.READ_DESCRIPTOR.enabled(permission);
		System.out.println(permission);
	}
	
}
