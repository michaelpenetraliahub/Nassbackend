package com.jtb.taxpayerws.utils;

import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

@Component
public class JavaMailNotificationProxy implements EmailNotificationProxy{
    private final JavaMailSender mailSender;

    public JavaMailNotificationProxy(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String from, String alias, String to, String subject, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from, alias);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message, true);

            CompletableFuture.runAsync(() -> mailSender.send(mimeMessageHelper.getMimeMessage())).exceptionally(ex -> {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.UNABLE_TO_SEND_MAIL.getErrorMessage());
            } );
        } catch (MessagingException | UnsupportedEncodingException ex) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.UNABLE_TO_SEND_MAIL.getErrorMessage());
        }
        System.out.println("mail send successfully..");
    }
}
