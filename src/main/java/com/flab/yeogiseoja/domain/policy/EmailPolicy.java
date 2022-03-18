package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailPolicy implements PolicyStrategy {
    private final String email;

    public EmailPolicy(String email) {
        this.email = email;
    }

    private void checkEmailEmpty(String email) {
        Assert.hasText(email, ErrorCode.EMAIL_IS_EMPTY.getErrorMsg());
    }

    private void checkEmailFormat(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        Assert.isTrue(m.matches(), ErrorCode.NOT_EMAIL_FORMAT.getErrorMsg());
    }

    @Override
    public boolean isSatisfiedBy() {
        checkEmailEmpty(email);
        checkEmailFormat(email);
        return true;
    }
}
