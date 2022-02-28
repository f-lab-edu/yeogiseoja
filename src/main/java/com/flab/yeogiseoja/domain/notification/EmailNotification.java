package com.flab.yeogiseoja.domain.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@ToString
@Entity
@DiscriminatorValue("EMAIL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailNotification extends Notification {
    private long id;
    private String title;

    public EmailNotification(String from, String to, String message, String title) {
        super(from, to, message);
        this.title = title;
    }
}
