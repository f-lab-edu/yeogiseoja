package com.flab.yeogiseoja.domain.owner;

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
    private final EmailSender emailSender;

    @Override
    public OwnerInfo registerOwner(OwnerCommand.RegisterOwnerRequest request) {
        final String ownerEmail = request.getEmail();
        ownerValidator.checkDuplicateEmail(request);

        var initOwner = request.toEntity();
        var authToken = initOwner.generateAuthTokenForAuthentication();

        emailSender.sendEmail(ownerEmail, "회원 가입 인증해주세요", authToken);

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
}
