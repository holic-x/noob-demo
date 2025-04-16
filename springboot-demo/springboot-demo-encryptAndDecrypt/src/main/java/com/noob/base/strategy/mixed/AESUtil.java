package com.noob.base.strategy.mixed;


import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 模拟前端 AES-GCM 加密（用于测试请求）
 */
public class AESUtil {

    // 填充模式
    private static String AES_TRANSFORMATION = "AES/GCM/NoPadding";

    // Charset 字符集(统一编码集，避免由于编码差异导致的加解密问题)
    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /*
    // rsa 私钥
    private static final String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuSMTL5GZfRT8AGDnmhTjmzHHS\n" +
            "PO9FALauhz5aTEpovX769+8AqWzvb7HwtsCW/Og/JOwk5CtsOuNh0OjhjRRzQpnwsUetz8ZU1qly\n" +
            "EW8u346lM/aw7g3tP4wRPnsfFgzESZ6yFDpvbfDeHMn8xTSamJMY6+Af31yNR+EGn8nykVbkrfP6\n" +
            "D1KCCFG5abG+jZ6Fg6mDLvlZ8wXvWwlFK/cWPWTythu7zoUVQ1ypgPD/DJKx6X96wWnpPK+7/7ni\n" +
            "DAm9yzOfaqT4n01M+uvDUPQBzIEmWtDcobcCZHP5leNeXUSjqe0HN1lmrycn3HaIinIKxwhyXFTE\n" +
            "G/+md6kQB6GPAgMBAAECggEAcajbTu0hJjiOoY0rFX8EYOAryqQalrGkcaWZsGHYlP4f9aQDpjbX\n" +
            "6D67mPRWHSHKhIObzWNQPkEZUQ1hEtdi4tgodKPozx2VhnaIAvADxlndOTfuRey6AGNVAVnyCY/j\n" +
            "u48Xg6NViJ32W6QCxmoFqFygcbxCagbM4QZjZmKG3T1+SHxV0PW6kQHYx3wSL6dTPzmBAlyhsyrx\n" +
            "X6d7CDlGqRTmNa+osdwGc200/uFr9Jn4ANw811B6jcZtnueEzuQWweBfQ4KhdARxEYuxdCWFlhM7\n" +
            "kTQ89hfZZdSBkZQ6ZoE6nZ0OakXrtIg1KIKoiU+DF45DfJGC/vQOG304stYLYQKBgQDtGB0GpY9l\n" +
            "SP8OoAfx7aSa+dzvu7RsYdtc7NCt18yZBi0ffsR/5VGthYRO+SjjjpBQu9qSL/dvcV3Si5GpxwuS\n" +
            "dZ3BGoKqmOe19cI+J7IDdS6jWIeZRd9BQkFVTNU+2cNqHdBAOQGvVpO9iSNoa2XvOzzxNxu/Y9gg\n" +
            "SisH/Tts7QKBgQC8Ln59K8vhAuklhqcl4d0vTZHIxzqzNWYxp82kMSNhmDNBY2zKbbxCWWjlIEPc\n" +
            "uBAUeGqz9hxQTJhVNwMEQWDj7GWBEBh3BAoTLATimxNw5it6KBpixc9UMriH12+1ul3m5H1Wafzk\n" +
            "3fxaEQHufQaJPLxQbZuU6wQvAJzqAPS06wKBgBZpaBz/u21UmB+Wywl4cE3h/pt+v9Ba87R2Akfc\n" +
            "lU+FwPAwOHzqfW0MqPQaI15XVJb9Gu0ksZwMOFU1skA9O3/NtGrTibR8nbKal5DuiapAAneYX8EC\n" +
            "VNdDXw+kIqg9R3aZeN0JRnp/kZmiBAHT3R/fFqSqk+nkk0KCIE0yB9MFAoGBAKaYNqzTsAvEyujI\n" +
            "24MDARCWiICKOCJdHNrK5pf8VIla981kmvRez7KheuaVeN6XKM0mibhGImzIUnfmWspSotdE+duX\n" +
            "h0EeF1k/uBdxGHXznvvnMuzMmC6/NHQ2OpRUqDr3fyqYbCWljHmj4uTiVH38ge5mCr18jdian0/e\n" +
            "hsT/AoGBAM4NueYQzmZMXgtSn7yuBGoqQZVAzQrratIKb5i+5qmZQ3jF/UeA5jCt/YGt0wRR22vS\n" +
            "xvjvIxygg/4PZKyrBe8UzFoEanvJ/SyMIiLGIvN/beTTkUlu1YZDpPzC++FYvLQ2EpPg8Pb/+9lo\n" +
            "nml7FG7bP9KEgwHKpxwX5+qBB2av";
     */

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
     *
     * @param plaintext    明文
     * @param aesKeyBase64 Base64 编码的 AES 密钥
     * @param ivBase64     Base64 编码的 IV
     * @return Base64 编码的加密数据
     */
    public static String encrypt(String plaintext, String aesKeyBase64, String ivBase64) throws Exception {
        // 1. 转换密钥和 IV
        byte[] aesKeyBytes = Base64.getDecoder().decode(aesKeyBase64);
        byte[] ivBytes = Base64.getDecoder().decode(ivBase64);

        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes); // 128位认证标签

        // 2. 加密
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(DEFAULT_CHARSET));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }


    /**
     * 解密数据
     * @param request 请求数据
     * @param RSA_PRIVATE_KEY rsa 私钥
     * @return
     * @throws Exception
     */
    public static String decryptData(EncryptedDataRequest request,String RSA_PRIVATE_KEY) throws Exception {
        // 1. 用 RSA 私钥解密 AES 密钥
        byte[] decryptedAesKeyBytes = RSAUtil.decrypt(request.getEncryptedKey(), RSA_PRIVATE_KEY).getBytes(DEFAULT_CHARSET);
        String aesKeyBase64 = new String(decryptedAesKeyBytes, DEFAULT_CHARSET);
        byte[] aesKeyBytes = Base64.getDecoder().decode(aesKeyBase64);

        // 2. 用 AES-256-GCM 解密业务数据
        byte[] ivBytes = Base64.getDecoder().decode(request.getIv());
        byte[] encryptedDataBytes = Base64.getDecoder().decode(request.getEncryptedData());

        Cipher aesCipher = Cipher.getInstance(AES_TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes); // 128位认证标签
        SecretKeySpec aesKeySpec = new SecretKeySpec(aesKeyBytes, "AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKeySpec, gcmSpec);

        byte[] decryptedBytes = aesCipher.doFinal(encryptedDataBytes);
        return new String(decryptedBytes, DEFAULT_CHARSET);
    }


}

