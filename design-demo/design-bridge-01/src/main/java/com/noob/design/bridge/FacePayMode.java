package com.noob.design.bridge;

/**
 * 风控模式：人脸识别
 */
public class FacePayMode implements IPayMode {
    @Override
    public void security(String uid) {
        System.out.println("人脸识别");
    }
}
