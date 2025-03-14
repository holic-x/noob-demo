package com.noob.base.test;

import com.noob.base.aes_256_gcm.AESGCMBackend;
import com.noob.base.aes_256_gcm.AESGCMSystemA;

public class TestAES {


    public static void main(String[] args) throws Exception {
        // 密钥
        byte[] key = new byte[32]; // 256-bit key

        // 明文
        String plaintext = "mysecretpassword";
        byte[] encrypted = AESGCMSystemA.encrypt(plaintext.getBytes(), key); // 调用加密算法

        // 发送加密数据到系统B
        String encryptedData = new String(encrypted);
        System.out.println("Encrypted Data: " + encryptedData);

        // 数据解密
        String decryptedData = AESGCMBackend.decrypt(encrypted, key);
        System.out.println("解密后数据：" + decryptedData);
    }
}