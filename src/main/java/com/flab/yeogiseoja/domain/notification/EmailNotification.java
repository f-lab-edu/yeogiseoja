package com.flab.yeogiseoja.domain.notification;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("EMAIL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailNotification extends Notification {
    private String subject;
    private String text;
    public EmailNotification(String receiver, String subject, String text) {
        super("admin@yeogiseoja.com", subject);
        this.subject = subject;
        this.text = text;
    }
}
