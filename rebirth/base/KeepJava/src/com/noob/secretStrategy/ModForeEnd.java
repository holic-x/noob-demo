package com.noob.secretStrategy;

/**
 * 模拟前端请求测试
 */
public class ModForeEnd {

    // 公钥定义
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArkjEy+RmX0U/ABg55oU45sxx0jzvRQC2\n" +
            "roc+WkxKaL1++vfvAKls72+x8LbAlvzoPyTsJOQrbDrjYdDo4Y0Uc0KZ8LFHrc/GVNapchFvLt+O\n" +
            "pTP2sO4N7T+MET57HxYMxEmeshQ6b23w3hzJ/MU0mpiTGOvgH99cjUfhBp/J8pFW5K3z+g9SgghR\n" +
            "uWmxvo2ehYOpgy75WfMF71sJRSv3Fj1k8rYbu86FFUNcqYDw/wySsel/esFp6Tyvu/+54gwJvcsz\n" +
            "n2qk+J9NTPrrw1D0AcyBJlrQ3KG3AmRz+ZXjXl1Eo6ntBzdZZq8nJ9x2iIpyCscIclxUxBv/pnep\n" +
            "EAehjwIDAQAB";

    public static void main(String[] args) throws Exception {
        // 模拟生成加密请求
        EncryptedDataRequest encryptedDataRequest = AESEncryptUtil.buildEncryptedRequest("123456", publicKey);
        // 解密处理
        String data = AESDecryptUtil.decryptData(encryptedDataRequest);
        System.out.println(data);
    }


}
