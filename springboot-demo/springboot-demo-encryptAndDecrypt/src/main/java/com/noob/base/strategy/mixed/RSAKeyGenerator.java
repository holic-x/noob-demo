package com.noob.base.strategy.mixed;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法策略 公钥、私钥 生成器
 */
public class RSAKeyGenerator {

    // 生成 RSA 密钥对
    public static Map<String, String> generatorRsaKeys() throws NoSuchAlgorithmException {
        // 1. 生成 RSA 密钥对（2048位）
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // 2. 输出公钥和私钥（Base64 PEM 格式）
        /*
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded()) +
                "\n-----END PUBLIC KEY-----";
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded()) +
                "\n-----END PRIVATE KEY-----";
         */

        String publicKey = Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        Map<String, String> rsaKeys = new HashMap<>();
        rsaKeys.put("publicKey", publicKey);
        rsaKeys.put("privateKey", privateKey);

        // 返回构造的密钥对
        return rsaKeys;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Map<String, String> rsaKeys = generatorRsaKeys();
        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println("Public Key:\n" + rsaKeys.get("publicKey"));
        System.out.println("-----END PUBLIC KEY-----");

        System.out.println("-----BEGIN PRIVATE KEY-----");
        System.out.println("Private Key:\n" + rsaKeys.get("privateKey"));
        System.out.println("-----END PRIVATE KEY-----");

    }
}
