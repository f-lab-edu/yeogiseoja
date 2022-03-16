package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.policy.Policy;
import com.flab.yeogiseoja.domain.policy.PolicyStrategy;
import org.springframework.util.Assert;

public class BusinessLicenseNumberPolicy implements PolicyStrategy {
    private final String businessLicenseNumber;

    public BusinessLicenseNumberPolicy(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    private void checkBusinessLicenseNumberEmpty(String businessLicenseNumber) {
        Assert.hasText(businessLicenseNumber, ErrorCode.BUSINESS_LICENSE_NUMBER_IS_EMPTY.getErrorMsg());
    }

    private void checkOnlyNumeric(String businessLicenseNumber) {
        businessLicenseNumber = businessLicenseNumber.replaceAll("[0-9|-]", "").trim();
        Assert.isTrue(businessLicenseNumber.isEmpty(), ErrorCode.BUSINESS_LICENSE_NUMBER_INCLUDE_STRING.getErrorMsg());
    }

    @Override
    public boolean isSatisfiedBy() {
        checkBusinessLicenseNumberEmpty(businessLicenseNumber);
        checkOnlyNumeric(businessLicenseNumber);
        return true;
    }

}
