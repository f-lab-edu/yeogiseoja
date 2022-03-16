package com.flab.yeogiseoja.infrastructure.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.owner.OwnerCommand;
import com.flab.yeogiseoja.domain.owner.OwnerReader;
import com.flab.yeogiseoja.domain.owner.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerValidatorImpl implements OwnerValidator {
    private final OwnerReader ownerReader;

    @Override
    public void checkRegisterOwnerRequest(OwnerCommand.RegisterOwnerRequest request) {
        checkDuplicateEmail(request.getEmail());
        checkBusinessLicenseNumber(request.getBusinessLicenseNumber());
    }

    private void checkBusinessLicenseNumber(String businessLicenseNumber) {
        Assert.hasText(businessLicenseNumber, ErrorCode.BUSINESS_LICENSE_NUMBER_IS_EMPTY.getErrorMsg());
    }

    private void checkDuplicateEmail(String ownerEmail) {
        var optOwner = ownerReader.findByEmail(ownerEmail);
        if (optOwner.isPresent()) throw new IllegalArgumentException(ErrorCode.DUPLICATE_EMAIL.getErrorMsg());
    }
}
