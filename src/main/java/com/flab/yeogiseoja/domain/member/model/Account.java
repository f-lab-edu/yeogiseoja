package com.flab.yeogiseoja.domain.member.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Embeddable
public class Account {
    private final String accountBankCode;
    private final String accountNumber;
    private final String accountHolderName;

    public Account(String accountBankCode, String accountNumber, String accountHolderName) {
        Assert.hasLength(accountNumber, ErrorCode.BANK_CODE_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountNumber, ErrorCode.ACCOUNT_NUMBER_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountHolderName, ErrorCode.ACCOUNT_HOLDER_IS_EMPTY.getErrorMsg());
        this.accountBankCode = accountBankCode;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }
}
