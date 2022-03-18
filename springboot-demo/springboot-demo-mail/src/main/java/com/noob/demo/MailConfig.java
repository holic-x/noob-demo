package com.noob.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * @description: 读取参数配置
 * @author：holic-x
 * @date: 2021/3/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */

@Component
public class MailConfig {

    public static String HOST;

    public static Integer PORT;

    public static String USERNAME;

    public static String PASSWORD;

    public static String TIMEOUT;

    public static String MAIL_FROM;

    @Value("${mailConfig.host}")
    public void setHOST(String HOST) {
        MailConfig.HOST = HOST;
    }

    @Value("${mailConfig.port}")
    public void setPORT(Integer PORT) {
        MailConfig.PORT = PORT;
    }

    @Value("${mailConfig.username}")
    public void setUSERNAME(String USERNAME) {
        MailConfig.USERNAME = USERNAME;
    }

    @Value("${mailConfig.password}")
    public void setPASSWORD(String PASSWORD) {
        MailConfig.PASSWORD = PASSWORD;
    }

    @Value("${mailConfig.timeout}")
    public void setTIMEOUT(String TIMEOUT) {
        MailConfig.TIMEOUT = TIMEOUT;
    }

    @Value("${mailConfig.from}")
    public void setMailFrom(String mailFrom) {
        MAIL_FROM = mailFrom;
    }

}