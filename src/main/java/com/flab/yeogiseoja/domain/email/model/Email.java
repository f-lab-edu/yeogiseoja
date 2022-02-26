package com.flab.yeogiseoja.domain.email.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;

import lombok.*;
import org.springframework.util.Assert;

@Getter
@ToString
@EqualsAndHashCode
public class Email {
    private final String from = "admin@yeogiseoja.com";
    private final String to;
    private final String title;
    private final String body;

    @Builder
    public Email(String to, String title, String body) {
        Assert.hasLength(to, ErrorCode.EMAIL_IS_EMPTY.getErrorMsg());

        this.to = to;
        this.title = title;
        this.body = body;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Title {
        FIND_PASSWORD_TITLE("임시 비밀번호 안내입니다"),
        EMAIL_AUTHENTICATION_TITLE("본인 인증 ");

        private final String description;
    }
}
