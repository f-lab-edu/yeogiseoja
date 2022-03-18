package com.flab.yeogiseoja.domain.owner;

public interface OwnerValidator {
    void validateRegisterOwnerRequest(OwnerCommand.RegisterOwnerRequest owner);

    void validateUpdateOwnerRequest(OwnerCommand.UpdateOwnerRequest owner);

    void validateSearchOwnerRequest(String ownerEmail);

    void validateRemoveOwnerRequest(String ownerEmail);
}
