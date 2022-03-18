package com.noob.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;

@SpringBootTest
class SpringbootDemoMailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void sendMail() {
        String emailMsg = "测试发送邮件";
        try {
            // 发送普通文本邮件
            MailUtil.sendMail("xxx@qq.com", "邮件发送测试", "<a href='https://www.baidu.com' >点击一下</a>");
            // 发送带附件的邮件(指定附件邮件路径)
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