package com.flab.yeogiseoja.domain.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OwnerCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOwnerRequest {
        private final String email;
        private final String businessLicenseNumber;
        private final String name;
        private final String password;
        private final String phoneNumber;
        private final String depositBankCode;
        private final String depositAccountNumber;
        private final String depositAccountHolderName;
        private final String settledBankCode;
        private final String settledAccountNumber;
        private final String settledAccountHolderName;

        public Owner toEntity() {
            // TODO - add verify logic

            Account depositAccount = new Account(
                    depositBankCode,
                    depositAccountNumber,
                    depositAccountHolderName
            );
            Account settledAccount = new Account(
                    depositBankCode,
                    settledAccountNumber,
                    settledAccountHolderName
            );
            return Owner.builder()
                    .name(name)
                    .businessLicenseNumber(businessLicenseNumber)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .depositAccountInfo(depositAccount)
                    .settledAccountInfo(settledAccount)
                    .email(email)
                    .build();
        }
    }
}
