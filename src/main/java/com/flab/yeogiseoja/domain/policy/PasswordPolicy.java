package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;


public class PasswordPolicy implements PolicyStrategy {
    private final String password;

    public PasswordPolicy(String password) {
        this.password = password;
    }

    private void checkPasswordEmpty(String email) {
        Assert.hasText(email, ErrorCode.PASSWORD_IS_EMPTY.getErrorMsg());
    }

    @Override
    public boolean isSatisfiedBy() {
        checkPasswordEmpty(password);
        return true;
    }
}
