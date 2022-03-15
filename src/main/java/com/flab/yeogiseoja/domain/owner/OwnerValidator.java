package com.flab.yeogiseoja.domain.owner;

public interface OwnerValidator {
    void checkRegisterOwnerRequest(OwnerCommand.RegisterOwnerRequest ownerEmail);
}
