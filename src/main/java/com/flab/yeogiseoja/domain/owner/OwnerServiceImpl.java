package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.domain.notification.NotificationCommand;
import com.flab.yeogiseoja.domain.notification.NotificationService;
import com.flab.yeogiseoja.domain.notification.NotificationSubject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerReader ownerReader;
    private final OwnerStore ownerStore;
    private final OwnerValidator ownerValidator;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public OwnerInfo.OwnerSummaryInfo registerOwner(OwnerCommand.RegisterOwnerRequest request) {
        final String ownerEmail = request.getEmail();
        ownerValidator.validateRegisterOwnerRequest(request);
        var initOwner = request.toEntity();
        var authToken = initOwner.generateAuthTokenForAuthentication();
        var authEmailRequest = makeEmailParam(ownerEmail, NotificationSubject.REQUIRE_AUTH.getSubjectDesc(), authToken);
        var owner = ownerStore.storeOwner(initOwner);
        notificationService.sendEmail(authEmailRequest);

        return new OwnerInfo.OwnerSummaryInfo(owner);
    }

    @Override
    @Transactional
    public OwnerInfo.OwnerSummaryInfo updateOwner(OwnerCommand.UpdateOwnerRequest request) {
        ownerValidator.validateUpdateOwnerRequest(request);
        var initOwner = request.toEntity();
        var owner = ownerStore.storeOwner(initOwner);

        return new OwnerInfo.OwnerSummaryInfo(owner);
    }

    @Override
    @Transactional
    public boolean removeOwner(OwnerCommand.RemoveOwnerRequest request) {
        var ownerEmail = request.getEmail();
        ownerValidator.validateRemoveOwnerRequest(ownerEmail);
        var owner = ownerReader.getByEmail(ownerEmail);
        owner.deleted();

        return true;
    }

    @Override
    public boolean passwordChangeOwner(OwnerCommand.OwnerPasswordChangeRequest request) {
        var ownerEmail = request.getEmail();
        ownerValidator.validateSearchOwnerRequest(ownerEmail);
        var owner = ownerReader.getByEmail(ownerEmail);
        var generatedPassword = owner.generateRandomPassword();
        var changePasswordEmailRequest = makeEmailParam(ownerEmail, NotificationSubject.CHANGE_PASSWORD.getSubjectDesc(), generatedPassword);
        notificationService.sendEmail(changePasswordEmailRequest);

        return true;
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
