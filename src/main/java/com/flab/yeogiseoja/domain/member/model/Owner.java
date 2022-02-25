package com.flab.yeogiseoja.domain.member.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends MemberCommonEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    private long id;
    @Column(updatable = false)
    private String businessLicenseNumber;
    @Embedded
    private EmailAuthentication emailAuthentication;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "accountBankCode", column = @Column(name = "deposit_account_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "deposit_account_number")),
            @AttributeOverride(name = "accountHolderName", column = @Column(name = "deposit_account_holder_name"))
    })
    private Account depositAccountInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "accountBankCode", column = @Column(name = "settled_account_coe")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "settled_account_number")),
            @AttributeOverride(name = "accountHolderName", column = @Column(name = "settled_account_holder_name"))
    })
    private Account settledAccountInfo;

    public Owner(String email, String businessLicenseNumber, String userName, String password, String phoneNumber, Account depositAccountInfo, Account settledAccountInfo) {
        super(email, userName, password, phoneNumber);
        this.depositAccountInfo = depositAccountInfo;
        this.settledAccountInfo = settledAccountInfo;
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public Owner(String email) {
        super(email);
    }

    public void prepareEmailAuthentication() {
        this.emailAuthentication = EmailAuthentication.prepareAuthentication();
    }

    public void confirmEmailAuthentication() {
        this.emailAuthentication = EmailAuthentication.doneAuthentication();
    }

    public boolean isGenerateToken(String token) {
        return this.emailAuthentication.getEmailAuthenticationToken().equals(token);
    }


}
