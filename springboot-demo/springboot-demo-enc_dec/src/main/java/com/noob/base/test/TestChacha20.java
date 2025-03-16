package com.noob.base.test;

import com.noob.base.chacha20_Poly1305.ChaCha20Poly1305Backend;
import com.noob.base.chacha20_Poly1305.ChaCha20Poly1305SystemA;

public class TestChacha20 {

    public static void main(String[] args) throws Exception {
        // 密钥
        byte[] key = new byte[32]; // 256-bit key

        // 明文
        String plaintext = "mysecretpassword";
        byte[] encrypted = ChaCha20Poly1305SystemA.encrypt(plaintext.getBytes(), key);

        // 发送加密数据到系统B
        String encryptedData = new String(encrypted);
        System.out.println("Encrypted Data: " + encryptedData);

        // 数据解密
        String decryptedData = ChaCha20Poly1305Backend.decrypt(encrypted, key);
        System.out.println("解密后数据：" + decryptedData);
    }
}