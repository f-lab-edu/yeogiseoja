package com.flab.yeogiseoja.domain.email.model;

import com.flab.yeogiseoja.domain.email.EmailCommand;
import com.flab.yeogiseoja.domain.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailTest {
    @Autowired
    private EmailService emailService;
    @Test
    @DisplayName("이메일 전송 테스트")
    public void email_send_test(){
        EmailCommand.EmailCommandBuilder email= EmailCommand.builder()
                                        .toEmailAddress("dev.bslee@gmail.com");


       System.out.println(email);

        email.emailBody("test");

        System.out.println(email.build());
    }
}