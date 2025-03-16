package com.noob.base.chacha20_Poly1305;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.Security;

public class ChaCha20Poly1305Example {

    static {
        // 添加 BouncyCastle 提供者
        Security.addProvider(new BouncyCastleProvider());
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

    // 解密
    public static byte[] decrypt(byte[] encryptedData, byte[] key) throws Exception {
        // 提取 Nonce 和密文
        ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
        byte[] nonce = new byte[12];
        byteBuffer.get(nonce);

        byte[] ciphertext = new byte[byteBuffer.remaining()];
        byteBuffer.get(ciphertext);

        // 初始化 Cipher
        Cipher cipher = Cipher.getInstance("ChaCha20-Poly1305", "BC");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

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