package com.guigu.library.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SecretKey {
	// 此处使用秘钥20000进行处理，与字符串的每一个字符进行异或得到相应加密后的字符串
	// 对密码进行加密处理
	public static String encrypt(String password) {
		char[] array = password.toCharArray();
		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] ^ 20000);
		}
		String key = new String(array);
		return key;
	}

	// 对密码进行解密处理
	public static String decrypt(String password) {
		char[] array = password.toCharArray();
		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] ^ 20000);
		}
		String key = new String(array);
		return key;
	}
}
