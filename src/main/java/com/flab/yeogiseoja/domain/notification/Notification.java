package com.flab.yeogiseoja.domain.notification;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "notification_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sender;
    private String receiver;
    @CreationTimestamp
    private LocalDateTime sendAt;
    public Notification(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
