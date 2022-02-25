package com.flab.yeogiseoja.domain.member;

import com.flab.yeogiseoja.domain.member.model.Account;
import com.flab.yeogiseoja.domain.member.model.Customer;
import com.flab.yeogiseoja.domain.member.model.Owner;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MemberCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterCustomerRequest {
        private final String email;
        private final String nickName;
        private final String userName;
        private final String password;
        private final String phoneNumber;

        public Customer toEntity() {
            return new Customer(email, nickName, userName, password, phoneNumber);
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOwnerRequest {
        private final String email;
        private final String businessLicenseNumber;
        private final String userName;
        private final String password;
        private final String phoneNumber;
        private final String depositBankCode;
        private final String depositAccountNumber;
        private final String depositAccountHolderName;
        private final String settledBankCode;
        private final String settledAccountNumber;
        private final String settledAccountHolderName;
        public Owner toEntity() {
            Account depositAccount = new Account(depositBankCode,depositAccountNumber, depositAccountHolderName);
            Account settledAccount = new Account(depositBankCode,settledAccountNumber, settledAccountHolderName);

            return new Owner(email, businessLicenseNumber, userName, password, phoneNumber,depositAccount, settledAccount);
        }
    }

}
