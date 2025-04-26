package com.noob.secretStrategy;

import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * AES 解密工具
 */
public class AESDecryptUtil {


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

    private static byte[] decryptRSA(String encryptedBase64) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
        // RSA/ECB/OAEPWithSHA-256AndMGF1Padding、RSA/ECB/PKCS1Padding
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding"); // todo:需确保前后端的Cipher实例填充格式一致

        // 加载 RSA 私钥
        String privateKeyPEM = RSA_PRIVATE_KEY.replaceAll("\\s", "");
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return rsaCipher.doFinal(encryptedBytes);
    }

    // 随机生成IV
    public static String randomIv(){
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        String ivBase64 = Base64.getEncoder().encodeToString(iv);
        return ivBase64;
    }


    /**
     * 解密数据
     * @param request
     * @return
     * @throws Exception
     */
    public static String decryptData(EncryptedDataRequest request) throws Exception {
        // 1. 用 RSA 私钥解密 AES 密钥
        byte[] decryptedAesKeyBytes = decryptRSA(request.getEncryptedKey());
        String aesKeyBase64 = new String(decryptedAesKeyBytes, StandardCharsets.UTF_8);
        byte[] aesKeyBytes = Base64.getDecoder().decode(aesKeyBase64);

        // 2. 用 AES-256-GCM 解密业务数据
        byte[] ivBytes = Base64.getDecoder().decode(request.getIv());
        byte[] encryptedDataBytes = Base64.getDecoder().decode(request.getEncryptedData());

        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, ivBytes); // 128位认证标签
        SecretKeySpec aesKeySpec = new SecretKeySpec(aesKeyBytes, "AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKeySpec, gcmSpec);

        byte[] decryptedBytes = aesCipher.doFinal(encryptedDataBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}


