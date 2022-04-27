package com.noob.maven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloFriendTest {
	@Test
	public void testHelloFriend() {
		// 正常引用Hello工程的内容
		HelloFriend hf = new HelloFriend();
		String res = hf.sayHelloFriend("张三");
		assertEquals(res, "Hello,张三!");
	}
}
