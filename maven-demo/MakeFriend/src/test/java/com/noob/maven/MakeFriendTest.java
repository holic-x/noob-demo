package com.noob.maven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MakeFriendTest {
	@Test
	public void testMakeFriend() {
		MakeFriend mf = new MakeFriend();
		String res = mf.sayMakeFriend("张三");
		assertEquals(res, "Hello,张三!");
	}
}
