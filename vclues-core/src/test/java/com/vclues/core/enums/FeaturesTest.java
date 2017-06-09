package com.vclues.core.enums;

import org.junit.Assert;
import org.junit.Test;

import com.vclues.core.enums.Features;
import com.vclues.core.utils.BitOperations;

public class FeaturesTest {

	@Test
	public void testEnableAll() {
		Integer permission = 0;

		Assert.assertTrue(BitOperations.isTrue(permission, Features.NONE.getBit()));

		permission = Features.enableAllFeatures();
		
		for(Features feature : Features.values()) {
			Assert.assertTrue(BitOperations.isTrue(permission, feature.getBit()));
		}
		
		System.out.println(permission);
	}
	
	@Test
	public void testEnableOne() {
		Integer permission = 0;
		
		Assert.assertFalse(BitOperations.isTrue(permission, Features.ADD_ACCOUNT.getBit()));
		
		permission = BitOperations.enable(permission, Features.ADD_ACCOUNT.getBit());
		System.out.println("permission " + permission);

		Assert.assertTrue(BitOperations.isTrue(permission, Features.ADD_ACCOUNT.getBit()));
		
		permission = 0;
		permission = Features.READ_ACCOUNT.enabled(0);
		permission = Features.READ_DISPUTES.enabled(permission);
		permission = Features.READ_DESCRIPTOR.enabled(permission);
		System.out.println(permission);
	}
	
}
