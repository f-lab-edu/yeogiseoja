package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

import java.util.List;

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

    private String checkNumberOfDigits(String businessLicenseNumber) {
        businessLicenseNumber = businessLicenseNumber.replaceAll("[^0-9]", "");
        Assert.isTrue(businessLicenseNumber.length() == 10, ErrorCode.BUSINESS_LICENSE_NUMBER_INCLUDE_STRING.getErrorMsg());
        return businessLicenseNumber;
    }

    private void checkBusinessLicenseNumberFormat(String businessLicenseNumber) {
        String[] businessLicenseNumberStringToArray = businessLicenseNumber.split("");
        int calculatorBusinessLicenseNumberFormat = calculatorBusinessLicenseNumberFormat(businessLicenseNumberStringToArray);
        int checkFormat = (10 - calculatorBusinessLicenseNumberFormat % 10) % 10;
        Assert.isTrue( Integer.parseInt(businessLicenseNumberStringToArray[8]) ==checkFormat, ErrorCode.BUSINESS_LICENSE_NUMBER_NOT_FORMAT.getErrorMsg());
    }

    private int calculatorBusinessLicenseNumberFormat(String[] businessLicenseNumberStringToArray) {
        final List<Integer> businessLicenseNumberAuthKey = List.of(1, 3, 7, 1, 3, 7, 1, 3, 5);
        int businessLicenseNumberDigitsSum = 0;

        final int secondSumAuthNumber = Integer.divideUnsigned(
                Math.multiplyExact(Integer.parseInt(businessLicenseNumberStringToArray[8]), 5),
                10);
        for (int index = 0; index < businessLicenseNumberStringToArray.length; index++) {
            businessLicenseNumberDigitsSum = Integer.sum(
                    businessLicenseNumberDigitsSum, Integer.parseInt(businessLicenseNumberStringToArray[index])
            );
            businessLicenseNumberDigitsSum = Math.multiplyExact(businessLicenseNumberDigitsSum, businessLicenseNumberAuthKey.get(index));
        }

        return Integer.sum(businessLicenseNumberDigitsSum, secondSumAuthNumber);
    }

    @Override
    public boolean isSatisfiedBy() {
        checkBusinessLicenseNumberEmpty(businessLicenseNumber);
        checkOnlyNumeric(businessLicenseNumber);
        String checkNumberOfDigits = checkNumberOfDigits(businessLicenseNumber);
        checkBusinessLicenseNumberFormat(checkNumberOfDigits);
        return true;
    }

}
