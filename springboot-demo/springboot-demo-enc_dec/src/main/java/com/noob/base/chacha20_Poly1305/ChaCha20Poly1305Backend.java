package com.noob.base.chacha20_Poly1305;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.Security;

public class ChaCha20Poly1305Backend {

    static {
        Security.addProvider(new BouncyCastleProvider()); // 添加 BouncyCastle 提供者
    }

    // 解密
    public static String decrypt(byte[] encryptedData, byte[] key) throws Exception {
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