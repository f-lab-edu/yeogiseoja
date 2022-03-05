package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class Account {
    private String accountBankCode;
    private String accountNumber;
    private String accountHolderName;

    public Account(String accountBankCode, String accountNumber, String accountHolderName) {
        Assert.hasLength(accountNumber, ErrorCode.BANK_CODE_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountNumber, ErrorCode.ACCOUNT_NUMBER_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountHolderName, ErrorCode.ACCOUNT_HOLDER_IS_EMPTY.getErrorMsg());
        this.accountBankCode = accountBankCode;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }
}
