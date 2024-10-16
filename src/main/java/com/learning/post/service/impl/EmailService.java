package com.learning.post.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    String subject = "Welcome to Manish's Threads Application";


    public void sendSignUpEmail(String to, String name) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("threadspostapplication@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(this.getBody(name));
        javaMailSender.send(simpleMailMessage);

    }

    private String getBody(String name) {
        return "Dear " + name + ",\n\n" +
                "Thank you for signing up to our service!\n\n" +
                "We are excited to have you onboard.\n" +
                "If you have any questions, feel free to reply to this email.\n\n" +
                "Best regards from Manish Choudhary,\n" +
                "The Threads Application Team";
    }
}
