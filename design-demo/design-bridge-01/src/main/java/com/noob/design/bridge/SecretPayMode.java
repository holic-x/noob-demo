package com.noob.design.bridge;

/**
 * 风控模式：密码
 */
public class SecretPayMode implements IPayMode {
    @Override
    public void security(String uid) {
        System.out.println("密码验证");
    }
}
