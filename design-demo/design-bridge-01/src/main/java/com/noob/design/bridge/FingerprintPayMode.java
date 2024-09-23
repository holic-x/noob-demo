package com.noob.design.bridge;

/**
 * 风控模式：指纹验证
 */
public class FingerprintPayMode implements IPayMode {
    @Override
    public void security(String uid) {
        System.out.println("指纹验证");
    }
}
