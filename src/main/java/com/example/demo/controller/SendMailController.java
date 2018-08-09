package com.example.demo.controller;

import com.example.demo.email.Email;
import com.example.demo.email.MailEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
public class SendMailController {

    @Resource
    private Email email;

    @RequestMapping("/send_mail")
    @ResponseBody
    public String send_mail(MailEntity entity){
        try {
            email.sendEmail(entity);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @RequestMapping({"/send_mail.html","/"})
    public String send_mail_page(){
        return "send_mail";
    }
}
