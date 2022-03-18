package com.noob.demo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @description: 邮件发送工具类
 * @author：holic-x
 * @date: 2021/3/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */
@Component
public class MailUtil {

    private static JavaMailSenderImpl mailSender ;

    static {
        mailSender = createMailSender();
    }

    /**
     * 装载邮件发送器
     * @return 配置好的邮件发送器
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(MailConfig.HOST);
        sender.setPort(MailConfig.PORT);
        sender.setUsername(MailConfig.USERNAME);
        sender.setPassword(MailConfig.PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", MailConfig.TIMEOUT);
        p.setProperty("mail.smtp.auth", "false");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     * @param to      接受人
     * @param subject 主题
     * @param html    发送内容
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码以避免乱码问题
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        // 设定发送者名称
        String personal = "noob";
        messageHelper.setFrom(MailConfig.MAIL_FROM, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带附件的邮件
     *
     * @param to      接受人
     * @param subject 主题
     * @param html    发送内容
     * @param filePath  附件路径
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendAttachmentMail(String to, String subject, String html, String filePath) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        // 设定发送者名称
        String personal = "noob";
        messageHelper.setFrom(MailConfig.MAIL_FROM, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        FileSystemResource file=new FileSystemResource(new File(filePath));
        String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
        messageHelper.addAttachment(fileName,file);
        mailSender.send(mimeMessage);
    }

}
