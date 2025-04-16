package com.noob.secretStrategy;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA 非对称加密策略
 */
public class RSAOAEPUtil {
    // 加密（公钥）
    public static String encrypt(String plaintext, String publicKeyPEM) throws Exception {

        // 数据校验：检查明文长度（2048位密钥的明文长度 ≤ 245字节）（如果加密的明文长度超过 RSA 密钥长度限制（2048位密钥最多加密 245 字节），会抛出 IllegalBlockSizeException）
        if (plaintext.getBytes(StandardCharsets.UTF_8).length > 245) {
            throw new IllegalArgumentException("Plaintext too long for RSA-2048");
        }

        // 1. 加载公钥
        publicKeyPEM = publicKeyPEM.replaceAll("\\s", "");
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);
        PublicKey publicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        // 2. 使用 OAEP 加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding"); // todo：确保前后端统一使用 OAEP
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 解密（私钥）
    public static String decrypt(String encryptedBase64, String privateKeyPEM) throws Exception {

        // 数据校验：确保传入有效的加密数据
        if (encryptedBase64 == null || encryptedBase64.isEmpty()) {
            throw new IllegalArgumentException("Encrypted data is empty");
        }

        // 1. 加载私钥
        privateKeyPEM = privateKeyPEM.replaceAll("\\s", "");
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PrivateKey privateKey = KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        // 2. 使用 OAEP 解密
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        // 加密数据
        String businessData = "123456";
        String encryptData = "";

        try {
            encryptData = RSAOAEPUtil.encrypt(businessData, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArkjEy+RmX0U/ABg55oU45sxx0jzvRQC2\n" +
                    "roc+WkxKaL1++vfvAKls72+x8LbAlvzoPyTsJOQrbDrjYdDo4Y0Uc0KZ8LFHrc/GVNapchFvLt+O\n" +
                    "pTP2sO4N7T+MET57HxYMxEmeshQ6b23w3hzJ/MU0mpiTGOvgH99cjUfhBp/J8pFW5K3z+g9SgghR\n" +
                    "uWmxvo2ehYOpgy75WfMF71sJRSv3Fj1k8rYbu86FFUNcqYDw/wySsel/esFp6Tyvu/+54gwJvcsz\n" +
                    "n2qk+J9NTPrrw1D0AcyBJlrQ3KG3AmRz+ZXjXl1Eo6ntBzdZZq8nJ9x2iIpyCscIclxUxBv/pnep\n" +
                    "EAehjwIDAQAB");
        } catch (Exception e) {
            System.out.println("数据加密失败");
        }

        // 解密数据
        String decryptData = "";
        try {
            decryptData = RSAOAEPUtil.decrypt(encryptData, "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuSMTL5GZfRT8AGDnmhTjmzHHS\n" +
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
                    "nml7FG7bP9KEgwHKpxwX5+qBB2av");
        } catch (Exception e) {
            System.out.println("数据解密失败");
        }

        System.out.println("加密数据：" + encryptData);
        System.out.println("解密数据：" + decryptData);

    }
}