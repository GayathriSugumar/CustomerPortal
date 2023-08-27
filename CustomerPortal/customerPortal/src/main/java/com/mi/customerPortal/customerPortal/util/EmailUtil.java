package com.mi.customerPortal.customerPortal.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email,String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");
        StringBuffer sb = new StringBuffer();
        sb.append("Hi Welcome to CUSTOMER PORTAL \n"+email+" App password created to sign in to your account.\n");
        sb.append("Dont't share your OTP with others - "+otp);
        mimeMessageHelper.setText(sb.toString());

        javaMailSender.send(mimeMessage);

    }
    public void sendResetPasswordEmail(String email, String resetToken) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Password Reset Alert !");
        String emailContent = "Your password reset request has been received. Please verify it's you.";
        helper.setText(emailContent);
        javaMailSender.send(message);
    }
}


