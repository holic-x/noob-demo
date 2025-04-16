package com.noob.secretStrategy;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * RSA 公钥、私钥 生成器
 */
public class RSAKeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 1. 生成 RSA 密钥对（2048位）
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // 2. 输出公钥和私钥（Base64 PEM 格式）
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded()) +
                "\n-----END PUBLIC KEY-----";
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded()) +
                "\n-----END PRIVATE KEY-----";

        System.out.println("Public Key:\n" + publicKey);
        System.out.println("Private Key:\n" + privateKey);
    }
}
