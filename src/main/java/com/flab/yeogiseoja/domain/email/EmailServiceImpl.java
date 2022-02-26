package com.flab.yeogiseoja.domain.email;


import com.flab.yeogiseoja.common.response.exception.CustomInternalServerError;
import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.email.model.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    // DIP 가 깨진 것... AWS SES (Simple Email Service)
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailCommand emailSendRequest) {
        try {
            Email email = emailSendRequest.toValueObject();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo(email.getTo());
            messageHelper.setFrom(email.getFrom());
            messageHelper.setSubject(email.getTitle());
            messageHelper.setText(email.getBody());
            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            throw new CustomInternalServerError(e.getMessage(), ErrorCode.EMAIL_SEND_FAIL);
        }
    }
}
