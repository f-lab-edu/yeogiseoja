package com.flab.yeogiseoja.domain.member.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Embeddable
public class EmailAuthentication {

    @Enumerated(EnumType.STRING)
    private final EmailAuthenticationStatus emailAuthenticationStatus;
    private final LocalDateTime emailAuthenticationAt;
    private final String emailAuthenticationToken;

    private EmailAuthentication(EmailAuthenticationStatus emailAuthenticationStatus, LocalDateTime emailAuthenticationAt, String emailAuthenticationToken) {
        this.emailAuthenticationStatus = emailAuthenticationStatus;
        this.emailAuthenticationAt = emailAuthenticationAt;
        this.emailAuthenticationToken = emailAuthenticationToken;
    }

    public static EmailAuthentication doneAuthentication() {
        return new EmailAuthentication(EmailAuthenticationStatus.Y, LocalDateTime.now(), "");
    }

    public static EmailAuthentication prepareAuthentication() {
        return new EmailAuthentication(EmailAuthenticationStatus.N, null, UUID.randomUUID().toString());
    }

    @Getter
    @RequiredArgsConstructor
    public enum EmailAuthenticationStatus {
        Y("인증완료"), N("미인증");
        private final String description;
    }
}
