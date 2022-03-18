package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.AbstractEntity;
import com.flab.yeogiseoja.domain.accommodation.Accommodation;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    private long id;
    private String businessLicenseNumber;
    private String authToken;
    private String email;
    private String representationName;
    private String password;
    private String phoneNumber;
    private LocalDateTime authenticatedAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final List<Accommodation> accommodations = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

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

    @Builder
    public Owner(
            String email,
            String businessLicenseNumber,
            String representationName, String password,
            String phoneNumber, Account depositAccountInfo,
            Account settledAccountInfo
    ) {
        this.email = email;
        this.representationName = representationName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.depositAccountInfo = depositAccountInfo;
        this.settledAccountInfo = settledAccountInfo;
        this.businessLicenseNumber = businessLicenseNumber;
        this.status = Status.AUTH_NOT_YET;
    }

    public String generateRandomPassword() {
        var randomPassword = UUID.randomUUID().toString();
        this.password = representationName;
        return randomPassword;
    }

    public boolean confirmPassword(String password) {
        Assert.isTrue(password.equals(this.password), ErrorCode.PASSWORD_CONFIRM_FAIL.getErrorMsg());
        return true;
    }

    public String generateAuthTokenForAuthentication() {
        var authToken = UUID.randomUUID().toString();
        this.authToken = authToken;
        return authToken;
    }

    public boolean confirmAuthToken(String authToken) {
        Assert.isTrue(authToken.equals(this.authToken), ErrorCode.AUTH_TOKEN_CONFIRM_FAIL.getErrorMsg());

        this.authenticatedAt = LocalDateTime.now();
        this.status = Status.AUTH_COMPLETE;
        return true;
    }

    public void verified() {
        if (this.status == Status.DELETED) throw new RuntimeException("한번 삭제된 Owner 는 다시 검증할 수 없습니다");
        if (this.status != Status.AUTH_COMPLETE) throw new RuntimeException();

        this.status = Status.VERIFIED;
        this.verifiedAt = LocalDateTime.now();
    }

    public void deleted() {
        this.status = Status.DELETED;
        this.deletedAt = LocalDateTime.now();
    }

    public void addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        AUTH_NOT_YET("미인증"), AUTH_COMPLETE("인증완료"),
        VERIFIED("검증완료"), DELETED("삭제");

        private final String description;
    }
}
