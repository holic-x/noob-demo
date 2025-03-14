package com.noob.base.chacha20_Poly1305;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.Security;

public class ChaCha20Poly1305SystemA {

    static {
        Security.addProvider(new BouncyCastleProvider()); // 添加 BouncyCastle 提供者
    }

    // 加密
    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {
        // 生成随机 Nonce（12字节）
        byte[] nonce = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(nonce);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("ChaCha20-Poly1305", "BC");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 加密数据
        byte[] ciphertext = cipher.doFinal(plaintext);

        // 将 Nonce 和密文打包
        ByteBuffer byteBuffer = ByteBuffer.allocate(nonce.length + ciphertext.length);
        byteBuffer.put(nonce);
        byteBuffer.put(ciphertext);
        return byteBuffer.array();
    }

    public static void main(String[] args) throws Exception {
        // 密钥
        byte[] key = new byte[32]; // 256-bit key

        // 明文
        String plaintext = "mysecretpassword";
        byte[] encrypted = encrypt(plaintext.getBytes(), key);

        // 发送加密数据到系统B
        System.out.println("Encrypted Data: " + new String(encrypted));
    }
}