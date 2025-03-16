package com.noob.base.aes_256_gcm;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;

public class AESGCMBackend {

    private static final int GCM_TAG_LENGTH = 128; // 128-bit authentication tag
    private static final int GCM_NONCE_LENGTH = 12; // 12-byte Nonce

    // 解密
    public static String decrypt(byte[] encryptedData, byte[] key) throws Exception {
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
        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        // 密钥（与前端共享）
        byte[] key = new byte[32]; // 256-bit key

        // 假设从前端接收到的加密数据
        byte[] encryptedData = "xxxxxxxxxxxxxxx".getBytes(); // 替换为实际数据

        // 解密
        String decrypted = decrypt(encryptedData, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}