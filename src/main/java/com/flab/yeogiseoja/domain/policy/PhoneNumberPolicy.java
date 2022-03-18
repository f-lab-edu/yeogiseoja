package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneNumberPolicy implements PolicyStrategy {
    private final String phoneNumber;

    public PhoneNumberPolicy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void checkPhoneNumberEmpty(String phoneNumber) {
        Assert.hasText(phoneNumber, ErrorCode.PHONE_NUMBER_IS_EMPTY.getErrorMsg());
    }

    private void checkPhoneNumberFormat(String phoneNumber) {
        String regex = "^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        Assert.isTrue(m.matches(), ErrorCode.NOT_PHONE_FORMAT.getErrorMsg());

    }

    @Override
    public boolean isSatisfiedBy() {
        checkPhoneNumberEmpty(phoneNumber);
        return true;
    }
}
