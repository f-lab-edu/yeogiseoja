package com.flab.yeogiseoja.domain.notification;

import lombok.*;

import javax.persistence.*;


@Getter
@Builder
public class EmailNotification {
    private final String from = "admin@yeogiseoja.com";
    private final String to;
    private final String subject;
    private final String text;
}
