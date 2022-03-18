package com.noob.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author：holic-x
 * @date: 2021/3/18
 * @Copyright： 无所事事是薄弱意志的避难所
 */
@RestController
public class MailController {

    /**
     * 发送邮件
     */
    @RequestMapping("/sendMail")
    public void sendMail() {
        String emailMsg = "测试发送邮件";
        try {
            // 发送普通文本邮件
            MailUtil.sendMail("xxx@qq.com", "邮件发送测试", "<a href='https://www.baidu.com' >点击一下</a>");
            // 发送带附件的邮件(指定附件路径)
            MailUtil.sendAttachmentMail("xxx@qq.com", "邮件发送测试", "<a href='https://www.baidu.com' >点击一下</a>", "E:\\temp\\1.png");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}