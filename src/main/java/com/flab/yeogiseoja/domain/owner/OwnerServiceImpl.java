package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.domain.notification.NotificationCommand;
import com.flab.yeogiseoja.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerReader ownerReader;
    private final OwnerStore ownerStore;
    private final OwnerValidator ownerValidator;
    private final NotificationService notificationService;

    @Override
    public OwnerInfo registerOwner(OwnerCommand.RegisterOwnerRequest request) {
        final String ownerEmail = request.getEmail();
        ownerValidator.checkRegisterOwnerRequest(request);

        var initOwner = request.toEntity();
        var authToken = initOwner.generateAuthTokenForAuthentication();
        var authEmailRequest = makeEmailParam(ownerEmail,"회원 가입 인증해주세요",authToken);
        notificationService.sendEmail(authEmailRequest);

        var owner = ownerStore.storeOwner(initOwner);
        return new OwnerInfo(owner);
    }

    @Override
    public OwnerInfo updateOwner(OwnerCommand.UpdateOwnerRequest request) {
        return null;
    }

    @Override
    public OwnerInfo removeOwner(OwnerCommand.RemoveOwnerRequest request) {
        return null;
    }

    private NotificationCommand.SendEmailRequest makeEmailParam(String to, String subject, String text) {
        return NotificationCommand.SendEmailRequest
                .builder()
                .to(to)
                .subject(subject)
                .text(text)
                .build();

    }
}
