package com.flab.yeogiseoja.common.annotaion.vaildator;

import com.flab.yeogiseoja.common.annotaion.BusinessLicenseNumber;
import com.flab.yeogiseoja.domain.policy.BusinessLicenseNumberPolicy;
import com.flab.yeogiseoja.domain.policy.PasswordPolicy;
import com.flab.yeogiseoja.domain.policy.Policy;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<BusinessLicenseNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Policy policy = new Policy(
                new PasswordPolicy(value)
        );

        boolean isValid = policy.executePolicyStrategy();
        return isValid;
    }
}
