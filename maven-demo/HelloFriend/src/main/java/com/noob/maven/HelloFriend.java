package com.noob.maven;

public class HelloFriend {
	public String sayHelloFriend(String name) {
		// 正常引用Hello工程的内容
		Hello hello = new Hello();
		return hello.sayHello(name);
	}
}
