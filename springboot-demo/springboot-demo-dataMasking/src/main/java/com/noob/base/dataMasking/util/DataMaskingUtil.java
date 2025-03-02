package com.noob.base.dataMasking.util;

public class DataMaskingUtil {

    // 手机号脱敏
    public static String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 7) {
            return phoneNumber; // 返回原始值
        }
        return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
    }

    // 邮箱脱敏
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email; // 返回原始值
        }
        String[] parts = email.split("@");
        String name = parts[0];
        String domain = parts[1];
        if (name.length() <= 1) {
            return "*@" + domain;
        }
        return name.charAt(0) + "****" + name.charAt(name.length() - 1) + "@" + domain;
    }

    // 身份证号脱敏
    public static String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() < 8) {
            return idCard; // 返回原始值
        }
        return idCard.substring(0, 4) + "********" + idCard.substring(idCard.length() - 4);
    }
}