package com.flab.yeogiseoja.domain.policy;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;


public class NamePolicy implements PolicyStrategy {
    private final String name;

    public NamePolicy(String name) {
        this.name = name;
    }

    private void checkNameEmpty(String email) {
        Assert.hasText(email, ErrorCode.NAME_IS_EMPTY.getErrorMsg());
    }

    @Override
    public boolean isSatisfiedBy() {
        checkNameEmpty(name);
        return true;
    }
}
