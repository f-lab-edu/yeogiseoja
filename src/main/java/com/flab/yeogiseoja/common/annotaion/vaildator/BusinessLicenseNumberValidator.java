package com.flab.yeogiseoja.common.annotaion.vaildator;

import com.flab.yeogiseoja.common.annotaion.BusinessLicenseNumber;
import com.flab.yeogiseoja.domain.policy.BusinessLicenseNumberPolicy;
import com.flab.yeogiseoja.domain.policy.Policy;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BusinessLicenseNumberValidator implements ConstraintValidator<BusinessLicenseNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Policy policy = new Policy(
                new BusinessLicenseNumberPolicy(value)
        );

        boolean isValid = policy.executePolicyStrategy();
        return isValid;
    }
}
