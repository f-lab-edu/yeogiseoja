package com.flab.yeogiseoja.domain.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Inheritance
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "notification_type")
public abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String from;
    private String to;
    private String message;
    @CreationTimestamp
    private LocalDateTime sendAt;

    public Notification(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}
