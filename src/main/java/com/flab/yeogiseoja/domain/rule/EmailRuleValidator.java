package com.flab.yeogiseoja.domain.rule;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRuleValidator extends RuleValidatorTemplate {

    public EmailRuleValidator(String email) {
        super(email);
    }

    @Override
    protected boolean isSatisfiedBy(String email) {
        executeEmailEmptyValidate(email);
        executeEmailFormatValidate(email);
        return true;
    }

    private void executeEmailEmptyValidate(String email){
        Assert.hasText(email, ErrorCode.EMAIL_IS_EMPTY.getErrorMsg());
    }

    private void executeEmailFormatValidate(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException(ErrorCode.NOT_EMAIL_FORMAT.getErrorMsg());
        }
    }

}
