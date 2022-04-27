package com.noob.maven;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HelloTest {
	@Test
	public void testHello() {
		Hello hello = new Hello();
		String res = hello.sayHello("张三");
		assertEquals("Hello,张三!", res);
	}
}
