package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.common.response.exception.CustomInternalServerError;
import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.policy.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

public class OwnerCommand {

    @Getter
    @Builder
    @ToString
    public static class UpdateOwnerRequest {
        private final String email;
        private final String password;
        private final String phoneNumber;
        private final String depositBankCode;
        private final String depositAccountNumber;
        private final String depositAccountHolderName;
        private final String settledBankCode;
        private final String settledAccountNumber;
        private final String settledAccountHolderName;

        public Owner toEntity() {

            if (StringUtils.isEmpty(email)) {
                throw new CustomInternalServerError(ErrorCode.EMAIL_IS_EMPTY);
            }

            Policy policy = new Policy(
                    new PhoneNumberPolicy(phoneNumber),
                    new PasswordPolicy(password)
            );

            policy.executePolicyStrategy();

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
                    .email(email)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .depositAccountInfo(depositAccount)
                    .settledAccountInfo(settledAccount)
                    .build();

        }
    }

    @Builder
    @ToString
    public static class RemoveOwnerRequest {
        private final String email;

        public String getEmail() {
            if (StringUtils.isEmpty(email)) {
                throw new CustomInternalServerError(ErrorCode.EMAIL_IS_EMPTY);
            }
            return email;
        }
    }

    @Builder
    @ToString
    public static class OwnerPasswordChangeRequest {
        private final String email;

        public String getEmail() {
            if (StringUtils.isEmpty(email)) {
                throw new CustomInternalServerError(ErrorCode.EMAIL_IS_EMPTY);
            }
            return email;
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOwnerRequest {
        private final String representationName;
        private final String email;
        private final String businessLicenseNumber;
        private final String password;
        private final String phoneNumber;
        private final String depositBankCode;
        private final String depositAccountNumber;
        private final String depositAccountHolderName;
        private final String settledBankCode;
        private final String settledAccountNumber;
        private final String settledAccountHolderName;

        public Owner toEntity() {
            Policy policy = new Policy(
                    new EmailPolicy(email),
                    new PhoneNumberPolicy(phoneNumber),
                    new PasswordPolicy(password),
                    new NamePolicy(representationName),
                    new BusinessLicenseNumberPolicy(businessLicenseNumber)
            );

            policy.executePolicyStrategy();
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
                    .representationName(representationName)
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
