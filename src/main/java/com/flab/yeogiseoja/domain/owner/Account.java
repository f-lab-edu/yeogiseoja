package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Embeddable
@NoArgsConstructor
public class Account {
    private BankInfo accountBankCode;
    private String accountNumber;
    private String accountHolderName;

    public Account(String accountBankCode, String accountNumber, String accountHolderName) {
        Assert.hasLength(accountNumber, ErrorCode.BANK_CODE_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountNumber, ErrorCode.ACCOUNT_NUMBER_IS_EMPTY.getErrorMsg());
        Assert.hasLength(accountHolderName, ErrorCode.ACCOUNT_HOLDER_IS_EMPTY.getErrorMsg());
        this.accountBankCode = BankInfo.of(accountBankCode);
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    @Getter
    public enum BankInfo {
        KB("004", "국민"),
        IBK("003", "기업"),
        NH("010", "농협"),
        KEB("005", "하나"),
        WOORI("020", "우리"),
        SINHAN("021", "신한"),
        SC("023", "SC제일"),
        CITY("027", "한국씨티"),
        JB("037", "전북"),
        BNk("039", "경남"),
        TOSS("092", "토스"),
        KAKAO("090", "카카오");
        private final String bankCode;
        private final String bankName;
        private static final Map<String, String> bankCodeMap = Collections.unmodifiableMap(
                Stream.of(values()).collect(Collectors.toMap(BankInfo::getBankCode, BankInfo::getBankName))
        );

        BankInfo(String bankCode, String bankName) {
            this.bankCode = bankCode;
            this.bankName = bankName;
        }

        public static BankInfo of(final String bankCode) {
            return BankInfo.valueOf(bankCodeMap.get(bankCode));
        }
    }
}
