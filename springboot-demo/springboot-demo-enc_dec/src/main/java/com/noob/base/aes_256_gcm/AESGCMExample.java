package com.noob.base.aes_256_gcm;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

/**
 * 系统交互AES加解密处理
 */
public class AESGCMExample {
    // AES-GCM 参数
    private static final int GCM_TAG_LENGTH = 128; // 128-bit 认证标签
    private static final int GCM_NONCE_LENGTH = 12; // 12-byte Nonce

    // 加密
    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {
        // 生成随机 Nonce
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(nonce);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);

        // 加密数据
        byte[] ciphertext = cipher.doFinal(plaintext);

        // 将 Nonce 和密文打包
        ByteBuffer byteBuffer = ByteBuffer.allocate(nonce.length + ciphertext.length);
        byteBuffer.put(nonce);
        byteBuffer.put(ciphertext);
        return byteBuffer.array();
    }

    // 解密
    public static byte[] decrypt(byte[] encryptedData, byte[] key) throws Exception {
        // 提取 Nonce 和密文
        ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        byteBuffer.get(nonce);

        byte[] ciphertext = new byte[byteBuffer.remaining()];
        byteBuffer.get(ciphertext);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);

        // 解密数据
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws Exception {
        // 密钥
        byte[] key = new byte[32]; // 256-bit key
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);

        // 明文
        String plaintext = "mysecretpassword";
        byte[] encrypted = encrypt(plaintext.getBytes(), key);
        System.out.println("Encrypted Data: " + new String(encrypted));

        // 解密
        byte[] decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + new String(decrypted));
    }
}