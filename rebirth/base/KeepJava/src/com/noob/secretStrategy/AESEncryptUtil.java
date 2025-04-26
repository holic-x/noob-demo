package com.noob.secretStrategy;

import com.noob.secretStrategy.EncryptedDataRequest;
import com.noob.secretStrategy.RSAOAEPUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 模拟前端 AES-GCM 加密（用于测试请求）
 */
public class AESEncryptUtil {

    /**
     * 生成随机 AES-256 密钥（Base64 编码）
     */
    public static String generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 生成随机 IV（12字节，Base64 编码）
     */
    public static String generateIV() {
        byte[] iv = new byte[12]; // GCM 推荐 12 字节
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    /**
     * AES-GCM 加密数据
     * @param plaintext 明文
     * @param aesKeyBase64 Base64 编码的 AES 密钥
     * @param ivBase64 Base64 编码的 IV
     * @return Base64 编码的加密数据
     */
    public static String encrypt(String plaintext, String aesKeyBase64, String ivBase64) throws Exception {
        // 1. 转换密钥和 IV
        byte[] aesKeyBytes = Base64.getDecoder().decode(aesKeyBase64);
        byte[] ivBytes = Base64.getDecoder().decode(ivBase64);

        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes); // 128位认证标签

        // 2. 加密
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 模拟前端生成加密请求数据（AES密钥 + IV + 加密数据）
     */
    public static EncryptedDataRequest buildEncryptedRequest(String plaintext, String rsaPublicKeyPEM) throws Exception {
        // 1. 生成随机 AES 密钥和 IV
        String aesKeyBase64 = generateAESKey();
        String ivBase64 = generateIV();

        // 2. AES 加密业务数据
        String encryptedData = encrypt(plaintext, aesKeyBase64, ivBase64);

        // 3. RSA 加密 AES 密钥（需替换为实际 RSA 加密逻辑）// todo RSA 加密处理
        String encryptedAesKey = RSAOAEPUtil.encrypt(aesKeyBase64, rsaPublicKeyPEM);

        // 4. 构造请求对象
        EncryptedDataRequest request = new EncryptedDataRequest();
        request.setEncryptedKey(encryptedAesKey);
        request.setEncryptedData(encryptedData);
        request.setIv(ivBase64);
        return request;
    }
}

