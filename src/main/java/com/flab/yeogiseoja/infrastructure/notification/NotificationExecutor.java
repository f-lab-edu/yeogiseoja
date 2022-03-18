package com.flab.yeogiseoja.infrastructure.notification;

import com.flab.yeogiseoja.domain.notification.NotificationCommand;
import com.flab.yeogiseoja.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationExecutor implements NotificationService {
    @Override
    public void sendEmail(NotificationCommand.SendEmailRequest sendEmailRequest) {

    }
}
