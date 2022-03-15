package com.flab.yeogiseoja.domain.notification;

import lombok.Builder;
import lombok.Getter;

public class NotificationCommand {
    @Getter
    @Builder
    public static class SendEmailRequest {
        private final String to;
        private final String text;
        private final String subject;

        public EmailNotification toEntity() {
            return EmailNotification.builder()
                    .to(to)
                    .subject(subject)
                    .text(text)
                    .build();
        }
    }
}
