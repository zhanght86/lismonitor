package com.sinosoft.service;

import com.sinosoft.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private MailSender mailSender;

    private SimpleMailMessage mailMessage;

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public void sendEmail(String text) {
//        LOGGER.info("<<<<<<<<开始发送邮件>>>>>>>>>" + text);
        mailMessage.setSubject("二狗子你好蠢");
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }

    public static void main(String[] args) {
        MailSender mailSender = SpringUtils.getBean("mailSender");
        SimpleMailMessage mailMessage = SpringUtils.getBean("mailMessage");
        mailMessage.setSubject("监控邮件");
        mailMessage.setText("123");
        mailMessage.setTo("jhscheer@qq.com");
        mailSender.send(mailMessage);
    }
}
