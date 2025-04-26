package com.noob.base.strategy.mixed;

/**
 * 模拟前端请求测试
 */
public class ModForeEnd {

    // 公钥定义(rsa 公钥)
    public static String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArkjEy+RmX0U/ABg55oU45sxx0jzvRQC2\n" +
            "roc+WkxKaL1++vfvAKls72+x8LbAlvzoPyTsJOQrbDrjYdDo4Y0Uc0KZ8LFHrc/GVNapchFvLt+O\n" +
            "pTP2sO4N7T+MET57HxYMxEmeshQ6b23w3hzJ/MU0mpiTGOvgH99cjUfhBp/J8pFW5K3z+g9SgghR\n" +
            "uWmxvo2ehYOpgy75WfMF71sJRSv3Fj1k8rYbu86FFUNcqYDw/wySsel/esFp6Tyvu/+54gwJvcsz\n" +
            "n2qk+J9NTPrrw1D0AcyBJlrQ3KG3AmRz+ZXjXl1Eo6ntBzdZZq8nJ9x2iIpyCscIclxUxBv/pnep\n" +
            "EAehjwIDAQAB";

    // 私钥定义(rsa 私钥)
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

    /**
     * 模拟前端生成加密请求数据（AES密钥 + IV + 加密数据）
     */
    public static EncryptedDataRequest buildEncryptedRequest(String plaintext, String rsaPublicKeyPEM) throws Exception {
        // 1. 生成随机 AES 密钥和 IV
        String aesKeyBase64 = AESUtil.generateAESKey();
        String ivBase64 = AESUtil.generateIV();

        // 2. AES 加密业务数据
        String encryptedData = AESUtil.encrypt(plaintext, aesKeyBase64, ivBase64);

        // 3. RSA 加密 AES 密钥（需替换为实际 RSA 加密逻辑）
        String encryptedAesKey = RSAUtil.encrypt(aesKeyBase64, rsaPublicKeyPEM);

        // 4. 构造请求对象
        EncryptedDataRequest request = new EncryptedDataRequest();
        request.setEncryptedKey(encryptedAesKey);
        request.setEncryptedData(encryptedData);
        request.setIv(ivBase64);
        return request;
    }


    public static void main(String[] args) throws Exception {
        // 模拟生成加密请求
        EncryptedDataRequest encryptedDataRequest = buildEncryptedRequest("123456", RSA_PUBLIC_KEY);
        System.out.println(encryptedDataRequest.getEncryptedKey());
        System.out.println(encryptedDataRequest.getEncryptedData());
        System.out.println(encryptedDataRequest.getIv());

        // 解密处理
        String data = AESUtil.decryptData(encryptedDataRequest, RSA_PRIVATE_KEY);
        System.out.println(data);


        for(int i=0;i<10;i++){
            System.out.println(AESUtil.generateIV());

        }
    }


}
