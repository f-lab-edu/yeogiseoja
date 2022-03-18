package com.flab.yeogiseoja.infrastructure.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.owner.Owner;
import com.flab.yeogiseoja.domain.owner.OwnerCommand;
import com.flab.yeogiseoja.domain.owner.OwnerReader;
import com.flab.yeogiseoja.domain.owner.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerValidatorImpl implements OwnerValidator {
    private final OwnerReader ownerReader;

    @Override
    public void validateRegisterOwnerRequest(OwnerCommand.RegisterOwnerRequest request) {
        checkDuplicateEmail(request.getEmail());
    }

    @Override
    public void validateUpdateOwnerRequest(OwnerCommand.UpdateOwnerRequest request) {
        checkExistingUser(request.getEmail());
    }

    @Override
    public void validateSearchOwnerRequest(String ownerEmail) {
        checkExistingUser(ownerEmail);
        checkIsRemoveOwner(ownerEmail);
    }

    @Override
    public void validateRemoveOwnerRequest(String ownerEmail) {
        checkExistingUser(ownerEmail);
        checkOwnerAlreadyRemove(ownerEmail);
    }

    private Owner.Status getUserStatus(String ownerEmail) {
        var owner = ownerReader.getByEmail(ownerEmail);
        return owner.getStatus();
    }

    private void checkIsRemoveOwner(String ownerEmail) {
        var ownerStatus = getUserStatus(ownerEmail);
        if (ownerStatus.equals(Owner.Status.DELETED)) {
            throw new IllegalArgumentException(ErrorCode.IS_REMOVE_USER.getErrorMsg());
        }
    }

    private void checkOwnerAlreadyRemove(String ownerEmail) {
        var ownerStatus = getUserStatus(ownerEmail);
        if (ownerStatus.equals(Owner.Status.DELETED)) {
            throw new IllegalArgumentException(ErrorCode.ALREADY_REMOVE_USER.getErrorMsg());
        }
    }

    private void checkExistingUser(String ownerEmail) {
        try {
            var owner = ownerReader.getByEmail(ownerEmail);
            owner.getEmail();
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException(ErrorCode.NOT_EXIST_USER.getErrorMsg());
        }
    }

    private void checkDuplicateEmail(String ownerEmail) {
        var owner = ownerReader.findByEmail(ownerEmail);
        if (owner.isPresent()) throw new IllegalArgumentException(ErrorCode.DUPLICATE_EMAIL.getErrorMsg());
    }
}
