package com.flab.yeogiseoja.domain.bank;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank {
    @Id
    @GeneratedValue
    @Column(name = "bank_id")
    private long id;
    private String bankCode;
    private String bankName;

    @Builder
    public Bank(String bankCode, String bankName) {
        Assert.hasLength(bankCode, ErrorCode.BANK_CODE_IS_EMPTY.getErrorMsg());
        this.bankCode = bankCode;
        this.bankName = bankName;
    }
}
