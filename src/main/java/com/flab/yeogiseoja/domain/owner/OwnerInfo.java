package com.flab.yeogiseoja.domain.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class OwnerInfo {
    @Getter
    @ToString
    public static class OwnerDetailInfo {
        private String businessLicenseNumber;
        private String representationName;
        private String phoneNumber;
        private final String depositBankCode;
        private final String depositAccountNumber;
        private final String depositAccountHolderName;
        private final String settledBankCode;
        private final String settledAccountNumber;
        private final String settledAccountHolderName;

        public OwnerDetailInfo(Owner owner) {
            var depositBankInfo = owner.getDepositAccountInfo();
            var settleBankInfo = owner.getSettledAccountInfo();
            this.businessLicenseNumber = owner.getBusinessLicenseNumber();
            this.representationName = owner.getRepresentationName();
            this.phoneNumber = owner.getPhoneNumber();
            this.depositBankCode = depositBankInfo.getAccountBankCode().getBankCode();
            this.depositAccountHolderName = depositBankInfo.getAccountHolderName();
            this.depositAccountNumber = depositBankInfo.getAccountNumber();
            this.settledBankCode = settleBankInfo.getAccountBankCode().getBankCode();
            this.settledAccountHolderName = settleBankInfo.getAccountHolderName();
            this.settledAccountNumber = settleBankInfo.getAccountNumber();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class OwnerSummaryInfo {
        private final String businessLicenseNumber;
        private final String email;
        private final String representationName;

        public OwnerSummaryInfo(Owner owner) {
            this.businessLicenseNumber = owner.getBusinessLicenseNumber();
            this.email = owner.getEmail();
            this.representationName = owner.getRepresentationName();
        }
    }

}
